package cs.blokus.data_access;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import cs.blokus.dao.GameDAO;
import cs.blokus.dao.PlayerDAO;
import cs.blokus.dao.PlayerDetailsDAO;
import cs.blokus.entity.Game;
import cs.blokus.entity.Player;
import cs.blokus.entity.PlayerDetails;
import cs.blokus.entity.User;
import cs.blokus.enums.TileColorEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class PlayerRepositoryDBUnitTest {


    @Autowired
    private PlayerDAO playerDAO;
    
    @Autowired
    private GameDAO gameDAO;
    
    @Autowired
    private PlayerDetailsDAO playersDetailsDAO;
	
	@DatabaseSetup(value = "/datasets/data.xml")
	@Test
	public void givenPlayer_whenSaveAndRetreiveEntity_thenOK() {
		Game game = gameDAO.findById(1L).get();
		User user =  new User();
		user.setUsername("user1");
		PlayerDetails details = playersDetailsDAO.save(new PlayerDetails(0L, user, TileColorEnum.red, 0));
	    Player player = playerDAO.save(new Player(game, details));
	    Player foundEntity = playerDAO.findById(player.getIdPlayer()).get();
	   
	    assertNotNull(foundEntity);
	    assertEquals( foundEntity.getGame(), player.getGame());
	}
}
