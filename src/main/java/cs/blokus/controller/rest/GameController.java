package cs.blokus.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs.blokus.dto.GameDTO;
import cs.blokus.service.IGameService;

@RestController
@RequestMapping("game")
public class GameController {
	
	@Autowired
	private IGameService gameService;
	
	@PutMapping("start")
	public GameDTO updateStartTime(@RequestBody GameDTO game) {
	       return gameService.updateStartTime(game.getStartTime(), game.getIdGame());
	    }

	@PutMapping("end")
	public GameDTO updateEndTime(@RequestBody GameDTO game) {
	       return gameService.updateEndTime(game.getEndTime(), game.getIdGame());
	    }
	
	@PutMapping("status")
	public GameDTO updateStatus(@RequestBody GameDTO game) {
	       return gameService.updateStatus(game.getStatus(), game.getIdGame());
	    }
	
	@PostMapping
	public GameDTO add(@RequestBody GameDTO game) {
    	System.out.println(game);
    	return gameService.create(game);	
	}
	
	@GetMapping("/{id}")
	public GameDTO getById(@PathVariable Long id) {
	       return gameService.getById(id);	
	}

}
