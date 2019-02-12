package Test;

import Game.*;
import Pieces.*;
import junit.framework.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class GiraffeTest{
	
	@Test
	public void testMoveGiraffeToEmptySpace() throws Exception {
		
		Board board = new Board(8,8);
       
		// when a player tries to move a piece to an empty space on the board
		Giraffe g  = new Giraffe(6,1,Piece.Color.black);
		board.setPiece(g, 6, 1);
		g.move(board, 2, 0);  // empty space originally
		g.move(board, 3, 4);  // empty space originally
		
		assertEquals(board.getPiece(6, 1), null);
		assertEquals(board.getPiece(2, 0), null);
		assertEquals(board.getPiece(3, 4), g);
	}
	
	@Test
	public void testMoveGiraffeOffTheBoard() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece off the board
		Giraffe g  = new Giraffe(7,1,Piece.Color.black);
		board.setPiece(g, 7, 1);
		g.move(board, 11, 0);  
		
		// Piece not moved
		assertEquals(board.getPiece(7, 1), g);
		
	}
	
	@Test
	public void testMoveGiraffeToFriendSpace() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move to space already containing one of his/her pieces
		Giraffe g1  = new Giraffe(7,1,Piece.Color.black);
		Giraffe g2  = new Giraffe(3,2,Piece.Color.black);
        board.setPiece(g1, 7, 1);
        board.setPiece(g2, 3, 2);
        g1.move(board, 3, 2);
        
        // Piece not moved
        assertEquals(board.getPiece(7, 1), g1);
        assertEquals(board.getPiece(3, 2), g2);

	}
	
	
	@Test
	public void testMoveGiraffeToCapture() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player captures another piece
		Giraffe g  = new Giraffe(7,1,Piece.Color.black);
	    Queen q = new Queen(6,5,Piece.Color.white);
	    board.setPiece(g, 7, 1);
        board.setPiece(q, 6, 5);
        g.move(board, 6, 5);
        
        // capture, captured piece disappear
        assertEquals(board.getPiece(7, 1), null);
        assertEquals(board.getPiece(6, 5), g);
	    
	}
	
	@Test
    public void testMoveGiraffeNotOneFourLeaper() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece not L-Shaped
		Giraffe g  = new Giraffe(7,1,Piece.Color.black);
		board.setPiece(g, 7, 1);
		g.move(board, 7, 2); 
		g.move(board, 6, 1);
		g.move(board, 2, 2);
		
		// Piece not moved
		assertEquals(board.getPiece(7, 1), g);
		assertEquals(board.getPiece(7, 2), null);
		assertEquals(board.getPiece(6, 1), null);
		assertEquals(board.getPiece(2, 2), null);
		
	}
	
	
	
}