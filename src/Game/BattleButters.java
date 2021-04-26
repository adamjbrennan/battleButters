package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * @author adamj
 * Dear next BattleButters group, I ran out of time to write comments. For that, I apologize. 
 */

public class BattleButters
{
	private static BufferedImage icon;
	private JFrame gameFrame;
	private JFrame gameConsole;
	private static final int SYRUP_SEA_PROGRAM_WIDTH = 425; 
	private static final int SYRUP_SEA_PROGRAM_HEIGHT = 835; 
	private static final int GAME_CONSOLE_WIDTH = 425;
	private static final int GAME_CONSOLE_HEIGHT = 225;
	
	public static void main(String[] args) throws IOException 
	{
		new BattleButters().start();
	}
	
	public void start()
	{
		try 
		{
			icon = ImageIO.read(new File("res/icon.PNG"));
		}
		catch (IOException e)
		{
			System.err.println("Could not read in icon image!");
			System.exit(-1);
		}
		
		gameFrame = new JFrame();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setTitle("BattleButters");
		gameFrame.setResizable(false);
		gameFrame.setSize(SYRUP_SEA_PROGRAM_WIDTH, SYRUP_SEA_PROGRAM_HEIGHT);
		gameFrame.setIconImage(icon);
		gameFrame.add(SyrupSea.getInstance());
		gameFrame.setVisible(true);
		
		gameConsole = new JFrame();
		gameConsole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameConsole.setTitle("BattleButters Console");
		gameConsole.setResizable(false);
		gameConsole.setSize(GAME_CONSOLE_WIDTH, GAME_CONSOLE_HEIGHT);
		gameConsole.setIconImage(icon);
		gameConsole.setVisible(true);
		gameConsole.add(GameConsole.getInstance());
	}
	
	public static int getGameWidth()
	{
		return SYRUP_SEA_PROGRAM_WIDTH;
	}
	
	public static int getGameHeight()
	{
		return SYRUP_SEA_PROGRAM_HEIGHT;
	}
	
	public static int getConsoleHeight()
	{
		return GAME_CONSOLE_HEIGHT;
	}
	
	public static int getConsoleWidth()
	{
		return GAME_CONSOLE_WIDTH;
	}
}

