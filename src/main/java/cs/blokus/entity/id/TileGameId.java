package cs.blokus.entity.id;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class TileGameId implements Serializable {

	private Long idGame;
	
	private Long idTile;

	public TileGameId() {

	}
	
	public TileGameId(Long idGame, Long idTile) {
		this.idGame = idGame;
		this.idTile = idTile;
	}

	@Override
	public String toString() {
		return "TileGameId [idGame=" + idGame + ", idTile=" + idTile + "]";
	}

	public Long getIdGame() {
		return idGame;
	}

	public void setIdGame(Long idGame) {
		this.idGame = idGame;
	}

	public Long getIdTile() {
		return idTile;
	}

	public void setIdTile(Long idTile) {
		this.idTile = idTile;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof TileGameId))
			return false;
		if (obj == this)
			return true;

		TileGameId m = (TileGameId) obj;

		return (this.getIdGame() == m.getIdGame() && this.getIdTile() == m.getIdTile());
	}

	@Override
	public int hashCode() {
		return (int) (3 * this.idTile.hashCode() + this.idGame.hashCode());
	}
}
