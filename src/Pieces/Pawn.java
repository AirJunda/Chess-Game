package Pieces;

import Game.Board;

public class Pawn extends Piece{
	
	/**
     * Constructor for Pawn.
     * @param x: the x location
     * @param y: the y location
     * @param color: the color of the piece
     */
	public Pawn(int x, int y, Color color) {
		super(x, y, color);
		type = Type.pawn;
	}
	
	/**
     * Getter for piece type
     * @return return the type of a piece
     */
	public Type getType() {
		return Type.pawn;
	}
	
	/**
     * Check if it is valid to move a pawn piece to specified location.
     * @param board: the current board
     * @param nx : the new x location
     * @param ny : the new y location
     * @return  whether valid
     */
    public boolean isValidMoveType(Board board, int nx, int ny) {
    	
    	int direction;  // check pawn can not move backwards
        int startX;
    	int diffX = nx-x;
        int diffY = ny-y;
        int abs_diffX = Math.abs(nx-x);
        int abs_diffY = Math.abs(ny-y);
      

        if(this.getColor() == Color.black){
            direction = 1;
            startX = 1;
        }
        else{
            direction = -1;
            startX = 6;
        }

        
        // the first time a pawn moves, it has the option of advancing two squares
        if (x == startX) {
        	if (diffY == 0 && (diffX == 2*direction || diffX == direction) && isNotBlocked(board,nx,ny,direction))
        		return true;
        
        }
 
        else {
        	// normally a pawn moves by advancing a single square
        	if (diffY == 0 && diffX == direction && !board.isOccupied(nx,ny))
        		return true;
        	
        	// pawn capture by moving a step diagonally 
        	if (abs_diffX == abs_diffY && diffX == direction && board.isOccupied(nx, ny) 
        			&& board.getPiece(nx,ny).getColor() != this.getColor())
        		return true;
        }
        
        System.out.println("Invalid movement: Check https://en.wikipedia.org/wiki/Pawn_(chess) to see legal moves for pawn");
        return false;
        
    }
    
    /**
     * Check if there is any piece block the way to specified location
     * @param board: the current board
     * @param nx : the new x location
     * @param ny : the new y location
     * @return whether blocked
     */
    public boolean isNotBlocked(Board board, int nx, int ny, int direction) {
    	
    	// black piece, only move downwards
    	if (direction == 1) {
    		for (int i=x+1; i<nx; i++) {
    			if (board.isOccupied(i, y)) {
    				System.out.println("Invalid movement: The path is blocked");
                    return false;
    			}
    		}   		
    	}
    	// white piece, only move upwards
    	else {
    		for (int i=x-1; i>nx; i--) {
    			if (board.isOccupied(i, y)) {
    				System.out.println("Invalid movement: The path is blocked");
                    return false;
    			}
    		}
    		
    	}
  	
    	return true;
    }


}