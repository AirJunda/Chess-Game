package Game;

import java.util.Stack;

import Pieces.*;
import Pieces.Piece.Color;

public class ChessGame{
	
	public int height = 8, width = 8;
	public Board board;
	public enum Player{
		playerWhite, playerBlack;
    }
	public Player player;
	public Player winner = null;
	
	Stack<State> history = new Stack<State>();
	
	
	/**
     * Constructor for a chess game
     * @param height: the height of the board
     * @param width: the width of the board
     * @return return the ChessGame object
     */
	public ChessGame(int height, int width, boolean init) {
		this.height = height;
		this.width = width;
		board = new Board(height, width);
		this.player = Player.playerWhite; // start the game with white player
		if (init)
			initGame();
	}
	
	public void initGame(){
		
		board.initPieces();
           
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	
	public Color getPlayerColor() {
		if (this.player == Player.playerWhite)
			return Color.white;
		else
			return Color.black;
	}
	
	/**
	 * Alternate turns 
	 */
	public void switchPlayer() {
		if(this.player == Player.playerWhite)
			this.player = Player.playerBlack;
		else
			this.player = Player.playerWhite;
	}
	
	
	/**
     * Getter for the winner
     * @return Return the winner(can be null)
     */
    public Player getWinner() { return winner; }

    
    /**
     * setter for winner 
     * @param winner : the player who will win
     */
    public void setWinner(Player winner) { this.winner = winner; }
	
    
	/**
     * Return the king position of the current player
     * @param 
     * @return king x and y position
     */
    public int[] getKingPos(Board board){
    	
        int [] pos = {-1,-1};
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
            	
                if (board.isOccupied(i, j)) {
                	if (( getPlayerColor() == board.getPiece(i,j).getColor()) && (board.getPiece(i, j).getType() == Piece.Type.king)){
                		pos[0] = i;
                		pos[1] = j;
                	}
                }
                
            }
        }
        return pos;
    }
    
    /**
     * Getter for stack of history
     * @return stack
     */
    public Stack<State> getHistory() { return history; }
	
	
	public boolean playerHasLegalMove(Board board) {

		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){    
	            
				if(board.isOccupied(i,j)) { 
	            	Piece curr = board.getPiece(i,j);
	            	
	            	if (curr.getColor() == getPlayerColor()) {
	            		for (int a=0; a<width; a++) {
		            		for (int b=0; b<height; b++) {
		            			
		            			if (curr.canMove(board,a,b)) {
		            				int x0 = curr.x;
		            				int y0 = curr.y;
		            				board.setPiece(curr, a, b);
		            				board.removePiece(x0, y0);
		            				boolean legal = !board.isInCheck(a,b);
		            				board.removePiece(a, b);
		            				board.setPiece(curr, x0,y0);
		            				return legal;
		            			}
		            			
		            		}
		            	
		            	}
	            	}
	            	
	                
	            }
	        }
	    	
	    }
		return false;
	}
	    
	    

}