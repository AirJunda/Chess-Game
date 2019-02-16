package Pieces;

import Game.Board;

public class Queen extends Piece{
	
	/**
     * Constructor for Queen.
     * @param x: the x location
     * @param y: the y location
     * @param color: the color of the piece
     */
	public Queen(int x, int y, Color color) {
		super(x, y, color);
		type = Type.queen;
	}
	
	/**
     * Getter for piece type
     * @return return the type of a piece
     */
	public Type getType() {
		return Type.queen;
	}
	
	/**
     * Check if it is valid to move a queen piece to specified location.
     * @param board: the current board
     * @param nx : the new x location
     * @param ny : the new y location
     * @return  whether valid
     */
    public boolean isValidMoveType(Board board, int nx, int ny) {
    	
    	int diffX = Math.abs(nx-x);
        int diffY = Math.abs(ny-y);

        if ((diffX ==0) || (diffY == 0) || (diffX == diffY)) {
        	if (isNotBlocked(board,nx,ny))
        		return true;
        	else {
        		System.out.println("Invalid movement: The path is blocked");
        		return false;
        	}
        }
     
        System.out.println("Invalid movement: Queen can only move vertically, horizontally and diagonally");
        return false;	
    }
    
    
    /**
     * Check if there is any piece block the way to specified location
     * @param board: the current board
     * @param nx : the new x location
     * @param ny : the new y location
     * @return whether blocked
     */
    public boolean isNotBlocked(Board board, int nx, int ny){
    	
    	int dirX = nx > x ? 1 : -1;
    	int dirY = ny > y ? 1 : -1;
    	
    	// move vertically
    	if (x == nx && y != ny) {    
    		for (int i=1; i<Math.abs(ny-y); i++) {
    			if (board.isOccupied(x, y+dirY*i))
    				return false;
    		}
    	}
    	
    	// move horizontally
    	if (y == ny && x != nx) {
    		for (int i=1; i<Math.abs(nx-x); i++) {
    			if (board.isOccupied(x+dirX*i, y))
    				return false;
    		}
    	}
    	
    	// move diagonally
    	if (Math.abs(nx-x) == Math.abs(ny-y)){
    		for(int i=1; i<Math.abs(nx-x); i++){
                if(board.isOccupied(x+dirX*i, y+dirY*i))
                    return false;
            }
    	}
        
    	return true;
	
    }

}