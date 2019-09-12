package cs.blokus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.TileSquareDAO;
import cs.blokus.entity.TileSquare;
import cs.blokus.entity.id.TileSquareId;
import cs.blokus.enums.TileNameEnum;
import cs.blokus.service.ISquareService;
import cs.blokus.service.ITileSquareService;

@Component
public class TileSquareServiceImpl implements ITileSquareService{

	@Autowired
	private TileSquareDAO tileSquareDAO;
	
	@Autowired
	private ISquareService squareService;
	
	

	@Override
	public void createAll() {
		squareService.createSquares();
		if(tileSquareDAO.count() != 89) {
			tileSquareDAO.deleteAll();
			addAll();
		}
		
		
		
	}
	
	private void save(TileSquareId id) {
		tileSquareDAO.save(new TileSquare(id));
	}
	
	private void addAll() {
		TileNameEnum[] names = TileNameEnum.values();
		for(TileNameEnum name: names) {
			switch(name) {
			case I : 
				save(new TileSquareId(TileNameEnum.I,"top0Left0"));
				break;
				
			case I2 : 
				save(new TileSquareId(TileNameEnum.I2,"top0Left0"));
				save(new TileSquareId(TileNameEnum.I2,"top0Left1"));
				break;
//3
			case I3 : 
				save(new TileSquareId(TileNameEnum.I3,"top0Left0"));
				save(new TileSquareId(TileNameEnum.I3,"top0Left1"));
				save(new TileSquareId(TileNameEnum.I3,"top0Left2"));
				break;			
			case V3 : 
				save(new TileSquareId(TileNameEnum.V3,"top0Left0"));
				save(new TileSquareId(TileNameEnum.V3,"top0Left1"));
				save(new TileSquareId(TileNameEnum.V3,"top1Left1"));
				break;
//4		
			case I4 : 
				save(new TileSquareId(TileNameEnum.I4,"top0Left0"));
				save(new TileSquareId(TileNameEnum.I4,"top0Left1"));
				save(new TileSquareId(TileNameEnum.I4,"top0Left2"));
				save(new TileSquareId(TileNameEnum.I4,"top0Left3"));
				break;
			case L4 : 
				save(new TileSquareId(TileNameEnum.L4,"top0Left0"));
				save(new TileSquareId(TileNameEnum.L4,"top1Left0"));
				save(new TileSquareId(TileNameEnum.L4,"top1Left1"));
				save(new TileSquareId(TileNameEnum.L4,"top1Left2"));
				break;
			case O : 
				save(new TileSquareId(TileNameEnum.O,"top0Left0"));
				save(new TileSquareId(TileNameEnum.O,"top0Left1"));
				save(new TileSquareId(TileNameEnum.O,"top1Left0"));
				save(new TileSquareId(TileNameEnum.O,"top1Left1"));
				break;
			case T4 : 
				save(new TileSquareId(TileNameEnum.T4,"top0Left1"));
				save(new TileSquareId(TileNameEnum.T4,"top1Left0"));
				save(new TileSquareId(TileNameEnum.T4,"top1Left1"));
				save(new TileSquareId(TileNameEnum.T4,"top1Left2"));
				break;
			case Z4 : 
				save(new TileSquareId(TileNameEnum.Z4,"top0Left0"));
				save(new TileSquareId(TileNameEnum.Z4,"top1Left0"));
				save(new TileSquareId(TileNameEnum.Z4,"top1Left1"));
				save(new TileSquareId(TileNameEnum.Z4,"top2Left1"));
				break;
//5				
			case I5 : 
				save(new TileSquareId(TileNameEnum.I5,"top0Left0"));
				save(new TileSquareId(TileNameEnum.I5,"top0Left1"));
				save(new TileSquareId(TileNameEnum.I5,"top0Left2"));
				save(new TileSquareId(TileNameEnum.I5,"top0Left3"));
				save(new TileSquareId(TileNameEnum.I5,"top0Left4"));
				break;
			case U : 
				save(new TileSquareId(TileNameEnum.U,"top0Left0"));
				save(new TileSquareId(TileNameEnum.U,"top1Left0"));
				save(new TileSquareId(TileNameEnum.U,"top0Left1"));
				save(new TileSquareId(TileNameEnum.U,"top0Left2"));
				save(new TileSquareId(TileNameEnum.U,"top1Left2"));
				break;
			case Y : 
				save(new TileSquareId(TileNameEnum.Y,"top0Left0"));
				save(new TileSquareId(TileNameEnum.Y,"top0Left1"));
				save(new TileSquareId(TileNameEnum.Y,"top0Left2"));
				save(new TileSquareId(TileNameEnum.Y,"top0Left3"));
				save(new TileSquareId(TileNameEnum.Y,"top1Left1"));
				break;
			case Z5 : 
				save(new TileSquareId(TileNameEnum.Z5,"top0Left0"));
				save(new TileSquareId(TileNameEnum.Z5,"top1Left0"));
				save(new TileSquareId(TileNameEnum.Z5,"top1Left1"));
				save(new TileSquareId(TileNameEnum.Z5,"top1Left2"));
				save(new TileSquareId(TileNameEnum.Z5,"top2Left2"));
				break;
			case N : 
				save(new TileSquareId(TileNameEnum.N,"top1Left0"));
				save(new TileSquareId(TileNameEnum.N,"top1Left1"));
				save(new TileSquareId(TileNameEnum.N,"top0Left1"));
				save(new TileSquareId(TileNameEnum.N,"top0Left2"));
				save(new TileSquareId(TileNameEnum.N,"top0Left3"));
				break;
			case W : 
				save(new TileSquareId(TileNameEnum.W,"top0Left0"));
				save(new TileSquareId(TileNameEnum.W,"top1Left0"));
				save(new TileSquareId(TileNameEnum.W,"top1Left1"));
				save(new TileSquareId(TileNameEnum.W,"top2Left1"));
				save(new TileSquareId(TileNameEnum.W,"top2Left2"));
				break;
			case V5 : 
				save(new TileSquareId(TileNameEnum.V5,"top0Left0"));
				save(new TileSquareId(TileNameEnum.V5,"top1Left0"));
				save(new TileSquareId(TileNameEnum.V5,"top2Left0"));
				save(new TileSquareId(TileNameEnum.V5,"top2Left1"));
				save(new TileSquareId(TileNameEnum.V5,"top2Left2"));
				break;
			case P : 
				save(new TileSquareId(TileNameEnum.P,"top0Left0"));
				save(new TileSquareId(TileNameEnum.P,"top0Left1"));
				save(new TileSquareId(TileNameEnum.P,"top1Left0"));
				save(new TileSquareId(TileNameEnum.P,"top1Left1"));
				save(new TileSquareId(TileNameEnum.P,"top2Left0"));
				break;
			case T5 : 
				save(new TileSquareId(TileNameEnum.T5,"top0Left1"));
				save(new TileSquareId(TileNameEnum.T5,"top1Left1"));
				save(new TileSquareId(TileNameEnum.T5,"top2Left0"));
				save(new TileSquareId(TileNameEnum.T5,"top2Left1"));
				save(new TileSquareId(TileNameEnum.T5,"top2Left2"));
				break;
			case X : 
				save(new TileSquareId(TileNameEnum.X,"top0Left1"));
				save(new TileSquareId(TileNameEnum.X,"top1Left0"));
				save(new TileSquareId(TileNameEnum.X,"top1Left1"));
				save(new TileSquareId(TileNameEnum.X,"top1Left2"));
				save(new TileSquareId(TileNameEnum.X,"top2Left1"));
				break;
			case F : 
				save(new TileSquareId(TileNameEnum.F,"top0Left1"));
				save(new TileSquareId(TileNameEnum.F,"top1Left0"));
				save(new TileSquareId(TileNameEnum.F,"top2Left0"));
				save(new TileSquareId(TileNameEnum.F,"top1Left1"));
				save(new TileSquareId(TileNameEnum.F,"top1Left2"));
				break;
			case L5 : 
				save(new TileSquareId(TileNameEnum.L5,"top0Left0"));
				save(new TileSquareId(TileNameEnum.L5,"top1Left0"));
				save(new TileSquareId(TileNameEnum.L5,"top0Left1"));
				save(new TileSquareId(TileNameEnum.L5,"top0Left2"));
				save(new TileSquareId(TileNameEnum.L5,"top0Left3"));
				break;
			}
			
		}
	}

	@Override
	public List<TileSquare> getForTile(TileNameEnum tileName){
		List<TileSquare> squares = tileSquareDAO.getForTile(tileName);
		return squares;
	}

}
