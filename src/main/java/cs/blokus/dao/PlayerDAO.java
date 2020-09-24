package cs.blokus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.Player;
import cs.blokus.enums.TileColorEnum;

@Transactional
@Repository
public interface PlayerDAO extends JpaRepository<Player, Long>  {
	
//	@Modifying
//	@Query("update Player player INNER JOIN PlayerDetails playerDetails ON  player.idPlayer = playerDetails.idPlayer "
//			+ "set player.out = ?1 where player.idGame = ?2 and playerDetails.color = color")
//	void updateOut(boolean isOut, Long idGame, TileColorEnum color);
	
	@Modifying
	@Query("update Player player set player.out = ?1 where player.idPlayer = ?2")
	void updateOutForIdPlayer(boolean isOut, Long idPlayer);
	
//	@Query("select p from  Player p where "
//			+ "exists (select pd from Player player where player.game.idGame = ?1 and "
//			+ "pd.idPlayerDetails.idPlayer = player.playerDetails.idPlayerDetails.idPlayer)")
	@Query("select p from  Player p where p.game.idGame = ?1 and p.playerDetails.color = ?2")
	Player getPlayerForGameAndColor(Long idGame, TileColorEnum color);
	
	
}
