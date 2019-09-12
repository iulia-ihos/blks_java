package cs.blokus.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cs.blokus.entity.id.MoveId;

@Entity
@Table(name = "move")
public class Move {
	
	@EmbeddedId
	private MoveId move;
	
	@ManyToOne
	@JoinColumn(name = "idTile", nullable = false, insertable = false, updatable = false)
	private Tile tile; 
	
	@ManyToOne
	@JoinColumn(name = "idGame", nullable = false, insertable = false, updatable = false)
	private Game game;
	
	@OneToOne
	@JoinColumn(name = "idBoardPosition")
	private TilePosition position;

	public Move(Tile tile, Game game, TilePosition position) {
		this.tile = tile;
		this.game = game;
		this.position = position;
	}
	
	public Move() {}

	public MoveId getMove() {
		return move;
	}

	public void setMove(MoveId move) {
		this.move = move;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public TilePosition getPosition() {
		return position;
	}

	public void setPosition(TilePosition position) {
		this.position = position;
	} 
	
	
}
