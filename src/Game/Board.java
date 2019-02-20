package Game;

import Game.ChessGame.Player;
import Pieces.*;

public class Board{
	
	public int height, width;
	public Piece[][] board;
	
	/**
     * Constructor for a Board.
     * @param height: the height of the board
     * @param width: the width of the board
     * @param color: the Player object associated with the Piece
     */
	public Board(int height, int width) {
		this.height = height;
		this.width = width;
		board = new Piece[height][width];
		
	}
	
	public void initPieces(boolean custom) {
		
		for (int i = 0; i < height; i++){
	    	 for (int j = 0; j < width; j++){
	    		 board[i][j] = null;
	         }
	     }
		
		board[0][0] = new Rook(0,0,Piece.Color.black);
	    board[0][7] = new Rook(0,7,Piece.Color.black);
	    board[7][0] = new Rook(7,0,Piece.Color.white);
	    board[7][7] = new Rook(7,7,Piece.Color.white);
	    
	    board[0][2] = new Bishop(0,2,Piece.Color.black);
	    board[0][5] = new Bishop(0,5,Piece.Color.black);
	    board[7][2] = new Bishop(7,2,Piece.Color.white);
	    board[7][5] = new Bishop(7,5,Piece.Color.white);

	    board[0][1] = new Knight(0,1,Piece.Color.black);
	    board[0][6] = new Knight(0,6,Piece.Color.black);
	    board[7][1] = new Knight(7,1,Piece.Color.white);
	    board[7][6] = new Knight(7,6,Piece.Color.white);

	    board[0][4] = new King(0,4,Piece.Color.black);
	    board[7][4] = new King(7,4,Piece.Color.white);

	    board[0][3] = new Queen(0,3,Piece.Color.black);
	    board[7][3] = new Queen(7,3,Piece.Color.white);
     
	    for (int i = 0; i < 8; i++) {
	    	board[1][i] = new Pawn(1,i,Piece.Color.black);
	    	board[6][i] = new Pawn(6,i,Piece.Color.white);
	    }
	    
	}
	
	/**
	 * add custom piece
	 */
	public void customPieceSetUp(boolean custom) {
		if (!custom) return;
		
		removePiece(1,3);
		removePiece(6,3);
		removePiece(1,4);
		removePiece(6,4);
		board[1][3] = new Princess(1,3,Piece.Color.black);
    	board[6][3] = new Princess(6,3,Piece.Color.white);
    	board[1][4] = new Giraffe(1,4,Piece.Color.black);
    	board[6][4] = new Giraffe(6,4,Piece.Color.white);
	}
	
	
	/**
     * Getter for board
     * @return the board configuration
     */
    public Piece[][] getBoard() {
        return board;
    }
   
    /**
     * Get board height
     * @return height
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Get board width
     *@return width
     */
    public int getWidth(){
        return width;
    }

    /**
     * Set board height
     * @param height
     */
    public void setHeight(int height){
        this.height = height;
    }
    
    /**
     * Set board width
     * @param width
     */
    public void setWidth(int width){
        this.width = width;
    }
    
    /**
     * Return the piece at specified location on the board
     * @param x: x location
     * @param y: y location
     * @return piece
     */
    public Piece getPiece(int x, int y){
        return board[x][y];
    }
    
    /**
     * Remove the piece at specified location on the board
     * @param x: x location
     * @param y: y location
     */
    public void removePiece(int x, int y){
        board[x][y] = null;
    }

    /**
     * Set a piece at specified location on the board
     * @param nPiece: newPiece
     * @param x: x location
     * @param y: y location
     */
    public void setPiece(Piece nPiece, int x, int y){
    	board[x][y] = nPiece;
    }
    
    /**
     * Check if a piece occupied at specified location on the board
     * @param x: x location
     * @param y: y location
     * @return boolean indicating whether occupied or not
     */
    public boolean isOccupied(int x, int y){
        if(getPiece(x, y) != null){
            return true;
        }
        return false;
    }
    
    /**
     * Replace a piece to a new specified location
     * @param piece: replacing piece 
     * @param nx: new x location
     * @param ny: new y location
     */
    public void replacePiece(Piece piece, int nx, int ny){
    	int x = piece.x;
        int y = piece.y;
        piece.x = nx; 
        piece.y = ny;
        board[nx][ny] = piece; 
        board[x][y] = null; 
    }
    
    /**
     * Check if the piece can be moved valid to a new location
     * @param Piece: the piece to be checked
     * @param nx : the new x location
     * @param ny : the new y location
     * @return  whether can move 
     */
    public boolean isValidMove(Piece p, int nx, int ny){
    	if ( (!isSameLoc(p,nx,ny)) && (!isOutOfBound(nx,ny)) 
    			&& isValidEndPoint(p,nx,ny)) {
    		return true;    		
    	}
    	return false;
    }
    
    
    /**
     * Check if the new location is the same as the old location 
     * @param Piece: the piece to be checked
     * @param nx : the new x location
     * @param ny : the new y location
     * @return  whether the same
     */
    public boolean isSameLoc(Piece p, int nx, int ny) {
   	 if(p.x == nx && p.y == ny){
            return true;
        }
   	 return false;
    }
    
    /**
     * Check if the new location is out of bound 
     * @param nx : the new x location
     * @param ny : the new y location
     * @return  whether out of bound
     */
    public boolean isOutOfBound(int nx, int ny) {
   	 if(nx < 0 || nx >= width || ny < 0 || ny >= height){
   		    System.out.println("Invalid movement: out of bound");
            return true;
        }
   	 return false;
    }
    
