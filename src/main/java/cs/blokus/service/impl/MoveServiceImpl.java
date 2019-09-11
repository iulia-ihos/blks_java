package cs.blokus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.BoardPositionDAO;
import cs.blokus.dao.MoveDAO;
import cs.blokus.dto.MoveDTO;
import cs.blokus.entity.BoardPosition;
import cs.blokus.entity.Move;
import cs.blokus.model_mapping.ModelMapping;
import cs.blokus.service.IMoveService;

@Component
public class MoveServiceImpl implements IMoveService{
	
	@Autowired
	private MoveDAO moveDAO;
	
	@Autowired
	private ModelMapping modelMapper;
	
	@Autowired 
	private BoardPositionDAO boardPositionDAO;

	@Override
	public MoveDTO createMove(MoveDTO moveDTO) {
		Move move = (Move)modelMapper.map(moveDTO, Move.class);
		BoardPosition bp = boardPositionDAO.save(move.getPosition());
		move.setPosition(bp);
		return modelMapper.map(moveDAO.save(move), MoveDTO.class);
	}

	@Override
	public List<MoveDTO> getMoveForGame(Long idGame) {
		List<MoveDTO> dtos = new ArrayList<>();
		List<Move> moves = moveDAO.getForGame(idGame);
		for(Move move: moves) {
			BoardPosition bp = boardPositionDAO.save(move.getPosition());
			move.setPosition(bp);
			dtos.add(modelMapper.map(move, MoveDTO.class));
		}
		return dtos;
	}

}
