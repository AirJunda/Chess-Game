package Test;

import Game.*;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Stack;

public class BoardTest{
	
	@Test
	public void testInitPieces() throws Exception{
		
		ChessGame game = new ChessGame(8,8,true);
		
		// test if the board size is 8*8
		assertEquals(game.board.getWidth(), 8);
        assertEquals(game.board.getHeight(), 8);
        
        // test player color 
        for(int j = 0; j < 8; j++){
            for(int i = 0; i < 2; i++){
                assertEquals(game.board.getPiece(i,j).getColor(), Piece.Color.black);
            }
            for(int i = 6; i < 8; i++){
                assertEquals(game.board.getPiece(i,j).getColor(), Piece.Color.white);
            }
        }
        
        // test pieces of different types set up correct
        assertEquals(game.board.getPiece(0,0).getType(), Piece.Type.rook);
        assertEquals(game.board.getPiece(0,7).getType(), Piece.Type.rook);
        assertEquals(game.board.getPiece(7,0).getType(), Piece.Type.rook);
        assertEquals(game.board.getPiece(7,7).getType(), Piece.Type.rook);


        assertEquals(game.board.getPiece(0,2).getType(), Piece.Type.bishop);
        assertEquals(game.board.getPiece(0,5).getType(), Piece.Type.bishop);
        assertEquals(game.board.getPiece(7,2).getType(), Piece.Type.bishop);
        assertEquals(game.board.getPiece(7,5).getType(), Piece.Type.bishop);
        
        assertEquals(game.board.getPiece(0,1).getType(), Piece.Type.knight);
        assertEquals(game.board.getPiece(0,6).getType(), Piece.Type.knight);
        assertEquals(game.board.getPiece(7,1).getType(), Piece.Type.knight);
        assertEquals(game.board.getPiece(7,6).getType(), Piece.Type.knight);

        assertEquals(game.board.getPiece(0,4).getType(), Piece.Type.king);
        assertEquals(game.board.getPiece(7,4).getType(), Piece.Type.king);
        
        assertEquals(game.board.getPiece(0,3).getType(), Piece.Type.queen);
        assertEquals(game.board.getPiece(7,3).getType(), Piece.Type.queen);
        
        for(int i = 0; i < 8; i++){
            assertEquals(game.board.getPiece(1,i).getType(), Piece.Type.pawn);
            assertEquals(game.board.getPiece(6,i).getType(), Piece.Type.pawn);
        }
		
	}
	
	@Test
	public void testRemovePieceAndSetPiece() throws Exception {
		
		ChessGame game = new ChessGame(8,8,true);
		// remove the piece at (0,0) which is black
		game.board.removePiece(0, 0);
		// replace it with a new type piece, say queen and white
		Queen newPiece = new Queen(0,0,Piece.Color.white);
		game.board.setPiece(newPiece, 0, 0);
		
		assertEquals(game.board.getPiece(0, 0).getType(), Piece.Type.queen);
		assertEquals(game.board.getPiece(0, 0).getColor(), Piece.Color.white);
	}
	
	
	@Test
    public void testSetPieceAndIsOccupied() throws Exception {
		
        Board board = new Board(8,8);
        Rook newPiece = new Rook(0,0,Piece.Color.white);
        // set a new piece on an empty board
        board.setPiece(newPiece, 0, 0);
        // test the piece that being set on the board
        assertNotEquals(board.getPiece(0,0), null);
        assertEquals(board.getPiece(0, 0).getType(), Piece.Type.rook);
		assertEquals(board.getPiece(0, 0).getColor(), Piece.Color.white);
    }

	
	@Test 
	public void testFirstPlayer() throws Exception {
		
		ChessGame game = new ChessGame(8,8,false);
	    assertEquals(game.getPlayer(),ChessGame.Player.playerWhite);
	}
	
	
	@Test
	public void testIsInCheck() throws Exception {
		
		Board board = new Board(8,8);
		
		King k = new King(0,0,Piece.Color.white);
		Queen q = new Queen(0,7,Piece.Color.black);
		board.setPiece(k, 0, 0);
		board.setPiece(q, 0, 7);
		
		assertEquals(board.isInCheck(0,0), true);
	}
	
	
	@Test
	public void testCheckmate() throws Exception {
		
		ChessGame game = new ChessGame(8,8,false);
		
		King k1 = new King(2,0,Piece.Color.white);
		Queen q = new Queen(2,1,Piece.Color.black);
		King k2 = new King(2,2,Piece.Color.black);	
		game.board.setPiece(k1, 2, 0);
		game.board.setPiece(q, 2, 1);
		game.board.setPiece(k2, 2, 2);
		
		assertEquals(game.board.isInCheck(2, 0), true);
		assertEquals(game.board.isCheckmate(game), true);
	}
	
	
	@Test
	public void testStalemate() throws Exception {
		
		ChessGame game = new ChessGame(8,8,false);
		game.switchPlayer();
		King k1 = new King(0,2,Piece.Color.black);
		Pawn q = new Pawn(1,2,Piece.Color.white);
		King k2 = new King(2,2,Piece.Color.white);	
		game.board.setPiece(k1, 0, 2);
		game.board.setPiece(q, 1, 2);
		game.board.setPiece(k2, 2, 2);		
	
        assertEquals(game.board.isStaleMate(game), true);

	}
	
	@ Test
	public void testStateStack() throws Exception{
		
		ChessGame game = new ChessGame(8,8,true);
		Stack<State> history = game.getHistory();
		assertEquals(history.empty(), true);
		
	}
	
	
	
}