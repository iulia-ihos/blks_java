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
import cs.blokus.service.ITileDetailsService;
import cs.blokus.service.ITileService;
import cs.blokus.service.ITileSquareService;
import cs.blokus.service.ITileVariationsService;

@Component
public class TileServiceImpl implements ITileService {

	@Autowired
	private TileDAO tileDAO;

	@Autowired
	private ModelMapping modelMapper;

	@Autowired
	private ITileSquareService tileSquareService;
	
	@Autowired
	private ITileDetailsService tileDetailsService;
	
	@Autowired
	private ITileVariationsService tileVariationsService;
	
	@Override
	public List<TileDTO> getAvailableForGame(Long idGame) {
		List<Tile> tiles = tileDAO.getAvailableTiles(idGame);
		List<TileDTO> dtos = new ArrayList<>();
		for(Tile tile: tiles) {
			dtos.add(modelMapper.map(tile, TileDTO.class));
		}
		return dtos;
	}

	@Override
	public List<TileDTO> getAll() {
		tileDetailsService.create();
		tileVariationsService.saveAll();
		tileSquareService.createAll();
		if (tileDAO.count() != 84) {
			tileDAO.deleteAll();
			addAll();
		}
		List<TileDTO> dtos = new ArrayList<>();
		List<Tile> tiles = tileDAO.findAll();
		for (Tile tile : tiles) {
			TileDetails tileDet = tile.getTileDetails();
			List<TileSquare> squares = tileSquareService.getForTile(tile.getTileDetails().getName());
			tileDet.setTileSquares(squares);
			tile.setTileDetails(tileDet);
			TileDTO dto = (TileDTO) modelMapper.map(tile, TileDTO.class);

			dtos.add(dto);
		}
		return dtos;
	}

	private void addAll() {
		TileNameEnum[] names = TileNameEnum.values();
		for (TileNameEnum name : names) {
			TileDetails tileDetails = new TileDetails(name);
			tileDAO.save(new Tile(TileColorEnum.red, tileDetails));
			tileDAO.save(new Tile(TileColorEnum.blue, tileDetails));
			tileDAO.save(new Tile(TileColorEnum.yellow, tileDetails));
			tileDAO.save(new Tile(TileColorEnum.green, tileDetails));
		}
	}

}
