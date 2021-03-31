package Game;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Square extends Rectangle2D.Double
{
	private BufferedImage display; 
	
	public Square(double x, double y, double s)
	{
		super(x, y, s, s);
	}
	
	public void show(Graphics2D g)
	{
		g.draw(this);
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
}
