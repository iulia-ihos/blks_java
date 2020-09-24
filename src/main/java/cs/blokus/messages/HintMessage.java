package cs.blokus.messages;

import java.io.Serializable;

import cs.blokus.enums.TileColorEnum;
import cs.blokus.enums.TileNameEnum;

@SuppressWarnings("serial")
public class HintMessage implements Serializable {
	
	private int left;
	private int top;
	private String username;
	private TileNameEnum tileName;
	private Long idGame;
	private TileColorEnum color;
	
	public HintMessage() {
	}
	
	public HintMessage(int top, int left, TileColorEnum color, String username, TileNameEnum tileName, Long idGame) {
		this.left = left;
		this.top = top;
		this.username = username;
		this.tileName = tileName;
		this.idGame = idGame;
		this.setColor(color);
	}
	
	@Override
	public String toString() {
		return "HintMessage [left=" + left + ", top=" + top + ", username=" + username + ", tile=" + tileName + "]";
	}
	
	
	
	public Long getIdGame() {
		return idGame;
	}

	public void setIdGame(Long idGame) {
		this.idGame = idGame;
	}

	public int getLeft() {
		return left;
	}
	
	public void setLeft(int left) {
		this.left = left;
	}
	
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	

	public TileNameEnum getTileName() {
		return tileName;
	}

	public void setTileName(TileNameEnum tileName) {
		this.tileName = tileName;
	}

	public TileColorEnum getColor() {
		return color;
	}

	public void setColor(TileColorEnum color) {
		this.color = color;
	}
	
	

}
