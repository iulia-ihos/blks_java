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
    @JoinColumn
    @MapsId
    private User user;
	
	@Column(name = "numberGames")
	private int numberGames;
	
	@Column(name = "numberGamesWon")
	private int numberGamesWon;

}
