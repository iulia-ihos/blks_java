package cs.blokus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.Move;
import cs.blokus.entity.MoveId;

@Transactional
@Repository
public interface MoveDAO extends JpaRepository<Move, MoveId> {

}
