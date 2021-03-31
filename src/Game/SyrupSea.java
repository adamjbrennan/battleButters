package Game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 * Sets the length and width of the playing field and
 * updates the field once a shot has been taken.
 *
 * @author Collective Brain Cell Inc.
 * @version 1.0 3/12/21
 */

public class SyrupSea extends JPanel implements KeyListener, MouseInputListener {
	/*
	 * Building a three dimensional array for each player. The Z-position of a
	 * waffle is only changed once the waffle has completely hit. All of the
	 * elements of the array start out as null. If there is a waffle inside a spot
	 * in the array, elements are set to true. If a spot is shot at and is a hit, if
	 * a spot is shot at and is a miss, or if a waffle is completely sunk, the
	 * elements of the array are set to false.
	 */

	private Grid playerGrid;
	private Grid opponentGrid;
	private BufferedImage gameBoard;
	
	boolean isTurn; 
	
	public SyrupSea(int width, int height, Color color) throws IOException  
	{
		setPreferredSize(new Dimension(width, height));
		setBackground(color); 
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		gameBoard = ImageIO.read(new File("res/gameTemplate.png"));
		opponentGrid = new Grid(0, 1, new Color(0, 255, 0, 0));
		playerGrid = new Grid(0, 347, new Color(255, 0, 0, 0));
		
		repaint();
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(gameBoard, 0, 0, null);
		opponentGrid.draw(g2);
		playerGrid.draw(g2);
		
	}
	

	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) 
	{
		boolean opponent = opponentGrid.find(arg0);
		boolean player = playerGrid.find(arg0);
		if(opponent || player)
			this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		else
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Update the grid display at a specific point. Only takes one specific point in
	 * space, so the PowerUps that have alrger damage areas will need to send each
	 * point they hit.
	 * 
	 * @param xP1 - X Position for the first player
	 * @param yP1 - Y Position for the first player
	 * @param zP1 - Z Position for the first player
	 * 
	 */
	
}