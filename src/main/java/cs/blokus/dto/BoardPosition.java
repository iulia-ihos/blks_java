package cs.blokus.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class BoardPosition implements Serializable {
	
	private List<Position> coords;
	
	public BoardPosition(List<Position> coords) {
		this.coords = coords;
	}
	
	public BoardPosition() {
	}

	public List<Position> getCoords() {
		return coords;
	}

	public void setCoords(List<Position> coords) {
		this.coords = coords;
	}

	@Override
	public String toString() {
		return "BoardPosition [coords=" + coords + "]";
	}
	
	

}
