package cs.blokus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.TileDetailsDAO;
import cs.blokus.entity.TileDetails;
import cs.blokus.enums.TileNameEnum;
import cs.blokus.service.ISquareService;
import cs.blokus.service.ITileDetailsService;

@Component
public class TileDetailsServiceImpl implements ITileDetailsService{
	
	@Autowired
	private TileDetailsDAO tileDetailsDAO;

	@Override
	public List<TileDetails> create() {
		if(tileDetailsDAO.count() != 21) {
			tileDetailsDAO.deleteAll();
			addAll();
		}
		return tileDetailsDAO.findAll();
	}
	
	private void addAll() {
		
		TileNameEnum[] names = TileNameEnum.values();
		for(TileNameEnum name: names) {
			int numberSquares = 0;
			if(name.equals(TileNameEnum.I)) {
				numberSquares = 1;
			}
			if(name.equals(TileNameEnum.I2)) {
				numberSquares = 2;
			}
			if(name.equals(TileNameEnum.I3) || name.equals(TileNameEnum.V3)) {
				numberSquares = 3;
			}
			if(name.equals(TileNameEnum.I4)  ||
					name.equals(TileNameEnum.T4) || name.equals(TileNameEnum.L4) ||
					name.equals(TileNameEnum.Z4) || name.equals(TileNameEnum.O)) {
				numberSquares = 4;
			}
			if(name.equals(TileNameEnum.I5) || name.equals(TileNameEnum.Y) ||
				name.equals(TileNameEnum.N) || name.equals(TileNameEnum.Z5) ||
				name.equals(TileNameEnum.W) || name.equals(TileNameEnum.V5) ||
				name.equals(TileNameEnum.P) || name.equals(TileNameEnum.T5) ||
				name.equals(TileNameEnum.U) || name.equals(TileNameEnum.F) ||
				name.equals(TileNameEnum.L5)|| name.equals(TileNameEnum.X) ) {
				numberSquares = 5;
			}
			
			tileDetailsDAO.save(new TileDetails(name, numberSquares));	
		}
		
	}


}
