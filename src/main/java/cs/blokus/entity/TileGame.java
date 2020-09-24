package cs.blokus.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cs.blokus.entity.id.TileGameId;

@Entity
@Table(name = "tileGame")
public class TileGame {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long idTileGame;
	
	@EmbeddedId
	private TileGameId idTileGame;
	
	@ManyToOne
	@JoinColumn(name = "idGame", insertable = false, updatable = false)
	private Game game;
	
	@ManyToOne
	@JoinColumn(name = "idTile", insertable = false, updatable = false)
	private Tile tile;
	
	@Column 
	private boolean isUsed;
	
	public TileGame() {
	}

	public TileGame(TileGameId idTileGame, boolean isUsed) {
		this.idTileGame = idTileGame;
		this.isUsed = isUsed;
	}

	public TileGameId getIdTileGame() {
		return idTileGame;
	}

	public void setIdTileGame(TileGameId idTileGame) {
		this.idTileGame = idTileGame;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public String toString() {
		return "TileGame [idTileGame=" + idTileGame + ", "
				+ "game=" + game + ", tile=" + tile + ", isUsed=" + isUsed + "]";
	}
	
	
}
