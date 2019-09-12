package cs.blokus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.TileGame;

@Repository
@Transactional
public interface TileGameDAO extends JpaRepository<TileGame, Long>{

	@Modifying
	@Query("update TileGame tg set tg.isUsed = ?1 where tg.game.idGame = ?2 and tg.tile.idTile = ?3")
	void updateUsed(boolean isUsed, Long idGame, Long idTile);
	
	
}
