package cs.blokus.dto;

import java.io.Serializable;

import cs.blokus.enums.TileColorEnum;

@SuppressWarnings("serial")
public class TileDTO implements Serializable{
	
	private Long idTile;
	private TileColorEnum color;
	private TileDetailsDTO tileDetails;
	
	public TileDTO(Long idTile, TileColorEnum color, TileDetailsDTO tileDetails) {
		this.idTile = idTile;
		this.color = color;
		this.tileDetails = tileDetails;
	}
	
	public TileDTO() {}
	
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
	public TileDetailsDTO getTileDetails() {
		return tileDetails;
	}
	public void setTileDetails(TileDetailsDTO tileDetails) {
		this.tileDetails = tileDetails;
	}

	@Override
	public String toString() {
		return "TileDTO [idTile=" + idTile + ", color=" + color + ", tileDetails=" + tileDetails + "]";
	}
		

}
