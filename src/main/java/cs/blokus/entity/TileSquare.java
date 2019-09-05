package cs.blokus.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tileSquare")
public class TileSquare {
	
	@EmbeddedId
	private TileSquareId tileSquare;
	
	@ManyToOne
	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "tileName", nullable = false, insertable = false, updatable = false)
	private TileDetails tileDetails;
	
	@ManyToOne
	@JoinColumn(name = "idSquare", nullable = false, insertable = false, updatable = false)
	private Square square;	
	

	public TileSquare(TileSquareId tileSquare) {
		this.tileSquare = tileSquare;
	}
	
	public TileSquare() {}

	public TileSquareId getTileSquare() {
		return tileSquare;
	}

	public void setTileSquare(TileSquareId tileSquare) {
		this.tileSquare = tileSquare;
	}

	public TileDetails getTileDetails() {
		return tileDetails;
	}

	public void setTileDetails(TileDetails tileDetails) {
		this.tileDetails = tileDetails;
	}

	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	@Override
	public String toString() {
		return "TileSquare [  " + ", square=" + square + "]";
	}
	
	
	
	

}
