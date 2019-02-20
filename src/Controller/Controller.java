package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.*;

import Game.*;
import Game.ChessGame.Player;
import Game.State;
import Pieces.*;
import View.*;

public class Controller{
	
	private ChessGame game;
	private BoardView view;
	
	 /**
     * Constructor for the Controller
     * @param game : current game
     * @param view : the gui of the board
     */
	public Controller(ChessGame game, BoardView view) {
		this.game = game;
		this.view = view;
	}
	
	
    /**
     * Change the selected button background; if already changed(selected), change back to original background color
     * @param button
     */
    public void changeButtonBackground(JButton button) {
    	// selected, change back 
    	if (button.getBackground() == Color.pink) {
    		 button.setBackground(view.selectedBackGroundColor);
    		 view.selectedBackGroundColor = null;
    		 return;
    	}
    	
    	view.selectedBackGroundColor = button.getBackground();
    	button.setBackground(Color.pink);
    	
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
        
		oneStepMove(button, loc[0],loc[1],nloc[0],nloc[1]);
		

	}
	
	/** 
	 * One step of move, check all possible conditions
	 */
	public void oneStepMove(JButton button, int x, int y, int nx, int ny) {
	
		
		int width = game.board.getWidth();
		int height = game.board.getHeight();
		
		// if selected out of bound
		if (x<0 || x>=width || y<0 || y>=height || nx<0 || nx>=width || ny<0 || ny>=height) {
			 JOptionPane.showMessageDialog(null,"Invalid Selection: Out Of Bound"); 
			 return;
		}
			
		// if the target location is the same as the current location, nothing happened and user may select again
		if (x==nx && y==ny)
			return;
		
		Piece p = game.board.getPiece(x, y);
		Piece np = game.board.getPiece(nx,ny);
	
		
		if((p.getColor() == Piece.Color.white && game.getPlayer() == Player.playerBlack)){
			JOptionPane.showMessageDialog(null,"You are player Black, you can only select black chess pieces"); 
			return;
		}
		if((p.getColor() == Piece.Color.black && game.getPlayer() == Player.playerWhite)){
			JOptionPane.showMessageDialog(null,"You are player White, you can only select white chess pieces"); 
			return;
		}
		
		// check if there already exists a winner, if not, continue the game
		if (game.getWinner() == null) {
			if (p.canMove(game.board, nx, ny)) {
				
				if (game.board.isCheckKing(p, nx, ny)) {
					// win after this move
					game.setWinner(game.getPlayer());
				}
				
				State history = new State(p,x,y,np,nx,ny);
				game.getHistory().push(history);
				
				// MOVE 
				p.move(game.board,nx,ny);
				updateIcon(button);
				
				// result in a winner after this move, game end
				if (game.getWinner() != null) {
					JOptionPane.showMessageDialog(null,view.getPlayerName()+" wins!"); 
					updateScore(game.getPlayer());
					showResults();
					return;
				}
			}
			
			// invalid move, player try again
			else {
				JOptionPane.showMessageDialog(null,"cannot move to this location"); 
				return;
			}
		}
		
		else {  // winner exists
			return;
		}
		
				
		game.switchPlayer();	
		view.window.setTitle("Chess Game  (" + view.getPlayerName()+" to Move)");
		System.out.println("Now it is "+ view.getPlayerName() + "'s turn");
		
	}
	
	
	/**
	 * update piece icons
	 * @param button 
	 */
	public void updateIcon(JButton button) {
		button.setIcon(view.selectedButton.getIcon());
		view.selectedButton.setIcon(null);
	}
	


	/**
	 * Undo event actionListener. A player can undo multiple steps until the first move
	 */
	public void undo() {
		
		Stack<State> history = game.getHistory();
		if (history.empty()) {
			JOptionPane.showMessageDialog(null,"Cannot Undo: no previous step");
            return;
		}
		
		game.switchPlayer();
		
		State curr = history.pop();
		game.board.setPiece(curr.p, curr.x, curr.y);
		game.board.setPiece(curr.np, curr.nx, curr.ny);
		
		view.resetIcon(game.board);
	}
	
	/**
	 * Restart event actionListener. Show confirmation message to opponent
	 * @param the current window frame
	 */
	public void restart() {
		int reply = JOptionPane.showConfirmDialog(
			    view.window,
			    view.getPlayerName()
			    + ", do you agree to restart the game? ",
			    "Make a Decision",
			    JOptionPane.YES_NO_OPTION);
		
		// restart the game
		if (reply == JOptionPane.YES_OPTION) {
			System.out.println("Restart the game");
			resetGame();
		}
	}
	
	/** 
	 * Reset all the pieces and variables
	 */
	public void resetGame() {
		// clear the board
		game.setWinner(null);
		game.getHistory().clear();
		game.player = Player.playerWhite;
		game.board.initPieces(false);
		view.removeAllChessPieces();
		view.initializeChessPieces();
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
			    view.getPlayerName()
			    + ", are you sure to forfeit the game? ",
			    "Make a Decision",
			    JOptionPane.YES_NO_OPTION);
		
		// forfeit the game
		if (reply == JOptionPane.YES_OPTION) {
			System.out.println(view.getPlayerName()+ " forfeit");
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
		System.out.println("Score: " + view.playerBlack_name + " " + view.score_black+ " : " + view.score_white + " " + view.playerWhite_name);
	}
	
	
	/**
	 *  Show Result event actionListener. display current scores
	 */
	public void showResults() {
		JOptionPane.showMessageDialog(null,"Score: " + view.playerBlack_name + " " + view.score_black+ " : " + view.score_white + " " + view.playerWhite_name);
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