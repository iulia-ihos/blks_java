package cs.blokus.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tileSquare")
public class TileSquare {
	
	@EmbeddedId
	private TileSquareId tileSquare;
	
	@ManyToOne
	@JoinColumn(name = "tileName", nullable = false, insertable = false, updatable = false)
	private TileDetails tileDetails;
	
	@ManyToOne
	@JoinColumn(name = "idSquare", nullable = false, insertable = false, updatable = false)
	private Square square;
	
	

}
