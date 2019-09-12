package cs.blokus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cs.blokus.enums.TileColorEnum;

@Entity
@Table(name = "corner")
public class Corner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCorner;
	
	@ManyToOne
	@JoinColumn(name = "idGame")
	private Game game;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "color")
	private TileColorEnum color;
	
	@Column(name = "topmost")
	private int top;
	
	@Column(name = "leftmost")
	private int left;
	

	public Corner(Long idCorner, Game game, TileColorEnum color, int top, int left) {
		this.idCorner = idCorner;
		this.game = game;
		this.color = color;
		this.top = top;
		this.left = left;
	}
	
	public Corner() {
	}

	public Long getIdCorner() {
		return idCorner;
	}

	public void setIdCorner(Long idCorner) {
		this.idCorner = idCorner;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public TileColorEnum getColor() {
		return color;
	}

	public void setColor(TileColorEnum color) {
		this.color = color;
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

	@Override
	public String toString() {
		return "Corner [idCorner=" + idCorner  + ", color=" + color + ", top=" + top + ", left="
				+ left + "]";
	}
	
	
	
	

}
