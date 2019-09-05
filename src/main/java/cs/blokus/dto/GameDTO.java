package cs.blokus.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cs.blokus.enums.GameStatusEnum;

@SuppressWarnings("serial")
public class GameDTO implements Serializable {

	private Long idGame;
	private Date startTime;
	private Date endTime;
	private UserDTO winner;
	private GameStatusEnum status;
	List<PlayerDTO> players;
	List<MoveDTO> moves;
	
	
	public GameDTO(Long idGame, Date startTime, Date endTime, UserDTO winner, List<PlayerDTO> players,
			List<MoveDTO> moves, GameStatusEnum status) {
		this.status = status;
		this.idGame = idGame;
		this.startTime = startTime;
		this.endTime = endTime;
		this.winner = winner;
		this.players = players;
		this.moves = moves;
	}
	
	public GameDTO() {
		
	}

	public Long getIdGame() {
		return idGame;
	}
	
	public void setIdGame(Long idGame) {
		this.idGame = idGame;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public UserDTO getWinner() {
		return winner;
	}
	
	public void setWinner(UserDTO winner) {
		this.winner = winner;
	}
	
	public List<PlayerDTO> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<PlayerDTO> players) {
		this.players = players;
	}
	
	public List<MoveDTO> getMoves() {
		return moves;
	}
	
	public void setMoves(List<MoveDTO> moves) {
		this.moves = moves;
	}
	
	public GameStatusEnum getStatus() {
		return status;
	}

	public void setStatus(GameStatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GameDTO [idGame=" + idGame + ", startTime=" + startTime + ", endTime=" + endTime + ", winner=" + winner
				+ ", status=" + status + ", players=" + players + ", moves=" + moves + "]";
	}
	
	
	
	
}
