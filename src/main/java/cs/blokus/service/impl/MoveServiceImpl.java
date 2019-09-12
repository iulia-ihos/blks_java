package cs.blokus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.MoveDAO;
import cs.blokus.dao.TileGameDAO;
import cs.blokus.dao.TilePositionDAO;
import cs.blokus.dto.MoveDTO;
import cs.blokus.entity.Move;
import cs.blokus.entity.TileGame;
import cs.blokus.entity.TilePosition;
import cs.blokus.model_mapping.ModelMapping;
import cs.blokus.service.IMoveService;

@Component
public class MoveServiceImpl implements IMoveService{
	
	@Autowired
	private MoveDAO moveDAO;
	
	@Autowired
	private ModelMapping modelMapper;
	
	@Autowired 
	private TilePositionDAO tilePositionDAO;

	@Override
	public MoveDTO createMove(MoveDTO moveDTO) {
		Move move = (Move)modelMapper.map(moveDTO, Move.class);
		TilePosition bp = tilePositionDAO.save(move.getPosition());
		move.setPosition(bp);
		MoveDTO savedMove = modelMapper.map(moveDAO.save(move), MoveDTO.class);
		return savedMove;
	}

	@Override
	public List<MoveDTO> getMoveForGame(Long idGame) {
		List<MoveDTO> dtos = new ArrayList<>();
		List<Move> moves = moveDAO.getForGame(idGame);
		for(Move move: moves) {
			TilePosition bp = tilePositionDAO.save(move.getPosition());
			move.setPosition(bp);
			dtos.add(modelMapper.map(move, MoveDTO.class));
		}
		return dtos;
	}

}
