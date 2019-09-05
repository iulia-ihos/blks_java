package cs.blokus.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cs.blokus.enums.TileNameEnum;

@Entity
@Table(name = "tileDetails")
public class TileDetails {
	
 
	@Id
	@Enumerated(EnumType.STRING)
	private TileNameEnum name;
	
	@Column(name = "numberSquares")
	private int numberSquares;


	@OneToMany(mappedBy = "tileDetails", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<TileSquare> tileSquares;
	
	@OneToMany(mappedBy = "tileDetails", cascade = CascadeType.ALL)
	private List<Tile> tiles;
	


	public TileDetails(TileNameEnum name, int numberSquares) {
		this.name = name;
		this.numberSquares = numberSquares;
	}
	
	public TileDetails(TileNameEnum name) {
		this.name = name;
	}


	public TileDetails() {
	}


	public TileNameEnum getName() {
		return name;
	}


	public void setName(TileNameEnum name) {
		this.name = name;
	}


	public int getNumberSquares() {
		return numberSquares;
	}


	public void setNumberSquares(int numberSquares) {
		this.numberSquares = numberSquares;
	}


	public List<TileSquare> getTileSquares() {
		return tileSquares;
	}


	public void setTileSquares(List<TileSquare> tileSquares) {
		this.tileSquares = tileSquares;
	}


	@Override
	public String toString() {
		return "TileDetails [name=" + name + ", numberSquares=" + numberSquares + ", tileSquares=" + tileSquares + "]";
	}

	
}
