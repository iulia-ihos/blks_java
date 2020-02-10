package cs.blokus.entity.id;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PlayerDetailsId implements Serializable {
	
	private Long idPlayer;
	private Long idUser;
	
	public PlayerDetailsId(Long idPlayer, Long idUser) {
		this.idPlayer = idPlayer;
		this.idUser = idUser;
	}
	
	public PlayerDetailsId() {
		
	}

	public Long getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Long idPlayer) {
		this.idPlayer = idPlayer;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	

	@Override
	public String toString() {
		return "PlayerDetailsId [idPlayer=" + idPlayer + ", idUser=" + idUser + "]";
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println(this);
		
	    if (obj == null) return false;
	  
	    if (!(obj instanceof PlayerDetailsId)) return false;
	    if (obj == this)return true;
	  
	    
	   PlayerDetailsId m = (PlayerDetailsId)obj;
	   System.out.println(m);
	   System.out.println("------------");
	    
	    return (this.idPlayer == m.getIdPlayer() && this.getIdUser() == m.getIdUser());
	}
	
	@Override
	public int hashCode() {
	    return (int) (3*idUser + idPlayer);
	}

}
