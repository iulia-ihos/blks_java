package cs.blokus.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cs.blokus.enums.RolesEnum;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private RolesEnum role;
	
	
	@OneToOne(mappedBy = "user")
	private Performance performance; 
	
	@OneToMany(mappedBy = "user")
	private List<PlayerDetails> playerDetails;
	
	@ManyToMany(mappedBy = "winner")
	private List<Game> gamesWon;
	

	public User(Long idUser, String email, String username, String password, RolesEnum role) {
		this.idUser = idUser;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User(Long idUser) {
		this.idUser = idUser;
	}
	
	public User() {}

	public RolesEnum getRole() {
		return role;
	}

	public void setRole(RolesEnum role) {
		this.role = role;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Performance getPerformance() {
		return performance;
	}

	public void setPerformance(Performance performance) {
		this.performance = performance;
	}


	public List<Game> getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(List<Game> gamesWon) {
		this.gamesWon = gamesWon;
	}

	public List<PlayerDetails> getColors() {
		return playerDetails;
	}

	public void setColors(List<PlayerDetails> playerDetails) {
		this.playerDetails = playerDetails;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", role=" + role + "]";
	}
	
	
	
	
}
