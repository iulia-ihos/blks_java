package cs.blokus.messages;

import java.util.List;

import cs.blokus.dto.PlayerDTO;
import cs.blokus.dto.TileDTO;

public class StartGameMessage {

	private List<PlayerDTO> players;
	private List<TileDTO> tiles;
	
	
	
	public StartGameMessage(List<PlayerDTO> players, List<TileDTO> tiles) {
		this.players = players;
		this.tiles = tiles;
	}



	public StartGameMessage(){
		
	}

	
	public List<PlayerDTO> getPlayers() {
		return players;
	}



	public void setPlayers(List<PlayerDTO> players) {
		this.players = players;
	}



	public List<TileDTO> getTiles() {
		return tiles;
	}



	public void setTiles(List<TileDTO> tiles) {
		this.tiles = tiles;
	}
	
	
}
