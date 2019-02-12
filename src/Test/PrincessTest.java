package Test;

import Game.*;
import Pieces.*;
import junit.framework.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PrincessTest{

	@Test
	public void testMovePrincessToEmptySpace() throws Exception {
		
		Board board = new Board(8,8);
       
		// when a player tries to move a piece to an empty space on the board
		Princess p = new Princess(0,2,Piece.Color.white);
		board.setPiece(p, 0, 2);
		p.move(board, 2, 4);  // empty space originally
		
		assertEquals(board.getPiece(0, 2), null);
		assertEquals(board.getPiece(2, 4), p);
	}
	
	@Test
	public void testMovePrincessOffTheBoard() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece off the board
		Princess p = new Princess(0,2,Piece.Color.white);
		board.setPiece(p, 0, 2);
		p.move(board, 6, 8);  
		
		// Piece not moved
		assertEquals(board.getPiece(0, 2), p);
		
	}
	
	@Test
	public void testMovePrincessToFriendSpace() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move to space already containing one of his/her pieces
		Princess p1 = new Princess(0,2,Piece.Color.white);
        Princess p2 = new Princess(3,5,Piece.Color.white);
        board.setPiece(p1, 0, 2);
        board.setPiece(p2, 3, 5);
        p1.move(board, 3, 5);
        
        // Piece not moved
        assertEquals(board.getPiece(0, 2), p1);
        assertEquals(board.getPiece(3, 5), p2);

	}
	
	@Test
	public void testMovePrincessToCapture() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player captures another piece
		Princess p = new Princess(0,2,Piece.Color.white);
	    Queen q = new Queen(2,3,Piece.Color.black);
	    board.setPiece(p, 0, 2);
        board.setPiece(q, 2, 3);
        p.move(board, 2, 3);
        
        // capture, captured piece disappear
        assertEquals(board.getPiece(0, 2), null);
        assertEquals(board.getPiece(2, 3), p);
	    
	}
	
	@Test
    public void testMovePrincessNotDiagonallyOrLshaped() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece not horizontally/ vertically
		Princess p = new Princess(0,2,Piece.Color.white);
		board.setPiece(p, 0, 2);
		p.move(board, 1, 2); 
		p.move(board, 0, 3);
		p.move(board, 6, 6);
		
		// Piece not moved
		assertEquals(board.getPiece(0, 2), p);
		assertEquals(board.getPiece(1, 2), null);
		assertEquals(board.getPiece(0, 3), null);
		assertEquals(board.getPiece(6, 6), null);
		
	}
	
	
	@Test
    public void testMovePrincessDiagonallyWithBlockedPath() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece not horizontally/ vertically
		Princess p1 = new Princess(0,2,Piece.Color.white);
		Princess p2 = new Princess(2,4,Piece.Color.white);
        board.setPiece(p1, 0, 2);
        board.setPiece(p2, 2, 4);
        p1.move(board, 3, 5);
        
		
		// Piece not moved
		assertEquals(board.getPiece(0, 2), p1);
		assertEquals(board.getPiece(2, 4), p2);
		assertEquals(board.getPiece(3, 5), null);
		
	}
	
	
	
	
}