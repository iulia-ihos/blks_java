package cs.blokus.pentobi.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cs.blokus.dto.BoardPosition;
import cs.blokus.dto.MoveDTO;
import cs.blokus.dto.Position;
import cs.blokus.dto.TileSquareDTO;

@Component
public class CoordinatesConverter {

	public  String getMoveString(MoveDTO move) {
		String result = "";
		int left = move.getPosition().getLeft();
		int top = move.getPosition().getTop();
		for(TileSquareDTO square: move.getTile().getTileDetails().getTileSquares()) {
			int sLeft = square.getSquare().getLeft() + left;
			int sTop = square.getSquare().getTop() + top;
			StringBuilder sb = new StringBuilder();
			sb.append((char) (sLeft+'a'));
	
			result += sb.toString() + (20-sTop) + ",";
		}
		result = result.substring(0, result.length()-1);
		System.out.println(result);
		return result;
	}
	
	public  BoardPosition getBoardPosition(String move) {
		List<Position> coords = new ArrayList<>();
		int s = 2;
		while(s < move.length() - 1) {
			if(Character.isDigit(move.charAt(s+1))) {
				if(Character.isDigit(move.charAt(s+2))) {
					coords.add(new Position(20 - Integer.parseInt(move.substring(s+1, s+3)), move.charAt(s) - 'a' ));
					s += 2;
				}
				else {
					coords.add(new Position(20 - Character.getNumericValue(move.charAt(s+1)), move.charAt(s) - 'a' ));
				}
					
			}
		}
		return new BoardPosition(coords);
	}
}
