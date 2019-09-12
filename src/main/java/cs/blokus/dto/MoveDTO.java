package cs.blokus.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MoveDTO implements Serializable {

	private TileDTO tile;
	private GameDTO game;
	private TilePositionDTO position;
	
	
	public MoveDTO(GameDTO game, TileDTO tile, TilePositionDTO position) {
		this.game = game;
		this.position = position;
		this.tile = tile;
	}
	
	public MoveDTO() {
	}

	public GameDTO getGame() {
		return game;
	}
	
	public void setGame(GameDTO game) {
		this.game = game;
	}
	
	public TilePositionDTO getPosition() {
		return position;
	}
	
	public void setPosition(TilePositionDTO position) {
		this.position = position;
	}

	public TileDTO getTile() {
		return tile;
	}

	public void setTile(TileDTO tile) {
		this.tile = tile;
	}
	
	
	
}
