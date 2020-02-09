package cs.blokus.service;

import cs.blokus.entity.Move;
import cs.blokus.enums.TileColorEnum;

public interface IPentobiService {
	

	boolean writePlayCommand(Long idGame, TileColorEnum color, Move move);

	String writeGenMoveCommand(Long idGame, TileColorEnum color, boolean playMove);

	void start(Long idGame);

	void endPentobiGame(Long idGame);
}
