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

import cs.blokus.dao.SquareDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class SquareServiceTest {


    @Autowired
    private ISquareService squareService;
    
    @Autowired 
    private SquareDAO squareDAO;
	
	@Test
	@DatabaseSetup(value = "/empty.xml")
	public void createSquares_EmptyTable_ElevenSquaresAdded() {
		
		squareService.createSquares().size();
		
		assertEquals(squareDAO.count(), 11);
	
	}
	
	@Test
	@DatabaseSetup(value = "/squares.xml")
	public void createSquares_PopulatedTable_DataDroppedAndElevenSquaresAdded() {
		
		squareService.createSquares().size();
		
		assertEquals(squareDAO.count(), 11);
	
	}
}
