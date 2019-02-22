package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controller.*;
import Game.Board;
import Game.ChessGame;
import Pieces.Piece;
 
 
public class BoardView {
 
	public ChessGame game = new ChessGame(8,8,true);
	public Controller controller = new Controller(game, this);
	
	public JFrame window;
	public JPanel chessBoard;
	public final int width = 8;
	public final int height = 8;
	public JButton[][] chessBoardSquares = new JButton[width][height];
	
	
	public JMenuBar menuBar = new JMenuBar();
	public MenuBarActionListener mal = new MenuBarActionListener();
	public JMenu menu = new JMenu("Game");
    public JMenuItem restart = new JMenuItem("Restart");
	public JMenuItem forfeit = new JMenuItem("Forfeit");
	public JMenuItem undo = new JMenuItem("Undo");
	public JMenu scores = new JMenu("Scores");
	public JMenuItem result = new JMenuItem("Show Results");
	
	public java.awt.Color selectedBackGroundColor;
	public JButton selectedButton;
	public String playerBlack_name = "";
	public int score_black = 0;
	public String playerWhite_name = "";
	public int score_white = 0;
	
    public Icon movedIcon = null;
    public JButton movedButton;
	

    public BoardView(){
    	
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        window = new JFrame("Chess Game");
        window.setSize(500, 500);  
        
        initializeBoardGrid();
        initializeChessPieces();
              
        window.getContentPane().add(chessBoard, BorderLayout.CENTER);
        setUpMenu(window);
        window.setVisible(true);
        
        initializePlayerName();
        getPlayerName();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
    
    /**
     * Initialize the chess board grid panel
     */
    public void initializeBoardGrid(){
    	
        chessBoard = new JPanel(new GridLayout(8,8));
        chessBoard.setOpaque(false);
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(8,8,8,8),
                new LineBorder(Color.BLACK)
                ));
        Color lightGray = new Color(204,204,204);
        chessBoard.setBackground(lightGray);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(lightGray);
        boardConstrain.add(chessBoard);

     // create the chess board squares
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                chessBoardSquares[i][j] = new JButton();
                if (Math.abs(i-j)%2==0)
                	chessBoardSquares[i][j].setBackground(new Color(232,235,239));
                else
                	chessBoardSquares[i][j].setBackground(new Color(125,135,150));
                
                chessBoardSquares[i][j].setBorder(null);
                chessBoardSquares[i][j].setOpaque(true);
                chessBoardSquares[i][j].setBorderPainted(false);
                chessBoardSquares[i][j].setActionCommand(""+i+j);
                chessBoard.add(chessBoardSquares[i][j]);
                
