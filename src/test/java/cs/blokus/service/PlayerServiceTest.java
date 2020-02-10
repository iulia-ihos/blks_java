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

import cs.blokus.dao.PlayerDAO;
import cs.blokus.dao.PlayerDetailsDAO;
import cs.blokus.dto.GameDTO;
import cs.blokus.dto.PlayerDTO;
import cs.blokus.dto.PlayerDetailsDTO;
import cs.blokus.enums.TileColorEnum;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class PlayerServiceTest {

	@Autowired
    private IPlayerService playerService;
	
	@Autowired
	private PlayerDAO playerDAO;
	
	@Autowired
	private PlayerDetailsDAO playerDetailsDAO;
	
	
	@Test
	@DatabaseSetup(value = "/player.xml")
	public void testCreate() {
	PlayerDTO player = playerService.create(new PlayerDTO(0L, new PlayerDetailsDTO(1L, "user", TileColorEnum.red, 0), new GameDTO(1L)));
	
	assertEquals("user", player.getPlayerDetails().getUsername());
	assertEquals(1L, playerDAO.findAll().get(0).getIdPlayer().longValue());
	assertEquals(1L, playerDetailsDAO.findAll().get(0).getIdPlayerDetails().getIdPlayer().longValue());
	
	}

}
