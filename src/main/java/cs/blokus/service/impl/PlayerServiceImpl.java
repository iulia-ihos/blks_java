package cs.blokus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.PlayerDAO;
import cs.blokus.dto.PlayerDTO;
import cs.blokus.dto.PlayerDetailsDTO;
import cs.blokus.entity.Player;
import cs.blokus.model_mapping.ModelMapping;
import cs.blokus.service.IPlayerDetailsService;
import cs.blokus.service.IPlayerService;

@Component
public class PlayerServiceImpl implements IPlayerService {
	
	@Autowired
	private ModelMapping modelMapper;

	@Autowired
	private PlayerDAO playerDAO;
	
	@Autowired
	private IPlayerDetailsService playerDetailsService;

	


	@Override
	public PlayerDTO create(PlayerDTO playerDTO) {
		PlayerDetailsDTO playerDetails = playerDetailsService.create(playerDTO.getPlayerDetails(), playerDTO);
		Player player = playerDAO.findById(playerDetails.getIdPlayer()).get();
		PlayerDTO returnedPlayerDTO =  modelMapper.map(player, PlayerDTO.class);
		returnedPlayerDTO.setPlayerDetails(playerDetails);
		return  returnedPlayerDTO;
	}





}
