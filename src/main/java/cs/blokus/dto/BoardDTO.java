package cs.blokus.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BoardDTO implements Serializable{
	
    private Long idBoard;

	private int[][] board;

	public BoardDTO(Long idBoard, int[][] board) {
		this.idBoard = idBoard;
		this.board = board;
	}

	public BoardDTO() {
	}
	
	public Long getIdBoard() {
		return idBoard;
	}

	public void setIdBoard(Long idBoard) {
		this.idBoard = idBoard;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	

}
