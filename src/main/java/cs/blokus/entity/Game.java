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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@Column(name = "usingPentobi")
	private boolean usingPentobi;
	
	public boolean isUsingPentobi() {
		return usingPentobi;
	}

	public void setUsingPentobi(boolean usingPentobi) {
		this.usingPentobi = usingPentobi;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private GameStatusEnum status;

	@ManyToMany
	@JoinTable(
			  name = "game_winner", 
			  joinColumns = @JoinColumn(name = "idGame"), 
			  inverseJoinColumns = @JoinColumn(name = "idUser"))
	private List<User> winner;
	
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
	List<Player> players;
	
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
	List<Move> moves;
	
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
	private List<TileGame> tiles;
	
	@OneToMany(mappedBy = "game", cascade = CascadeType.REMOVE)
	private List<Corner> corners;
	
	@OneToOne(mappedBy = "game")
	private Board board;


	public Game(Long idGame, Date startTime, Date endTime,  GameStatusEnum status) {
		this.status = status;
		this.idGame = idGame;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Game() {}

	public Game(Long idGame) {
		this.idGame = idGame;
	}

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

	public List<User> getWinner() {
		return winner;
	}

	public void setWinner(List<User> winner) {
		this.winner = winner;
	}

	public List<TileGame> getTiles() {
		return tiles;
	}

	public void setTiles(List<TileGame> tiles) {
		this.tiles = tiles;
	}

	public List<Corner> getCorners() {
		return corners;
	}

	public void setCorners(List<Corner> corners) {
		this.corners = corners;
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
	
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Override
	public String toString() {
		return "Game [idGame=" + idGame + ", startTime=" + startTime + ", endTime=" + endTime + 
				", status=" + status + 
				", pentobi=" + usingPentobi;
	}
	
	
	
	
}

