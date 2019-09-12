package cs.blokus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.TileVariations;
import cs.blokus.enums.TileNameEnum;

@Transactional
@Repository
public interface TileVariationsDAO extends JpaRepository<TileVariations, Long> {
	
	@Query("select tv from  TileVariations tv where tv.tileDetails.name = ?1")
	List<TileVariations> getForTileName(TileNameEnum name);

}
