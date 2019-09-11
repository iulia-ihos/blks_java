package cs.blokus.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TileSquareDTO implements Serializable{
	
	private SquareDTO square;

	public TileSquareDTO(SquareDTO square) {
		this.square = square;
	}
	
	public TileSquareDTO() {}


	public SquareDTO getSquare() {
		return square;
	}

	public void setSquare(SquareDTO square) {
		this.square = square;
	}

	@Override
	public String toString() {
		return "TileSquareDTO [ square=" + square + "]";
	}
	
	
	
}
