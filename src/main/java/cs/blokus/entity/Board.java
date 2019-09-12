package cs.blokus.entity;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cs.blokus.entity.attribute_converter.ArrayConverter;

@Entity
@Table(name = "board")
public class Board {
	
	@Id
    private Long idBoard;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idBoard")
    @MapsId
    private Game game;

    @Column(length = 850)
    @Convert(converter = ArrayConverter.class)
	private int[][] board;
    
	public Board(Long idBoard, int[][] board) {
		this.idBoard = idBoard;
		this.game = new Game();
		game.setIdGame(idBoard);
		this.board = board;
	}

    
    public Board(int[][] board) {
		this.board = board;
	}
	

	public Board() {}

	public Long getIdBoard() {
		return idBoard;
	}

	public void setIdBoard(Long idBoard) {
		this.idBoard = idBoard;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
		this.game.setBoard(this);
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}


	@Override
	public String toString() {
		return "Board [idBoard=" + idBoard + ", game=" + game + ", board=" + Arrays.toString(board) + "]";
	}
    
    
	
	
}
