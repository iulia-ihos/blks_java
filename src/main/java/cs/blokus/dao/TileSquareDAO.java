package cs.blokus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.TileSquare;
import cs.blokus.entity.TileSquareId;

@Transactional
@Repository
public interface TileSquareDAO extends JpaRepository<TileSquare, TileSquareId> {

}
