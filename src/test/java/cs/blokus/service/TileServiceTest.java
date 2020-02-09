package cs.blokus.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import cs.blokus.dto.GameDTO;
import cs.blokus.dto.TileDTO;
import cs.blokus.enums.TileColorEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class TileServiceTest {


    @Autowired
    private ITileService tileService;
    
    @Autowired
    private IGameService gameService;
	
	
	@Test
	@DatabaseSetup(value = "/empty.xml")
	public void testGetTiles() {
		List<TileDTO> tiles = tileService.getAll();
	    assertNotNull(tiles);
	    assertEquals(84, tiles.size());
	    for(TileDTO tile: tiles) {
	    	assertEquals(tile.getTileDetails().getNumberSquares(), tile.getTileDetails().getTileSquares().size());
	    }
	}
	
	@Test
	@DatabaseSetup(value = "/empty.xml")
	public void testGetAvailableTiles() {
		tileService.getAll();
		gameService.create(new GameDTO(1L));
	    assertEquals(21, tileService.getAvailableForGame(1L, TileColorEnum.red).size());
	}
}
