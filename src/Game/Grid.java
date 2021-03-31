package Game;

import java.awt.Color;

public abstract class Grid 
{
	private Square[][] grid;
	private Color fireHoverColor;
	
	public Grid(Color hoverColor)
	{
		this.fireHoverColor = hoverColor;
		fillGrid();
		drawGrid();
	}
	
	private void fillGrid() 
	{
		
	}
	
	private void drawGrid() 
	{
		
	}
}
