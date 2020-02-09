package cs.blokus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.MoveDAO;
import cs.blokus.dao.TilePositionDAO;
import cs.blokus.dto.MoveDTO;
import cs.blokus.entity.Move;
import cs.blokus.entity.TilePosition;
import cs.blokus.model_mapping.ModelMapping;
import cs.blokus.service.IMoveService;
import cs.blokus.service.ITileGameService;

@Component
public class MoveServiceImpl implements IMoveService{
	
	@Autowired
	private MoveDAO moveDAO;
	
	@Autowired
	private ModelMapping modelMapper;
	
	@Autowired 
	private TilePositionDAO tilePositionDAO;
	
	@Autowired 
	private ITileGameService tileGameService;

	@Override
	public MoveDTO createMove(MoveDTO moveDTO) {
		Move move = (Move)modelMapper.map(moveDTO, Move.class);
		TilePosition bp = tilePositionDAO.save(move.getPosition());
		move.setPosition(bp);
		MoveDTO savedMove = modelMapper.map(moveDAO.save(move), MoveDTO.class);
		tileGameService.setUsed(move.getGame().getIdGame(), move.getTile().getIdTile());
		return savedMove;
	}

	@Override
	public List<MoveDTO> getMoveForGame(Long idGame) {
		List<MoveDTO> dtos = new ArrayList<>();
		List<Move> moves = moveDAO.getForGame(idGame);
		moves.stream().forEach(move -> dtos.add(modelMapper.map(move, MoveDTO.class)));
		return dtos;
	}

}
