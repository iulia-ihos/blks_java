package cs.blokus.messages;

import java.util.List;

import cs.blokus.dto.PlayerDTO;

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
