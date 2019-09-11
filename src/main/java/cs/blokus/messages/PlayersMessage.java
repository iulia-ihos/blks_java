package cs.blokus.messages;

import java.util.List;

import cs.blokus.dto.PlayerDTO;

public class PlayersMessage {
	
	private List<PlayerDTO> players;

	public PlayersMessage(){}
	
	

	public PlayersMessage(List<PlayerDTO> players) {
		this.players = players;
	}



	public List<PlayerDTO> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDTO> players) {
		this.players = players;
	}
	
	
	
}
