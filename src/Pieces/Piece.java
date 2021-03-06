package Pieces;

import Game.*;
//import Exceptions.InvalidMovementException;

public abstract class Piece {
	
	public enum Color{
        white, black
    }
    public enum Type{
        rook, bishop, knight, king, queen, pawn, princess, giraffe
    }
    
    public int x,y;
    public Color color;
    public Type type;
	
	/**
     * Constructor for a Piece.
     * @param x: the x location of the Piece
     * @param y: the y location of the Piece
     * @param color: the color associated with the Piece
     */
	public Piece(int x, int y, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
	}

     
     /**
      * Getter for piece color
      * @return return the color of a piece
      */
     public Color getColor() {
         return color;
     }
     
     /**
      * Getter for piece type
      * @return return the type of a piece
      */
     public abstract Type getType();

     /**
      * Setter for piece type 
      * @param type: one of six types: (rook, bishop, knight, king, queen, pawn)
      */
     public void setType(Type type) {
         this.type = type;
     }
     
     /**
      * Check whether a piece can move to specified location
      * @param board : the current board
      * @param nx : new x location
      * @param ny : new y location
      * @return whether can move
      */
     public boolean canMove(Board board, int nx, int ny) {
    	 if(board.isValidMove(this, nx, ny) && isValidMoveType(board, nx, ny)){
    		 return true;
    	 }
    	 return false;
     }

     /**
      * Move the piece
      * @param board: the current board
      * @param nx : the new x location
      * @param ny : the new y location
      */
     public void move(Board board, int nx, int ny){
    	 if (canMove(board,nx,ny)) {
        	 System.out.printf("Moving to %d, %d\n",nx,ny);
             board.replacePiece(this, nx, ny);
         }
         //else{
         //    System.out.println("Piece not moved: invalid movement");
         //}
     }
     
     /**
      * Undo the moved piece
      * 
      */
     
     /**
      * Check if a path is valid based on Piece type.
      * @param nx : the new x location
      * @param ny : the new y location
      * @return  whether a path is valid 
      */
     public abstract boolean isValidMoveType(Board board, int nx, int ny);
     
    
	
	
}