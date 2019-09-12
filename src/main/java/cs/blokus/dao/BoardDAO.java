package cs.blokus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.Board;

@Transactional
@Repository
public interface BoardDAO extends JpaRepository<Board, Long>{
	
	public Board findByIdBoard(Long idBoard);
	
}
