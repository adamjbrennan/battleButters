package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Square extends Rectangle2D.Double implements Drawable
{
	private BufferedImage display;
	
	public Color fillColor; 
	
	private boolean shipPlaced; 
	
	private static final int SQUARE_DIMENSIONS = 34; 
	
	public Square(double x, double y, Color fc)
	{
		super(x, y, SQUARE_DIMENSIONS, SQUARE_DIMENSIONS);
		this.fillColor = fc;
		this.display = null;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.draw(this);
		g.setColor(fillColor);
		g.fill(this);
		if(display != null)
		{
			g.drawImage(display, (int)this.getX(), (int)this.getY(), null);
		}
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void loadDisplay() 
	{
		if(this.display == null)
		{
			if(shipPlaced)
			{
				try
				{
					if(this.y >= 347)
						this.display = ImageIO.read(new File("res/butter.png"));
					else
						this.display = ImageIO.read(new File("res/butterOpponent.png"));
					GameConsole.getInstance().writeLine("1X Hit!\n");
				}
				catch (IOException e) 
				{
					System.err.println("Could not read in butter image!");
					System.exit(-1);
				}
			}
			else
			{
				try
				{
					this.display = ImageIO.read(new File("res/splash.png"));
				}
				catch (IOException e)
				{
					System.err.println("Could not read in splash image!");
					System.exit(-1);
				}
			}
		}
	}
	
	public void setColor(Color c, int a)
	{
		this.fillColor = new Color(c.getRed(), c.getGreen(), c.getBlue(), a);
	}
	
	public static int getSquareDimensions()
	{
		return SQUARE_DIMENSIONS;
	}
	
	public void setShipInLocation(boolean b)
	{
		this.shipPlaced = b;
	}
	
	public boolean getShipInLocation()
	{
		return this.shipPlaced;
	}
	
	
}
