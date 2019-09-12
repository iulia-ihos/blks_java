package cs.blokus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.BoardDAO;
import cs.blokus.dto.BoardPosition;
import cs.blokus.dto.Position;
import cs.blokus.dto.TileDTO;
import cs.blokus.dto.TileSquareDTO;
import cs.blokus.entity.Board;
import cs.blokus.entity.Corner;
import cs.blokus.entity.Game;
import cs.blokus.entity.TileVariations;
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
		
		List<Corner> corners = cornerService.getCornersForColor(color, idBoard);

		this.availableTiles = tileService.getAvailableForGame(idBoard);
		System.out.println("a valable " +availableTiles);
		for(Corner corner: corners) {
			int row = corner.getTop();
			int col = corner.getLeft();
			for(TileDTO tile: availableTiles) {
				boolean found = true;
				//check original tile
				List<TileSquareDTO> squares = tile.getTileDetails().getTileSquares();
				for(TileSquareDTO square: squares) {
					if(board[row + square.getSquare().getTop()][col + square.getSquare().getLeft()] != 0)
						found = false;
				}
				if(found) {
					System.out.println(" corner " + corner);
					System.out.println(" tile " + tile);
					return true;
				}
				//check tile variations
				return checkVariations(corner, tileVariationsService.getFotTile(tile.getTileDetails().getName()));
			}	
			
		}
		return false;
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
		System.out.println("after up");
		System.out.println(cornerService.getCornersForColor(TileColorEnum.red,idBoard));
		addCorners(position.getCoords(), color, idBoard);
		System.out.println("after add");
		System.out.println(cornerService.getCornersForColor(TileColorEnum.red, idBoard));
		
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
				if(board[top - 1][left - 1] == 0 && checkCornerUsable(top - 1, left - 1, colorCode)) {
					cornerService.create(idGame, color, new Position(top - 1, left - 1));
				}
			if((top + 1 <= 20) && (left - 1 >= 0 )) 
				if((board[top + 1][left - 1] == 0) && checkCornerUsable(top + 1, left - 1, colorCode)) {
					cornerService.create(idGame, color, new Position(top + 1, left - 1));
				}
			if((top - 1 >= 0) && (left + 1 <= 20)) 
				if((board[top - 1][left + 1] == 0) && checkCornerUsable(top - 1, left + 1, colorCode)) {
					cornerService.create(idGame, color, new Position(top - 1, left + 1));
				}
			if((top + 1 <= 20) && (left + 1 <= 20)) 
				if((board[top + 1][left + 1] == 0) && checkCornerUsable(top + 1, left + 1, colorCode)) {
					cornerService.create(idGame, color, new Position(top + 1, left + 1));
				}
			
		}
	}
	
	private boolean checkCornerUsable(int top, int left, int colorCode) {
//		System.out.println("-------------");
//		System.out.println(board[3][2]==colorCode);
//		if(top == 3 && left==1){
//			System.out.println(board[top][left + 1]);
//			System.out.println(board[top][left - 1]);
//			System.out.println(board[top+1][left]);
//			System.out.println(board[top-1][left]);
//		}
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
	
	
	private boolean checkVariations(Corner corner, List<TileVariations> variations) {
		
		int row = corner.getTop();
		int col = corner.getLeft();
		for(TileVariations variation: variations) {
			boolean found = true;
			int[][] tile = variation.getTile();
			for(int i = 0; i < tile.length; i++) {
				if(!found) break;
				for(int j = 0; j < tile[0].length; j++) {
					if(board[row + i][col + j] != 0)
						found = false;
				}
			}
		   if(found) {
			   System.out.println(" corner " + corner);
			   System.out.println(" var " + variation);
			   return true;
		   }
		}	
		return false;
	}
	
	private int getColorCode(TileColorEnum color) {
		switch(color) {
			case red: return 1;
			case green: return 2;
			case blue: return 3;
			case yellow: return 4;
		}
		return 0;
	}



	@Override
	public Board createBoard(Game game) {
		game.setIdGame(null);
		Board board = new Board(new int[20][20]);
		board.setGame(game);
		return boardDAO.save(board);
	}
	
	private void print() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++)
				System.out.print(board[i][j]);
			System.out.println();
		}
	}
	 

}
