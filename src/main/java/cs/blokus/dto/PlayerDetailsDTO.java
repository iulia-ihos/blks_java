package cs.blokus.dto;

import java.io.Serializable;

import cs.blokus.enums.TileColorEnum;

@SuppressWarnings("serial")
public class PlayerDetailsDTO implements Serializable{

	private Long idPlayer;
	private String username;
	private TileColorEnum color;
	private int points;
	
	
	
	
	public PlayerDetailsDTO(Long idPlayer,String username, TileColorEnum color, int points) {
		this.idPlayer = idPlayer;
		this.username = username;
		this.color = color;
		this.points = points;
	}
	
	
	public PlayerDetailsDTO() {
	}
	
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Long getIdPlayer() {
		return idPlayer;
	}
	public void setIdPlayer(Long idPlayer) {
		this.idPlayer = idPlayer;
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


	@Override
	public String toString() {
		return "PlayerDetailsDTO [idPlayer=" + idPlayer + ", username=" + username + ", color=" + color + ", points="
				+ points + "]";
	}
	
	
	
}
