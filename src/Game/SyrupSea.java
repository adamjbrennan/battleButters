package Game;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class SyrupSea extends JPanel implements KeyListener, MouseInputListener{

	private Grid playerGrid;
	private Grid opponentGrid;
	private BufferedImage gameBoard;
	private ArrayList<Square> firedShots = new ArrayList<Square>();
	boolean isTurn; 
	
	public SyrupSea(int width, int height, Color color)
	{
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(color); 
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		try
		{
			gameBoard = ImageIO.read(new File("res/gameTemplate.png"));
		}
		catch(Exception e)
		{
			System.err.println("Could not read in game template image! Check file path!");
			System.exit(-1);
		}

		opponentGrid = new Grid(0, 1, new Color(0, 255, 0, 0));
		playerGrid = new Grid(0, 347, new Color(255, 0, 0, 0));
	}
	 
	@Override 
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(gameBoard, 0, 0, null);
		opponentGrid.draw(g2);
		playerGrid.draw(g2);
		
		g2.setColor(Color.BLACK);
		for(Rectangle2D.Double a: firedShots)
		{
			g2.draw(a);
			g2.setColor(Color.GREEN);
			g2.fill(a);
		}
		
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}
	

	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		boolean clickInOpponentGrid = opponentGrid.find(arg0.getPoint());
		if(clickInOpponentGrid)
		{
			System.out.println("Syrup sea clicked!");
			Color fillColor = Color.GREEN;
			Square firedShot = new Square(835, 425, 10, fillColor);
			firedShots.add(firedShot);
			int targX = arg0.getX() - 5;
			int targY = arg0.getY() - 5;
			double differenceX = targX - firedShot.x;
			double differenceY = targY - firedShot.y; 
			
			while((int)firedShot.x != targX && (int)firedShot.y != targY)
			{
				firedShot.x += differenceX / 100000.0;
				firedShot.y += differenceY / 100000.0;
				System.out.println(firedShot.x);
				System.out.println(firedShot.y);
				
			}
		}
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
		boolean opponent = opponentGrid.find(arg0.getPoint());
		boolean player = playerGrid.find(arg0.getPoint());
		if(opponent || player)
			this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		else
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode())
		{
			case KeyEvent.VK_SPACE:
				break;
				
				
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
//		switch(arg0.getKeyCode())
//		{
//			case KeyEvent.VK_SPACE:
//				this.remove(visualization);
//				break; 
//		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
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