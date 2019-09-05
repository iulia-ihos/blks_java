package cs.blokus.messages;

import cs.blokus.enums.TileColorEnum;

public class JoinMessage {

	private Long idGame;
	private String username;
	private TileColorEnum color;	
	private String sendTo; 
	
	public JoinMessage(Long idGame, String username, String sendTo, TileColorEnum color) {
		this.idGame = idGame;
		this.username = username;
		this.color = color;
		this.sendTo = sendTo;
	}

	public JoinMessage() {
		
	}

	public Long getIdGame() {
		return idGame;
	}


	public void setIdGame(Long idGame) {
		this.idGame = idGame;
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


	public String getSendTo() {
		return sendTo;
	}


	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
		
	
	
}
