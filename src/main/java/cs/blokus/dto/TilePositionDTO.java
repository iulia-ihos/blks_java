package cs.blokus.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TilePositionDTO implements Serializable{
	
	private Long idTilePosition;
	private double top;
	private double left;
	private int angle;
	private boolean isFlippedHorizontally;
	private boolean isFlippedVertically;
	
	public TilePositionDTO(Long idTilePosition, double top, double left, int angle, boolean isFlippedHorizontally,
			boolean isFlippedVertically) {
		this.idTilePosition = idTilePosition;
		this.top = top;
		this.left = left;
		this.angle = angle;
		this.isFlippedHorizontally = isFlippedHorizontally;
		this.isFlippedVertically = isFlippedVertically;
	}
	
	public TilePositionDTO() {
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
