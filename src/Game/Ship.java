package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

public class Ship
{
	private Rectangle2D.Double shipContainer;
	private Color outlineColor;
	private BufferedImage shipImage;
	private boolean hits [][];
	private double currentX;
	private double currentY;
	private int size;
	private boolean lockedInPlace;
	
	private static int shipsPlaced = 0;
	private static Ship selectedShip;
	
	public Ship(int s, String si, double x, double y) 
	{
		this.currentX = x;
		this.currentY = y;
		this.size = s;
		this.hits = new boolean[s][s];
		this.outlineColor = Color.BLACK;
		try 
		{
			this.shipImage = ImageIO.read(new File(si));
			this.shipContainer = new Rectangle2D.Double(x - 1, y - 1, this.shipImage.getWidth() + 2, this.shipImage.getHeight() + 1);
		}
		catch(Exception e)
		{
			System.err.println("Could not read in ship image!");
			System.exit(-1);
		}
	}
	
	public void placeInPlayerGrid(Point p)
	{
		SyrupSea.getInstance().getPlayerGrid().place(SyrupSea.getInstance().getPlayerGrid().findIndices(p)[0], SyrupSea.getInstance().getPlayerGrid().findIndices(p)[1], this);
	}
	
	public void draw(Graphics2D g2)
	{
		if(!lockedInPlace)
		{
			g2.setColor(this.outlineColor);
			g2.draw(this.shipContainer);
		}
		g2.drawImage(this.shipImage, (int)this.currentX, (int)this.currentY, null);
	}
	
	public void moveShip(double x, double y)
	{
		this.currentX = x;
		this.currentY = y;
		
		shipsPlaced++;
	}
	
	public Rectangle2D getRect()
	{
		return this.shipContainer;
	}

	public void setColor(Color c)
	{
		this.outlineColor = c;
	}
	
	public void setLockedInPlace(boolean b)
	{
		this.lockedInPlace = b;
	}
	
	public boolean getLockedInPlace()
	{
		return this.lockedInPlace;
	}

	public static Ship getSelectedShip()
	{
		return selectedShip;
	}
	
	//Sets the shit that is currently selected...
	public static void setSelectedShip(Ship s)
	{
		selectedShip = s;
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	public static int getPlacedShips()
	{
		return shipsPlaced;
	}
	
	public static ArrayList<Ship> randomlyPlaceShips()
	{
		Ship[] returnArray = new Ship[6];
		Square[][] opponentGrid = SyrupSea.getInstance().getOpponentGrid().getSquareArray();
		
		int size = 1;
		int row = (int)(Math.random() * (11 - size));
		int column = (int)(Math.random() * (11 - size));

		boolean beenPlaced;
		
		for(int i = 0; i < 6; i++)
		{
			beenPlaced = false;
			
			if(i < 3)
				size = 1;
			
			else if(i > 2 && i < 5)
				size = 2;
			
			else
				size = 3;
			
			while(!beenPlaced)
			{
				beenPlaced = true;
				
				row = (int)(Math.random() * (10 - size));
				column = (int)(Math.random() * (10 - size));
				
				for(int i2 = row; i2 < row + size; i2++)
					for(int j = column; j < column + size; j++)
						if(opponentGrid[i2][j].getShipInLocation())
							beenPlaced = false;
			}
			returnArray[i] = new Ship(size, "res/waffle" + size + "X" + size + ".png", opponentGrid[row][column].getX(), opponentGrid[row][column].getY());
			SyrupSea.getInstance().getOpponentGrid().place(row, column, returnArray[i]);
			System.out.println(SyrupSea.getInstance().getOpponentGrid());
		}
		return new ArrayList<Ship>(Arrays.asList(returnArray));
	}
	
	public boolean isSunk() 
	{
		for(int i = 0; i < this.hits.length; i++)
			for(int j = 0; j < this.hits[i].length; j++)
				if(!this.hits[i][j])
					return false;
		return true;
	}

}
