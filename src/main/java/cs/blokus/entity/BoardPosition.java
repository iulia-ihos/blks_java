package cs.blokus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class BoardPosition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBoardPosition;
	
	@Column(name = "topmost")
	private double top;
	
	@Column(name = "leftmost")
	private double left;
	
	@Column(name = "angle")
	private int angle;
	
	@Column(name = "flipX")
	private boolean isFlippedHorizontally;
	
	@Column(name = "flipY")
	private boolean isFlippedVertically;
	
	
}
