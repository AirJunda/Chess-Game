package Test;

import Game.*;
import Pieces.*;
import junit.framework.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class KingTest{
	
	@Test
	public void testMoveKingToEmptySpace() throws Exception {
		
		Board board = new Board(8,8);
       
		// when a player tries to move a piece to an empty space on the board
		King k = new King(0,4,Piece.Color.white);
		board.setPiece(k, 0, 4);
		k.move(board, 0, 3);  // empty space originally
		
		assertEquals(board.getPiece(0, 4), null);
		assertEquals(board.getPiece(0, 3), k);
	}
	
}