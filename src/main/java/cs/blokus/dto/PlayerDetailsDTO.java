package cs.blokus.dto;

import java.io.Serializable;

import cs.blokus.enums.TileColorEnum;

@SuppressWarnings("serial")
public class PlayerDetailsDTO implements Serializable{

	private Long idPlayerDetails;
	private String username;
	private TileColorEnum color;
	private int points;
	
	
	
	
	public PlayerDetailsDTO(Long idPlayerDetails,String username, TileColorEnum color, int points) {
		this.idPlayerDetails = idPlayerDetails;
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


	public Long getIdPlayerDetails() {
		return idPlayerDetails;
	}
	public void setIdPlayerDetails(Long idPlayerDetails) {
		this.idPlayerDetails = idPlayerDetails;
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
