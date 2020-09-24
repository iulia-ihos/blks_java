package cs.blokus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.PerformanceDAO;
import cs.blokus.dao.PlayerDetailsDAO;
import cs.blokus.dto.PerformanceDTO;
import cs.blokus.entity.Performance;
import cs.blokus.entity.PlayerDetails;
import cs.blokus.model_mapping.ModelMapping;
import cs.blokus.service.IPerformanceService;

@Component
public class PerformanceServiceImpl implements IPerformanceService{
	
	@Autowired
	private PerformanceDAO performanceDAO;
	
	@Autowired
	private PlayerDetailsDAO playerDetailsDAO;
	
	@Autowired
	private ModelMapping modelMapper;

	@Override
	public PerformanceDTO addWin(Long idUser) {
		Performance p = performanceDAO.findByIdUser(idUser);
		int won = p.getNumberGamesWon();
		int played = p.getNumberGamesPlayed();
		p.setRanking((++won)/(++played));
		performanceDAO.save(p);
		return modelMapper.map(performanceDAO.save(p), PerformanceDTO.class);
	}

	@Override
	public PerformanceDTO addLost(Long idUser) {
		Performance p = performanceDAO.findByIdUser(idUser);
		int won = p.getNumberGamesWon();
		int played = p.getNumberGamesPlayed();
		p.setRanking(won/(++played));performanceDAO.save(p);
		return modelMapper.map(performanceDAO.save(p), PerformanceDTO.class);
	}

	@Override
	public List<PerformanceDTO> getTopRankingUsers() {
		List<PerformanceDTO> list = new ArrayList<>();
		performanceDAO.findTop10ByOrderByRankingDesc().stream().forEach(p -> list.add(modelMapper.map(p, PerformanceDTO.class)));
		return list;
	}

	@Override
	public PerformanceDTO getPerformanceByUsername(String username) {
		return modelMapper.map(performanceDAO.findByUsername(username), PerformanceDTO.class);
	}

	@Override
	public void updateForGame(Long idGame) {
		List<PlayerDetails> players = playerDetailsDAO.getPlayersForGame(idGame);
		int max = 0;
		for(PlayerDetails player: players) {
			if(player.getPoints() > max)
				max = player.getPoints();
		}
		
		for(PlayerDetails player: players) {
			if(player.getPoints() != max)
				addLost(player.getUser().getIdUser());
		}
		
	}

}
