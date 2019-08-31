package cs.blokus.entity;

import java.util.List;

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
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "tileName")
	private TileDetails tileDetails;
	
	/*@ManyToMany
	@JoinTable(
	  name = "tile_components", 
	  joinColumns = @JoinColumn(name = "idTile"), 
	  inverseJoinColumns = @JoinColumn(name = "idSquare"))
	List<Square> components;*/
	
/*
	@OneToMany(mappedBy = "tile")
	List<TileSquare> tileSquares;*/
	
	@OneToMany(mappedBy = "tile")
	private List<Move> moves;


}
