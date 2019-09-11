package cs.blokus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.Move;
import cs.blokus.entity.MoveId;

@Transactional
@Repository
public interface MoveDAO extends JpaRepository<Move, MoveId> {

	@Query("select move from Move move where move.move.idGame = ?1")
	List<Move> getForGame(Long igGame);
}
