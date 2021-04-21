package Game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Grid
{
	private Square[][] grid;
	private Color fireHoverColor;
	private double topLeftX;
	private double topLeftY;
	
	public Grid(double x, double y, Color hoverColor)
	{
		this.topLeftX = x;
		this.topLeftY = y;
		this.fireHoverColor = hoverColor;
		grid = new Square[10][10];
		this.placeGrid();
	}
	
	public void placeGrid() 
	{
		double x = topLeftX;
		double y = topLeftY;
		
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				grid[i][j] = new Square(x, y, new Color(0, 0, 0, 0));
				x += Square.getSquareDimensions();
			}
			
			x = topLeftX;
			y += Square.getSquareDimensions();
		}
	}
	
	public void draw(Graphics2D g)
	{
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[i].length; j++)
				grid[i][j].draw(g);
	}
	
	public void shotHover(Point p)
	{
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[i].length; j++)
				if(grid[i][j].contains(p))
					grid[i][j].setColor(this.fireHoverColor, 50);
				else
					grid[i][j].setColor(this.fireHoverColor, 0);
	}
	
	//Determines if the grid contains Point p
	public boolean find(Point p)
	{
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[i].length; j++)
				if(grid[i][j].contains(p))
					return true;
		return false;
	}
	
	public int[] findIndices(Point p)
	{
		int[] indices = {-1, -1};
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[i].length; j++)
				if(grid[i][j].contains(p))
				{
					indices[0] = i;
					indices[1] = j;
				}
		return indices;
	}
	
	public void placeHover(Point p, Ship s)
	{
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[i].length; j++)
				if(grid[i][j].contains(p))
				{
					for(int i2 = 0; i2 < grid.length; i2++)
						for(int j2 = 0; j2 < grid[i2].length; j2++)
							if(i2 >= i && i2 < i + s.getSize() && j2 >= j && j2 < j + s.getSize())
								grid[i2][j2].setColor(Color.BLUE, 50);
					
							else
								grid[i2][j2].setColor(Color.BLACK, 0);
					return;
				}
				else
					grid[i][j].setColor(Color.BLACK, 0);
	}
	
	public void place(int r, int c, Ship s)
	{
		for(int i = r; i < r + s.getSize(); i++)
			for(int j = c; j < c + s.getSize(); j++)
				grid[i][j].setShipInLocation(true);
	}
	public Square[][] getSquareArray()
	{
		return this.grid;
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder("[");
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				s.append(grid[i][j].getShipInLocation());
				if(j < grid[i].length - 1)
					s.append(",");
			}
			s.append("]\n[");
		}
		return s.toString().substring(0, s.toString().length() - 1);
	}
}
