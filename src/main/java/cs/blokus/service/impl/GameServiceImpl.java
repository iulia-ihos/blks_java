package cs.blokus.service.impl;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.GameDAO;
import cs.blokus.dto.GameDTO;
import cs.blokus.entity.Board;
import cs.blokus.entity.Game;
import cs.blokus.enums.GameStatusEnum;
import cs.blokus.model_mapping.ModelMapping;
import cs.blokus.service.IBoardService;
import cs.blokus.service.IGameService;
import cs.blokus.service.ITileGameService;

@Component
public class GameServiceImpl implements IGameService {
	
	@Autowired 
	private GameDAO gameDAO;
	
	@Autowired
	private ModelMapping modelMapper;
	
	@Autowired
	private IBoardService boardService;
	
	@Autowired
	private ITileGameService tileGameService;

	@Override
	public GameDTO create(GameDTO gameDTO) {
		Game game = (Game) modelMapper.map(gameDTO, Game.class);
		Board savedBoard = boardService.createBoard(game);
		GameDTO dto = modelMapper.map(savedBoard.getGame(), GameDTO.class);
		tileGameService.createTileForGame(dto.getIdGame());
		return dto;
	}

	@Override
	public GameDTO updateStartTime(Date startTime, Long idGame) {
		gameDAO.updateStartTime(startTime, idGame);
		return getById(idGame);

	}
	
	@Override
	public GameDTO updateEndTime(Date endTime, Long idGame) {
		gameDAO.updateEndTime(endTime, idGame);
		return getById(idGame);
	}
	
	@Override
	public GameDTO updateStatus(GameStatusEnum status, Long idGame) {
		gameDAO.updateStatus(status, idGame);
		return getById(idGame);
	}

	@Override
	public GameDTO getById(Long id) {
		Optional<Game> opt = gameDAO.findById(id);
		try {
			Game game = opt.get();
			return modelMapper.map(game, GameDTO.class);
		}
		catch(NoSuchElementException e){
			return null;
		}
		
		
	}
	
	

}
