package cs.blokus.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cs.blokus.enums.TileColorEnum;

@Entity
@Table(name = "tile")
public class Tile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTile;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "color")
	private TileColorEnum color;
	
	@ManyToOne
	@JoinColumn(name = "tileName")
	private TileDetails tileDetails;
	
	
	@OneToMany(mappedBy = "tile", cascade = CascadeType.ALL)
	private List<Move> moves;
	
	@OneToMany(mappedBy = "tile", cascade = CascadeType.ALL)
	private List<TileGame> games;
	
	


	public Tile(TileColorEnum color, TileDetails tileDetails) {
		this.color = color;
		this.tileDetails = tileDetails;
	}
	
	public Tile() {
		
	}


	public Long getIdTile() {
		return idTile;
	}


	public void setIdTile(Long idTile) {
		this.idTile = idTile;
	}


	public TileColorEnum getColor() {
		return color;
	}


	public void setColor(TileColorEnum color) {
		this.color = color;
	}


	public TileDetails getTileDetails() {
		return tileDetails;
	}


	public void setTileDetails(TileDetails tileDetails) {
		this.tileDetails = tileDetails;
	}


	public List<Move> getMoves() {
		return moves;
	}


	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}

	@Override
	public String toString() {
		return "Tile [idTile=" + idTile + ", color=" + color + ", tileDetails=" + tileDetails + "]";
	}
	

}
