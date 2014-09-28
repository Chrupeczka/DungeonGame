package display;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utility.MyKeyListener;

public class MenuPanel extends JPanel implements ActionListener{

	private JButton newGame = new JButton("NEW GAME");
	private JButton options = new JButton("OPTIONS");
	private JButton exit = new JButton("EXIT");
	private final JFrame mainFrame;
	
	public MenuPanel(JFrame mainFrame){
		this.mainFrame = mainFrame;
		
		newGame.addActionListener(this);
		options.addActionListener(this);
		exit.addActionListener(this);
		
		newGame.setFocusable(false);
		options.setFocusable(false);
		exit.setFocusable(false);
		
		add(newGame);
		add(options);
		add(exit);
		
		mainFrame.setPreferredSize(new Dimension(500,400));
		mainFrame.pack();
		
	}
	
	public void actionPerformed(ActionEvent ae){
		if (ae.getSource()==newGame) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					mainFrame.getContentPane().removeAll();
					JLayeredPane game = new PlayPanel(mainFrame);
					mainFrame.add(game);
					mainFrame.validate();
					
				}
			});
		} else {
			if (ae.getSource()==options) {
				
			} else {
				if (ae.getSource()==exit) {
					if (mainFrame instanceof JFrame)mainFrame.dispose();
				}
			}
		}
		 
	}
	
}
