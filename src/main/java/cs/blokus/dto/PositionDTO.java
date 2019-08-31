package cs.blokus.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PositionDTO implements Serializable {
	
	private Long idPosition;
	private int top;
	private int left;
	
	public PositionDTO(int top, int left) {
		this.top = top;
		this.left = left;
	}
	
	

}
