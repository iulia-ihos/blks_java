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

import cs.blokus.dao.BoardDAO;
import cs.blokus.dao.GameDAO;
import cs.blokus.dto.GameDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class GameServiceTest {


	@Autowired
	private BoardDAO boardDAO;

	@Autowired
	private GameDAO gameDAO;

	@Autowired
	private IGameService gameService;

	@Test
	@DatabaseSetup(value = "/datasets/empty.xml")
	public void testCreate() {
		gameService.create(new GameDTO(2L));
		assertEquals(1L, gameDAO.findAll().get(0).getIdGame().longValue());

		assertEquals(1L, boardDAO.findAll().get(0).getIdBoard().longValue());
	}

}
