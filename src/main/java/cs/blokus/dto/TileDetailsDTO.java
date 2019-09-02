package cs.blokus.dto;

import java.io.Serializable;
import java.util.List;

import cs.blokus.enums.TileNameEnum;

@SuppressWarnings("serial")
public class TileDetailsDTO implements Serializable{

	private TileNameEnum name;
	private int numberSquares;
	List<TileSquareDTO> tileSquares;

	
	public TileDetailsDTO(TileNameEnum name, int numberSquares, List<TileSquareDTO> tileSquares) {
		this.name = name;
		this.numberSquares = numberSquares;
		this.tileSquares = tileSquares;
	}
	
	public TileDetailsDTO() {}

	public TileNameEnum getName() {
		return name;
	}
	
	public void setName(TileNameEnum name) {
		this.name = name;
	}
	
	public int getNumberSquares() {
		return numberSquares;
	}
	
	public void setNumberSquares(int numberSquares) {
		this.numberSquares = numberSquares;
	}
	
	public List<TileSquareDTO> getTileSquares() {
		return tileSquares;
	}
	
	public void setTileSquares(List<TileSquareDTO> tileSquares) {
		this.tileSquares = tileSquares;
	}

	@Override
	public String toString() {
		return "TileDetailsDTO [name=" + name + ", numberSquares=" + numberSquares + ", tileSquares=" + tileSquares
				+ "]";
	}
	
	
}
