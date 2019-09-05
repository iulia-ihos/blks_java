package cs.blokus.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cs.blokus.enums.GameStatusEnum;

@Entity
@Table(name = "game")
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGame;
	
	@Column(name = "startTime")
	private Date startTime;
	
	@Column(name = "endTime")
	private Date endTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private GameStatusEnum status;

	@OneToOne
	@JoinColumn(name = "winner")
	private User winner;
	
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
	List<Player> players;
	
	@OneToMany(mappedBy = "game")
	List<Move> moves;


	public Game(Long idGame, Date startTime, Date endTime, User winner, GameStatusEnum status) {
		this.status = status;
		this.idGame = idGame;
		this.startTime = startTime;
		this.endTime = endTime;
		this.winner = winner;
	}
	
	public Game() {}

	public Long getIdGame() {
		return idGame;
	}

	public void setIdGame(Long idGame) {
		this.idGame = idGame;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public User getWinner() {
		return winner;
	}

	public void setWinner(User winner) {
		this.winner = winner;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}

	public GameStatusEnum getStatus() {
		return status;
	}

	public void setStatus(GameStatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Game [idGame=" + idGame + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status
				+ ", winner=" + winner + ", players=" + players + ", moves=" + moves + "]";
	}
	
	
	
	
}

