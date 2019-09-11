package cs.blokus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.Player;

@Transactional
@Repository
public interface PlayerDAO extends JpaRepository<Player, Long>  {
	
	
}
