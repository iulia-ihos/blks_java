package cs.blokus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.TileSquare;
import cs.blokus.entity.TileSquareId;
import cs.blokus.enums.TileNameEnum;

@Transactional
@Repository
public interface TileSquareDAO extends JpaRepository<TileSquare, TileSquareId> {
	

	@Query("select ts from TileSquare ts where ts.tileSquare.tileName = ?1")
	List<TileSquare> getForTile(TileNameEnum tileName);
	
}
