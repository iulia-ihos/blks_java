package cs.blokus.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cs.blokus.enums.TileColorEnum;

@Entity
@Table(name = "player")

public class Player {
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlayer;
	*/
	
	//@Id	
	@ManyToOne
	@JoinColumn(name = "idUser", nullable = false, insertable = false, updatable = false)
	private User user;
	
//	@Id
	@ManyToOne
	@JoinColumn(name = "idGame", nullable = false, insertable = false, updatable = false)
	private Game game;
	
	@EmbeddedId
	private PlayerId player;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "color")
	private TileColorEnum color;
	
	@Column(name = "points")
	private int points;
	
	/*@OneToOne(mappedBy = "redPlayer")
	private GamePlayers gameRed; 
	
	@OneToOne(mappedBy = "bluePlayer")
	private GamePlayers gameBlue; 
	
	@OneToOne(mappedBy = "greenPlayer")
	private GamePlayers gameGreen; 
	
	@OneToOne(mappedBy = "yellowPlayer")
	private GamePlayers gameYellow; */
	
	/*@OneToOne(mappedBy = "winner")
	private Game gameWinner; */

}
