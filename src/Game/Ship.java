package Game;

import java.awt.image.BufferedImage;

import javax.imageio.stream.FileImageOutputStream;

public class Ship
{
	BufferedImage shipImage;
	private int startingX;
	private int startingY;
	private boolean isLocked = true; 
	
	public Ship(int rows, int columns, BufferedImage si, int x, int y)
	{
		this.startingX = x;
		this.startingY = y;
		this.shipImage = si;
//		this.shipFrags = new ShipFragment[rows][columns];
//		fillShip();
	}
	
	private void toggleLock()
	{
		isLocked = !isLocked;
	}
	
	
//	public void fillShip()
//	{
//		int x = startingX;
//		int y = startingY; 
//		
//		for(int i = 0; i < shipFrags.length; i++)
//		{
//			for(int j = 0; j < shipFrags[i].length; j++)
//			{
//				
//			}
//		}
//	}
}
