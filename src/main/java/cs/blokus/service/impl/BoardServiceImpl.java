package cs.blokus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.BoardDAO;
import cs.blokus.dto.BoardPosition;
import cs.blokus.dto.Position;
import cs.blokus.dto.TileDTO;
import cs.blokus.entity.Board;
import cs.blokus.entity.Corner;
import cs.blokus.entity.Game;
import cs.blokus.entity.TileVariations;
import cs.blokus.enums.CornerType;
import cs.blokus.enums.TileColorEnum;
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
	private ICornerService cornerService;
	
	@Autowired
	private ITileVariationsService tileVariationsService;
	

	private int[][] board;
	private List<TileDTO> availableTiles;
	

	@Override
	public boolean hasMove(TileColorEnum color, Long idBoard) {
		this.board = boardDAO.findByIdBoard(idBoard).getBoard();
		
		if(hasEmptyCorner())
			return true;
		List<Corner> corners = cornerService.getCornersForColor(color, idBoard);
        System.out.println(corners);
		this.availableTiles = tileService.getAvailableForGame(idBoard, color);
		System.out.println("available " + availableTiles);
		for(Corner corner: corners) {

			for(TileDTO tile: availableTiles) {
				
				if(checkVariations(corner, tileVariationsService.getForTile(tile.getTileDetails().getName()))) {
					return true;
				}
			}	
			
		}
		return false;
	}
	
	@Override
	public Board createBoard(Game game) {
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
		
		this.board = boardDAO.save(new Board(idBoard, board)).getBoard();
		updateCorners(color, idBoard);
	
		addCorners(position.getCoords(), color, idBoard);

		
	}
	
	
	private boolean hasEmptyCorner() {
		if(board[0][0] == 0 || board[0][19] == 0 || board[19][0] == 0 || board[19][19] == 0)
			return true;
		return false;
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
	
	private boolean checkVariations(Corner corner, List<TileVariations> variations) {
		
		for(TileVariations variation: variations) {
			int[][] tile = variation.getTile();
			
			/*upper  line of the tile*/
			for(int i = 0; i < tile[0].length; i++) {
				if(tile[0][i] == 1) {
					if(checkTileForCorner(tile, corner.getTop(), corner.getLeft() - i, 
							getColorCode(corner.getColor())))
							return true;
					
				}	
			}
			
			/*bottom  line of the tile*/
			for(int i = 0; i < tile[0].length; i++) {
				if(tile[tile.length - 1][i] == 1) {
					if(checkTileForCorner(tile, corner.getTop() - tile.length + 1, corner.getLeft() - i, 
							getColorCode(corner.getColor())))
							return true;
					
				}	
			}
			
			/*leftmost  line of the tile*/
			for(int i = 0; i < tile.length; i++) {
				if(tile[i][0] == 1) {
					if(checkTileForCorner(tile, corner.getTop() - i, corner.getLeft(), 
							getColorCode(corner.getColor())))
							return true;
					
				}	
			}

			/*rightmost  line of the tile*/
			for(int i = 0; i < tile.length; i++) {
				if(tile[i][tile[0].length - 1] == 1) {
					if(checkTileForCorner(tile, corner.getTop() - i , corner.getLeft() - tile[0].length + 1, 
							getColorCode(corner.getColor())))
							return true;
					
				}	
			}
					
				
					

		}	
		return false;
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
