package cs.blokus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.TileSquareDAO;
import cs.blokus.dto.TileSquareDTO;
import cs.blokus.service.ITileSquareService;

@Component
public class TileSquareServiceImpl implements ITileSquareService{

	@Autowired
	private ModelMapping modelMapper;
	
	@Autowired
	private TileSquareDAO tileSquareDao;

	@Override
	public List<TileSquareDTO> getForTileName(String name) {

		return null;
	}

	@Override
	public TileSquareDTO create(String tileName, Long idSquare) {
		// TODO Auto-generated method stub
		return null;
	}

}
