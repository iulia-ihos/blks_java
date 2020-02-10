package cs.blokus.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlayer;
		
	@ManyToOne
	@JoinColumn(name = "idGame", nullable = false)
	private Game game;

	@OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
	private PlayerDetails playerDetails;


	public Player(Game game, PlayerDetails playerDetails) {
		this.game = game;
		this.playerDetails = playerDetails;	
	}
	
	public Player() {

	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}


	public Long getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Long idPlayer) {
		this.idPlayer = idPlayer;
	}

	public PlayerDetails getPlayerDetails() {
		return playerDetails;
	}

	public void setPlayerDetails(PlayerDetails playerDetails) {
		this.playerDetails = playerDetails;
	}

	@Override
	public String toString() {
		return "Player [idPlayer=" + idPlayer + ", game=" + game + ", playerDetails=" + playerDetails + "]";
	}

	
	
	
	
}
