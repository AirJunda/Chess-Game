package Game;

import Pieces.*;

public class ChessGame{
	
	final static int height = 8, width = 8;
	public Board board;
	public enum Player{
		playerWhite, playerBlack;
    }
	public Player player;
	
	/**
     * Constructor for a chess game
     * @param height: the height of the board
     * @param width: the width of the board
     * @return return the ChessGame object
     */
	public ChessGame() {
		initGame();
	}
	
	public void initGame(){
		board = new Board(height, width);
		this.player = Player.playerWhite; // start the game with white player
		board.initPieces();
           
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	
	public void switchPlayer() {
		if(this.player == Player.playerWhite)
			this.player = Player.playerBlack;
		else
			this.player = Player.playerWhite;
	}

}