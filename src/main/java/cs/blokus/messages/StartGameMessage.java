package cs.blokus.messages;

import java.util.List;

import cs.blokus.dto.PlayerDTO;
import cs.blokus.dto.TileDTO;

public class StartGameMessage {

	private List<PlayerDTO> players;
	
	public StartGameMessage(List<PlayerDTO> players) {
		this.players = players;
	}

	public StartGameMessage(){
		
	}
	
	public List<PlayerDTO> getPlayers() {
		return players;
	}


	public void setPlayers(List<PlayerDTO> players) {
		this.players = players;
	}


	
	
}
