package cs.blokus.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PlayerId implements Serializable {

	private Long idUser;
	private Long  idGame;

	public PlayerId(Long idUser, Long idGame) {
		this.idUser = idUser;
		this.idGame = idGame;
	}

	public PlayerId() {}
	
	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	public Long getIdGame() {
		return idGame;
	}


	public void setIdGame(Long idGame) {
		this.idGame = idGame;
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	  
	    if (!(obj instanceof PlayerId)) return false;
	    if (obj == this)return true;
	    
	    PlayerId m = (PlayerId)obj;
	    
	    return (this.idGame == m.getIdGame() && this.idUser == m.getIdUser());
	}
	
	@Override
	public int hashCode() {
	    return (int) (3*idUser + idGame);
	}
	
	
	
	
}
