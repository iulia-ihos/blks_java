package cs.blokus.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cs.blokus.entity.attribute_converter.ArrayConverter;

@Entity
@Table(name = "tileVariations")
public class TileVariations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTileVariation;
	
	@ManyToOne
	@JoinColumn(name = "idTileDetails")
	private TileDetails tileDetails;
	
	@Convert(converter = ArrayConverter.class)
	private int[][] tile;
	
	@Column(name = "angle")
	private int angle;
	
	@Column(name = "flipH")
	private boolean flipH;
	
	@Column(name = "flipV")
	private boolean flipV;
	
	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public boolean isFlipH() {
		return flipH;
	}

	public void setFlipH(boolean flipH) {
		this.flipH = flipH;
	}

	public boolean isFlipV() {
		return flipV;
	}

	public void setFlipV(boolean flipV) {
		this.flipV = flipV;
	}

	public TileVariations(Long idTileVariation, TileDetails tileDetails, int[][] tile,
			int angle, boolean flipH, boolean flipV) {
		this.idTileVariation = idTileVariation;
		this.tileDetails = tileDetails;
		this.tile = tile;
		this.angle = angle;
		this.flipH = flipH;
		this.flipV = flipV;
	}
	
	public TileVariations() {
	}

	public Long getIdTileVariation() {
		return idTileVariation;
	}

	public void setIdTileVariation(Long idTileVariation) {
		this.idTileVariation = idTileVariation;
	}

	public TileDetails getTileDetails() {
		return tileDetails;
	}

	public void setTileDetails(TileDetails tileDetails) {
		this.tileDetails = tileDetails;
	}

	public int[][] getTile() {
		return tile;
	}

	public void setTile(int[][] tile) {
		this.tile = tile;
	}

	@Override
	public String toString() {
		printTile();
		return "TileVariations [idTileVariation=" + idTileVariation + ", tileDetails=" + tileDetails + ", tile="
				+ Arrays.toString(tile) + "  "  + "]";
	}
	
	private void printTile(){
		for(int i = 0; i < tile.length; i++) {
			for(int j = 0; j < tile[0].length; j++) {
				System.out.print(tile[i][j]);
			}
			System.out.println();
		}
	}
	

}
