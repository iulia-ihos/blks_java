package cs.blokus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.PlayerDetailsDAO;
import cs.blokus.dao.UserDAO;
import cs.blokus.dto.PlayerDetailsDTO;
import cs.blokus.entity.PlayerDetails;
import cs.blokus.entity.User;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.model_mapping.ModelMapping;
import cs.blokus.service.IPlayerDetailsService;

@Component
public class PlayerDetailsServiceImpl implements IPlayerDetailsService{
	
	@Autowired
	private ModelMapping modelMapper;

	@Autowired
	private PlayerDetailsDAO playerDetailsDAO;
	
	
	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	public PlayerDetailsDTO updateScore(Long id, int points) {
		playerDetailsDAO.updateScore(id, points);
		return getById(id);
	}




	private PlayerDetailsDTO getById(Long id) {
		PlayerDetails details = playerDetailsDAO.findById(id).get();
		PlayerDetailsDTO dto = modelMapper.map(details, PlayerDetailsDTO.class);
		dto.setUsername(details.getUser().getUsername());
		return dto;
	}





	@Override
	public PlayerDetailsDTO create(PlayerDetailsDTO playerDetails) {
		User user = userDAO.findByUsername(playerDetails.getUsername());
		PlayerDetails pd = (PlayerDetails)modelMapper.map(playerDetails, PlayerDetails.class);
		pd.setUser(user);
		System.out.println(pd);
		PlayerDetails details = playerDetailsDAO.save(pd);
		
		PlayerDetailsDTO returnedEntity =  modelMapper.map(details, PlayerDetailsDTO.class);
		returnedEntity.setUsername(user.getUsername());
		return returnedEntity;
	}




	@Override
	public PlayerDetailsDTO getNextPlayer(TileColorEnum currentColor, Long idGame) {
		TileColorEnum nextColor = TileColorEnum.red;
		switch(currentColor) {
			case red: 
				nextColor = TileColorEnum.green;
				break;
			case green: 
				nextColor = TileColorEnum.yellow;
				break;
			case yellow: 
				nextColor = TileColorEnum.blue;
				break;
			case blue: 
				nextColor = TileColorEnum.red;
				break;
			
		}
		PlayerDetails details = playerDetailsDAO.getPlayer(nextColor, idGame);
		PlayerDetailsDTO dto =  modelMapper.map(details, PlayerDetailsDTO.class);
		dto.setUsername(details.getUser().getUsername());
		return dto;
	}




	@Override
	public List<PlayerDetailsDTO> getPlayersDetailsForGame(Long idGame) {
		List<PlayerDetails> details = playerDetailsDAO.getPlayersForGame(idGame) ;
		List<PlayerDetailsDTO> dtos = new ArrayList<>();
		for(PlayerDetails pd: details) {
			PlayerDetailsDTO dto = modelMapper.map(pd, PlayerDetailsDTO.class);
			dto.setUsername(pd.getUser().getUsername());
			dtos.add(dto);
		}
		return dtos;
	}

}
