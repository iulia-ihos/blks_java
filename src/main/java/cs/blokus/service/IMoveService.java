package cs.blokus.service;

import java.util.List;

import cs.blokus.dto.MoveDTO;

public interface IMoveService {

	public MoveDTO createMove(MoveDTO moveDTO);
	public List<MoveDTO> getMoveForGame(Long idGame);
}
