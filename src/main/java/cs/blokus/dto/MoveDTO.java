package cs.blokus.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MoveDTO implements Serializable {

	private GameDTO game;
	private BoardPositionDTO position;
	
	
	public MoveDTO(GameDTO game, BoardPositionDTO position) {
		this.game = game;
		this.position = position;
	}

	public GameDTO getGame() {
		return game;
	}
	
	public void setGame(GameDTO game) {
		this.game = game;
	}
	
	public BoardPositionDTO getPosition() {
		return position;
	}
	
	public void setPosition(BoardPositionDTO position) {
		this.position = position;
	}
	
	
}
