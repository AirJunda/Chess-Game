package Test;

import Game.*;
import Pieces.*;
import junit.framework.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class RookTest{

	@Test
	public void testMoveRookToEmptySpace() throws Exception {
		
		Board board = new Board(8,8);
       
		// when a player tries to move a piece to an empty space on the board
		Rook r = new Rook(0,0,Piece.Color.white);
		board.setPiece(r, 0, 0);
		r.move(board, 4, 0);  // empty space originally
		
		assertEquals(board.getPiece(0, 0), null);
		assertEquals(board.getPiece(4, 0), r);
		assertEquals(board.getPiece(4, 0).getType(), Piece.Type.rook);
		assertEquals(board.getPiece(4, 0).getColor(), Piece.Color.white);
	}
	
	
	@Test
	public void testMoveRookOffTheBoard() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece off the board
		Rook r = new Rook(7,0,Piece.Color.white);
		board.setPiece(r, 7, 0);
		r.move(board, 8, 0);  
		
		// Piece not moved
		assertEquals(board.getPiece(7, 0), r);
		assertEquals(board.getPiece(7, 0).getType(), Piece.Type.rook);
		assertEquals(board.getPiece(7, 0).getColor(), Piece.Color.white);
		
	}
	
	
	@Test
	public void testMoveRookToFriendSpace() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move to space already containing one of his/her pieces?
        Rook r1 = new Rook(0,0,Piece.Color.white);
        Rook r2 = new Rook(4,0,Piece.Color.white);
        board.setPiece(r1, 0, 0);
        board.setPiece(r2, 4, 0);
        r1.move(board, 4, 0);
        
        // Piece not moved
        assertEquals(board.getPiece(0, 0), r1);
        assertEquals(board.getPiece(4, 0), r2);

	}
	
	@Test
	public void testMoveRookToCapture() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player captures another piece
		Rook r = new Rook(0,0,Piece.Color.white);
	    Queen q = new Queen(0,4,Piece.Color.black);
	    board.setPiece(r, 0, 0);
        board.setPiece(q, 0, 4);
        r.move(board, 0, 4);
        
        // capture, captured piece disappear
        assertEquals(board.getPiece(0, 0), null);
        assertEquals(board.getPiece(0, 4), r);
	    
	}
	
	@Test
    public void testMoveRookNotHorizontallyOrVertically() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece not horizontally/ vertically
		Rook r = new Rook(0,0,Piece.Color.white);
		board.setPiece(r, 0, 0);
		r.move(board, 1, 2);  
		
		// Piece not moved
		assertEquals(board.getPiece(0, 0), r);
		assertEquals(board.getPiece(0, 0).getType(), Piece.Type.rook);
		assertEquals(board.getPiece(0, 0).getColor(), Piece.Color.white);
		
	}
	
	@Test
    public void testMoveRookWithBlockedPath() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece not horizontally/ vertically
		Rook r1 = new Rook(0,0,Piece.Color.white);
		Rook r2 = new Rook(0,2,Piece.Color.white);
		board.setPiece(r1, 0, 0);
		board.setPiece(r2, 0, 2);
		r1.move(board, 0, 4);  
		
		// Piece not moved
		assertEquals(board.getPiece(0, 0), r1);
		assertEquals(board.getPiece(0, 2), r2);
		assertEquals(board.getPiece(0, 4), null);
		
	}
	
	
	
}