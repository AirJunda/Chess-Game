package Test;

import Game.*;
import Pieces.*;
import junit.framework.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueenTest{
	
	@Test
	public void testMoveQueenToEmptySpace() throws Exception {
		
		Board board = new Board(8,8);
	       
		// when a player tries to move a piece to an empty space on the board
		Queen q = new Queen(0,3,Piece.Color.white);
		board.setPiece(q, 0, 3);
		q.move(board, 7, 3);  // empty space originally
		
		assertEquals(board.getPiece(0, 3), null);
		assertEquals(board.getPiece(7, 3), q);
	}
	
	@Test
	public void testMoveQueentOffTheBoard() throws Exception {
		Board board = new Board(8,8);
	       
		// when a player tries to move a piece to an empty space on the board
		Queen q = new Queen(0,3,Piece.Color.white);
		board.setPiece(q, 0, 3);
		q.move(board, 0, 10);  // empty space originally
		
		// Piece not moved
		assertEquals(board.getPiece(0, 3), q);
		
	}
	
	@Test
	public void testMoveQueenToFriendSpace() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move to space already containing one of his/her pieces
		Queen q = new Queen(0,3,Piece.Color.white);
		Knight k  = new Knight(1,4,Piece.Color.white);
        board.setPiece(q, 0, 3);
        board.setPiece(k, 1, 4);
        q.move(board, 1, 4);
        
        // Piece not moved
        assertEquals(board.getPiece(0, 3), q);
        assertEquals(board.getPiece(1, 4), k);

	}
	
	@Test
	public void testMoveQueenToCapture() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player captures another piece
		Queen q = new Queen(0,3,Piece.Color.black);
		Knight k  = new Knight(3,6,Piece.Color.white);
	    board.setPiece(q, 0, 3);
        board.setPiece(k, 3, 6);
        q.move(board, 3, 6);
        
        // capture, captured piece disappear
        assertEquals(board.getPiece(0, 3), null);
        assertEquals(board.getPiece(3, 6), q);
       
	}
	
	@Test
    public void testMoveQueenNotLegally() throws Exception {
		
		Board board = new Board(8,8);
		Queen q = new Queen(0,3,Piece.Color.black);
		board.setPiece(q, 0, 3);
		q.move(board, 1, 6);
		
		// not moved
		assertEquals(board.getPiece(1, 6), null);
	    assertEquals(board.getPiece(0, 3), q);
	    
	    Knight k = new Knight(2,5,Piece.Color.black);
	    board.setPiece(k,2,5);
	    q.move(board, 3, 6);
	    
	    // not moved
	    assertEquals(board.getPiece(3, 6), null);
	    assertEquals(board.getPiece(0, 3), q);
		
	}
	
}
