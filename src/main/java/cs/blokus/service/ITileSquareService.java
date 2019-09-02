package cs.blokus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cs.blokus.dto.TileSquareDTO;

@Service
public interface ITileSquareService {
	
	List<TileSquareDTO> getForTileName(String name);
	
	TileSquareDTO create(String tileName, Long idSquare);
}
