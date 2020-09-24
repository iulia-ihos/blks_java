package cs.blokus.service;

import cs.blokus.dto.BoardPosition;
import cs.blokus.dto.MoveDTO;
import cs.blokus.entity.Board;
import cs.blokus.entity.Game;
import cs.blokus.enums.TileColorEnum;

public interface IBoardService {
	
	public void addToBoard(TileColorEnum color, BoardPosition position, Long idBoard);
	
	public MoveDTO getMove(TileColorEnum color, Long idBoard);
	
	public boolean hasMove(TileColorEnum color, Long idBoard);
	
	public Board createBoard(Game game);


}
