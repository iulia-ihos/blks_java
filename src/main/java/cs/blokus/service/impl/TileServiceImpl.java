package cs.blokus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.TileDAO;
import cs.blokus.dto.TileDTO;
import cs.blokus.entity.Tile;
import cs.blokus.entity.TileDetails;
import cs.blokus.entity.TileSquare;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.enums.TileNameEnum;
import cs.blokus.model_mapping.ModelMapping;
import cs.blokus.service.ITileService;
import cs.blokus.service.ITileSquareService;


@Component
public class TileServiceImpl implements ITileService{
	
	@Autowired
	private TileDAO tileDAO;
	
	@Autowired
	private ModelMapping modelMapper;
	
	@Autowired
	private ITileSquareService tileSquareService;

	@Override
	public List<TileDTO> getAll() {
		tileSquareService.createAll();
		if(tileDAO.count() != 84) {
			tileDAO.deleteAll();
			addAll();
		}
		List<TileDTO> dtos = new ArrayList<>();
		List<Tile> tiles = tileDAO.findAll();
		System.out.println(tiles);
		for(Tile tile: tiles) {
//			TileDetails tileDet = tile.getTileDetails();
//			//System.out.println(tileDet.getTileSquares());
//			List<TileSquare> squares = tileSquareService.getForTile(tileDet);
//			System.out.println(squares);
//
//			for(TileSquare square: squares) {
//				System.out.println(square.getSquare());
//			}
//			
//			tileDet.setTileSquares(squares);
//			tile.setTileDetails(tileDet);
			TileDTO dto = (TileDTO)modelMapper.map(tile, TileDTO.class);
			
			dtos.add(dto);
		}
		System.out.println(dtos);
		return dtos;
	}

	
	private void addAll() {
		TileNameEnum[] names = TileNameEnum.values();
		for(TileNameEnum name: names) {
			TileDetails tileDetails = new TileDetails(name);
			tileDAO.save(new Tile(TileColorEnum.red, tileDetails));
			tileDAO.save(new Tile(TileColorEnum.blue, tileDetails));
			tileDAO.save(new Tile(TileColorEnum.yellow, tileDetails));
			tileDAO.save(new Tile(TileColorEnum.green, tileDetails));
		}
	}

}
