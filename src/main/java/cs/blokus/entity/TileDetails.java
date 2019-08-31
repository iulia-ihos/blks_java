package cs.blokus.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	

	@OneToMany(mappedBy = "tileDetails")
	List<TileSquare> tileDetails;
	

	@OneToMany(mappedBy = "tileDetails")
	List<TileSquare> tileSquares;

}
