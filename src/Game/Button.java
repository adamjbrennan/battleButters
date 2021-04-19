package Game;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Button extends Rectangle2D.Double {
	
	private int startingX;
	private int startingY;
	
	private Color outlineColor;
	
	public Button (int x, int y, int width, int height, Color c) 
	{
		super(x, y, width, height);
		this.outlineColor = c;
	}
	
	public void draw(Graphics2D g2)
	{
		//if(!lockedInPlace)
		//{
		//	g2.setColor(this.outlineColor);
		//	g2.draw(this.buttonContainer);
		//}
		g2.setColor(outlineColor);
		g2.draw(this);
	}
}
	
	
