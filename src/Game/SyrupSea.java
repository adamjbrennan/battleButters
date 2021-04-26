package Game;

import java.awt.BorderLayout;
import java.awt.Color;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

public class SyrupSea extends JPanel implements KeyListener, MouseInputListener, ActionListener {
	
	//Single instance of the SyrupSea class...
	private static SyrupSea single_sea = null;
	//Balance of the player...
	private int playerBalance = 0;

	//Player's grid...
	private Grid playerGrid;
	//Opponent's grid...
	private Grid opponentGrid;
	
	//Buttons on screen
	private ArrayList<Rectangle2D.Double> buttons;
	
	//Game board image...
	private BufferedImage gameBoard;
	
	//Player's ships on screen...
	private ArrayList<Ship> playerShips;
	//The opponent's ships on screen...
	private ArrayList<Ship> opponentShips;
	//ArrayList containing all gameboards...
	private ArrayList<BufferedImage> gameBoards;
	
	//Current type of the shot selected by the player...
	private ShotType playerShotType;
	
	//If it is the player's turn...
	private boolean isTurn;
	//If the player has placed all of their ships...
	private boolean setup; 
	
	/**
	 * @param width - width of the syrup sea
	 * @param height - height of the syrup sea
	 * @param color - color of the syrup sea background
	 */
	private SyrupSea(int width, int height, Color color)
	{
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(color); 
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);

		opponentGrid = new Grid(0, 1, new Color(0, 255, 0, 50));
		playerGrid = new Grid(0, 347, new Color(255, 0, 0, 50));
		
		setup = false;
		isTurn = false;
		
		this.playerShotType = ShotType.NORMAL;
		
		playerShips = new ArrayList<Ship>();
		opponentShips = new ArrayList<Ship>();
		gameBoards = new ArrayList<BufferedImage>();
		buttons = new ArrayList<Rectangle2D.Double>();
		
		buttons.add(new Rectangle2D.Double(BattleButters.getGameWidth() - 89, BattleButters.getGameHeight() - 80, 100, 100));
		buttons.add(new Rectangle2D.Double(BattleButters.getGameWidth() - 55, BattleButters.getGameHeight() - 455, 35, 40));
		buttons.add(new Rectangle2D.Double(BattleButters.getGameWidth() - 55, BattleButters.getGameHeight() - 390, 35, 40));
		
		
		try
		{
			gameBoards.add(ImageIO.read(new File("res/opponentTurn.png")));
			gameBoards.add(ImageIO.read(new File("res/yourTurn.png")));
		}
		catch(Exception e)
		{
			System.err.println("Could not read in game template image! Check file path!");
			System.exit(-1);
		}
		
		gameBoard = gameBoards.get(0);
		
		playerShips.add(new Ship(3, "res/waffle3X3.png", 7.0, 693.0));
		playerShips.add(new Ship(2, "res/waffle2X2.png", 129.0, 711.0));
		playerShips.add(new Ship(2, "res/waffle2X2.png", 211.0, 711.0));
		playerShips.add(new Ship(1, "res/waffle1X1.png", 299.0, 690.0));
		playerShips.add(new Ship(1, "res/waffle1X1.png", 299.0, 727.0));
		playerShips.add(new Ship(1, "res/waffle1X1.png", 299.0, 764.0));

		GameConsole.getInstance().writeLine("Welcome to BattleButters! Place your waffles!\n");
		
