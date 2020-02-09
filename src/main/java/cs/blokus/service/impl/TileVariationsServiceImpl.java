package cs.blokus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.TileDetailsDAO;
import cs.blokus.dao.TileVariationsDAO;
import cs.blokus.entity.TileDetails;
import cs.blokus.entity.TileSquare;
import cs.blokus.entity.TileVariations;
import cs.blokus.enums.TileNameEnum;
import cs.blokus.service.ITileVariationsService;

@Component
public class TileVariationsServiceImpl implements ITileVariationsService{
	
	@Autowired
	private TileVariationsDAO tileVariationsDAO;
	
	@Autowired
	private TileDetailsDAO tileDetailsDAO;

	@Autowired
	private MatrixService matrixService;

	@Override
	public void saveAll() {
		if(tileVariationsDAO.count() != 70) {
			tileVariationsDAO.deleteAll();
			List<TileDetails> details = tileDetailsDAO.findAll();
			for(TileDetails detail: details) {
				addTileVariations(detail);
			}
		}
		
	}
	
	@Override
	public List<TileVariations> getForTile(TileNameEnum name) {
		return tileVariationsDAO.getForTileName(name);	
	}


	private void addTileVariations(TileDetails details) {
		TileNameEnum name = details.getName();
		if(name.equals(TileNameEnum.I) || name.equals(TileNameEnum.X) || name.equals(TileNameEnum.O)) {
			tileVariationsDAO.save(new TileVariations(0L, details, getMatrix(details.getTileSquares()), 0, false, false));
			return;
		}
		if(name.equals(TileNameEnum.I3) || name.equals(TileNameEnum.I2) || name.equals(TileNameEnum.I4)
				|| name.equals(TileNameEnum.I5)) {
			addOne90Rotation(getMatrix(details.getTileSquares()), details);
			return;
		}
		if(name.equals(TileNameEnum.T4) || name.equals(TileNameEnum.T5) || name.equals(TileNameEnum.V3)
				|| name.equals(TileNameEnum.V5) || name.equals(TileNameEnum.W)) {
			addThree90Rotation(getMatrix(details.getTileSquares()), details);
			return;
		}
		
		if(name.equals(TileNameEnum.Z4) || name.equals(TileNameEnum.Z5) || name.equals(TileNameEnum.U)) {
			addThree(getMatrix(details.getTileSquares()), details);
			return;
		}
		
		if(name.equals(TileNameEnum.L4) || name.equals(TileNameEnum.L5) || name.equals(TileNameEnum.P) ||
				name.equals(TileNameEnum.F) || name.equals(TileNameEnum.Y) || name.equals(TileNameEnum.N)) {
			addSeven(getMatrix(details.getTileSquares() ), details);
			return;
		}
	}
	
	private int[][] getMatrix(List<TileSquare> squares) {
		int maxRows = 0;
		int maxCols = 0;
		for(TileSquare tileSquare: squares) {
			if(tileSquare.getSquare().getLeft() > maxCols)
				maxCols = tileSquare.getSquare().getLeft();
			if(tileSquare.getSquare().getTop() > maxRows)
				maxRows = tileSquare.getSquare().getTop();
		}
		maxRows++;
		maxCols++;
		int[][] matrix = new int[maxRows][maxCols];
		for(TileSquare tileSquare: squares) {
			matrix[tileSquare.getSquare().getTop()][tileSquare.getSquare().getLeft()] = 1;
		}
		return matrix;
		
	}
	
	private void addOne90Rotation(int[][] tile, TileDetails details) {
		tileVariationsDAO.save(new TileVariations(0L, details, tile, 0, false, false));
		int [][] rotated = matrixService.rotate90Clockwise(tile);
		tileVariationsDAO.save(new TileVariations(0L, details, rotated, 90, false, false));
	}
	
	private void addThree90Rotation(int[][] tile, TileDetails details) {
		tileVariationsDAO.save(new TileVariations(0L, details, tile, 0, false, false));

		int [][] rotated = tile;
		for(int i = 1; i <=3; i++){
			rotated = matrixService.rotate90Clockwise(rotated);
			tileVariationsDAO.save(new TileVariations(0L, details, rotated, i*90, false, false));
		}
	}
	
	private void addThree(int[][] tile, TileDetails details) {
		tileVariationsDAO.save(new TileVariations(0L, details, tile, 0, false, false));

		// one flipX - 1 , one rotation -2 then flipY -3
		int [][] flipX = matrixService.flipX(tile);
		tileVariationsDAO.save(new TileVariations(0L, details, flipX, 0, true, false));
		
		int [][] rotated = matrixService.rotate90Clockwise(tile);
		tileVariationsDAO.save(new TileVariations(0L, details, rotated, 90, true, false));
		
		int [][] flipY = matrixService.flipY(rotated);
		tileVariationsDAO.save(new TileVariations(0L, details, flipY, 90, true, true));
	}
	
	private void addSeven(int[][] tile, TileDetails details) {
		tileVariationsDAO.save(new TileVariations(0L, details, tile, 0, false, false));

		//1 
		int [][] flipX = matrixService.flipX(tile);
		tileVariationsDAO.save(new TileVariations(0L, details, flipX, 0, true, false));
		//2
		int [][] flipY = matrixService.flipY(flipX);
		tileVariationsDAO.save(new TileVariations(0L, details, flipY, 0, true, true));
		//3
		flipY = matrixService.flipY(tile);
		tileVariationsDAO.save(new TileVariations(0L, details, flipY, 0, false, true));
		//4
		int [][] rotated = matrixService.rotate90Clockwise(tile);
		tileVariationsDAO.save(new TileVariations(0L, details, rotated, 90, false, false));
		//5
		flipX = matrixService.flipX(rotated);
		tileVariationsDAO.save(new TileVariations(0L, details, flipX, 90, true, false));
		//6
		flipY = matrixService.flipY(flipX);
		tileVariationsDAO.save(new TileVariations(0L, details, flipY, 90, true, true));
		//7
		flipY = matrixService.flipY(rotated);
		tileVariationsDAO.save(new TileVariations(0L, details, flipY, 90, false, true));
		
	}
	
	


}
