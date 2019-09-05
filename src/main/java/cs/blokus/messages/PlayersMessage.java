package cs.blokus.messages;

public class PlayersMessage {
	
	private Long idGame;
	private String redPlayer;
	private String bluePlayer;
	private String yellowPlayer;
	private String greenPlayer;	
	
	
	public PlayersMessage(Long idGame, String redPlayer, String bluePlayer, String yellowPlayer, 
			String greenPlayer) {
		this.idGame = idGame;
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.yellowPlayer = yellowPlayer;
		this.greenPlayer = greenPlayer;
	}

	public PlayersMessage(){}
	
	public Long getIdGame() {
		return idGame;
	}
	
	public void setIdGame(Long idGame) {
		this.idGame = idGame;
	}
	
	public String getRedPlayer() {
		return redPlayer;
	}
	
	public void setRedPlayer(String redPlayer) {
		this.redPlayer = redPlayer;
	}
	
	public String getBluePlayer() {
		return bluePlayer;
	}
	
	public void setBluePlayer(String bluePlayer) {
		this.bluePlayer = bluePlayer;
	}
	
	public String getYellowPlayer() {
		return yellowPlayer;
	}
	
	public void setYellowPlayer(String yellowPlayer) {
		this.yellowPlayer = yellowPlayer;
	}
	
	public String getGreenPlayer() {
		return greenPlayer;
	}
	
	public void setGreenPlayer(String greenPlayer) {
		this.greenPlayer = greenPlayer;
	}
	

}
