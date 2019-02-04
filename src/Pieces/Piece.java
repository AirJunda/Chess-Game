package Pieces;

import Game.*;
//import Exceptions.InvalidMovementException;

public abstract class Piece {
	
	public enum Color{
        white, black
    }
    public enum Type{
        rook, bishop, knight, king, queen, pawn
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
     * Copy constructor of a piece 
     * @param 
     */
     public Piece(Piece p) {
    	 System.out.println("piece copying");
    	 this.x = p.x;
    	 this.y = p.y;
         this.color = p.color;
     }
     
     /**
      * Getter for piece color
      * @return return the color of a piece
      */
     public Color getColor() {
         return color;
     }

     /**
      * Setter for piece color
      * @param color: one of two colors: (black, white)
      */
     public void setColor(Color color) {
         this.color = color;
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
      * Move the piece
      * @param board: the current board
      * @param nx : the new x location
      * @param ny : the new y location
      */
     public void move(Board board, int nx, int ny){
         if(board.isValidMove(this, nx, ny) && isValidMoveType(board, nx, ny)){
        	 System.out.println("valid movement");
        	 //board.removePiece(nx, ny);
             board.replacePiece(this, nx, ny);
         }
         else
         {
             System.out.println("Piece not moved: invalid movement");
         }
     }
     
     /**
      * Check if a path is valid based on Piece type.
      * @param nx : the new x location
      * @param ny : the new y location
      * @return  whether a path is valid 
      */
     public abstract boolean isValidMoveType(Board board, int nx, int ny);
     
    
	
	
}