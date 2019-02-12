package Pieces;

import Game.Board;

//import ChessGame.*;

public class Princess extends Piece{
	
	/**
     * Constructor for Princess.
     * @param x: the x location
     * @param y: the y location
     * @param color: the color of the piece
     */
	public Princess(int x, int y, Color color) {
		super(x, y, color);
		type = Type.princess;
	}
	
	/**
     * Getter for piece type
     * @return return the type of a piece
     */
	public Type getType() {
		return Type.princess;
	}
	
	/**
     * Check if it is valid to move a princess piece to specified location.
     * @param board: the current board
     * @param nx : the new x location
     * @param ny : the new y location
     * @return  whether valid
     */
    public boolean isValidMoveType(Board board, int nx, int ny) {
    	
    	//A princess is a fairy chess piece that can move like a bishop or a knight.
    	
    	int diffX = Math.abs(nx-x);
        int diffY = Math.abs(ny-y);

        if((diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1)) // moving as a knight
       		 return true;
    	
    	if ( (diffX == diffY) && isNotBlocked(board,nx,ny))   // moving as a bishop
    		return true;
    	
    	return false;
    }
    
    /**
     * Check if there is any piece block the way to specified location
     * @param board: the current board
     * @param nx : the new x location
     * @param ny : the new y location
     * @return whether blocked
     */
    public boolean isNotBlocked(Board board, int nx, int ny) {
    	
    	// must move diagonally
    	int dirX = nx > x ? 1 : -1;
    	int dirY = ny > y ? 1 : -1;
    	
    	if (Math.abs(nx-x) == Math.abs(ny-y)){
    		for(int i=1; i<Math.abs(nx-x); i++){
                if(board.isOccupied(x+dirX*i, y+dirY*i)) {
                	System.out.println("Invalid movement: The path is blocked");
                    return false;
                }
            }
    	}
    	
    	return true;
    }


}