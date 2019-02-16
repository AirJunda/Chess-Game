package Pieces;

import Game.Board;

public class Knight extends Piece{
	
	/**
     * Constructor for Knight.
     * @param x: the x location
     * @param y: the y location
     * @param color: the color of the piece
     */
	public Knight(int x, int y, Color color) {
		super(x, y, color);
		type = Type.knight;
	}
	
	/**
     * Getter for piece type
     * @return return the type of a piece
     */
	public Type getType() {
		return Type.knight;
	}
	
	/**
     * Check if it is valid to move a knight piece to specified location.
     * @param board: the current board
     * @param nx : the new x location
     * @param ny : the new y location
     * @return  whether valid
     */
    public boolean isValidMoveType(Board board, int nx, int ny) {
    	
    	 int diffX = Math.abs(nx-x);
         int diffY = Math.abs(ny-y);

         if((diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1))
        		 return true;
         
         System.out.println("Invalid movement: Knight can only move L-shaped");
         return false;

    }
    
    


}