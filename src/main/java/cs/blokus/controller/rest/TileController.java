package cs.blokus.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs.blokus.dto.TileDTO;
import cs.blokus.service.ITileService;

@RestController
@RequestMapping("tiles")
public class TileController {
	
	@Autowired
	private ITileService tileService;
	

	
	@GetMapping()
	public List<TileDTO> getAll() {
	       return tileService.getAll();	
	}

}
