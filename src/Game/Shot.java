package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shot 
{
	private Point sp;
	//TODO - Implement different effected widths for different shots
	private int effectedWidth;
	//TODO - Implement different effected heights for different shots 
	private int effectedHeight;
	private double currentX = 425.0;
	private double currentY = 835.0;
	private BufferedImage shotImage; 
	boolean beingFired;
	
	public Shot(Point p, int ew, int eh, String fp)
	{
		this.sp = p;
		this.effectedWidth = ew;
		this.effectedHeight = eh;
		this.beingFired = false;
		try
		{
			this.shotImage = ImageIO.read(new File(fp));
		} 
		catch (IOException e)
		{
			System.err.println("Could not read in shot image!");
			System.exit(-1);
		}
	}
	
	public void runFireAnimation(Graphics2D g2)
	{
		currentX = sp.getX() - 20;
		currentY = sp.getY() - 15;
//		this.beingFired = true;
//		int iterations = 0;
//		
//		double targX = sp.getX() - 5;
//		double targY = sp.getY() - 5;
//		
//		double differenceX = targX - currentX;
//		double differenceY = targY - currentY;
//		
//		while((int)currentX != targX && (int)currentY != targY)
//		{
//			currentX += differenceX / 10000.0;
//			currentY += differenceY / 10000.0;
//			System.out.println(currentX);
//			System.out.println(currentY);
//			if(iterations % 3 == 0)
//				SyrupSea.getInstance().paintComponent(g2);
//			iterations++;
//		}
	}
	
	public void draw(Graphics2D g2)
	{
		g2.drawImage(shotImage, (int)currentX, (int)currentY, null);
	}
	
	public boolean getBeingFired()
	{
		return beingFired; 
	}
}
