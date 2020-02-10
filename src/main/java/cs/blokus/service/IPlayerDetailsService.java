package cs.blokus.service;

import java.util.List;

import cs.blokus.dto.PlayerDTO;
import cs.blokus.dto.PlayerDetailsDTO;
import cs.blokus.enums.TileColorEnum;

public interface IPlayerDetailsService {

	PlayerDetailsDTO create(PlayerDetailsDTO playerDetails, PlayerDTO player);
	PlayerDetailsDTO updateScore(Long id, int points);
	PlayerDetailsDTO getNextPlayer(TileColorEnum currentPlayer, Long idGame);
	List<PlayerDetailsDTO> getPlayersDetailsForGame(Long idGame);
}
