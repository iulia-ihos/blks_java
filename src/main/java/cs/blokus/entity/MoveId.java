package cs.blokus.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class MoveId implements Serializable{
	
	private Long idGame;
	private Long idTile;
	
	public MoveId(Long idGame, Long idTile) {
		this.idGame = idGame;
		this.idTile = idTile;
	}
	
	public MoveId() {}


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
	    if (obj == null) return false;
	  
	    if (!(obj instanceof MoveId)) return false;
	    if (obj == this)return true;
	    
	    MoveId m = (MoveId)obj;
	    
	    return (this.idGame == m.getIdGame() && this.idTile == m.getIdTile());
	}
	
	@Override
	public int hashCode() {
	    return (int) (3*idTile + idGame);
	}
}
