package cs.blokus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import cs.blokus.dao.BoardDAO;
import cs.blokus.dao.PlayerDAO;
import cs.blokus.dao.PlayerDetailsDAO;
import cs.blokus.dto.BoardPosition;
import cs.blokus.dto.GameDTO;
import cs.blokus.dto.MoveDTO;
import cs.blokus.dto.Position;
import cs.blokus.dto.TileDTO;
import cs.blokus.dto.TilePositionDTO;
import cs.blokus.entity.Board;
import cs.blokus.entity.Corner;
import cs.blokus.entity.Game;
import cs.blokus.entity.Player;
import cs.blokus.entity.TileVariations;
import cs.blokus.enums.CornerType;
import cs.blokus.enums.TileColorEnum;
import cs.blokus.enums.TileNameEnum;
import cs.blokus.messages.HintMessage;
import cs.blokus.service.IBoardService;
import cs.blokus.service.ICornerService;
import cs.blokus.service.ITileService;
import cs.blokus.service.ITileVariationsService;

@Component
public class BoardServiceImpl implements IBoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private ITileService tileService;
	
	@Autowired
	private PlayerDetailsDAO playerDetailsDAO;
	
	@Autowired
	private PlayerDAO playerDAO;
	
	@Autowired
	private ICornerService cornerService;
	
	@Autowired
	private ITileVariationsService tileVariationsService;
	
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
	

	private int[][] board;
	private List<TileDTO> availableTiles;

	@Override
	public MoveDTO getMove(TileColorEnum color, Long idBoard) {
		GameDTO game = new GameDTO(idBoard);
		Player player = playerDAO.getPlayerForGameAndColor(idBoard, color);
		if(player.isOut())
			return null;
		this.board = boardDAO.findByIdBoard(idBoard).getBoard();
		Position c = hasEmptyCorner();
		if( c != null) {
			List<Position> coords = new ArrayList<>();
			coords.add(c);
			addToBoard(color, new BoardPosition(coords), idBoard);
			return new MoveDTO(game, tileService.getForColorAndName(color, TileNameEnum.I),
					new TilePositionDTO(c.getTop(), c.getLeft(), 0, false, false));
		}
			
		List<Corner> corners = cornerService.getCornersForColor(color, idBoard);
        System.out.println(corners);
		this.availableTiles = tileService.getAvailableForGame(idBoard, color);
		System.out.println("available " + availableTiles);
		for(Corner corner: corners) {
			for(TileDTO tile: availableTiles) {
				TileVariations var  = checkVariations(corner, tileVariationsService.getForTile(tile.getTileDetails().getName()), true, idBoard);

				if(var != null) {
					System.out.println(tile.getTileDetails().getName());
					System.out.println(corner);
					sendHint(corner, color, tile.getTileDetails().getName(), idBoard);
					
					return new MoveDTO(game, tileService.getForColorAndName(color, var.getTileDetails().getName()),
							new TilePositionDTO(corner.getTop(), corner.getLeft(), var.getAngle(), var.isFlipH(), var.isFlipV()));
				}
			}	
			
		}
		playerDAO.updateOutForIdPlayer(true, player.getIdPlayer());
		return null;
	}
	
	@Override
	public boolean hasMove(TileColorEnum color, Long idBoard) {
		Player player = playerDAO.getPlayerForGameAndColor(idBoard, color);
		if(player.isOut())
			return false;
		this.board = boardDAO.findByIdBoard(idBoard).getBoard();
		if( hasEmptyCorner() != null) 
			return true;
		List<Corner> corners = cornerService.getCornersForColor(color, idBoard);
        System.out.println(corners);
		this.availableTiles = tileService.getAvailableForGame(idBoard, color);
		System.out.println("available " + availableTiles);
		for(Corner corner: corners) {
			for(TileDTO tile: availableTiles) {
				TileVariations var  = checkVariations(corner, tileVariationsService.getForTile(tile.getTileDetails().getName()), false, idBoard);
				if(var != null) {
					System.out.println(tile.getTileDetails().getName());
					System.out.println(corner);
					sendHint(corner, color, tile.getTileDetails().getName(), idBoard);
					return true;
				}
			}	
			
		}
		playerDAO.updateOutForIdPlayer(true, player.getIdPlayer());
		return false;
	}
	
	@Override
	public Board createBoard(Game game) {
		System.out.println(game);
		game.setIdGame(null);
		Board board = new Board(new int[20][20]);
		board.setGame(game);
		return boardDAO.save(board);
	}
	
	@Override
	public void addToBoard(TileColorEnum color, BoardPosition position, Long idBoard) {
		this.board = boardDAO.findByIdBoard(idBoard).getBoard();
		List<Position> coords = position.getCoords();
		int colorCode = getColorCode(color);
		for(Position pos: coords) {
			board[pos.getTop()][pos.getLeft()] = colorCode;
		}
		print();
		this.board = boardDAO.save(new Board(idBoard, board)).getBoard();
		updateCorners(color, idBoard);
	
		addCorners(position.getCoords(), color, idBoard);

		
	}
	
	private void print() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
		
	}
	
	private void sendHint(Corner corner, TileColorEnum color, TileNameEnum tile, Long idGame) {
		String username = playerDetailsDAO.getPlayer(color, idGame).getUser().getUsername();
		System.out.println(username);
		HintMessage message = new HintMessage(corner.getTop(), corner.getLeft(), color, username, tile, idGame);
		messagingTemplate.convertAndSendToUser(username, "/game/hint", message);
		
	}
	
	
	private Position hasEmptyCorner() {
		if(board[0][0] == 0)
			return new Position(0,0);
		if(board[0][19] == 0)
			return new Position(0,19);
		if(board[19][0] == 0 )
			return new Position(19,0);
		if(board[19][19] == 0)
			return new Position(19,19);
		return null;
	}
	
	private boolean checkSquareSides(int colorCode, int top, int left) {
		if(top > 0) {
			if (board[top - 1][left] == colorCode)
				return false;
		}
		if(top < 19) {
			if (board[top + 1][left] == colorCode)
				return false;
		}
		if(left > 0) {
			if (board[top][left - 1] == colorCode)
				return false;
		}
		if(left < 19) {
			if (board[top][left + 1] == colorCode)
				return false;
		}
		return true;
	}
	
	
	private void updateCorners(TileColorEnum color, Long idGame) {
		List<Corner> corners = cornerService.getCornersForColor(color, idGame);
		
		for(Corner corner: corners) {
			if(board[corner.getTop()][corner.getLeft()] != 0) {
				cornerService.deleteById(corner.getIdCorner());
			} else {
				if(!checkCornerUsable(corner.getTop(), corner.getLeft(), getColorCode(color))){
					cornerService.deleteById(corner.getIdCorner());
				}
				
			}
			
		}

	}
	
	private void addCorners(List<Position> coords, TileColorEnum color, Long idGame) {
		int colorCode = getColorCode(color);
		for(Position pos: coords) {
			int top = pos.getTop();
			int left = pos.getLeft();
			if((top - 1 >= 0) && (left - 1 >= 0)) 
				if(board[top - 1][left - 1] == 0 && board[top][left - 1] != colorCode && board[top - 1][left] != colorCode
				&& checkCornerUsable(top - 1, left - 1, colorCode)) {
					cornerService.create(idGame, color, new Position(top - 1, left - 1), CornerType.NV);
				}
			if((top + 1 <= 19) && (left - 1 >= 0 )) 
				if(board[top + 1][left - 1] == 0 && board[top][left - 1] != colorCode && board[top + 1][left] != colorCode
						&& checkCornerUsable(top + 1, left - 1, colorCode)) {
					cornerService.create(idGame, color, new Position(top + 1, left - 1), CornerType.SV);
				}
			if((top - 1 >= 0) && (left + 1 <= 19)) 
				if(board[top - 1][left + 1] == 0 && board[top][left + 1] != colorCode && board[top - 1][left] != colorCode
					&& checkCornerUsable(top - 1, left + 1, colorCode)) {
					cornerService.create(idGame, color, new Position(top - 1, left + 1), CornerType.NE);
				}
			if((top + 1 <= 19) && (left + 1 <= 19)) 
				if(board[top + 1][left + 1] == 0 && board[top][left + 1] != colorCode && board[top + 1][left] != colorCode
				&& checkCornerUsable(top + 1, left + 1, colorCode)) {
					cornerService.create(idGame, color, new Position(top + 1, left + 1), CornerType.SE);
				}
			
		}
	}
	
	private boolean checkCornerUsable(int top, int left, int colorCode) {

		if(top != 0 )
			if(board[top-1][left] == colorCode) return false;
		if(left != 0 )
			if(board[top][left - 1] == colorCode) return false;
		if(top != 19 )
			if(board[top+1][left] == colorCode) return false;
		if(left != 19 ){
			if(board[top][left + 1] == colorCode) return false;
		}
			
		return true;
	}
	
	private boolean checkTileForCorner(int[][] tile, int top, int left,  int colorCode) {
		boolean found = true;
//		System.out.println(top + "_________________" + left);
		for(int r = 0; r < tile.length; r++) {
			for(int c = 0; c < tile[0].length; c++) {
				if(top + r < 0 || top + r > 19 || left + c < 0 || left + c > 19) {
					found = false;
					break;
				}
				//System.out.println((r + top) + "  " + (c + left) + " " + board[top + r][left + c]);
				if(tile[r][c] == 0)
					continue;
				if(board[top + r][left + c] != 0) {
					found = false;
					break;
					}
				if(!checkSquareSides(colorCode, top + r, left + c )) {
					found = false;
					break;
				}
				
			}
		}
		if(found) 
			return true;
		return false;
	}
	
	private void addToBoard(TileColorEnum color, Long idBoard, int top, int left, int[][] tile) {
		List<Position> coords = new ArrayList<>();
		for(int i = 0; i < tile.length; i++) 
			for(int j = 0; j < tile[0].length; j++) {
				if(tile[i][j] == 1)
				coords.add(new Position(i + top, j + left));
			}
		this.addToBoard(color, new BoardPosition(coords), idBoard);
	}
	
	
	private TileVariations checkVariations(Corner corner, List<TileVariations> variations, boolean addToBoard, Long idBoard) {
		
		for(TileVariations variation: variations) {
			int[][] tile = variation.getTile();
			
			/*upper  line of the tile*/
			for(int i = 0; i < tile[0].length; i++) {
				if(tile[0][i] == 1) {
					if(checkTileForCorner(tile, corner.getTop(), corner.getLeft() - i, 
							getColorCode(corner.getColor()))) {
						if(addToBoard)
							addToBoard(corner.getColor(), idBoard, corner.getTop(), corner.getLeft() - i, tile);
						return variation;
					}
							
					
				}	
			}
			
			/*bottom  line of the tile*/
			for(int i = 0; i < tile[0].length; i++) {
				if(tile[tile.length - 1][i] == 1) {
					if(checkTileForCorner(tile, corner.getTop() - tile.length + 1, corner.getLeft() - i, 
							getColorCode(corner.getColor()))) {
						if(addToBoard)
							addToBoard(corner.getColor(), idBoard, corner.getTop() - tile.length + 1, corner.getLeft() - i, tile);
						return variation;
					}
							
					
				}	
			}
			
			/*leftmost  line of the tile*/
			for(int i = 0; i < tile.length; i++) {
				if(tile[i][0] == 1) {
					if(checkTileForCorner(tile, corner.getTop() - i, corner.getLeft(), 
							getColorCode(corner.getColor()))) {
						if(addToBoard)
								addToBoard(corner.getColor(), idBoard, corner.getTop() - i, corner.getLeft(), tile);
								return variation;
					}
							
					
				}	
			}

			/*rightmost  line of the tile*/
			for(int i = 0; i < tile.length; i++) {
				if(tile[i][tile[0].length - 1] == 1) {
					if(checkTileForCorner(tile, corner.getTop() - i , corner.getLeft() - tile[0].length + 1, 
							getColorCode(corner.getColor()))) {
						if(addToBoard)
							addToBoard(corner.getColor(), idBoard, corner.getTop() - i, corner.getLeft() - tile[0].length + 1, tile);
						return variation;
					}
							
					
				}	
			}
					
				
					

		}	
		return null;
	}
	
	private int getColorCode(TileColorEnum color) {
		switch(color) {
			case red: return 1;
			case green: return 2;
			case blue: return 4;
			case yellow: return 3;
		}
		return 0;
	}



	


}
