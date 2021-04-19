package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Square extends Rectangle2D.Double
{
	private BufferedImage display;
	//testing publicity here
	public Color fillColor; 
	private boolean shipPlaced; 
	private static final int SQUARE_DIMENSIONS = 34; 
	
	public Square(double x, double y, Color fc)
	{
		super(x, y, SQUARE_DIMENSIONS, SQUARE_DIMENSIONS);
		this.fillColor = fc;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.draw(this);
		g.setColor(fillColor);
		g.fill(this);
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void setColor(Color c, int a)
	{
		this.fillColor = new Color(c.getRed(), c.getGreen(), c.getBlue(), a);
	}
	
	public static int getSquareDimensions()
	{
		return SQUARE_DIMENSIONS;
	}
	
	public void toggleShipInLocation()
	{
		this.shipPlaced = !this.shipPlaced;
	}
	
	
}
