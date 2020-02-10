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

import cs.blokus.dao.PlayerDetailsDAO;
import cs.blokus.dto.PlayerDTO;
import cs.blokus.dto.PlayerDetailsDTO;
import cs.blokus.enums.TileColorEnum;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class PlayerDetailsServiceTest {

	@Autowired
    private IPlayerDetailsService playerDetailsService;	
	
	@Autowired
    private PlayerDetailsDAO playerDetailsDAO;	
	
	@Test
	@DatabaseSetup(value = "/player.xml")
	public void testCreateThenUpdateScore() {
		PlayerDTO playerDTO = new PlayerDTO(0L, 1L);
		PlayerDetailsDTO playerDetailsDTO = playerDetailsService.create(new PlayerDetailsDTO(1L, "user", TileColorEnum.red, 0), playerDTO);
		assertEquals(0, playerDetailsDTO.getPoints());
		playerDetailsService.updateScore(playerDetailsDTO.getIdPlayer(), 5);
		assertEquals(5, playerDetailsDAO.findByIdPlayerDetailsIdPlayer(playerDetailsDTO.getIdPlayer()).getPoints());
	}

}
