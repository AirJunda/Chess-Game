package Game;

import javax.swing.Icon;
import javax.swing.JButton;

import Pieces.*;

public class State {
	
	public int x, y, nx, ny;
	public Piece p, np;
//	JButton oldButton, newButton;
//	Icon oldIcon, newIcon;
	
	/**
	 * Constructor for State
	 * @param p
	 * @param x
	 * @param y
	 * @param oldButton
	 * @param oldIcon
	 * @param nx
	 * @param np
	 * @param ny
	 * @param newButton
	 * @param newIcon
	 */
	public State(Piece p, int x, int y, Piece np, int nx, int ny) {
		this.x = x;
		this.y = y;
		this.nx = nx;
		this.ny = ny;
		this.p = p;
		this.np = np;
//		this.oldButton = oldButton;
//		this.newButton = newButton;
//		this.oldIcon = oldIcon;
//		this.newIcon = newIcon;
	}
	
	
	
	
	
	
}