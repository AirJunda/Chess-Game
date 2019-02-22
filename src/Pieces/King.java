package Pieces;

import Game.Board;

public class King extends Piece{
	
	/**
     * Constructor for King
     * @param x: the x location
     * @param y: the y location
     * @param color: the color of the piece
     */
	public King(int x, int y, Color color) {
		super(x, y, color);
		type = Type.king;
	}
	
	/**
     * Getter for piece type
     * @return return the type of a piece
     */
	public Type getType() {
		return Type.king;
	}

	/**
     * Check if it is valid to move a king piece to specified location.
     * @param board: the current board
     * @param nx : the new x location
     * @param ny : the new y location
     * @return  whether valid
     */
    public boolean isValidMoveType(Board board, int nx, int ny) {
    	
    	int diffX = Math.abs(nx-x);
        int diffY = Math.abs(ny-y);

        if(diffX <= 1 && diffY <= 1) {
        	if (!board.isInCheck(x,y)) {
        		return true;
        	}
        	else {
        		System.out.println("Invalid movement: King will be in check");
                return false;
        	}
        		
        }
       		 
        System.out.println("Invalid movement: King can only move one square in any direction");
        return false;
    }

}