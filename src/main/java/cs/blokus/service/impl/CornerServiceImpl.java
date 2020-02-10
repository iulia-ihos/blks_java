package cs.blokus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.CornerDAO;
import cs.blokus.dto.Position;
import cs.blokus.entity.Corner;
import cs.blokus.entity.Game;
import cs.blokus.enums.CornerType;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.service.ICornerService;

@Component
public class CornerServiceImpl implements ICornerService{
	
	@Autowired
	private CornerDAO cornerDAO;

	@Override
	public void create(Long idGame, TileColorEnum color, Position pos, CornerType type) {
		Game game = new Game(idGame);
		Corner corner = new Corner(0L, game, color, pos.getTop(), pos.getLeft());
		cornerDAO.save(corner);
	}

	@Override
	public List<Corner> getCornersForColor(TileColorEnum color, Long idGame) {
		return cornerDAO.getCornersForColor(color, idGame);
	}

	@Override
	public void deleteById(Long idCorner) {
		cornerDAO.deleteById(idCorner);
		
	}

}
