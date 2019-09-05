package cs.blokus.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.PlayerDAO;
import cs.blokus.dto.PlayerDTO;
import cs.blokus.entity.Player;
import cs.blokus.entity.PlayerId;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.model_mapping.ModelMapping;
import cs.blokus.service.IPlayerService;
import cs.blokus.service.IUserService;

@Component
public class PlayerServiceImpl implements IPlayerService {
	
	@Autowired
	private ModelMapping modelMapper;

	@Autowired
	private PlayerDAO playerDAO;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public PlayerDTO create(PlayerDTO playerDTO) {
		Player player = (Player) modelMapper.map(playerDTO, Player.class);
		return modelMapper.map(playerDAO.save(player), PlayerDTO.class);
	}
	
	

	@Override
	public PlayerDTO updateScore(PlayerId id, int points) {
		playerDAO.updateScore(id, points);
		return getById(id);
	}

	@Override
	public PlayerDTO getById(PlayerId id) {
		Optional<Player> optional = playerDAO.findById(id);
		try{
			Player player = optional.get();
			return modelMapper.map(player, PlayerDTO.class);
		} catch(Exception e) {
			return null;
		}
	}



	@Override
	public PlayerDTO create(Long idGame, String username, TileColorEnum color) {
		Long idUser = userService.findByUsername(username).getIdUser();
		PlayerId id = new PlayerId(idUser, idGame);
		PlayerDTO player = new PlayerDTO(id, color, 0);
		Player returnedPlayer = this.playerDAO.save(modelMapper.map(player, Player.class));
		
		return modelMapper.map(returnedPlayer, PlayerDTO.class);
	}


}
