package cs.blokus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.TileVariationsDAO;
import cs.blokus.dto.BoardPosition;
import cs.blokus.dto.MoveDTO;
import cs.blokus.dto.Position;
import cs.blokus.dto.TilePositionDTO;
import cs.blokus.entity.TileVariations;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.messages.MoveMessage;
import cs.blokus.pentobi.utils.CoordinatesConverter;
import cs.blokus.service.IBoardService;
import cs.blokus.service.IPentobiService;
import cs.blokus.service.ITileService;

@Component
public class PentobiPlayerService {	
	@Autowired
	private IPentobiService pentobiService;
	
	@Autowired
	private IBoardService boardService;
	
	@Autowired
	private TileVariationsDAO tileVariationsDAO;
	
	@Autowired 
	private CoordinatesConverter converter;
	
	@Autowired
	private ITileService tileService;
	
	public void setMove(MoveMessage message) {
		pentobiService.writePlayCommand(message.getMove().getGame().getIdGame(), message.getCurrentPlayer().getColor(), message.getMove());
	}
	
	public MoveDTO getMove(MoveMessage message) {
		int points = 0;
		TileColorEnum color = message.getCurrentPlayer().getColor();
		Long idBoard = message.getMove().getGame().getIdGame();
		
		String result = pentobiService.writeGenMoveCommand(idBoard, color, true);
		System.out.println(result);
		BoardPosition bp = converter.getBoardPosition(result);
		points = bp.getCoords().size();
		if(points == 0)
			return null;
		boardService.addToBoard(color, bp, idBoard);

		System.out.println("cal pos"+ bp);
		int mostLeft = 0;
		int leastLeft = 19;
		int mostTop = 0;
		int leastTop = 19;
		for(Position pos: bp.getCoords()) {
			if(pos.getLeft() < leastLeft)
				leastLeft = pos.getLeft();
			if(pos.getLeft() > mostLeft)
				mostLeft = pos.getLeft();
			if(pos.getTop() < leastTop)
				leastTop = pos.getTop();
			if(pos.getTop() > mostTop)
				mostTop = pos.getTop();
			
		}
		int height = mostTop - leastTop + 1;
		int width = mostTop - leastTop + 1;
		
		int t[][] = new int [height][width];
		for(Position pos: bp.getCoords())
			t[pos.getTop()][pos.getLeft()] = 1;
		
		TileVariations var = findVariation(t, points);
		
	
		return new MoveDTO(message.getMove().getGame(), tileService.getForColorAndName(color, var.getTileDetails().getName()),
				new TilePositionDTO(leastTop, leastLeft, var.getAngle(), var.isFlipH(), var.isFlipV()));
	}
	
	private TileVariations findVariation(int[][] t, int nbSquares) {
		List<TileVariations> variations = tileVariationsDAO.findAll();
		for(TileVariations var: variations) {
			if(var.getTileDetails().getNumberSquares() == nbSquares) {
				int [][] tile = var.getTile();
				if(tile.length != t.length || tile[0].length != t[0].length)
					continue;
				boolean found = false;
				for(int i = 0; i < tile.length; i++) {
					for(int j = 0; j < tile[0].length; j++) {
						if(tile[i][j] != t[i][j]) {
							found = false;
							break;
						}
							
					}
					if(!found)
						break;
				}
				if(found)	
				return var;
					
			}
		}
		return null;
	}
	
	
	
	
}
