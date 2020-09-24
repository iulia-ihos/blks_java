package cs.blokus.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PerformanceDTO implements Serializable{

    private UserDTO user;
	private int numberGamesPlayed;
	private int numberGamesWon;
	private double ranking;
	
	public PerformanceDTO(UserDTO user, int numberGamesPlayed, int numberGamesWon) {
		this.user = user;
		this.numberGamesPlayed = numberGamesPlayed;
		this.numberGamesWon = numberGamesWon;
	}
	
	public PerformanceDTO() {}
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public int getNumberGamesPlayed() {
		return numberGamesPlayed;
	}
	public void setNumberGamesPlayed(int numberGamesPlayed) {
		this.numberGamesPlayed = numberGamesPlayed;
	}
	public int getNumberGamesWon() {
		return numberGamesWon;
	}
	public void setNumberGamesWon(int numberGamesWon) {
		this.numberGamesWon = numberGamesWon;
	}

	public double getRanking() {
		return ranking;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}
	
	
}
