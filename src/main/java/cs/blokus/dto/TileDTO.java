package cs.blokus.dto;

import java.io.Serializable;

import cs.blokus.enums.TileColorEnum;

@SuppressWarnings("serial")
public class TileDTO implements Serializable{
	
	private Long idTile;
	private TileColorEnum color;
	private boolean isFlippedHorizontally;
	private boolean isFlippedVertically;
	private int angle;
	
	
	
	

}
