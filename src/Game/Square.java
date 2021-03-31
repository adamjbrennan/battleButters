package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Square extends Rectangle2D.Double
{
	private BufferedImage display;
	private Color fillColor; 
	
	public Square(double x, double y, double s, Color fc)
	{
		super(x, y, s, s);
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
}
