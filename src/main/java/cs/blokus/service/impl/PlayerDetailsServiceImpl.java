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
import cs.blokus.service.IBoardService;
import cs.blokus.service.IPlayerDetailsService;

@Component
public class PlayerDetailsServiceImpl implements IPlayerDetailsService{
	
	@Autowired
	private ModelMapping modelMapper;

	@Autowired
	private PlayerDetailsDAO playerDetailsDAO;
	
	
	@Autowired
	private UserDAO userDAO;
	
	 @Autowired 
	 private IBoardService boardService;
	
	
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
		TileColorEnum nextColor = null;
		TileColorEnum[] checkColors = new TileColorEnum[4];
//		switch(currentColor) {
//			case red: 
//				checkColors[0] = TileColorEnum.green;
//				checkColors[1] = TileColorEnum.green;
//				checkColors[2] = TileColorEnum.green;
//				checkColors[3] = TileColorEnum.green;
//				nextColor = checkTheNextColors(checkColors, idGame);		
//				break;
//			case green: 
//				if(boardService.hasMove(TileColorEnum.yellow, idGame))
//					nextColor = TileColorEnum.yellow;
//				break;
//			case yellow: 
//				if(boardService.hasMove(TileColorEnum.blue, idGame))
//					nextColor = TileColorEnum.blue;
//				break;
//			case blue: 
//				if(boardService.hasMove(TileColorEnum.red, idGame))
//					nextColor = TileColorEnum.red;
//				break;
//			
//		}
		nextColor = checkTheRestOfColors(currentColor, idGame);
		
		if(nextColor == null) 
			return null;
		PlayerDetails details = playerDetailsDAO.getPlayer(nextColor, idGame);
		PlayerDetailsDTO dto =  modelMapper.map(details, PlayerDetailsDTO.class);
		dto.setUsername(details.getUser().getUsername());
		return dto;
	}

    private TileColorEnum checkTheNextColors(TileColorEnum[] checkColor, Long idGame) {
    	for(TileColorEnum color: checkColor) {
    		if(boardService.hasMove(color, idGame))
    			return color;
    	}
    return null;
    }
    
    private TileColorEnum checkTheRestOfColors(TileColorEnum checkColor, Long idGame) {
    	TileColorEnum[] colors = TileColorEnum.values();
    	boolean check = false;
    	//check next after color
    	for(TileColorEnum color: colors) {
    		if(check) {
    			if(boardService.hasMove(color, idGame))
        			return color;
    		} else {
    			if(color.equals(checkColor)) {
        			check = true;
        		}
    		}	
    	}
    	//check up to color
    	for(TileColorEnum color: colors) {
    		if(check) {
    			if(boardService.hasMove(color, idGame))
        			return color;
    			if(color.equals(checkColor)) {
        			check = false;
        		}
    		}
    	}
    return null;
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
