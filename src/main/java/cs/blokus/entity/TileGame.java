package cs.blokus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tileGame")
public class TileGame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTileGame;
	
	@ManyToOne
	@JoinColumn(name = "idGame")
	private Game game;
	
	@ManyToOne
	@JoinColumn(name = "idTile")
	private Tile tile;
	
	@Column 
	private boolean isUsed;
	
	public TileGame() {
	}

	public TileGame(Long idTileGame, Game game, Tile tile, boolean isUsed) {
		this.idTileGame = idTileGame;
		this.game = game;
		this.tile = tile;
		this.isUsed = isUsed;
	}

	public Long getIdTileGame() {
		return idTileGame;
	}

	public void setIdTileGame(Long idTileGame) {
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
