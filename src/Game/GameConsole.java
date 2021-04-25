package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameConsole extends JPanel
{
	private JTextArea console;
	private JTextField codeInput;
	private static GameConsole console_instance;
	Map<String, Integer> codeMap = new HashMap<String, Integer>() 
	{{
	    put("jannatbelackinnocap", 10);
	    put("mrsteverocks", 20);
	    put("itsdababylesgo", 10000);
	}};
	
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
		codeInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				if(codeMap.containsKey(codeInput.getText()))
				{
					SyrupSea.getInstance().addCurrency(codeMap.get(codeInput.getText()));
					GameConsole.getInstance().writeLine("You entered product code '" + codeInput.getText() + "' and received " + codeMap.get(codeInput.getText()) + " digital sugar!\n");
				}
				else
					GameConsole.getInstance().writeLine("Sorry! We don't recognize that product code!");
				SyrupSea.getInstance().repaint();
			}
		});
		
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
