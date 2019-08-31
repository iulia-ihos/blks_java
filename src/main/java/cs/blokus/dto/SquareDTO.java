package cs.blokus.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SquareDTO implements Serializable{
	

	private String idSquare;
	private int top;
	private int left;
	
	public SquareDTO(String idSquare, int top, int left) {
		this.idSquare = idSquare;
		this.top = top;
		this.left = left;
	}

	public SquareDTO() {}

	public String getIdSquare() {
		return idSquare;
	}

	public void setIdSquare(String idSquare) {
		this.idSquare = idSquare;
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
	
	

}
