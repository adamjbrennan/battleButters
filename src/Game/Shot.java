package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import javax.imageio.ImageIO;

public class Shot implements Drawable
{
	private Point sp;
	private boolean isPlayerShot;
	private Color color;
	private ShotType shotType;
	private Ellipse2D.Double shot; 
	private static HashSet<Square> opponentFiredIn = new HashSet<Square>();
	
	public Shot(ShotType st, boolean ps)
	{
		this.isPlayerShot = ps;
		this.shotType = st;
		switch(this.shotType)
		{
			case NORMAL:
				this.color = Color.YELLOW;
			break;
			case JELLYSHOT:
				this.color = Color.GREEN;
			break;
			case JAMSHOT:
				this.color = Color.MAGENTA;
			break;
		}
		if(this.isPlayerShot)
			shot = new Ellipse2D.Double(425.0, 835.0, 1.0, 1.0);
		else
			shot = new Ellipse2D.Double(425.0, 0.0, 1.0, 1.0);
	}
	
	public Shot(Point p, ShotType st, boolean ps)
	{
		this(st, ps);
		this.sp = p;
	}
	
	public void moveCloser()
	{
		shot.x += (sp.getX() - shot.x)/3000.0; 
		shot.y += (sp.getY() - shot.y)/3000.0;
	}
	
	public Point getPoint()
	{
		return sp;
	}
	
	public double getCurrentX()
	{
		return shot.x;
	}
	
	public Ellipse2D.Double getShotEllipse()
	{
		return this.shot;
	}
	
	public void draw(Graphics2D g2)
	{
		g2.setColor(color);
		g2.draw(shot);
		g2.setColor(color);
		g2.fill(shot);
	}
	
	public static ShotType chooseShot()
	{
		int shot = (int)(Math.random() * 3);
		if(shot == 0)
			return ShotType.NORMAL;
		else if(shot == 1)
			return ShotType.JELLYSHOT;
		else
			return ShotType.JAMSHOT;
	}
	
	public void fire()
	{
		Grid seaGrid;
		String message;
		
		if(isPlayerShot)
		{
			seaGrid = SyrupSea.getInstance().getOpponentGrid();
			message = "Player ";
		}
		else
		{
			seaGrid = SyrupSea.getInstance().getPlayerGrid();
			message = "CPU ";
			
			this.sp = new Point((int)(Math.random() * 340), 347 + (int)(Math.random() * 340));
			
			int[] indices = seaGrid.findIndices(sp);
			
			while(opponentFiredIn.contains(seaGrid.getSquareArray()[indices[0]][indices[1]]))
			{
				this.sp = new Point((int)(Math.random() * 340), 347 + (int)(Math.random() * 340));
				indices = seaGrid.findIndices(sp);
			}
			
			opponentFiredIn.add(seaGrid.getSquareArray()[indices[0]][indices[1]]);
		}
			
			
		while(!seaGrid.getSquareArray()[seaGrid.findIndices(this.sp)[0]][seaGrid.findIndices(this.sp)[1]].intersects(this.getShotEllipse().getBounds2D()))
		{
			this.moveCloser();
			this.draw((Graphics2D)SyrupSea.getInstance().getGraphics());
		}					
		
		switch(this.shotType)
		{
			case NORMAL:
				
				GameConsole.getInstance().writeLine(message + "firing normal shot!\n");
				seaGrid.getSquareArray()[seaGrid.findIndices(this.sp)[0]][seaGrid.findIndices(this.sp)[1]].loadDisplay();
				seaGrid.getSquareArray()[seaGrid.findIndices(this.sp)[0]][seaGrid.findIndices(this.sp)[1]].setShipInLocation(false);
				
			break;
			
			case JELLYSHOT:
				
				GameConsole.getInstance().writeLine(message + "firing jelly shot!\n");
				for(int i = seaGrid.findIndices(this.sp)[0]; i < seaGrid.findIndices(this.sp)[0] + 3; i++)
					for(int j = seaGrid.findIndices(this.sp)[1]; j < seaGrid.findIndices(this.sp)[1] + 3; j++)
					{
						try
						{
							seaGrid.getSquareArray()[i][j].loadDisplay();
							seaGrid.getSquareArray()[i][j].setShipInLocation(false);
						}
						catch(Exception e)
						{
							continue;
						}
					}
				
			break;
			
			case JAMSHOT:
				
				GameConsole.getInstance().writeLine(message + "firing jam shot!\n");
				int row = seaGrid.findIndices(this.sp)[0];
				
				for(int i = 0; i < 10; i++)
				{
					seaGrid.getSquareArray()[row][i].loadDisplay();
					seaGrid.getSquareArray()[row][i].setShipInLocation(false);
				}
				
			break;
		}
	}
}
