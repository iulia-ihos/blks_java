package cs.blokus.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TileSquareDTO implements Serializable{
	
	private TileDetailsDTO tileDetails;
	private SquareDTO square;

	public TileSquareDTO(TileDetailsDTO tileDetails, SquareDTO square) {
		this.tileDetails = tileDetails;
		this.square = square;
	}
	
	public TileSquareDTO() {}

	public TileDetailsDTO getTileDetails() {
		return tileDetails;
	}

	public void setTileDetails(TileDetailsDTO tileDetails) {
		this.tileDetails = tileDetails;
	}

	public SquareDTO getSquare() {
		return square;
	}

	public void setSquare(SquareDTO square) {
		this.square = square;
	}

	@Override
	public String toString() {
		return "TileSquareDTO [tile=" + tileDetails.getName() + ", square=" + square + "]";
	}
	
	
	
}
