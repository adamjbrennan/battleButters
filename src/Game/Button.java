package Game;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Button {
	private Rectangle2D.Double buttonContainer;
	private Color outlineColor;
	private BufferedImage buttonImage;
	
	private int startingX;
	private int startingY;
	

	
	public Button (int x, int y, String buttonImg) 
	{
		this.startingX = x;
		this.startingY = y;
		
		
		try 
		{
			this.buttonImage = ImageIO.read(new File(buttonImg));
		}
		catch(Exception e)
		{
			System.err.println("Could not read in ship image!");
			System.exit(-1);
		}
		
		this.buttonContainer = new Rectangle2D.Double(startingX -1, startingY -1, this.buttonImage.getWidth() -2 , this.buttonImage.getHeight() - 2);
		
	}
	
	public void draw(Graphics2D g2)
	{
		//if(!lockedInPlace)
		//{
		//	g2.setColor(this.outlineColor);
		//	g2.draw(this.buttonContainer);
		//}
		this.outlineColor = Color.black;
		g2.draw(this.buttonContainer);
		g2.drawImage(this.buttonImage, (int)this.startingX, (int)this.startingY, null);
	}

	
	
	public Rectangle2D.Double getRect()
	{
		return this.buttonContainer;
	}
}
	
	
