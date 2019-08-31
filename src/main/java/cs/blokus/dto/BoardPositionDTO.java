package cs.blokus.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BoardPositionDTO implements Serializable{
	
	
	private Long idBoardPosition;
	

	private double top;
	
	
	private double left;
	

	private int angle;
	

	private boolean isFlippedHorizontally;
	
	
	private boolean isFlippedVertically;

}
