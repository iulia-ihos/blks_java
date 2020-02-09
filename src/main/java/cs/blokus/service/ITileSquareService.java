package cs.blokus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cs.blokus.entity.TileSquare;
import cs.blokus.enums.TileNameEnum;

@Service
public interface ITileSquareService {
	
	void createAll();

	List<TileSquare> getForTile(TileNameEnum tileName);


}