    /**
     * check if the new location is occupied by the piece in same color
     * @param piece: the piece to move
     * @param nx : the new x location
     * @param ny : the new y location
     * @return  boolean
     */
    public boolean isValidEndPoint(Piece p, int nx, int ny)
    {
    	if (isOccupied(nx,ny)) {
			if (p.getColor() == getPiece(nx,ny).getColor()) { // occupied by piece in the same team
				System.out.println("Invalid movement: Space Occupied by a friend");
				return false;
			}
//			else {   // will capture an enemy
//				System.out.println("Capture an enemy!");
//			}
		}
		return true; 
    }
    
    /**
     * Check if the move results in a capture
     * @param piece: the piece to move
     * @param nx : the new x location
     * @param ny : the new y location
     * @return whether a capture will occur
     */
    public boolean isCapture(Piece piece, int nx, int ny){
        if(isOccupied(nx,ny) && getPiece(nx,ny).getColor() != piece.getColor()){
            //if(boardArray[finalX][finalY].getType() == Type.KING)
            //    boardArray[finalX][finalY].player.isLoser = true;
        	//System.out.println("Valid movement: Capture an enemy!");
        	return true;
        }
        return false;
    }
    
    /**
     * Check if the king will be checked 
     * @param piece : the piece to move
     * @param nx : the new x location
     * @param ny : the new y location
     * @return
     */
    public boolean isCheckKing(Piece piece, int nx, int ny) {
    	if (isCapture(piece,nx,ny) && (getPiece(nx,ny).getType()==Piece.Type.king)) {
    		// king will be captured, game end
    		System.out.println("Caputre the king!");
    		return true;
    	}
    	return false;
    }
    
    /**
     * Check if king is in check
     * @param piece: the piece to move
     * @param x : the x location
     * @param y : the y location
     * @return boolean
     */
    public boolean isInCheck(int x, int y){
    	
    	Piece p = getPiece(x,y);
    	
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                
            	if(isOccupied(i,j)) {
            		
            		Piece curr = getPiece(i,j);
            		// one or more piece of the opponent can move 
            		if ((curr.getColor() != p.getColor()) && curr.canMove(this, x, y)) {
            			System.out.println(curr.getType().toString() + " at location " + i + ", " + j + " can check king");
                        return true;
            		}
            	}
                
            }
        }
        return false;
    }
    
    
    /**
     * Check if the checked player has a legal move to get out of check
     * @param player a given player
     * @param k_i the player's king row position
     * @param k_j the player's king col position
     * @return Return false if none of player's pieces except king can make a legal move, that is,
     * no matter how that piece moves, the king will be checked. Return true if otherwise.
     */
    public boolean hasLegalMoveToGetOutOfCheck(Piece king){

    	// get the location of the checking piece
    	int checkX = -1;
        int checkY = -1;
    	for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                
            	if(isOccupied(i,j)) {
            		
            		Piece curr = getPiece(i,j);
            		if ((curr.getColor() != king.getColor()) && curr.canMove(this, king.x, king.y)) {
            			checkX = i;
            			checkY = j;
            		}
            	}
                
            }
        }
    	assert(checkX != -1 && checkY != -1);
    	
    	// Check whether can capture the checking piece, with either the king or another piece.
    	for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
            	
            	if (isOccupied(i,j)) {

            		Piece curr = getPiece(i,j);
            		if((curr.getColor() == king.getColor()) && curr.canMove(this, checkX, checkY)) {
            			return true;
            		} 		
            		
            	}                 
            }
        }
    	
    	
    	// Check whether can move the king to an adjacent square where it is not in check
    	if (king.canMove(this, king.x-1, king.y)) {
        		if (!isInCheck(king.x-1,king.y)) return true;	
        }	
    	if (king.canMove(this, king.x+1, king.y)) {
    		if (!isInCheck(king.x+1,king.y)) return true;
    	}
    	if (king.canMove(this, king.x, king.y-1)) {
    		if (!isInCheck(king.x,king.y-1)) return true;
    	}
    	if (king.canMove(this, king.x, king.y+1)) {
    		if (!isInCheck(king.x,king.y+1)) return true;
    	}
    	if (king.canMove(this, king.x-1, king.y-1)) {
    		if (!isInCheck(king.x-1,king.y-1)) return true;
    	}
    	if (king.canMove(this, king.x+1, king.y+1)) {
    		if (!isInCheck(king.x+1,king.y+1)) return true;
    	}
    
        return false;
    }
    
    
    /**
     * Check if the king is checkmated (king is in check and no legal moves to get out of check)
     * @param king: king 
     * @return whether the king is checkmated and the player loses
     */
    public boolean isCheckmate(ChessGame game){
 
    	int[] kingPos = game.getKingPos(this);
    	if (isInCheck(kingPos[0],kingPos[1])) {
    		if (!hasLegalMoveToGetOutOfCheck(getPiece(kingPos[0],kingPos[1]))) {
    			
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Check if there is a stalemate (player has no legal moves, and the player's king is not in check)
     * @param game : the current game
     * @return whether stalemate
     */
    public boolean isStaleMate(ChessGame game){
        
    	int[] kingPos = game.getKingPos(this);
    	if (!isInCheck(kingPos[0],kingPos[1])) {
    		if (!game.playerHasLegalMove(this))
    			return true;    			
    	}
    	return false;
    	
    }
    
    
 
}


