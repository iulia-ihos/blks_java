package cs.blokus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class TilePosition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTilePosition;
	
	@Column(name = "topmost")
	private int top;
	
	@Column(name = "leftmost")
	private int left;
	
	@Column(name = "angle")
	private int angle;
	
	@Column(name = "flipX")
	private boolean isFlippedHorizontally;
	
	@Column(name = "flipY")
	private boolean isFlippedVertically;

	public TilePosition(Long idTilePosition, int top, int left, int angle, boolean isFlippedHorizontally,
			boolean isFlippedVertically) {
		this.idTilePosition = idTilePosition;
		this.top = top;
		this.left = left;
		this.angle = angle;
		this.isFlippedHorizontally = isFlippedHorizontally;
		this.isFlippedVertically = isFlippedVertically;
	}

	public TilePosition() {
	}

	public Long getIdBoardPosition() {
		return idTilePosition;
	}

	public void setIdBoardPosition(Long idBoardPosition) {
		this.idTilePosition = idBoardPosition;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	public Long getIdTilePosition() {
		return idTilePosition;
	}

	public void setIdTilePosition(Long idTilePosition) {
		this.idTilePosition = idTilePosition;
	}

	public boolean isFlippedHorizontally() {
		return isFlippedHorizontally;
	}

	public void setFlippedHorizontally(boolean isFlippedHorizontally) {
		this.isFlippedHorizontally = isFlippedHorizontally;
	}

	public boolean isFlippedVertically() {
		return isFlippedVertically;
	}

	public void setFlippedVertically(boolean isFlippedVertically) {
		this.isFlippedVertically = isFlippedVertically;
	}

	@Override
	public String toString() {
		return "TilePosition [idTilePosition=" + idTilePosition + ", top=" + top + ", left=" + left + ", angle=" + angle
				+ ", isFlippedHorizontally=" + isFlippedHorizontally + ", isFlippedVertically=" + isFlippedVertically
				+ "]";
	}
	
	
	
	
	
}
