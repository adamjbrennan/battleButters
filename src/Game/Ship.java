package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

public class Ship
{
	private Rectangle2D.Double shipContainer;
	private Color outlineColor;
	private BufferedImage shipImage;
	
	private boolean hits [][];
	
	private int startingX;
	private int startingY;
	
	private double currentX;
	private double currentY;
	
	private int rows;
	private int columns;
	
	private boolean isSunk;
	private boolean isSelected;
	private boolean lockedInPlace;
	private static Ship selectedShip;
	
	public Ship(int r, int c, String si, int x, int y) 
	{
		this.startingX = x;
		this.startingY = y;
		
		this.currentX = this.startingX;
		this.currentY = this.startingY;
		
		this.rows = r;
		this.columns = c;
		
		this.hits = new boolean[rows][columns];
		
		try 
		{
			this.shipImage = ImageIO.read(new File(si));
		}
		catch(Exception e)
		{
			System.err.println("Could not read in ship image!");
			System.exit(-1);
		}
		this.outlineColor = Color.BLACK;
		this.shipContainer = new Rectangle2D.Double(x - 1, y - 1, this.shipImage.getWidth() + 2, this.shipImage.getHeight() + 1);
		this.isSelected = false;
		this.isSunk = false;
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
		shipContainer.x = this.currentX - 1;
		shipContainer.y = this.currentY - 1;
	}
	
	public Rectangle2D.Double getRect()
	{
		return this.shipContainer;
	}

	public void setColor(Color c)
	{
		this.outlineColor = c;
	}
	
	public void setToggleSelected(boolean b)
	{
		this.isSelected = b;
	}
	
	public boolean getIseSelected()
	{
		return this.isSelected;
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
	
	//Gets the number of rows that the ship has...
	public int getRows()
	{
		return rows;
	}
	
	//Gets the number of columns that the ship has...
	public int getColumns()
	{
		return columns;
	}
	
	public boolean isSunk() 
	{
		for(int i = 0; i < this.hits.length; i++)
		{
			for(int j = 0; j < this.hits[i].length; j++)
			{
				if(!this.hits[i][j])
				{
					return false;
				}
			}
		}
		return true;
	}

}
