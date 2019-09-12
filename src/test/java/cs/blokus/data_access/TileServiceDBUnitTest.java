package cs.blokus.data_access;

import static org.junit.Assert.*;

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

import cs.blokus.dto.TileDTO;
import cs.blokus.service.ITileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class TileServiceDBUnitTest {


    @Autowired
    private ITileService tileService;
	
	
	@Test
	@DatabaseSetup(value = "/tiles.xml")
	public void createTiles() {
		
		List<TileDTO> tiles = tileService.getAll();
	
	    assertNotNull(tiles);
	    assertEquals(84, tiles.size());
	    
	    for(TileDTO tile: tiles) {
	    	assertEquals(tile.getTileDetails().getNumberSquares(), tile.getTileDetails().getTileSquares().size());
	    }
	}
}
