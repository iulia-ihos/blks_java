package cs.blokus.service;

import java.util.List;

import cs.blokus.dto.TileDTO;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.enums.TileNameEnum;

public interface ITileService {
	
	List<TileDTO> getAll();

	List<TileDTO> getAvailableForGame(Long idGame, TileColorEnum color);

	TileDTO getForColorAndName(TileColorEnum color, TileNameEnum name);

}
