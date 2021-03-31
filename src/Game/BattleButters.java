package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class BattleButters extends JFrame 
{
	private final int PROGRAM_WIDTH = 425; 
	private final int PROGRAM_HEIGHT = 835; 

	public static void main(String[] args) throws IOException 
	{
		new BattleButters().start();
	}
	
	public void start() throws IOException
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(PROGRAM_WIDTH, PROGRAM_HEIGHT));
		this.setTitle("Battle Butters");
		this.setIconImage(ImageIO.read(new File("res/icon.PNG")));
		this.setResizable(false);
		this.add(new SyrupSea(PROGRAM_WIDTH, PROGRAM_HEIGHT, Color.BLACK));
		this.setVisible(true);
	}
}
