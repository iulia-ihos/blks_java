package cs.blokus.dto;

import java.io.Serializable;

import cs.blokus.entity.Game;

@SuppressWarnings("serial")
public class PlayerDTO implements Serializable{

	private Long idPlayer;
	
	private PlayerDetailsDTO playerDetails;
	
	private GameDTO game;
	

	public PlayerDTO() {
	}
	

	public PlayerDTO(Long idPlayer, PlayerDetailsDTO playerDetails, GameDTO game) {
		this.idPlayer = idPlayer;
		this.playerDetails = playerDetails;
		this.game = game;
	}


	public Long getIdPlayer() {
		return idPlayer;
	}



	public void setIdPlayer(Long idPlayer) {
		this.idPlayer = idPlayer;
	}



	public PlayerDetailsDTO getPlayerDetails() {
		return playerDetails;
	}



	public void setPlayerDetails(PlayerDetailsDTO playerDetails) {
		this.playerDetails = playerDetails;
	}



	public GameDTO getGame() {
		return game;
	}



	public void setGame(GameDTO game) {
		this.game = game;
	}
	
	
}
