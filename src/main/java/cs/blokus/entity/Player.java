package cs.blokus.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cs.blokus.enums.TileColorEnum;

@Entity
@Table(name = "player")

public class Player {
	
	@EmbeddedId
	private PlayerId player;

	@ManyToOne
	@JoinColumn(name = "idUser", nullable = false, insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "idGame", nullable = false, insertable = false, updatable = false)
	private Game game;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "color")
	private TileColorEnum color;
	
	@Column(name = "points")
	private int points;


	public Player(PlayerId player, TileColorEnum color, int points) {
		this.player = player;
		this.color = color;
		this.points = points;
	}
	
	public Player() {

	}

	public PlayerId getPlayer() {
		return player;
	}

	public void setPlayer(PlayerId player) {
		this.player = player;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public TileColorEnum getColor() {
		return color;
	}

	public void setColor(TileColorEnum color) {
		this.color = color;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
}
