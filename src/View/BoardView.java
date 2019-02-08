package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
 
 
public class BoardView implements ActionListener{
 
	private JPanel chessBoard;
	private final int width = 8;
	private final int height = 8;
	private JButton[][] chessBoardSquares = new JButton[width][height];
	
    public BoardView(){
    	
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        JFrame window = new JFrame("Chess Game");
        window.setSize(500, 500);  
        
        initializeBoardGrid();
        
        window.getContentPane().add(chessBoard, BorderLayout.CENTER);
        //window.getContentPane().add(info, BorderLayout.EAST);
        //JPanel myPanel = initializePanel();
        //initializeButton(myPanel);
        //setUpMenu(window);
        //window.setContentPane(myPanel);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initializeBoardGrid(){
    	
        chessBoard = new JPanel(new GridLayout(8,8));
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
                	chessBoardSquares[i][j].setBackground(Color.WHITE);
                else
                	chessBoardSquares[i][j].setBackground(Color.GRAY);
                chessBoardSquares[i][j].setBorder(null);
                chessBoardSquares[i][j].setOpaque(true);
                //chessBoardSquares[i][j].setActionCommand(""+i+j);
                chessBoard.add(chessBoardSquares[i][j]);
            }
        }
    }
  
 
    private void initializeButton(JPanel myPanel) {
        JButton button = new JButton("Click me");
        button.addActionListener(this);
        myPanel.add(button, BorderLayout.SOUTH);
    }
 
    private JPanel initializePanel() {
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(500,500));
        myPanel.setLayout(new BorderLayout());
        return myPanel;
    }
 
    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);
        menubar.add(file);
        window.setJMenuBar(menubar);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
                    "I was clicked by "+e.getActionCommand(),
                    "Title here", JOptionPane.INFORMATION_MESSAGE);
    }
 
    public static void main(String[] args) {
        new BoardView();
    }
}