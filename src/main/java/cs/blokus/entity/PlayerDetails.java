package cs.blokus.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cs.blokus.entity.id.PlayerDetailsId;
import cs.blokus.enums.TileColorEnum;

@Entity
@Table(name = "playerDetails")
public class PlayerDetails {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long idPlayerDetails;
	
//	@Id
//	private Long idPlayerDetails;
	
	@EmbeddedId
	private PlayerDetailsId idPlayerDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPlayer", insertable = false, updatable = false)
	@MapsId("idPlayer")
	private Player player;

	@ManyToOne
	@JoinColumn(name = "idUser", nullable = false, insertable = false, updatable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	@Column(name = "color")
	private TileColorEnum color;
	

	@Column(name = "points")
	private int points;
	

	public PlayerDetails(Long id, User user, TileColorEnum color, int points) {
		this.idPlayerDetails = new PlayerDetailsId(id, user.getIdUser());
		this.color = color;
		this.points = points;
	}
	
	public PlayerDetails() {
	}
	
	public PlayerDetails(PlayerDetailsId id) {
		this.idPlayerDetails = id;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		PlayerDetailsId id = this.idPlayerDetails;
		id.setIdPlayer(player.getIdPlayer());
		this.setIdPlayerDetails(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		PlayerDetailsId id = this.idPlayerDetails;
		id.setIdUser(user.getIdUser());
		this.setIdPlayerDetails(id);
	}

	public TileColorEnum getColor() {
		return color;
	}

	public void setColor(TileColorEnum color) {
		this.color = color;
	}

	public PlayerDetailsId getIdPlayerDetails() {
		return idPlayerDetails;
	}

	public void setIdPlayerDetails(PlayerDetailsId idPlayerDetails) {
		this.idPlayerDetails = idPlayerDetails;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "PlayerDetails [idPlayerDetails=" + idPlayerDetails + ", user=" + user + ", color=" + color + ", points="
				+ points + "]";
	}
	
	
	

}
