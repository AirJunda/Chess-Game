package Test;

import Game.*;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class RookTest{
	
	// TODO : IMPLEMENT TESTS BELOW
	@Test
	public void testMoveRookToEmptySpace() throws Exception {
		
		Board board = new Board(8,8);
       
		// when a player tries to move a piece to an empty space on the board
		Rook r = new Rook(0,0,Piece.Color.white);
		r.move(board, 4, 4);  // empty originally
		
		assertEquals(board.getPiece(0, 0), null);
		assertEquals(board.getPiece(4, 4).getType(), Piece.Type.rook);
		assertEquals(board.getPiece(4, 4).getColor(), Piece.Color.black);
	}
	
	
	@Test
	public void testMovePieceToInvalidSpace() throws Exception {
		
		ChessGame game = new ChessGame();
		
		// when a player tries to move a piece off the board
		Piece p =game.board.getPiece(0, 0);
		p.move(game.board, 9, 9);  
		
		// Piece not moved
		assertEquals(game.board.getPiece(0, 0).getType(), Piece.Type.rook);
		assertEquals(game.board.getPiece(0, 0).getColor(), Piece.Color.black);
		
	}
	
	
	//@Test
	//public void testMovePieceToFriendSpace() throws Exception {
		
	// board = new Board(8,8);
    //    Rook newPiece = new Rook(0,0,Piece.Color.white);
		
	//}
	// when a player tries to move to space already containing one of his/her pieces?
	
	
}