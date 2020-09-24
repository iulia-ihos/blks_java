package cs.blokus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.PlayerDAO;
import cs.blokus.dao.PlayerDetailsDAO;
import cs.blokus.dao.UserDAO;
import cs.blokus.dto.PlayerDTO;
import cs.blokus.dto.PlayerDetailsDTO;
import cs.blokus.entity.Player;
import cs.blokus.entity.PlayerDetails;
import cs.blokus.entity.User;
import cs.blokus.entity.id.PlayerDetailsId;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.model_mapping.ModelMapping;
import cs.blokus.service.IBoardService;
import cs.blokus.service.IPlayerDetailsService;

@Component
public class PlayerDetailsServiceImpl implements IPlayerDetailsService{
	
	@Autowired
	private ModelMapping modelMapper;

	@Autowired
	private PlayerDetailsDAO playerDetailsDAO;
	
	@Autowired
	private PlayerDAO playerDAO;
	
	
	@Autowired
	private UserDAO userDAO;
	
	 @Autowired 
	 private IBoardService boardService;
	
	
	@Override
	public PlayerDetailsDTO updateScore(Long id, int points) {
		int score = playerDetailsDAO.findByIdPlayerDetailsIdPlayer(id).getPoints();
		playerDetailsDAO.updateScore(id, score + points);
		return getById(id);
	}

	@Override
	public PlayerDetailsDTO create(PlayerDetailsDTO playerDetailsDTO, PlayerDTO player) {
		User user = userDAO.findByUsername(playerDetailsDTO.getUsername());
		PlayerDetails pd = (PlayerDetails)modelMapper.map(playerDetailsDTO, PlayerDetails.class);
		Player p = modelMapper.map(player,  Player.class);
		pd.setIdPlayerDetails(new PlayerDetailsId());
		pd.setUser(user);
    	pd.setPlayer(p);
		p.setPlayerDetails(pd);
		PlayerDetails details = playerDAO.save(p).getPlayerDetails();
		PlayerDetailsDTO returnedEntity =  modelMapper.map(details, PlayerDetailsDTO.class);
		returnedEntity.setUsername(details.getUser().getUsername());
		returnedEntity.setIdPlayer(details.getPlayer().getIdPlayer());
		return returnedEntity;
	}


	@Override
	public PlayerDetailsDTO getNextPlayer(TileColorEnum currentColor, Long idGame) {
		TileColorEnum nextColor = null;
		nextColor = checkTheOtherColors(currentColor, idGame);
		System.out.println("next color");
		System.out.println(nextColor);
		if(nextColor == null) 
			return null;
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
	
	private PlayerDetailsDTO getById(Long id) {
		PlayerDetails details = playerDetailsDAO.findByIdPlayerDetailsIdPlayer(id);
		PlayerDetailsDTO dto = modelMapper.map(details, PlayerDetailsDTO.class);
		dto.setUsername(details.getUser().getUsername());
		return dto;
	}
	
	 private TileColorEnum checkTheOtherColors(TileColorEnum currentColor, Long idGame) {
	    	TileColorEnum[] colors = TileColorEnum.values();
	    	boolean currentChecked = false;
	    	//check next colors
	    	for(TileColorEnum color: colors) {
	    		if(currentChecked) {
	    			if(boardService.hasMove(color, idGame))
	        			return color;
	    		} else {
	    			if(color.equals(currentColor)) {
	        			currentChecked = true;
	        		}
	    		}	
	    	}
	    	//check up to color
	    	for(TileColorEnum color: colors) {
	    		if(currentChecked) {
	    			if(boardService.hasMove(color, idGame))
	        			return color;
	    		}
	    	}
	    return null;
	    }

}
