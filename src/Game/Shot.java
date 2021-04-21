package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shot 
{
	private Point sp;
	private double affectedWidth;
	private double affectedHeight;
	private boolean isPlayerShot;
	private Color color;
	private Ellipse2D.Double shot; 
	
	public Shot(Point p, int aw, int ah, String fp, Color sc, boolean ps)
	{
		this.sp = p;
		this.affectedWidth = aw;
		this.affectedHeight = ah;
		this.isPlayerShot = ps;
		this.color = sc;
		if(this.isPlayerShot)
			shot = new Ellipse2D.Double(425.0, 835.0, 1.0, 1.0);
		else
			shot = new Ellipse2D.Double(425.0, 0.0, 1.0, 1.0);
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
}