		repaint();
	}
	
	/**
	 * Creates an instance of SyrupSea if it does not exist. Then returns it.
	 * @return - Returns the single instance of SyrupSea
	 */
	public static SyrupSea getInstance()
	{
		if(single_sea == null)
		{
			single_sea = new SyrupSea(BattleButters.getGameWidth(), BattleButters.getGameHeight(), Color.WHITE);
		}
		
		return single_sea;
	}
	
	@Override 
	/**
	 * All objects on screen are drawn to the SyrupSea here
	 */
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(gameBoard, 0, 0, null);
		
		for(Ship s: playerShips)
			s.draw(g2);
		
		opponentGrid.draw(g2);
		playerGrid.draw(g2);
		g2.setColor(Color.BLACK);
		g2.drawString(String.valueOf(playerBalance), 350, 665);
	}
	
	@Override
	/**
	 * Triggers when mouse is clicked...
	 */
	public void mouseClicked(MouseEvent me) 
	{	
		//If the player has not placed all of their waffles...
		if(!setup)
		{
			//If the player has a ship selected and clicks in the player's grid
			if(Ship.getSelectedShip() != null && playerGrid.find(me.getPoint()))
			{
				//If the waffle is outside of the grid...
				if(Ship.getSelectedShip().getSize() == 3 && (playerGrid.findIndices(me.getPoint())[0] > 7 || playerGrid.findIndices(me.getPoint())[1] > 7) || Ship.getSelectedShip().getSize() == 2 && playerGrid.findIndices(me.getPoint())[0] > 8 || playerGrid.findIndices(me.getPoint())[1] > 8)
					return;
				Ship.getSelectedShip().moveShip(playerGrid.getSquareArray()[playerGrid.findIndices(me.getPoint())[0]][playerGrid.findIndices(me.getPoint())[1]].x, playerGrid.getSquareArray()[playerGrid.findIndices(me.getPoint())[0]][playerGrid.findIndices(me.getPoint())[1]].y);
				Ship.getSelectedShip().setLockedInPlace(true);
				Ship.getSelectedShip().setColor(Color.BLACK);
				Ship.getSelectedShip().placeInPlayerGrid(me.getPoint());
				GameConsole.getInstance().writeLine("Placed a waffle! Press the 'N' key when all waffles have been placed.\n");
				playerGrid.setColor(Color.BLACK, 0);
				Ship.setSelectedShip(null);
				return;
			}
			
			//If no ship is selected...
			else
			{
				for(Ship s: playerShips)
				{
					//If the ship has already been placed...
					if(s.getLockedInPlace())
					{
						continue;
					}
					
					//If the ship is clicked...
					if(s.getRect().contains(me.getPoint()) && Ship.getSelectedShip() == null)
					{
						Ship.setSelectedShip(s);
						s.setColor(Color.RED);
					}
				}
			}
			
			
			
		}
		
		//The player has placed all of their waffles...
		else 
		{
			//If it is the player's turn and they click in the opponent grid...
			if(isTurn && opponentGrid.find(me.getPoint()))
			{
				isTurn = false;
				new Shot(me.getPoint(), playerShotType, true).fire();
				gameBoard = gameBoards.get(0);
				opponentGrid.getSquareArray()[opponentGrid.findIndices(me.getPoint())[0]][opponentGrid.findIndices(me.getPoint())[1]].setColor(Color.BLACK, 0);
				playerShotType = ShotType.NORMAL;
				if(opponentGrid.isDefeated())
				{
					GameConsole.getInstance().writeLine("You win!\n");
					System.exit(0);
				}
				else
					GameConsole.getInstance().writeLine("It is the CPU's turn! Press the 'N' key to simulate their move!\n");
			}
		}
		
		//If click button is pressed...
		if (buttons.get(0).contains(me.getPoint())) 
		{
			System.exit(0);
		}
		//If it is the player's turn and jelly shot button is pressed.
		else if(buttons.get(1).contains(me.getPoint()) && isTurn)
		{
			//If a shot has not already been selected....
			if(this.playerShotType == ShotType.NORMAL)
			{
				if(this.playerBalance >= 10)
				{
					GameConsole.getInstance().writeLine("Purchased JellyShot power up!\n");
					this.playerBalance -= 10;
					this.playerShotType = ShotType.JELLYSHOT;
				}
				else
					GameConsole.getInstance().writeLine("You are broke! Enter some product codes!\n");
			}
			else
				GameConsole.getInstance().writeLine("You have already purchased a power up this turn!\n");
		}
		//If the player clicks the JamShot button...
		else if(buttons.get(2).contains(me.getPoint()) && isTurn)
		{
			//If no power up has been selected this turn...
			if(this.playerShotType == ShotType.NORMAL)
			{
				if(this.playerBalance >= 25)
				{
					GameConsole.getInstance().writeLine("Purchased JamShot power up!\n");
					this.playerBalance -= 25;
					this.playerShotType = ShotType.JAMSHOT;
				}
				else
					GameConsole.getInstance().writeLine("You are broke! Enter some product codes!\n");
			}
			else
				GameConsole.getInstance().writeLine("You have already purchased a power up this turn!\n");
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
	@Override
	public void mouseMoved(MouseEvent me) 
	{
		//If the mouse is moved in the player grid...
		boolean opponent = opponentGrid.find(me.getPoint());
		//If the mouse is moved in the opponent grid...
		boolean player = playerGrid.find(me.getPoint());
		
		//if it is the player's turn and the mouse is in the opponent or player grid...
		if(isTurn && (opponent || player)) {
			this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		//If it is not the player's turn
		else {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		//If it is the player's turn
		if(isTurn)
		{
			
			playerGrid.shotHover(me.getPoint());
			opponentGrid.shotHover(me.getPoint());
		}
		//If the selected ship is not equal to null
		else if(Ship.getSelectedShip() != null)
		{
			playerGrid.placeHover(me.getPoint(), Ship.getSelectedShip());
		}
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		
		switch(arg0.getKeyCode())
		{
		//If the key pressed is N
			case KeyEvent.VK_N:
				if(!setup && Ship.getPlacedShips() == 6)
				{
					opponentShips = Ship.randomlyPlaceShips();
					opponentGrid.runOpponentPlaceAnimation();
					
					setup = true;
					isTurn = true;
					
					gameBoard = gameBoards.get(1);
				}
				else if (setup && !isTurn)
				{
					isTurn = true;
					new Shot(Shot.chooseShot(), false).fire();
					if(playerGrid.isDefeated())
					{
						GameConsole.getInstance().writeLine("You lose!\n");
						System.exit(0);
					}
					else
						GameConsole.getInstance().writeLine("It is your turn! Fire a shot!\n");
					gameBoard = gameBoards.get(1);
	
				}
			break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}

	public Grid getPlayerGrid()
	{
		return this.playerGrid;
	}
	
	public Grid getOpponentGrid()
	{
		return this.opponentGrid;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void addCurrency(int c)
	{
		this.playerBalance += c;
	}
	
}