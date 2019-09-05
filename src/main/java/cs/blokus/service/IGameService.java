package cs.blokus.service;

import java.util.Date;

import cs.blokus.dto.GameDTO;
import cs.blokus.enums.GameStatusEnum;

public interface IGameService {

	GameDTO create(GameDTO game);
	GameDTO getById(Long id);
	GameDTO updateStartTime(Date startTime, Long idGame);
	GameDTO updateEndTime(Date endTime, Long idGame);
	GameDTO updateStatus(GameStatusEnum status, Long idGame);
}
