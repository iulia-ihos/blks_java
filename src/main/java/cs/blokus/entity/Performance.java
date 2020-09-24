package cs.blokus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userPerformance")
public class Performance {
	
	@Id
	private Long idUser;
	
	@OneToOne
    @JoinColumn(name = "")
    @MapsId
    private User user;
	
	@Column(name = "totalGames")
	private int numberGamesPlayed;
	
	@Column(name = "numberGamesWon")
	private int numberGamesWon;
	
	@Column(name = "ranking")
	private double  ranking;
	
	public Performance(Long idUser, int numberGamesPlayed, int numberGamesWon) {
		this.idUser = idUser;
		this.user = new User(idUser);
		this.numberGamesPlayed = numberGamesPlayed;
		this.numberGamesWon = numberGamesWon;
	}
	
	
	
	public double getRanking() {
		return ranking;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

	public Performance() {}
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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
