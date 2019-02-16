package Pieces;

import Game.Board;

//import ChessGame.*;

public class Rook extends Piece{
	
	/**
     * Constructor for Rook.
     * @param x: the x location
     * @param y: the y location
     * @param color: the color of the piece
     */
	public Rook(int x, int y, Color color) {
		super(x, y, color);
		type = Type.rook;
	}
	
	/**
     * Getter for piece type
     * @return return the type of a piece
     */
	public Type getType() {
		return Type.rook;
	}
	
	/**
     * Check if it is valid to move a rook piece to specified location.
     * @param board: the current board
     * @param nx : the new x location
     * @param ny : the new y location
     * @return  whether valid
     */
    public boolean isValidMoveType(Board board, int nx, int ny) {
    	
    	if ((x == nx || y == ny) && isNotBlocked(board,nx,ny)) {
    		return true;
    	}
    	else if (x != nx && y != ny)
    		System.out.println("Invalid movement: Rook can only move horizontally or vertically");
    	
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
    	// move vertically
    	if (x == nx && y != ny) {
            int dirY = ny > y ? 1 : -1;
    		for (int i=1; i<Math.abs(ny-y); i++) {
    			if (board.isOccupied(x, y+dirY*i)) {
    				System.out.println("Invalid movement: The path is blocked");
    				return false;
    			}
    		}
    	}
    	// move horizontally
    	if (y == ny && x != nx) {
    		int dirX = nx > x ? 1 : -1;
    		for (int i=1; i<Math.abs(nx-x); i++) {
    			if (board.isOccupied(x+dirX*i, y)) {
    				System.out.println("Invalid movement: The path is blocked");
    				return false;
    			}
    		}
    	}
        
    	return true;
	
    }
    
    


}