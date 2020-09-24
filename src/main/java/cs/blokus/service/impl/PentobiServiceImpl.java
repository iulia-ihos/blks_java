package cs.blokus.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dto.MoveDTO;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.pentobi.PentobiEngine;
import cs.blokus.pentobi.PentobiGame;
import cs.blokus.pentobi.utils.CoordinatesConverter;
import cs.blokus.service.IPentobiService;

@Component
public class PentobiServiceImpl implements IPentobiService{
	
	@Autowired 
	private CoordinatesConverter converter;
	
	private static final Logger LOGGER = Logger.getLogger(PentobiServiceImpl.class.getName());
	
	private Map<Long, PentobiGame> games;
	
	public PentobiServiceImpl() {
		games = new HashMap<>();
	}

	@Override
	public void start(Long idGame) {
		if(games.get(idGame) == null) {
			PentobiEngine engine = PentobiEngine.getInstance();
			PentobiGame game = new PentobiGame(engine.getProcess());
			games.put(idGame, game);	
		}
	}

	
	private void readResponse(Long idGame) {
		PentobiGame game = games.get(idGame);
		BufferedReader reader = game.getReader();
		new Thread() {
			public void run() {
				try {
					String data;
					while ((data = reader.readLine()) != null) {
						LOGGER.info("[Pentobi] " + data);
						if(data.length() == 0)
							continue;
						game.addResponse(data);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}.start();
	}
	
	private void readError(Long idGame) {
		PentobiGame game = games.get(idGame);
		BufferedReader reader = game.getErrorReader();
		new Thread() {
			public void run() {
				try {
					String data;
					while ((data = reader.readLine()) != null) {
						LOGGER.warning("[Pentobi] " + data);
						game.addResponse(data);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}.start();
	}
	
	public String waitForAnswer(Long idGame) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = games.get(idGame).removeResponse();

		return data;
	}

	@Override
	public boolean writePlayCommand(Long idGame, TileColorEnum color, MoveDTO move) {
		if(games.get(idGame) == null)
			start(idGame);
		int player = color.ordinal() + 1;
		String moveString = converter.getMoveString(move);
		String command = "play " + player + " " +  moveString; 
		readResponse(idGame);
		readError(idGame);
		sendCommand(idGame, command);
		String response = waitForAnswer(idGame);
		System.out.println("why : " + response);
		StringBuilder sb = new StringBuilder();
		sb.append('=');
		if (response.contains(sb))
			return true;
		return false;
			
		
	}

	@Override
	public String writeGenMoveCommand(Long idGame, TileColorEnum color, boolean playMove) {
		if(games.get(idGame) == null)
			start(idGame);
		int player = color.ordinal() + 1;
		String command = ""; 
		if(playMove) {
			command += "genmove ";
		}
		else
			command += "reg_genmove ";
		command += player;
		readResponse(idGame);
		readError(idGame);
		sendCommand(idGame, command);
		return waitForAnswer(idGame);
		
	}
	
	@Override
	public void endPentobiGame(Long idGame) {
		PentobiGame game = games.get(idGame);
		if(game==null)
			return;
		if(game.getProcess()==null)
			return;
	
		game.getProcess().destroy();
			
	}
	
	private void sendCommand(Long idGame, String command) {
		PentobiGame game = games.get(idGame);
		BufferedWriter writer = game.getWriter();
		System.out.println( command);
		try {
			writer.write(command + "\n");
			writer.flush();
			//writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
