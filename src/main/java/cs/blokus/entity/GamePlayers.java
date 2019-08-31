/*package cs.blokus.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gamePlayer")
public class GamePlayers {
	
	@Id
	private Long id;
	
	@OneToOne
    @JoinColumn(name = "idGame")
    @MapsId
    private Game game;
	
	@OneToOne
	@JoinColumn(name = "idRedPlayer")
	private Player  redPlayer;
	
	@OneToOne
	@JoinColumn(name = "idBluePlayer")
	private Player  bluePlayer;
	
	@OneToOne
	@JoinColumn(name = "idGreenPlayer")
	private Player  greenPlayer;
	
	@OneToOne
	@JoinColumn(name = "idYellowPlayer")
	private Player  yellowPlayer;

}
*/