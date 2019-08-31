package cs.blokus.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class TileSquareId implements Serializable {

	private String tileName;
	private String idSquare;
	
	

	public TileSquareId(String tileName, String idSquare) {
		this.tileName = tileName;
		this.idSquare = idSquare;
	}

	public String getTileName() {
		return tileName;
	}

	public void setTileName(String tileName) {
		this.tileName = tileName;
	}

	public String getIdSquare() {
		return idSquare;
	}

	public void setIdSquare(String idSquare) {
		this.idSquare = idSquare;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof MoveId))
			return false;
		if (obj == this)
			return true;

		TileSquareId m = (TileSquareId) obj;

		return (this.getIdSquare() == m.getIdSquare() && this.tileName == m.getTileName());
	}

	@Override
	public int hashCode() {
		return (int) (3 * this.idSquare.hashCode() + this.tileName.hashCode());
	}

}