                chessBoardSquares[i][j].addActionListener(new ButtonActionListener());
              
                
            }
        }
        

        
    }
    
    
    
        
   /**
    * Fill in the chess board with pieces in their initial positions
    */
   public void initializeChessPieces() {
	   
   	String[] blackPieces = {"bRook","bKnight","bBishop","bQueen","bKing","bBishop","bKnight","bRook",
               "bPawn","bPawn","bPawn","bPawn","bPawn","bPawn","bPawn","bPawn"};
   	String[] whitePieces = {"wRook","wKnight","wBishop","wQueen","wKing","wBishop","wKnight","wRook",
               "wPawn","wPawn","wPawn","wPawn","wPawn","wPawn","wPawn","wPawn"};
   	
   	for (int i=0; i<8; i++) {
   		chessBoardSquares[0][i].setIcon(new ImageIcon(readImage(blackPieces[i])));
        chessBoardSquares[1][i].setIcon(new ImageIcon(readImage(blackPieces[i+8])));
        chessBoardSquares[6][i].setIcon(new ImageIcon(readImage(whitePieces[i+8])));
        chessBoardSquares[7][i].setIcon(new ImageIcon(readImage(whitePieces[i])));

   	}
   	
   }
    

   /**
    * Create the buffered image
    * @param image_name: image(file) names
    * @return buffered image
    */
   public BufferedImage readImage(String image_name) {
        try {
            return ImageIO.read(getClass().getResource("Image/" + image_name + ".png"));
        } catch (IOException e) {
            return null;
        }
    }

   
    /**
     * set up the menu bar 
     * @param current window 
     */
    public void setUpMenu(JFrame window) {
    	
    	window.setJMenuBar(menuBar);
    	menuBar.add(menu);
    	menu.add(restart);
    	restart.addActionListener(mal);
    	menu.addSeparator();
        menu.add(forfeit);
        forfeit.addActionListener(mal);
        menu.addSeparator();
        menu.add(undo);
        undo.addActionListener(mal);
        
		menuBar.add(scores);
		scores.add(result);
		result.addActionListener(mal);

    }
    
    
    /** 
     * creates a tool bar message that shows player name
     */
    public void initializePlayerName() {
    	getPlayerNamePopUp();
    
    	window.setTitle("Chess Game (" + getPlayerName()+" to Move)");
    }
    
    
    /**
     * Map to piece to image address
     * @param p a chess piece
     * @return Return a string that contains the location of its corresponding image
     */
    private String getPieceImageFile(Piece p){
        if(p.getColor() == Piece.Color.white)
            return "w" + p.getType().toString();
        else
            return "b" + p.getType().toString();
    }

    /**
     * Render the chess board GUI configuartion given a piece array
     * @param pieces piece array that characterizes the chess board
     */
    public void resetIcon(Board board){
    	
        removeAllChessPieces();
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
            	{
                if (board.isOccupied(i,j))
                	
                    chessBoardSquares[i][j].setIcon(new ImageIcon(readImage(getPieceImageFile(board.getPiece(i, j)))));
                    chessBoardSquares[i][j].revalidate();
                }
            }
        }
    }
    
    
    /**
     * a window that let player enter their names
     */
    public void getPlayerNamePopUp() {
    	playerWhite_name = JOptionPane.showInputDialog("PlayerWhite: Enter your name: ");
    	if (playerWhite_name == null)
    		playerWhite_name = "player White";
    	playerBlack_name = JOptionPane.showInputDialog("PlayerBlack: Enter your name: ");
    	if (playerBlack_name == null)
    		playerBlack_name = "player Black";
    }
    
    /**
     * get the current player name
     * @return string
     */
    public String getPlayerName() {
    	if (game.getPlayer() == ChessGame.Player.playerWhite) {
    		return playerWhite_name;
    	}
    	return playerBlack_name;
    }
    
    
    /**
     * Remove mall button (icons) on the board
     */
    public void removeAllChessPieces(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                chessBoardSquares[i][j].setIcon(null);
                chessBoardSquares[i][j].revalidate();
            }
        }
    }
    
    
    /**
	 * Creates a new button listener that will take mall
	 * possible commands that we have programmed.
	 * @param frame the frame that the actionlistener is anchored to
	 * @return the actionlistener to take button press input
	 */
    public class MenuBarActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        	JMenuItem item = (JMenuItem) e.getSource();
            String name = item.getText();
 
            if (name.equals("Restart")) {
            	controller.restart();
            }
            
            if (name.equals("Forfeit")) {
            	controller.forfeit();
            }
            
            if (name.equals("Undo")) {
            	controller.undo();         	
            }
            
            if (name.equals("Show Results")) {
            	controller.showResults();
            }
  
        }
    }
    
    /**
     * Called when button is clicked.
     */
    public class ButtonActionListener implements ActionListener {
    	
        @Override
        public void actionPerformed(ActionEvent e) {
            // change the color of the button with icon when clicked
            JButton button = (JButton) e.getSource();
            int[] loc = controller.getLocation(e.getActionCommand());
            System.out.println("button " + loc[0] + " , " + loc[1] + " clicked");
            
            
            // if click on a button where no pieces(icons) are placed, cannot do anything
            if (button.getIcon() == null && selectedButton == null) 
            	return;
            // select an unselected piece(button)
            else if (button.getIcon() != null && selectedButton == null) {
            	selectedButton = button;
                movedIcon = button.getIcon();
            	controller.changeButtonBackground(button);   // wait for next click to move
            	return;
            }
            
            // move a piece
            if (selectedButton != null) {
            	controller.moveAttempt(e); 	
            	// after a move, clean indicator variables 
            	controller.changeButtonBackground(selectedButton);
            	movedButton = selectedButton;
            	selectedButton = null;
            	selectedBackGroundColor = null;
            }
              
        }

    }

 
    public static void main(String[] args) {
        new BoardView();
    }





}