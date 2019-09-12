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
	private double top;
	
	@Column(name = "leftmost")
	private double left;
	
	@Column(name = "angle")
	private int angle;
	
	@Column(name = "flipX")
	private boolean isFlippedHorizontally;
	
	@Column(name = "flipY")
	private boolean isFlippedVertically;

	public TilePosition(Long idTilePosition, double top, double left, int angle, boolean isFlippedHorizontally,
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

	public double getTop() {
		return top;
	}

	public void setTop(double top) {
		this.top = top;
	}

	public double getLeft() {
		return left;
	}

	public void setLeft(double left) {
		this.left = left;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
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
	
	
}
