package cs.blokus.controller.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {
	
	@MessageMapping("/game")
	@SendTo("/game/move")
	public Message send(Message message) throws Exception {
	   System.out.println(message);
	   message.setFrom("iulia");
	    return message;
	}
}
