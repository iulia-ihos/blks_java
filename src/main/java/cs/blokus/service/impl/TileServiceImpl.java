package cs.blokus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.TileDAO;
import cs.blokus.dto.TileDTO;
import cs.blokus.entity.Tile;
import cs.blokus.entity.TileDetails;
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
	public List<TileDTO> getAvailableForGame(Long idGame, TileColorEnum color) {
		List<Tile> tiles = tileDAO.getAvailableTiles(idGame, color);
		List<TileDTO> dtos = new ArrayList<>();
		tiles.stream().forEach(tile -> dtos.add(modelMapper.map(tile, TileDTO.class)));
		return dtos;
	}

	@Override
	public List<TileDTO> getAll() {
		tileDetailsService.create();
		tileSquareService.createAll();
		if (tileDAO.count() != 84) {
			tileDAO.deleteAll();
			addAll();
		}
		tileVariationsService.saveAll();
		
		List<TileDTO> dtos = new ArrayList<>();
		List<Tile> tiles = tileDAO.findAll();
		tiles.stream().forEach(tile -> dtos.add(modelMapper.map(tile, TileDTO.class)));
	
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
