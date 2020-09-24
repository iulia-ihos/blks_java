package cs.blokus.messages;

import cs.blokus.enums.TileColorEnum;

public class NoMovesMessage {

	private String username;
	private TileColorEnum color;
	private Long idGame;
	
	
	
	public NoMovesMessage(String username, TileColorEnum color, Long idGame) {
		this.username = username;
		this.color = color;
		this.idGame = idGame;
	}
	
	public NoMovesMessage() {
	}
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public TileColorEnum getColor() {
		return color;
	}
	public void setColor(TileColorEnum color) {
		this.color = color;
	}
	public Long getIdGame() {
		return idGame;
	}
	public void setIdGame(Long idGame) {
		this.idGame = idGame;
	}



	@Override
	public String toString() {
		return "NoMovesMessage [username=" + username + ", color=" + color + ", idGame=" + idGame + "]";
	}
	
	
}
