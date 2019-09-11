package cs.blokus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cs.blokus.enums.TileColorEnum;

@Entity
@Table(name = "playerDetails")
public class PlayerDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlayerDetails;
	
	@ManyToOne
	@JoinColumn(name = "idUser", nullable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	@Column(name = "color")
	private TileColorEnum color;
	
	
	@Column(name = "points")
	private int points;
	

	public PlayerDetails(Long id, User user, TileColorEnum color, int points) {
		this.idPlayerDetails = id;
		this.user = user;
		this.color = color;
		this.points = points;
	}
	
	public PlayerDetails() {
	}
	
	public PlayerDetails(Long id) {
		this.idPlayerDetails = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TileColorEnum getColor() {
		return color;
	}

	public void setColor(TileColorEnum color) {
		this.color = color;
	}

	public Long getIdPlayerDetails() {
		return idPlayerDetails;
	}

	public void setIdPlayerDetails(Long idPlayerDetails) {
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
