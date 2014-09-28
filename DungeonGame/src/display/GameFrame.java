package display;

import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utility.MyKeyListener;

public class GameFrame extends JFrame{

	public final int WINDOW_HEIGHT = 580;
	public final int WINDOW_WIDTH = 800;
	
	public Dimension windowSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
	
	public GameFrame() {
		super("Popco Dungeon");
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(true);
	
		JPanel menu = new MenuPanel(this);
		add(menu);
	}
	
	public Dimension getWindowSize(){
		return windowSize;
	}
	
}
