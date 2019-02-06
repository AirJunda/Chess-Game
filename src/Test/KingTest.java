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
	
	
	@Test
	public void testMoveKingOffTheBoard() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece off the board
		King k  = new King(0,4,Piece.Color.black);
		board.setPiece(k, 0, 4);
		k.move(board, -1, 4);  
		
		// Piece not moved
		assertEquals(board.getPiece(0, 4), k);
		
	}
	
	@Test
	public void testMoveKingToFriendSpace() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move to space already containing one of his/her pieces
		King k1  = new King(0,4,Piece.Color.black);
		Knight k2  = new Knight(1,3,Piece.Color.black);
        board.setPiece(k1, 0, 4);
        board.setPiece(k2, 1, 3);
        k1.move(board, 1, 3);
        
        // Piece not moved
        assertEquals(board.getPiece(0, 4), k1);
        assertEquals(board.getPiece(1, 3), k2);

	}
	
	@Test
	public void testMoveKingToCapture() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player captures another piece
		King k  = new King(0,4,Piece.Color.black);
		Knight q  = new Knight(1,3,Piece.Color.white);
	    board.setPiece(k, 0, 4);
        board.setPiece(q, 1, 3);
        k.move(board, 1, 3);
        
        
        // capture, captured piece disappear
        assertEquals(board.getPiece(0, 4), null);
        assertEquals(board.getPiece(1, 3), k);
       
	}
	
	@Test
    public void testMoveKingInCheck() throws Exception {
		
		Board board = new Board(8,8);
		
		//  Players cannot make any move that places their own king in check
		King k  = new King(0,6,Piece.Color.white);
		Queen q = new Queen(7,6,Piece.Color.black);
		board.setPiece(k, 0, 6);
		board.setPiece(q, 7, 6);
		k.move(board, 0, 7);    // will be in check 
		k.move(board, 1, 7);    // will be in check
		
		// Piece not moved
		assertEquals(board.getPiece(0, 6), k);
		assertEquals(board.getPiece(0, 7), null);
		assertEquals(board.getPiece(1, 7), null);
		
	}
	
	@Test
    public void testMoveKingNotOneBlock() throws Exception {
		
		Board board = new Board(8,8);
		
		//  King can only move one block away
		King k  = new King(0,6,Piece.Color.white);
		board.setPiece(k,0,6);
		k.move(board,0,0);
		k.move(board,1,3);
		
		// Piece not moved
		assertEquals(board.getPiece(0, 6), k);
		assertEquals(board.getPiece(0, 0), null);
		assertEquals(board.getPiece(1, 3), null);
		
	}

	
}