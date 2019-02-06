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
        int startY;
    	int diffX = nx-x;
        int diffY = ny-y;
        int abs_diffX = Math.abs(nx-x);
        int abs_diffY = Math.abs(ny-y);
      

        if(this.getColor() == Color.black){
            direction = 1;
            startY = 1;
        }
        else{
            direction = -1;
            startY = 6;
        }
        
        // the first time a pawn moves, it has the option of advancing two squares
        if (y == startY) {
        	if (diffX == 0 && (diffY == 2*direction || diffY == direction))
        		return true;
        
        }
 
        else {
        	// normally a pawn moves by advancing a single square
        	if (diffX == 0 && diffY == direction)
        		return true;
        	
        	// pawn capture by moving a step diagonally 
        	if (abs_diffX == abs_diffY && diffY == direction && board.isOccupied(nx, ny) 
        			&& board.getPiece(nx,ny).getColor() != this.getColor())
        		return true;
        }
        
        System.out.println("Invalid movement: Check https://en.wikipedia.org/wiki/Pawn_(chess) to see legal moves for pawn");
        return false;
        
    }


}