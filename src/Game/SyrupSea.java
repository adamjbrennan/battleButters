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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class SyrupSea extends JPanel implements KeyListener, MouseInputListener {
	
	private static SyrupSea single_sea = null;

	private Grid playerGrid;
	private Grid opponentGrid;
	
	private BufferedImage gameBoard;
	
	private ArrayList<Ship> playerShips = new ArrayList<Ship>();
	private ArrayList<Ship> opponentShips = new ArrayList<Ship>();
	
	private Shot outgoingShot;
	
	int frames = 0;
	int seconds = 0;
	
	
	
	private SyrupSea(int width, int height, Color color)
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
		repaint();
	}
	
	public static SyrupSea getInstance()
	{
		if(single_sea == null)
			single_sea = new SyrupSea(BattleButters.getGameWidth(), BattleButters.getGameHeight(), Color.WHITE);
		
		return single_sea;
	}
	 
	@Override 
	protected void paintComponent(Graphics g) 
	{
		System.err.println("Graphics Rendered!");
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(gameBoard, 0, 0, null);
		opponentGrid.draw(g2);
		playerGrid.draw(g2);
		
		if(outgoingShot != null)
		{
			if(!outgoingShot.getBeingFired())
				outgoingShot.runFireAnimation(g2);

			System.out.println("Drew the shot!");
			outgoingShot.draw(g2);
		}
		
//		long start = System.currentTimeMillis();
//		long delta = 0;
//		long frameTime = 1000/60;
//		
//		while(delta < frameTime) 
//		{
//			delta = System.currentTimeMillis() - start;
//		}
//		
//		frames++;
//		
//		if(frames%60==0) 
//		{
//			seconds++;
//		}
//		
//		repaint();
	}
	

	
	@Override
	public void mouseClicked(MouseEvent me) {
		boolean clickInOpponentGrid = opponentGrid.find(me.getPoint());
		if(clickInOpponentGrid)
		{
			outgoingShot = new Shot(me.getPoint(), 1, 1, "res/greenBall.png");
		}
		repaint();
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

	//FINISHED
	public void mouseMoved(MouseEvent me) 
	{
		boolean opponent = opponentGrid.find(me.getPoint());
		boolean player = playerGrid.find(me.getPoint());
		
		if(opponent || player)
			this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		else
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		switch(arg0.getKeyCode())
		{
			
				
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
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