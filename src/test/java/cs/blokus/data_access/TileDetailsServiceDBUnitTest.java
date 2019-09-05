package cs.blokus.data_access;

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

import cs.blokus.entity.TileDetails;
import cs.blokus.entity.TileSquare;
import cs.blokus.service.ITileDetailsService;
import cs.blokus.service.ITileSquareService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class TileDetailsServiceDBUnitTest {


    @Autowired
    private ITileSquareService tileSquareService;
    
    @Autowired
    private ITileDetailsService tileDetailsService;
	
	
	@Test
	@DatabaseSetup(value = "/tiles.xml")
	public void testSquares() {
		
		tileSquareService.createAll();
		List<TileDetails> tiles = tileDetailsService.create();
				
		for(TileDetails det: tiles) {
			
			List<TileSquare> squares = det.getTileSquares();
			assert(squares.size()!=0);
			
			assertNotNull(squares.get(0).getSquare());
		}
	
	}
}
