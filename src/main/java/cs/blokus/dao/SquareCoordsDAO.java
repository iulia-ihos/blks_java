package cs.blokus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.Square;

@Repository
@Transactional
public interface SquareCoordsDAO extends JpaRepository<Square, String>{

}
