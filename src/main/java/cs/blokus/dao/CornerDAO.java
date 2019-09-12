package cs.blokus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.Corner;
import cs.blokus.enums.TileColorEnum;

@Transactional
@Repository
public interface CornerDAO extends JpaRepository<Corner, Long>{
	
	@Query("select corner from Corner corner where corner.color = ?1 and corner.game.idGame = ?2")
	public List<Corner> getCornersForColor(TileColorEnum color, Long idGame);

}
