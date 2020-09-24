package cs.blokus.service;

import java.util.List;

import cs.blokus.dto.PerformanceDTO;

public interface IPerformanceService {
	
	PerformanceDTO addWin(Long idUser);
	 
	PerformanceDTO addLost(Long idUser);
	 
	List<PerformanceDTO> getTopRankingUsers();

	PerformanceDTO getPerformanceByUsername(String username);
	
	void updateForGame(Long idGame);
	

}
