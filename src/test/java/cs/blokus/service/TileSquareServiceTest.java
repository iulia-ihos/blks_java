package cs.blokus.service;

import static org.junit.Assert.assertEquals;

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

import cs.blokus.dao.TileSquareDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class TileSquareServiceTest {


    @Autowired
    private ITileSquareService tileSquareService;
    
    @Autowired
    private ITileService tileService;
    
    @Autowired 
    private TileSquareDAO tileSquareDAO;
	
	@Test
	@DatabaseSetup(value = "/empty.xml")
	public void createTileSquares_EmptyTable_EightyNineTileSquaresAdded() {
		tileService.getAll();
		tileSquareService.createAll();
		
		assertEquals(tileSquareDAO.count(), 89);
	
	}
	
}
