package cs.blokus.test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
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

import cs.blokus.dto.BoardPosition;
import cs.blokus.dto.GameDTO;
import cs.blokus.dto.Position;
import cs.blokus.dto.TileDTO;
import cs.blokus.enums.GameStatusEnum;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.service.IBoardService;
import cs.blokus.service.ICornerService;
import cs.blokus.service.IGameService;
import cs.blokus.service.IMoveService;
import cs.blokus.service.ITileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class NextPlayerTest {

	@Autowired
	private IBoardService boardService;

	@Autowired
	private IMoveService moveService;
	
	@Autowired
	private IGameService gameService;

	@Autowired
	private ICornerService cornerService;
	
	@Autowired
	private ITileService tileService;
	

	@Test
	@DatabaseSetup(value = "/tiles.xml")
	public void testGameOver() {
		List<TileDTO> tiles = tileService.getAll();
		System.out.println(tiles);
		GameDTO game = gameService.create(new GameDTO(0L, GameStatusEnum.PENDING));
		
		List<Position> coords = new ArrayList<>();
		coords.add(new Position(0,0));
		coords.add(new Position(1,0));
		coords.add(new Position(2,0));
		coords.add(new Position(1,1));
		BoardPosition pos = new BoardPosition(coords);
		
		System.out.println("b");
		System.out.println(cornerService.getCornersForColor(TileColorEnum.red, game.getIdGame()));
		boardService.addToBoard(TileColorEnum.red, pos, game.getIdGame());
		System.out.println("a");
		System.out.println(cornerService.getCornersForColor(TileColorEnum.red, game.getIdGame()));
		assertEquals(3, cornerService.getCornersForColor(TileColorEnum.red, game.getIdGame()).size());
		
		assertEquals(true , boardService.hasMove(TileColorEnum.red, game.getIdGame()));
		
		coords = new ArrayList<>();
		coords.add(new Position(2,2));
		coords.add(new Position(3,2));
		coords.add(new Position(4,2));
		coords.add(new Position(5,2));
		coords.add(new Position(3,3));
		pos = new BoardPosition(coords);
		
		boardService.addToBoard(TileColorEnum.red, pos, game.getIdGame());
		System.out.println("a");
		System.out.println(cornerService.getCornersForColor(TileColorEnum.red, game.getIdGame()));
		assertEquals(6, cornerService.getCornersForColor(TileColorEnum.red, game.getIdGame()).size());
		
	}
}
