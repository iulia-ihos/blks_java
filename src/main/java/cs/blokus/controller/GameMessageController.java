package cs.blokus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import cs.blokus.dto.MoveDTO;
import cs.blokus.dto.PlayerDTO;
import cs.blokus.dto.PlayerDetailsDTO;
import cs.blokus.messages.InviteMessage;
import cs.blokus.messages.JoinMessage;
import cs.blokus.messages.MoveMessage;
import cs.blokus.messages.PlayersMessage;
import cs.blokus.service.IBoardService;
import cs.blokus.service.IMoveService;
import cs.blokus.service.IPlayerDetailsService;
import cs.blokus.service.IPlayerService;
import cs.blokus.service.ITileGameService;
import cs.blokus.service.ITileService;
import cs.blokus.service.IUserService;

@Controller
public class GameMessageController {
	
	 @Autowired
	 private SimpMessagingTemplate messagingTemplate;
	 
	 @Autowired
	 private IPlayerService playerService;
	 
	 @Autowired
	 private IPlayerDetailsService playerDetailsService;
	 
	 @Autowired 
	 private IMoveService moveService;
	 
	 @Autowired 
	 private IBoardService boardService;
	 
	 
	 
	
	@MessageMapping("/invite")
	public void sendInvite(InviteMessage message) {
		messagingTemplate.convertAndSendToUser(message.getUsername(), "/game/invite" , message);
	}
	
	@MessageMapping("/join")
	public void sendJoin(JoinMessage message) {
		messagingTemplate.convertAndSendToUser(message.getSendTo(), "/game/join", message);
	}
	
	@MessageMapping("/players")
	public void startGame(PlayersMessage message) {
		List<PlayerDTO> players = message.getPlayers();
		List<PlayerDTO> finalPlayers = new ArrayList<>();
		for(PlayerDTO player: players) {
			finalPlayers.add(playerService.create(player));
		}
		
		PlayersMessage playersMessage = new PlayersMessage(finalPlayers);
	
		for(PlayerDTO player: players) {
			messagingTemplate.convertAndSendToUser(player.getPlayerDetails().getUsername(), "/game/start", playersMessage);
		}
	
	}
	
	@MessageMapping("/move")
	
	public void sendNextMove(MoveMessage message) {
		MoveDTO move = moveService.createMove(message.getMove());
		boardService.addToBoard(message.getMove().getTile().getColor(), message.getBoardPosition(), 
				message.getMove().getGame().getIdGame());
		PlayerDetailsDTO current = playerDetailsService.updateScore(message.getCurrentPlayer().getIdPlayerDetails(),
				move.getTile().getTileDetails().getNumberSquares());
		PlayerDetailsDTO next = playerDetailsService.getNextPlayer(current.getColor(), move.getGame().getIdGame());
		
		MoveMessage moveMessage = new MoveMessage(move, current, next);
		
		List<PlayerDetailsDTO> players = this.playerDetailsService.getPlayersDetailsForGame(move.getGame().getIdGame()); 
		
		for(PlayerDetailsDTO player: players) {
			messagingTemplate.convertAndSendToUser(player.getUsername(), "/game/move", moveMessage);
		}
		

	}
}
