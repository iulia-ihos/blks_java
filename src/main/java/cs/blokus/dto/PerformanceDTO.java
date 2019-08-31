package cs.blokus.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PerformanceDTO implements Serializable{

	private Long idUser;
    private UserDTO user;
	private int numberGamesPlayed;
	private int numberGamesWon;
	
	public PerformanceDTO(Long idUser, UserDTO user, int numberGamesPlayed, int numberGamesWon) {
		this.idUser = idUser;
		this.user = user;
		this.numberGamesPlayed = numberGamesPlayed;
		this.numberGamesWon = numberGamesWon;
	}
	
	public PerformanceDTO() {}
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
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
	
	
}
