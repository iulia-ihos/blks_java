package cs.blokus.dto;

import java.io.Serializable;

import cs.blokus.enums.TileColorEnum;

@SuppressWarnings("serial")
public class PlayerDTO implements Serializable{

	private UserDTO user;
	private GameDTO game;
	private TileColorEnum color;
	private int points;
	
	
	
	public PlayerDTO(UserDTO user, GameDTO game, TileColorEnum color, int points) {
		this.user = user;
		this.game = game;
		this.color = color;
		this.points = points;
	}
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public GameDTO getGame() {
		return game;
	}
	public void setGame(GameDTO game) {
		this.game = game;
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
