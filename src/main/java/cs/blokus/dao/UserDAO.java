package cs.blokus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.User;

@Transactional
@Repository
public interface UserDAO extends JpaRepository<User,Long>{
	
	User findByEmail(String email);
	User findByUsername(String username);
}
