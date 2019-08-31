//package cs.blokus.entity;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "tileShape")
//public class TileShape {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long idTileShape;
//	
//	@ManyToOne
//	@JoinColumn(name = "idTile", nullable = false)
//	private Tile tile;
//	
//	@ManyToMany
//	@JoinColumn(name = "idShape", nullable = false)
//	private Square square;
//	
//	
//
//}
