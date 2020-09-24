package cs.blokus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import cs.blokus.dao.GameDAO;
import cs.blokus.dto.MoveDTO;
import cs.blokus.dto.PlayerDTO;
import cs.blokus.dto.PlayerDetailsDTO;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.messages.InviteMessage;
import cs.blokus.messages.JoinMessage;
import cs.blokus.messages.MoveMessage;
import cs.blokus.messages.PlayersMessage;
import cs.blokus.service.IBoardService;
import cs.blokus.service.IMoveService;
import cs.blokus.service.IPentobiService;
import cs.blokus.service.IPerformanceService;
import cs.blokus.service.IPlayerDetailsService;
import cs.blokus.service.IPlayerService;
import cs.blokus.service.impl.PentobiPlayerService;

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
	 
	 @Autowired 
	 private IPentobiService pentobiService;
	 
	 @Autowired 
	 private IPerformanceService performanceService;
	 
	 @Autowired
	 private GameDAO gameDAO;
	 
	 @Autowired 
	 private PentobiPlayerService pentobiPlayer;
	 
	
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
			if(player.getPlayerDetails().getUsername().equals("pentobi"))
				pentobiService.start(player.getGame().getIdGame());
			finalPlayers.add(playerService.create(player));
		}
		
		PlayersMessage playersMessage = new PlayersMessage(finalPlayers);
	
		for(PlayerDTO player: players) {
			messagingTemplate.convertAndSendToUser(player.getPlayerDetails().getUsername(), "/game/start", playersMessage);
		}
	
	}
	
	@MessageMapping("/move")
	public void sendNextMove(MoveMessage message) {
		PlayerDetailsDTO current = message.getCurrentPlayer();
		MoveDTO move = null;
		Long idGame = message.getMove().getGame().getIdGame();
		TileColorEnum color = message.getCurrentPlayer().getColor();
		if(message.getCurrentPlayer().getUsername().equals("pentobi") || message.getCurrentPlayer().getUsername().equals("ibotnep")) {
			System.out.println(" hello");
			if(message.getMove().getGame().isUsingPentobi())
				move = pentobiPlayer.getMove(message);
			else {
				move = boardService.getMove(color, idGame);
			}
			System.out.println(move);
		}
		else {
			if(message.getMove().getGame().isUsingPentobi())
				pentobiPlayer.setMove(message);
			System.out.println(" no");
			move = message.getMove();
			boardService.addToBoard(color, message.getBoardPosition(), idGame);
		}
		current = playerDetailsService.updateScore(message.getCurrentPlayer().getIdPlayer(),
				move.getTile().getTileDetails().getNumberSquares());
		move = moveService.createMove(move);
		PlayerDetailsDTO next = playerDetailsService.getNextPlayer(current.getColor(), idGame);
		List<PlayerDetailsDTO> players = this.playerDetailsService.getPlayersDetailsForGame(idGame); 
		if(next == null)
			performanceService.updateForGame(idGame);
		for(PlayerDetailsDTO player: players) {
			messagingTemplate.convertAndSendToUser(player.getUsername(), "/game/move", new MoveMessage(move, current, next));
		}
		
	}
}
