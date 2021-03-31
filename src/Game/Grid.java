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
	private Color defaultColor;
	private double topLeftX;
	private double topLeftY;
	
	public Grid(double x, double y, Color hoverColor)
	{
		this.topLeftX = x;
		this.topLeftY = y;
		this.defaultColor = hoverColor;
		grid = new Square[10][10];
		placeGrid();
	}
	
	public void placeGrid() 
	{
		double x = topLeftX;
		double y = topLeftY;
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				grid[i][j] = new Square(x, y, 34, defaultColor);
				x += 34;
			}
			x = topLeftX;
			y += 34;
		}
	}
	
	public void draw(Graphics2D g) 
	{
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				grid[i][j].draw(g);
			}
		}
	}
	
	public boolean find(MouseEvent me)
	{
		boolean found = false;
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				if(grid[i][j].contains(me.getPoint()))
				{
					grid[i][j].setColor(defaultColor, 50);
					found = true;
				}
				else
				{
					grid[i][j].setColor(defaultColor, 0);
				}
			}
		}
		return found;
	}
}
