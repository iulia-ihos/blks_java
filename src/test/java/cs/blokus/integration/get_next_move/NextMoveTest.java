package cs.blokus.integration.get_next_move;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import cs.blokus.dao.CornerDAO;
import cs.blokus.dao.MoveDAO;
import cs.blokus.dao.TileDAO;
import cs.blokus.dao.TileDetailsDAO;
import cs.blokus.dao.TileGameDAO;
import cs.blokus.dto.BoardPosition;
import cs.blokus.dto.GameDTO;
import cs.blokus.dto.MoveDTO;
import cs.blokus.dto.Position;
import cs.blokus.dto.TileDTO;
import cs.blokus.dto.TilePositionDTO;
import cs.blokus.entity.TileDetails;
import cs.blokus.entity.TileSquare;
import cs.blokus.entity.TileVariations;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.service.IBoardService;
import cs.blokus.service.IMoveService;
import cs.blokus.service.IPlayerDetailsService;
import cs.blokus.service.ITileGameService;
import cs.blokus.service.ITileService;
import cs.blokus.service.ITileVariationsService;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class NextMoveTest {
	
	@Autowired
    private IMoveService moveService;
	
	@Autowired
    private ITileService tileService;
	
	@Autowired
    private ITileVariationsService tileVariationsService;
	
	@Autowired
    private IPlayerDetailsService playerDetailsService;		
	
	@Autowired
    private IBoardService boardService;	
	
	@Autowired
    private TileDetailsDAO tileDetailsDAO;	
	
	@Autowired
    private TileDAO tileDAO;	
	
	@Autowired
    private ModelMapper mapper;	
	
	@Autowired
    private ITileGameService tileGameService;
	
	@Autowired
    private TileGameDAO tileGameDAO;
	
	@Autowired
    private MoveDAO moveDAO;
	
	@Autowired
    private CornerDAO cornerDAO;
	
	@Before
	public void setup() {
		tileService.getAll();
		tileGameService.createTileForGame(1L);	
	}
	
	@After
	public void cleanUp() {
		cornerDAO.deleteAll();
		moveDAO.deleteAll();
		tileGameDAO.deleteAll();
	}
	
	@Test
	@DatabaseSetup("/setup.xml")
	public void testGetNextMove_GameOver_ShouldReturnNull() {
		String filename = "src/test/java/cs/blokus/integration/get_next_move/finishedTest.blksgf";
		tileService.getAll();
		addMovesFromFile(filename);
		assertNull(playerDetailsService.getNextPlayer(TileColorEnum.green, 1L));
	
		
	}
	
	@Test
	@DatabaseSetup("/setup.xml")
	public void testGetNextMove_GreenYellowBlueOut_ShouldReturnRed() {
		String filename = "src/test/java/cs/blokus/integration/get_next_move/player_2_3_4_out.blksgf";
		
		addMovesFromFile(filename);
		assertEquals(TileColorEnum.red, playerDetailsService.getNextPlayer(TileColorEnum.yellow, 1L).getColor());
		assertEquals(TileColorEnum.red, playerDetailsService.getNextPlayer(TileColorEnum.green, 1L).getColor());
		assertEquals(TileColorEnum.red, playerDetailsService.getNextPlayer(TileColorEnum.blue, 1L).getColor());
		
	}
	
	@Test
	@DatabaseSetup("/setup.xml")
	public void testGetNextMove_GreenYellowOut() {
		String filename = "src/test/java/cs/blokus/integration/get_next_move/player_2_3_out.blksgf";
		addMovesFromFile(filename);
		assertEquals(TileColorEnum.blue, playerDetailsService.getNextPlayer(TileColorEnum.yellow, 1L).getColor());
		assertEquals(TileColorEnum.blue, playerDetailsService.getNextPlayer(TileColorEnum.red, 1L).getColor());
		assertEquals(TileColorEnum.red, playerDetailsService.getNextPlayer(TileColorEnum.blue, 1L).getColor());
		
		
	}
	
	private void addMovesFromFile(String filename) {
		 try {
		      File myObj = new File(filename);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		       
		        if(data.charAt(0) == ';' && Character.isDigit(data.charAt(1))) {
		        	 System.out.println(data);
		        	 moveService.createMove(getMove(data));
		        }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	private MoveDTO getMove(String line) {
		MoveDTO move = new MoveDTO();
		String tile = line.substring(3, line.length()-1);
		String[] squares = tile.split(",");
		int[][] coords = new int[squares.length][2];
		int leftMost = 19;
		int topMost = 19;
		
		for(int  i = 0; i < squares.length; i++) {
			String s = squares[i];
			int top = 20 - Integer.parseInt(s.substring(1));
			int left =  s.charAt(0) -'a';
			coords[i][0] = top;
			coords[i][1] = left;
			if(top < topMost) 
				topMost = top;
			if(left < leftMost)
				leftMost = left;
		}
		for(int  i = 0; i < squares.length; i++) {
			coords[i][0] = coords[i][0] - topMost;
			coords[i][1] = coords[i][1] - leftMost;
		}
		move.setGame(new GameDTO(1L));
		List<Position> pos = new ArrayList<>();
		print(coords);
		TileDTO t = findTile(coords, findColor(line.charAt(1)));
		if(t != null) {
			System.out.println(t.getTileDetails().getName());
			move.setTile(t);
			move.setPosition(new TilePositionDTO(0L, topMost, leftMost, 0, false, false));
			int tm = topMost;
			int lm = leftMost;
			t.getTileDetails().getTileSquares().stream().forEach(ts -> {
				pos.add(new Position(ts.getSquare().getTop() + tm, ts.getSquare().getLeft()+lm));
			});
		}
		else {
			TileVariations var = findTileVariation(coords);
			System.out.println(" var " + var.getTileDetails().getName());
			move.setTile(mapper.map(tileDAO.findByColorAndTileDetailsName(findColor(line.charAt(1)),
					var.getTileDetails().getName()), TileDTO.class));
			move.setPosition(new TilePositionDTO(0L, topMost, leftMost, var.getAngle(),
					var.isFlipH(), var.isFlipV()));
			int[][] m = var.getTile();
			for(int i = 0; i < m.length; i++)
				for(int j = 0; j < m[0].length; j++){
					if(m[i][j] == 1)
						pos.add(new Position(i + topMost, j + leftMost));
				}
		}
		System.out.println(pos);
		boardService.addToBoard(findColor(line.charAt(1)), new BoardPosition(pos), 1L);
		
		return move;
	}
	
	private TileColorEnum findColor(char c) {
		switch(c) {
			case '1': 
				return TileColorEnum.red;
			case '2': 
				return TileColorEnum.green;
			case '3': 
				return TileColorEnum.yellow;
			case '4': 
				return TileColorEnum.blue;
		}
		return null;
	}
	
	
	
	private TileDTO findTile(int[][] coords, TileColorEnum color) {
		List<TileDetails> tiles = tileDetailsDAO.findAll();
		for(TileDetails tile: tiles) { 
			boolean mismatched = false;

			List<TileSquare> squares = tile.getTileSquares();
			if(squares.size() != coords.length)
				continue;
			for(TileSquare square: squares) {
				boolean squareMatched = false;
//				System.out.println("square b_ _ _ _ _ _ _ _");
//				System.out.println(square.getSquare().getTop());
//				System.out.println(square.getSquare().getLeft());
//				System.out.println("square e_ _ _ _ _ _ _ _");
				for(int i = 0; i<coords.length; i++) {
//					System.out.println(coords[i][0]);
//					System.out.println(coords[i][1]);
					if(coords[i][0] == square.getSquare().getTop() 
							&& coords[i][1] == square.getSquare().getLeft()) {
						squareMatched = true;
						break;
					}	
 
				}
				if(!squareMatched) {
					mismatched = true;
					break;
				}
			};
			if(!mismatched) {
				return mapper.map(tileDAO.findByColorAndTileDetailsName(color, tile.getName()),TileDTO.class);
			}
		};

		return null;
		
	}
	
	private TileVariations findTileVariation(int[][] coords) {
		
		TileVariations foundTile = null;
		List<TileDTO> tiles = tileService.getAll();
		for(TileDTO tile: tiles) {
			boolean found = false;
			//System.out.println(tile.getTileDetails().getName());
			if(found)
				break;
			List<TileVariations> var = tileVariationsService.getForTile(tile.getTileDetails().getName());
			//System.out.println(var.size());
			for(TileVariations v: var) {
				boolean wrongVar = false;
				int[][] tileVar = v.getTile();
				if(v.getTileDetails().getNumberSquares() != coords.length)
					continue;
				for(int i = 0; i<coords.length; i++) {
					if(coords[i][0]>(tileVar.length-1) || coords[i][1]>(tileVar[0].length-1)) {
						wrongVar = true;
						break;
					}	
					if(tileVar[coords[i][0]][coords[i][1]] == 1)
						continue;
					else {
						wrongVar = true;
						break;
					}	
				}
				if(!wrongVar) {
					found = true;
					foundTile = v;
					break;
				}
			};
		};

		return foundTile;
		
	}
	
	private void print(int[][] m) {
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[0].length; j++)
				System.out.print(m[i][j]+ " ");
			System.out.println();
		}
	}

}
