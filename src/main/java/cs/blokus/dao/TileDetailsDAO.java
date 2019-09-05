package cs.blokus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.TileDetails;
import cs.blokus.enums.TileNameEnum;

@Transactional
@Repository
public interface TileDetailsDAO extends JpaRepository<TileDetails, TileNameEnum> {
	
}
