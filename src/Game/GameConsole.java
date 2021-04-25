package Game;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameConsole extends JPanel
{
	private JTextArea console;
	private JTextField codeInput;
	private static GameConsole console_instance;
	
	private GameConsole()
	{
		this.setBackground(new Color(179, 107, 62));
		this.setLayout(new BorderLayout());
		console = new JTextArea(8, 0);
		console.setEditable(false);
		codeInput = new JTextField();
		this.setBorder(BorderFactory.createTitledBorder("BattleButters Console"));
		this.add(console, BorderLayout.NORTH);
		this.add(codeInput, BorderLayout.SOUTH);
		
	}
	
	public void writeLine(String s)
	{
		if(this.console.getLineCount() > 7)
			this.console.setText("");
		this.console.append(s);
	}
	
	public JTextArea getConsole()
	{
		return this.console;
	}
	
	public JTextField getCodeInput()
	{
		return this.codeInput;
	}
	
	public static GameConsole getInstance()
	{
		if(console_instance == null)
			console_instance = new GameConsole();
		return console_instance;
	}
}
