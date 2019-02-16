package Pieces;

import Game.Board;

public class Giraffe extends Piece{
	
	/**
     * Constructor for Giraffe.
     * @param x: the x location
     * @param y: the y location
     * @param color: the color of the piece
     */
	public Giraffe(int x, int y, Color color) {
		super(x, y, color);
		type = Type.giraffe;
	}
	
	/**
     * Getter for piece type
     * @return return the type of a piece
     */
	public Type getType() {
		return Type.giraffe;
	}
	
	/**
     * Check if it is valid to move a giraffe piece to specified location.
     * @param board: the current board
     * @param nx : the new x location
     * @param ny : the new y location
     * @return  whether valid
     */
    public boolean isValidMoveType(Board board, int nx, int ny) {
    	
    	//giraffe can jump to a square that is four squares horizontally and one square vertically, 
    	//or four squares vertically and one square horizontally, regardless of intervening pieces; 
    	//thus, it is a (1,4)-leaper
    	
    	 int diffX = Math.abs(nx-x);
         int diffY = Math.abs(ny-y);

         if((diffX == 1 && diffY == 4) || (diffX == 4 && diffY == 1))
        		 return true;
         
         System.out.println("Invalid movement: Giraffe can only move as a (1,4)-leaper");
         return false;

    }
    
    


}