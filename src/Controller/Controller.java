package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Game.*;
import Game.ChessGame.Player;
import Pieces.*;
import View.*;

public class Controller{
	
	private ChessGame game;
	private BoardView view;
	private JButton[][] buttons;
	
	 /**
     * Constructor for the Controller
     * @param game : current game
     * @param view : the gui of the board
     */
	public Controller(ChessGame game, BoardView view) {
		this.game = game;
		this.view = view;
		buttons = view.getButtons();
	}
	
	
    /**
     * Change the selected button background; if already changed(selected), change back to original background color
     * @param button
     */
    public void changeButtonBackground(JButton button) {
    	// selected, change back 
    	if (button.getBackground() == Color.blue) {
    		 button.setBackground(view.selectedBackGroundColor);
    		 view.selectedBackGroundColor = null;
    		 return;
    	}
    	
    	view.selectedBackGroundColor = button.getBackground();
    	button.setBackground(Color.blue);
    	
    }
	
	/**
	 * Move Piece to another location 
	 * @param e : event
	 */
	public void moveAttempt(ActionEvent e) {
		
		// nothing selected, double check
		if (view.selectedButton == null) {
			return;
		}
		
		JButton button = (JButton) e.getSource();
		
		// get original location of the selected piece and its target location
		int [] loc = getLocation(view.selectedButton.getActionCommand());
		int [] nloc = getLocation(e.getActionCommand());
        //System.out.println("This piece would like to move to " + nloc[0] + " , " + nloc[1]);
        
		boolean isMoved = oneStepMove(loc[0],loc[1],nloc[0],nloc[1]);
		if (isMoved) {
			updateIcon(button);
		}
		
	}
	
	/** 
	 * One step of move, check all possible conditions
	 */
	public boolean oneStepMove(int x, int y, int nx, int ny) {
		
		// TODO : ADD ENDING CONDITIONS
		
		int width = game.board.getWidth();
		int height = game.board.getHeight();
		
		// if selected out of bound
		if (x<0 || x>=width || y<0 || y>=height || nx<0 || nx>=width || ny<0 || ny>=height) {
			 JOptionPane.showMessageDialog(null,"Invalid Selection: Out Of Bound"); 
			 return false;
		}
			
		// if the target location is the same as the current location, nothing happened and user may select again
		if (x==nx && y==ny)
			return false;
		
		Piece p = game.board.getPiece(x, y);
	
		
		if((p.getColor() == Piece.Color.white && game.getPlayer() == Player.playerBlack)){
			JOptionPane.showMessageDialog(null,"You are player Black, you can only select black chess pieces"); 
			return false;
		}
		if((p.getColor() == Piece.Color.black && game.getPlayer() == Player.playerWhite)){
			JOptionPane.showMessageDialog(null,"You are player White, you can only select white chess pieces"); 
			return false;
		}
		
		// valid move
		if (p.canMove(game.board, nx, ny)) {
			p.move(game.board,nx,ny);	
			
		}
		else {
			JOptionPane.showMessageDialog(null,"Invalid Location"); 
			return false;
		}
				
		game.switchPlayer();	
		System.out.println("Now it is Player "+ game.getPlayerColor().toString() + "'s turn");
		
  
		return true;
		
	}
	
	public void updateIcon(JButton button) {
		button.setIcon(view.selectedButton.getIcon());
		view.selectedButton.setIcon(null);
	}
	

	
	/**
	 * Undo event actionListener. A player can only undo one step and only after a step was make.
	 */
	public void undo() {
		// TODO
	}
	
	/**
	 * Restart event actionListener. Show confirmation message to opponent
	 * @param the current window frame
	 */
	public void restart() {
		int reply = JOptionPane.showConfirmDialog(
			    view.window,
			    game.getPlayer()
			    + ", do you agree to restart the game? ",
			    "Make a Decision",
			    JOptionPane.YES_NO_OPTION);
		
		// restart the game
		if (reply == JOptionPane.YES_OPTION) {
			System.out.println("Restart the game");
			resetGame();
			// reset score
			view.score_white = 0;
			view.score_black = 0;
		}
	}
	
	/** 
	 * Reset all the pieces and variables
	 */
	public void resetGame() {
		// clear the board
		game.player = Player.playerWhite;
		game.board.initPieces();
		view.removemAllChessPieces();
		view.initimalizeChessPieces();
		view.selectedBackGroundColor = null;
		view.selectedButton = null;
		
	}
	
	/**
	 * Forfeit event actionListener. update score and restart game
	 * @param the current window frame
	 */
	public void forfeit() {
		int reply = JOptionPane.showConfirmDialog(
			    view.window,
			    game.getPlayer()
			    + ", are you sure to forfeit the game? ",
			    "Make a Decision",
			    JOptionPane.YES_NO_OPTION);
		
		// forfeit the game
		if (reply == JOptionPane.YES_OPTION) {
			System.out.println(game.getPlayer()+ " forfeit");
			// forfeit, lose the game
			if (game.getPlayer() == ChessGame.Player.playerWhite) {
				// opponent get one point
				updateScore(ChessGame.Player.playerBlack);
			}
			else {
				updateScore(ChessGame.Player.playerWhite);
			}
			resetGame();
		}
		
	}
	
	/**
	 * Update player's score
	 */
	public void updateScore(ChessGame.Player player) {
		if (player == ChessGame.Player.playerWhite) {
			view.score_white++;
		}
		else {
			view.score_black++;
		}
		System.out.println("Score: Player Black " + view.score_black+ " : " + view.score_white + " Player White");
	}
	
	
	/**
	 *  Show Result event actionListener. display current scores
	 */
	public void showResults() {
		JOptionPane.showMessageDialog(null,"Score: Player Black " + view.score_black+ " : " + view.score_white + " Player White"); 
	}

	
	
	/**
     * string manipulation to get the x, y location of the selected button 
     * @param s : string of selected button location 
     * @return An integer array of x,y location 
     */
    public int [] getLocation(String s){
        int [] ret = {-1,-1};
        ret[0] = Character.getNumericValue(s.charAt(0));
        ret[1] = Character.getNumericValue(s.charAt(1));
        return ret;
    }
    


	
}