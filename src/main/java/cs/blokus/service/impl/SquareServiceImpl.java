package cs.blokus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.SquareDAO;
import cs.blokus.entity.Square;
import cs.blokus.service.ISquareService;

@Component
public class SquareServiceImpl implements ISquareService {

	@Autowired
	private SquareDAO squareDAO;

	@Override 
	public List<Square> createSquares() {
		
		if (squareDAO.count() != 11) {
			squareDAO.deleteAll();
			addAll();
		}
		return squareDAO.findAll();
	}

	private void addAll() {
		this.squareDAO.save(new Square("top0Left0", 0, 0));
		this.squareDAO.save(new Square("top0Left1", 0, 1));
		this.squareDAO.save(new Square("top0Left2", 0, 2));
		this.squareDAO.save(new Square("top0Left3", 0, 3));
		this.squareDAO.save(new Square("top0Left4", 0, 4));

		this.squareDAO.save(new Square("top1Left0", 1, 0));
		this.squareDAO.save(new Square("top1Left1", 1, 1));
		this.squareDAO.save(new Square("top1Left2", 1, 2));
		//this.squareDAO.save(new Square("top1Left3", 1, 3));

		this.squareDAO.save(new Square("top2Left0", 2, 0));
		this.squareDAO.save(new Square("top2Left1", 2, 1));
		this.squareDAO.save(new Square("top2Left2", 2, 2));

		//this.squareDAO.save(new Square("top3Left2", 3, 2));
	}

}
