package cs.blokus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.PlayerDetails;
import cs.blokus.entity.id.PlayerDetailsId;
import cs.blokus.enums.TileColorEnum;

@Transactional
@Repository
public interface PlayerDetailsDAO extends JpaRepository<PlayerDetails, PlayerDetailsId>{
	@Modifying
	@Query("update PlayerDetails pd set pd.points = ?2 where pd.idPlayerDetails.idPlayer = ?1")
	void updateScore(Long id, int score);
	
	@Query("select pd from  PlayerDetails pd where pd.color = ?1 and "
			+ "exists (select player from Player player where player.game.idGame = ?2 and "
			+ "pd.idPlayerDetails.idPlayer = player.playerDetails.idPlayerDetails.idPlayer)")
	PlayerDetails getPlayer(TileColorEnum color, Long idGame);

	@Query("select pd from  PlayerDetails pd where "
			+ "exists (select player from Player player where player.game.idGame = ?1 and "
			+ "pd.idPlayerDetails.idPlayer = player.playerDetails.idPlayerDetails.idPlayer)")
	List<PlayerDetails> getPlayersForGame(Long idGame);
	
	PlayerDetails findByIdPlayerDetailsIdPlayer(Long idPlayer);
}
