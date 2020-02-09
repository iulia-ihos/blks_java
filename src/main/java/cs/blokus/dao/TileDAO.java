package cs.blokus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.Tile;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.enums.TileNameEnum;

@Transactional
@Repository
public interface TileDAO extends JpaRepository<Tile, Long> {

	@Query("select tg.tile from  TileGame tg where tg.game.idGame = ?1 and tg.isUsed = false and tg.tile.color = ?2 "
			+ " order by tg.tile.tileDetails.numberSquares desc")
	List<Tile> getAvailableTiles(Long idGame, TileColorEnum color);
	
	Tile findByColorAndTileDetailsName(TileColorEnum color, TileNameEnum name);
}
