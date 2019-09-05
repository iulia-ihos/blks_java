package cs.blokus.messages;

import cs.blokus.enums.TileColorEnum;

public class InviteMessage {

	private Long idGame;
	private String username;
	private String from;
	private TileColorEnum color;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public TileColorEnum getColor() {
		return color;
	}

	public void setColor(TileColorEnum color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return idGame + "InviteMessage [username=" + username + ", from=" + from + ", color=" + color + "]";
	}

	public Long getIdGame() {
		return idGame;
	}

	public void setIdGame(Long idGame) {
		this.idGame = idGame;
	}
	
	

}
