package Test;

import Game.*;
import Pieces.*;
import junit.framework.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PawnTest{
	
	@Test
	public void testMovePawnToEmptySpace() throws Exception {
		
		Board board = new Board(8,8);
	       
		// when a player tries to move a piece to an empty space on the board
		Pawn p = new Pawn(4,1,Piece.Color.black);
		board.setPiece(p, 4, 1);
		p.move(board, 4, 3);  // empty space originally
		
		assertEquals(board.getPiece(4, 1), null);
		assertEquals(board.getPiece(4, 3), p);
		
	}
	
	@Test
	public void testMovePawnOffTheBoard() throws Exception {
		
		Board board = new Board(8,8);
		
		// when a player tries to move a piece off the board
		Pawn p = new Pawn(4,7,Piece.Color.black);
		board.setPiece(p, 4, 7);
		p.move(board, 4, 8);  
		
		// Piece not moved
		assertEquals(board.getPiece(4, 7), p);
		
	}
	
	@Test
	public void testMovePawnNotLegally() throws Exception {
		
		Board board = new Board(8,8);
		
		// at starting position, black pawn can only move down 1 or 2 block
		Pawn pBlack = new Pawn(3,1,Piece.Color.black);
		board.setPiece(pBlack, 3, 1);
		pBlack.move(board,3,4);
		pBlack.move(board,3,0);   // cannot move backwards
		pBlack.move(board,2,1);   // cannot move horizontally
		pBlack.move(board,4,2);   // cannot move diagonally		
		assertEquals(board.getPiece(3, 1), pBlack);
		
		// if not at starting position, black pawn can only move down 1 block or move diagonally to capture
		pBlack.move(board,3,3);
		pBlack.move(board,3,5);  // cannot move 2 blocks
		pBlack.move(board,3,2);  // cannot move backward
		pBlack.move(board,2,3);  // cannot move horizontally
		pBlack.move(board,4,4);  // cannot move diagonally when not capturing
		assertEquals(board.getPiece(3, 3), pBlack);
		
		Queen q = new Queen(4,4,Piece.Color.white);
		board.setPiece(q, 4, 4);
		pBlack.move(board,4,4);  // can move and capture
		assertEquals(board.getPiece(4, 4), pBlack);
		
		
		// at starting position, white pawn can only move up 1 or 2 block
		Pawn pWhite = new Pawn(3,6,Piece.Color.white);
		board.setPiece(pWhite, 3,6);
		pWhite.move(board,3,3);
		pWhite.move(board,3,7);   // cannot move backwards
		pWhite.move(board,2,6);   // cannot move horizontally
		pWhite.move(board,1,4);   // cannot move diagonally		
		assertEquals(board.getPiece(3,6), pWhite);
		
		// if not at starting position, black pawn can only move down 1 block or move diagonally to capture
		pWhite.move(board,3,4);
		pWhite.move(board,3,2);  // cannot move 2 blocks
		pWhite.move(board,3,5);  // cannot move backward
		pWhite.move(board,2,4);  // cannot move horizontally
		pWhite.move(board,2,3);  // cannot move diagonally when not capturing
		assertEquals(board.getPiece(3, 4), pWhite);
		
		Queen q2 = new Queen(4,3,Piece.Color.black);
		board.setPiece(q2, 4, 3);
		pWhite.move(board,4,3);  // can move and capture
		assertEquals(board.getPiece(4, 3), pWhite);
		
		
		
	}
	
}