package cs.blokus.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "square")
public class Square {
	

	@Id
	private String idSquare;
	
	@Column(name = "topmost")
	private int top;
	
	@Column(name = "leftmost")
	private int left;
	
	@OneToMany(mappedBy = "square")
	private List<TileSquare> tileSquares;

	
	public Square() {
		
	}

	public Square(String id, int top, int left) {
		this.idSquare = id;
		this.top = top;
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public String getIdSquare() {
		return idSquare;
	}

	public void setIdSquare(String idSquare) {
		this.idSquare = idSquare;
	}

	public List<TileSquare> getTileSquares() {
		return tileSquares;
	}

	public void setTileSquares(List<TileSquare> tileSquares) {
		this.tileSquares = tileSquares;
	}

	@Override
	public String toString() {
		return "Square [idSquare=" + idSquare + ", top=" + top + ", left=" + left + "]";
	}
	
	
	
}
