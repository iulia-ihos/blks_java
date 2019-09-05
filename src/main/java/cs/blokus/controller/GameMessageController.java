package cs.blokus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import cs.blokus.dto.PlayerDTO;
import cs.blokus.dto.TileDTO;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.messages.InviteMessage;
import cs.blokus.messages.JoinMessage;
import cs.blokus.messages.PlayersMessage;
import cs.blokus.messages.StartGameMessage;
import cs.blokus.service.IPlayerService;
import cs.blokus.service.ITileService;
import cs.blokus.service.IUserService;

@Controller
public class GameMessageController {
	
	 @Autowired
	 private SimpMessagingTemplate messagingTemplate;
	 
	 @Autowired
	 private IPlayerService playerService;
	 
	 @Autowired IUserService userService;
	 
	 @Autowired ITileService tileService;
	 
	
//	@MessageMapping("/game")
//	@SendTo("/game/move")
//	public Message send(Message message) throws Exception {
//	   System.out.println(message);
//	   Authentication a =  SecurityContextHolder.getContext().getAuthentication();
//	   message.setFrom(a.getName());
//	    return message;
//	}
	

	@MessageMapping("/invite")
	//@SendTo("/game/invite")
	public void sendInvite(InviteMessage message) {
		messagingTemplate.convertAndSendToUser(message.getUsername(), "/game/invite" , message);
	    //return message;
	}
	
	@MessageMapping("/join")
	public void sendJoin(JoinMessage message) {
		messagingTemplate.convertAndSendToUser(message.getSendTo(), "/game/join", message);
	}
	
	@MessageMapping("/players")
	public void startGame(PlayersMessage message) {
		List<PlayerDTO> players = new ArrayList<>();
		players.add(playerService.create(message.getIdGame(), message.getRedPlayer(), TileColorEnum.red));
		players.add(playerService.create(message.getIdGame(), message.getBluePlayer(), TileColorEnum.blue));
		players.add(playerService.create(message.getIdGame(), message.getGreenPlayer(), TileColorEnum.green));
		players.add(playerService.create(message.getIdGame(), message.getYellowPlayer(), TileColorEnum.yellow));
		List<TileDTO> tiles = tileService.getAll();
		
		StartGameMessage startMessage = new StartGameMessage(players, tiles);
		messagingTemplate.convertAndSendToUser(message.getBluePlayer(), "/game/start", startMessage);
	}
}
