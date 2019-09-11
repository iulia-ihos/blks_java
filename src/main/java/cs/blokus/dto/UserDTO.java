package cs.blokus.dto;

import java.io.Serializable;

import cs.blokus.enums.RolesEnum;

@SuppressWarnings("serial")
public class UserDTO implements Serializable {
	
	private Long idUser;
	private String email;
	private String username;
	private String password;
	private RolesEnum role;
	
	//setters and getters
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
	public RolesEnum getRole() {
		return role;
	}
	public void setRole(RolesEnum role) {
		this.role = role;
	}
	

}
