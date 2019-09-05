package cs.blokus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cs.blokus.entity.TileDetails;
import cs.blokus.entity.TileSquare;

@Service
public interface ITileSquareService {
	
//	List<TileSquareDTO> getForTileName(String name);
//	
//	TileSquareDTO create(String tileName, Long idSquare);
	
	void createAll();

//	List<TileSquare> getForTile(TileNameEnum tileName);
	List<TileSquare> getForTile(TileDetails tileDetails);
}
