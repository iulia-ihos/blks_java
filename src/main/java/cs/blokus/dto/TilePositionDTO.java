package cs.blokus.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class TilePositionDTO implements Serializable{
	
	private Long idTilePosition;
	private double top;
	private double left;
	private int angle;
	 
	@JsonProperty(value="isFlippedHorizontally")   
	private boolean isFlippedHorizontally;
	
	@JsonProperty(value="isFlippedVertically") 
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

	public Long getIdTilePosition() {
		return idTilePosition;
	}

	public void setIdTilePosition(Long idTilePosition) {
		this.idTilePosition = idTilePosition;
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

	@Override
	public String toString() {
		return "TilePositionDTO [idTilePosition=" + idTilePosition + ", top=" + top + ", left=" + left + ", angle="
				+ angle + ", isFlippedHorizontally=" + isFlippedHorizontally + ", isFlippedVertically="
				+ isFlippedVertically + "]";
	}

	
	
}
