package Test;

import Game.*;
import Pieces.*;
import junit.framework.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class KnightTest{
	
	@Test
	public void testMoveKnightToEmptySpace() throws Exception {
		
		Board board = new Board(8,8);
       
		// when a player tries to move a piece to an empty space on the board
		Knight k  = new Knight(7,1,Piece.Color.black);
		board.setPiece(k, 7, 1);
		k.move(board, 6, 3);  // empty space originally
		k.move(board, 4, 4);  // empty space originally
		
		assertEquals(board.getPiece(7, 1), null);
		assertEquals(board.getPiece(6, 3), null);
		assertEquals(board.getPiece(4, 4), k);
	}
	
	@Test
	public void testMoveKnightOffTheBoard() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece off the board
		Knight k  = new Knight(7,1,Piece.Color.black);
		board.setPiece(k, 7, 1);
		k.move(board, 9, 0);  
		
		// Piece not moved
		assertEquals(board.getPiece(7, 1), k);
		
	}
	
	@Test
	public void testMoveKnightToFriendSpace() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move to space already containing one of his/her pieces
		Knight k1  = new Knight(7,1,Piece.Color.black);
		Knight k2  = new Knight(5,2,Piece.Color.black);
        board.setPiece(k1, 7, 1);
        board.setPiece(k2, 5, 2);
        k1.move(board, 5, 2);
        
        // Piece not moved
        assertEquals(board.getPiece(7, 1), k1);
        assertEquals(board.getPiece(5, 2), k2);

	}
	
	
	@Test
	public void testMoveKnightToCapture() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player captures another piece
		Knight k  = new Knight(7,1,Piece.Color.black);
	    Queen q = new Queen(6,3,Piece.Color.white);
	    board.setPiece(k, 7, 1);
        board.setPiece(q, 6, 3);
        k.move(board, 6, 3);
        
        // capture, captured piece disappear
        assertEquals(board.getPiece(7, 1), null);
        assertEquals(board.getPiece(6, 3), k);
	    
	}
	
	@Test
    public void testMoveKnightNotLShaped() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece not L-Shaped
		Knight k  = new Knight(7,1,Piece.Color.black);
		board.setPiece(k, 7, 1);
		k.move(board, 7, 2); 
		k.move(board, 6, 1);
		k.move(board, 2, 2);
		
		// Piece not moved
		assertEquals(board.getPiece(7, 1), k);
		assertEquals(board.getPiece(7, 2), null);
		assertEquals(board.getPiece(6, 1), null);
		assertEquals(board.getPiece(2, 2), null);
		
	}
	
	
	
}