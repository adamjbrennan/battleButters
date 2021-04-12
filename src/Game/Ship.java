package Game;

public class Ship
{
	private ShipFragment[][] shipFrags; 
	private int startingX;
	private int startingY;
	boolean isPlayerShip;
	
	public Ship(int rows, int columns, String shipImage, int x, int y)
	{
		this.startingX = x;
		this.startingY = y;
		this.shipFrags = new ShipFragment[rows][columns];
		fillShip();
	}
	
	public void fillShip()
	{
		int x = startingX;
		int y = startingY; 
		
		for(int i = 0; i < shipFrags.length; i++)
		{
			for(int j = 0; j < shipFrags[i].length; j++)
			{
				
			}
		}
	}
}
