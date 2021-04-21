package Game;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class RenderFrame extends JFrame
{
	private static final int PROGRAM_WIDTH = 425; 
	private static final int PROGRAM_HEIGHT = 835; 

	public static void main(String[] args) throws IOException 
	{
		new BattleButters().start();
	}
	
	public void start()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Battle Butters");
		this.setResizable(false);
		this.setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
		try
		{
			this.setIconImage(ImageIO.read(new File("res/icon.PNG")));
		}
		catch(Exception e)
		{
			System.err.println("Could not read in icon file! Check file path!");
			System.exit(-1);
		}
		
		this.add(SyrupSea.getInstance());
		this.setVisible(true);
	}
	
	public static int getGameWidth()
	{
		return PROGRAM_WIDTH;
	}
	
	public static int getGameHeight()
	{
		return PROGRAM_HEIGHT;
	}
}
