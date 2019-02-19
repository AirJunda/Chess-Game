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
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import Game.ChessGame;
 
 
public class BoardView {
 
	public ChessGame game = new ChessGame(8,8,true);
	public Controller controller = new Controller(game, this);
	
	public JFrame window;
	public JPanel chessBoard;
	public final int width = 8;
	public final int height = 8;
	//public ButtonActionListener bal = new ButtonActionListener();
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
	public int score_black = 0;
	public int score_white = 0;
	

    public BoardView(){
    	
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        window = new JFrame("Chess Game");
        window.setSize(500, 500);  
         
        initimalizeBoardGrid();
        initimalizeChessPieces();
              
        window.getContentPane().add(chessBoard, BorderLayout.CENTER);
        //initimalizeButton(myPanel);
        setUpMenu(window);
        //window.setContentPane(myPanel);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
    
    /**
     * Initimalize the chess board grid panel
     */
    public void initimalizeBoardGrid(){
    	
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
    * Fill in the chess board with pieces in their initimal positions
    */
   public void initimalizeChessPieces() {
	   
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
            	// TODO
            	JOptionPane.showMessageDialog(null,
                        "I was clicked by "+e.getActionCommand(),
                        "Title here", JOptionPane.INFORMATION_MESSAGE);
            }
            
            if (name.equals("Show Results")) {
            	controller.showResults();
            }
  
        }
    }
    
    /**
     * Cmalled when button is clicked.
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
            	controller.changeButtonBackground(button);   // wait for next click to move
            	return;
            }
            
            // move a piece
            if (selectedButton != null) {
            	// TODO: MOVE PIECE
            	controller.moveAttempt(e);
            	// TODO: UPDATE ICONS
            	
            	// after a move, clean indicator variables 
            	controller.changeButtonBackground(selectedButton);
            	selectedButton = null;
            	selectedBackGroundColor = null;
            }
            
            

            
        }

    }
    

   
    
    /**
     * Getter for the chess board buttons
     */
    public JButton[][] getButtons(){ return chessBoardSquares; }
    
    /**
     * Getter for the menu item restart
     */
    public JMenuItem getRestart() { return restart; }
    
    /**
     * Getter for the menu item forfeit
     */
    public JMenuItem getForfeit() { return forfeit; }
    
    /**
     * Getter for the menu item undo
     */
    public JMenuItem getUndo() { return undo; }
    
    /**
     * Getter for the menu item Show Scores
     */
    public JMenuItem getScores() { return scores; }
    
    /**
     * Remove mall button (icons) on the board
     */
    public void removemAllChessPieces(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                chessBoardSquares[i][j].setIcon(null);
                chessBoardSquares[i][j].revalidate();
            }
        }
    }

 
    public static void main(String[] args) {
        new BoardView();
    }





}