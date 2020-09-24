package cs.blokus.messages;

import cs.blokus.dto.BoardPosition;
import cs.blokus.dto.MoveDTO;
import cs.blokus.dto.PlayerDetailsDTO;

public class MoveMessage {

	private MoveDTO move;
	private PlayerDetailsDTO nextPlayer;
	private PlayerDetailsDTO currentPlayer;
	private BoardPosition boardPosition;
	private int [][] board;
	
	
	public int[][] getBoard() {
		return board;
	}


	public void setBoard(int[][] board) {
		this.board = board;
	}


	public MoveMessage(MoveDTO move, PlayerDetailsDTO currentPlayer, PlayerDetailsDTO nextPlayer) {
		this.move = move;
		this.nextPlayer = nextPlayer;
		this.currentPlayer =  currentPlayer;
	}
	

	public MoveMessage() {
	}
	
	public MoveDTO getMove() {
		return move;
	}
	public void setMove(MoveDTO move) {
		this.move = move;
	}
	public PlayerDetailsDTO getNextPlayer() {
		return nextPlayer;
	}
	public void setNextPlayer(PlayerDetailsDTO nextPlayer) {
		this.nextPlayer = nextPlayer;
	}


	public PlayerDetailsDTO getCurrentPlayer() {
		return currentPlayer;
	}


	public void setCurrentPlayer(PlayerDetailsDTO currentPlayer) {
		this.currentPlayer = currentPlayer;
	}


	public BoardPosition getBoardPosition() {
		return boardPosition;
	}


	public void setBoardPosition(BoardPosition boardPosition) {
		this.boardPosition = boardPosition;
	}
	
	
	
	
}
