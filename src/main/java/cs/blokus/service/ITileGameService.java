package cs.blokus.service;

public interface ITileGameService {

	void createTileForGame(Long idGame);

	void setUsed(Long idGame, Long idTile);
	
}
