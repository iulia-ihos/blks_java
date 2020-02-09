package cs.blokus.service;

import java.util.List;

import cs.blokus.dto.Position;
import cs.blokus.entity.Corner;
import cs.blokus.enums.CornerType;
import cs.blokus.enums.TileColorEnum;

public interface ICornerService {
	
	public void create(Long idGame, TileColorEnum color, Position pos , CornerType type);

	public List<Corner> getCornersForColor(TileColorEnum color, Long idGame);

	public void deleteById(Long idCorner);

}
