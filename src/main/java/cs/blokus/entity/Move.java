package cs.blokus.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "")
public class Move {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long idMove;
	
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
	private BoardPosition position; 
	
}
