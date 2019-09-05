package cs.blokus.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.Game;
import cs.blokus.enums.GameStatusEnum;

@Transactional
@Repository
public interface GameDAO extends JpaRepository<Game, Long> {
	
	@Modifying
	@Query("update Game game set game.status = ?1 where game.idGame = ?2")
	void updateStatus(GameStatusEnum status, Long idGame);
	
	@Modifying
	@Query("update Game game set game.startTime = ?1 where game.idGame = ?2")
	void updateStartTime(Date startTime, Long idGame);
	
	@Modifying
	@Query("update Game game set game.endTime = ?1 where game.idGame = ?2")
	void updateEndTime(Date startTime, Long idGame);
	
	

}
