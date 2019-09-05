package cs.blokus.dto;

import java.io.Serializable;

import cs.blokus.entity.PlayerId;
import cs.blokus.enums.TileColorEnum;

@SuppressWarnings("serial")
public class PlayerDTO implements Serializable{

	private PlayerId player;
	private TileColorEnum color;
	private int points;
	
	
	
	public PlayerDTO(PlayerId id, TileColorEnum color, int points) {
		this.player = id;
		this.color = color;
		this.points = points;
	}
	

	
	
	
	public PlayerId getPlayer() {
		return player;
	}


	public void setPlayer(PlayerId player) {
		this.player = player;
	}

	public TileColorEnum getColor() {
		return color;
	}
	public void setColor(TileColorEnum color) {
		this.color = color;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	
}
