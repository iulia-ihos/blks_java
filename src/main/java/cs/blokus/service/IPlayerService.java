package cs.blokus.service;

import cs.blokus.dto.PlayerDTO;
import cs.blokus.entity.PlayerId;
import cs.blokus.enums.TileColorEnum;

public interface IPlayerService {
	
	PlayerDTO create(PlayerDTO player);
	PlayerDTO create(Long idGame, String username, TileColorEnum color);
	PlayerDTO updateScore(PlayerId playerId, int points);
	PlayerDTO getById(PlayerId id);


}
