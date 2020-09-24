package cs.blokus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.TileDAO;
import cs.blokus.dao.TileGameDAO;
import cs.blokus.entity.Tile;
import cs.blokus.entity.TileGame;
import cs.blokus.entity.id.TileGameId;
import cs.blokus.service.ITileGameService;

@Component
public class TileGameServiceImpl implements ITileGameService{
	
	@Autowired
	private TileGameDAO tileGameDAO;
	
	@Autowired
	private TileDAO tileDAO;

	@Override
	public void createTileForGame(Long idGame) {
		List<Tile> tiles = tileDAO.findAll();
		for(Tile tile: tiles) {
			tileGameDAO.save(new TileGame(new TileGameId(idGame, tile.getIdTile()), false));
		}
	}
	
	@Override
	public void setUsed(Long idGame, Long idTile) {
		tileGameDAO.updateUsed(true, idGame, idTile);
	}
	
	

}
