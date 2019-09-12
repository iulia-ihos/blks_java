package cs.blokus.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Position implements Serializable {
	
	private int left;
	private int top;
	
	public Position(int top, int left) {
		this.left = left;
		this.top = top;
	}
	
	public Position() {}
	
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}

	@Override
	public String toString() {
		return "Position [left=" + left + ", top=" + top + "]";
	}
	
	

}
