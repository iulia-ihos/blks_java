package cs.blokus.service;

import java.util.List;

import cs.blokus.entity.TileVariations;
import cs.blokus.enums.TileNameEnum;

public interface ITileVariationsService {

	public void saveAll();

	List<TileVariations> getFotTile(TileNameEnum name);
	
}
