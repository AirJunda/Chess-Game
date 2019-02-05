package Test;

import Game.*;
import Pieces.*;
import junit.framework.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BishopTest{

	@Test
	public void testMoveBishopToEmptySpace() throws Exception {
		
		Board board = new Board(8,8);
       
		// when a player tries to move a piece to an empty space on the board
		Bishop b = new Bishop(0,2,Piece.Color.white);
		board.setPiece(b, 0, 2);
		b.move(board, 2, 4);  // empty space originally
		
		assertEquals(board.getPiece(0, 2), null);
		assertEquals(board.getPiece(2, 4), b);
	}
	
	@Test
	public void testMoveBishopOffTheBoard() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece off the board
		Bishop b = new Bishop(0,2,Piece.Color.white);
		board.setPiece(b, 0, 2);
		b.move(board, 6, 8);  
		
		// Piece not moved
		assertEquals(board.getPiece(0, 2), b);
		
	}
	
	@Test
	public void testMoveBishopToFriendSpace() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move to space already containing one of his/her pieces
        Bishop b1 = new Bishop(0,2,Piece.Color.white);
        Bishop b2 = new Bishop(3,5,Piece.Color.white);
        board.setPiece(b1, 0, 2);
        board.setPiece(b2, 3, 5);
        b1.move(board, 3, 5);
        
        // Piece not moved
        assertEquals(board.getPiece(0, 2), b1);
        assertEquals(board.getPiece(3, 5), b2);

	}
	
	@Test
	public void testMoveBishopToCapture() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player captures another piece
		Bishop b = new Bishop(0,2,Piece.Color.white);
	    Queen q = new Queen(5,7,Piece.Color.black);
	    board.setPiece(b, 0, 2);
        board.setPiece(q, 5, 7);
        b.move(board, 5, 7);
        
        // capture, captured piece disappear
        assertEquals(board.getPiece(0, 2), null);
        assertEquals(board.getPiece(5, 7), b);
	    
	}
	
	@Test
    public void testMoveBishopNotDiagonally() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece not horizontally/ vertically
		Bishop b = new Bishop(0,2,Piece.Color.white);
		board.setPiece(b, 0, 2);
		b.move(board, 1, 2); 
		b.move(board, 0, 3);
		b.move(board, 6, 6);
		
		// Piece not moved
		assertEquals(board.getPiece(0, 2), b);
		assertEquals(board.getPiece(1, 2), null);
		assertEquals(board.getPiece(0, 3), null);
		assertEquals(board.getPiece(6, 6), null);
		
	}
	
	@Test
    public void testMoveBishopWithBlockedPath() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece not horizontally/ vertically
		Bishop b1 = new Bishop(0,2,Piece.Color.white);
        Bishop b2 = new Bishop(2,4,Piece.Color.white);
        board.setPiece(b1, 0, 2);
        board.setPiece(b2, 2, 4);
        b1.move(board, 3, 5);
        
		
		// Piece not moved
		assertEquals(board.getPiece(0, 2), b1);
		assertEquals(board.getPiece(2, 4), b2);
		assertEquals(board.getPiece(3, 5), null);
		
	}
	
	
	
	
}