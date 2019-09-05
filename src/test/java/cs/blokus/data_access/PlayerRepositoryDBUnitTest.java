package cs.blokus.data_access;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

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
import cs.blokus.entity.Player;
import cs.blokus.entity.PlayerId;
import cs.blokus.enums.TileColorEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class PlayerRepositoryDBUnitTest {


    @Autowired
    private PlayerDAO playerDAO;
	
	@DatabaseSetup(value = "/datasets/data.xml")
	@Test
	public void givenPlayer_whenSaveAndRetreiveEntity_thenOK() {
	    PlayerId playerId  = new PlayerId(1L, 1L);
	    Player player = playerDAO.save(new Player(playerId, TileColorEnum.blue, 0));
	    Optional<Player> foundEntity = playerDAO.findById(player.getPlayer());
	    foundEntity.get();
	    //assertThat(player);
	    assertNotNull(foundEntity);
	    assertEquals( foundEntity.get().getPlayer(), player.getPlayer());
	}
}
