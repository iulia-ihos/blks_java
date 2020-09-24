package cs.blokus.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
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

import cs.blokus.dao.MoveDAO;
import cs.blokus.dto.MoveDTO;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.model_mapping.ModelMapping;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
public class PentobiServiceTest {
	
	@Autowired
	private IPentobiService pentobiService;


	@Autowired
	private MoveDAO moveDAO;

	@Autowired
	private ModelMapping mapper;

	@Before
	public void init() {
		pentobiService.start(1L);
	}

	@After
	public void finalize() {
		pentobiService.endPentobiGame(1L);
	}

	
	@Test
	public void testGenMoveCommand() {
		pentobiService.writeGenMoveCommand(1L, TileColorEnum.red, true);
		assertNotEquals("= pass", pentobiService.writeGenMoveCommand(1L, TileColorEnum.red, true));
		assertNotEquals("= pass", pentobiService.writeGenMoveCommand(1L, TileColorEnum.red, false));
	}
	
	@Test
	@DatabaseSetup(value = "/moves.xml")
	public void testPlayCommand() {
		MoveDTO  move = mapper.map(moveDAO.getForGame(2L).get(0), MoveDTO.class);
		assertEquals(true, pentobiService.writePlayCommand(1L, TileColorEnum.red, move));
		move = mapper.map(moveDAO.getForGame(1L).get(0), MoveDTO.class);
		assertEquals(false, pentobiService.writePlayCommand(1L, TileColorEnum.red, move));
	}
}
