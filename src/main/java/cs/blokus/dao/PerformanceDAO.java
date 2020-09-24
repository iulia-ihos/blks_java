package cs.blokus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.entity.Performance;

@Transactional
@Repository
public interface PerformanceDAO extends JpaRepository<Performance, Long> {
	
//	@Query(nativeQuery = true,
//            value = "SELECT TOP ?1 * FROM Performance p ORDER BY p.ranking DESC")
	List<Performance> findTop10ByOrderByRankingDesc();
	
	Performance findByIdUser(Long idUser);
	
	@Query("select p from  Performance p where "
			+ "exists (select user from User user where user.username = ?1 and "
			+ "p.idUser = user.idUser)")
	public Performance findByUsername(String username);

}
