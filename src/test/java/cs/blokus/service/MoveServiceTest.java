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

import cs.blokus.dao.MoveDAO;
import cs.blokus.dao.TilePositionDAO;
import cs.blokus.dto.GameDTO;
import cs.blokus.dto.MoveDTO;
import cs.blokus.dto.TileDTO;
import cs.blokus.dto.TilePositionDTO;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.service.IMoveService;
import cs.blokus.service.ITileGameService;
import cs.blokus.service.ITileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class MoveServiceTest {

	@Autowired
	private ITileGameService tileGameService;

	@Autowired
	private ITileService tileService;

	@Autowired
	private TilePositionDAO tilePositionDAO;

	@Autowired
	private MoveDAO moveDAO;

	@Autowired
	private IMoveService moveService;

	@Test
	@DatabaseSetup(value = "/datasets/moves.xml")
	public void testCreateMove() {
		tileGameService.createTileForGame(1L);
		TilePositionDTO pos = new TilePositionDTO(0, 0, 0, false, false);
		GameDTO game = new GameDTO(1L);
		TileDTO tile = new TileDTO(2L);

		MoveDTO moveDTO = new MoveDTO(game, tile, pos);
		moveService.createMove(moveDTO);
		assertEquals(2, tilePositionDAO.count());
		assertEquals(3, moveDAO.count());
		assertEquals(1, tileService.getAvailableForGame(1L, TileColorEnum.red).size());
	}
	
	@Test
	@DatabaseSetup(value = "/datasets/moves.xml")
	public void testGetMoves() {
		assertEquals(1, moveService.getMoveForGame(1L).size());
	}

}
