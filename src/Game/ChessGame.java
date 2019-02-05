package Game;

import Pieces.*;
import Pieces.Piece.Color;

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
	
	
	public Color getPlayerColor() {
		if (this.player == Player.playerWhite)
			return Color.white;
		else
			return Color.black;
	}
	
	
	public void switchPlayer() {
		if(this.player == Player.playerWhite)
			this.player = Player.playerBlack;
		else
			this.player = Player.playerWhite;
	}
	
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
	
	
	public boolean playerHasLegalMove(Board board) {
		
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){    
	            
				if(board.isOccupied(i,j)) { 		
	            	
	            	Piece curr = board.getPiece(i,j);
	            	for (int a=0; a<width; a++) {
	            		for (int b=0; b<height; b++) {
	            				
	            			if ((curr.getColor() == getPlayerColor()) && curr.canMove(board,a,b)) {
	            				return true;
	            			}
	            			
	            		}
	            	
	            	}
	                
	            }
	        }
	    	
	    }
		return false;
	}
	    
	    

}