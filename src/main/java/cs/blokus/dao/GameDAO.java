package cs.blokus.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cs.blokus.entity.Game;

public interface GameDAO extends JpaRepository<Game, Long> {

}
