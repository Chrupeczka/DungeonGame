package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import player.Player;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import utility.MonsterLabel;
import board.Board;
import board.MonsterBoard;

public class PlayPanel extends JLayeredPane implements KeyListener, ActionListener, MouseMotionListener{

	private int mouseLocationX = 0;
	private int mouseLocationY = 0;
	
	private int boardWidth = 16;
	private int boardHeight = 11;
	private int windowWidth = boardWidth + 6;
	private int boardSizeX = boardWidth*50 ;
	private int windowSizeX = boardWidth*50 + 300;
	private int windowSizeY = boardHeight*50 + 45;
	
	private JPanel gameBoardPanel = new JPanel();
	JButton btn = new JButton("RESET");
	private Board board;
	private MonsterBoard monsterBoard;
	private MonsterLabel[][] monsterPics = new MonsterLabel[boardWidth][boardHeight];
	
	private final JFrame mainFrame;
	
	private Player player = new Player("zeniu");
	private BufferedImage playerImg;
	private BufferedImage celownikImg;
	
	public PlayPanel(JFrame mainFrame){
		this.mainFrame=mainFrame;
	
		addMouseMotionListener(this);
		mainFrame.addKeyListener(this);
		mainFrame.setPreferredSize(new Dimension(windowSizeX,windowSizeY));
		mainFrame.setLocationRelativeTo(this);
		mainFrame.setResizable(false);
		
		try{
			File playerImgPath = new File("punkt.gif");
			File celownikImgPath = new File("celownik.gif");
			playerImg = ImageIO.read(playerImgPath);
			celownikImg = ImageIO.read(celownikImgPath);
		} catch (IOException e) {
			System.err.println("blad z wczytaniem gracza/celownika!");
		}
		
		startLevel();
		
		this.setBounds(0, 0, windowSizeX, windowSizeY);
		this.add(createLeftPanel());
		this.add(createRightPanel());
		this.add(gameBoardPanel);
		
		mainFrame.pack();
		
		repaint();
		System.out.println(mainFrame.getMostRecentFocusOwner());
	}
	

	private JPanel createLeftPanel(){
		JPanel buttonPanel = new JPanel();
		btn.addActionListener(this);
		btn.setFocusable(false);
		buttonPanel.add(btn, BorderLayout.PAGE_END);
		buttonPanel.setOpaque(false);
		buttonPanel.setBounds(0, 0, 150, windowSizeY);
		buttonPanel.setBackground(Color.RED);
		return buttonPanel;
	}
	
	private JPanel createRightPanel(){
		JPanel rightPanel = new JPanel();
		JTextField napis = new JTextField("TUTAJ STATY");
		rightPanel.add(napis);
		rightPanel.setBackground(Color.BLUE);
		rightPanel.setOpaque(false);
		rightPanel.setBounds(150+boardSizeX, 0, 150, windowSizeY);
		return rightPanel;
	}
	
	private void startLevel(){
		monsterBoard = new MonsterBoard(boardWidth, boardHeight);
		board = new Board(boardWidth,boardHeight);
		createMonsterDisplayPanel(boardWidth, boardHeight);
		repaint();
		this.updateUI();
	}
	
	// POMOCNICZY PANEL Z PLANSZA
	private JPanel createMonsterDisplayPanel(int x, int y){
	
		for (int i=0; i<boardWidth; i++){
			for (int i2=0; i2<boardHeight; i2++){
				monsterBoard.createMonsters(i, i2, board.isWall(i,i2));
				MonsterLabel tempLab = new MonsterLabel(monsterBoard.getMonsterType(i,i2), mainFrame);
				monsterPics[i][i2]=tempLab;
				monsterPics[i][i2].setImg();
			}
		}
		gameBoardPanel.setOpaque(false);
		gameBoardPanel.setBounds(150, 0, boardWidth*50, windowSizeY+5);
		gameBoardPanel.addKeyListener(this);
		// GridBagLayout dla wyswietlania stworów
		gameBoardPanel.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.insets = new Insets(0,0,0,0);
		gameBoardPanel.setVisible(true);
		gameBoardPanel.removeAll();
		for (int i=0; i<boardWidth; i++){
			for (int i2=1; i2<boardHeight; i2++){
				con.gridx=i;
				con.gridy=i2;
				gameBoardPanel.add(monsterPics[i][i2], con);
			}
		}
		return gameBoardPanel;
	}
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int i=0 ; i<boardWidth ; i++){
        	for (int i2=0 ; i2<boardHeight ; i2++){
        		 g2d.drawImage(board.getImg(i,i2), 150+i*50, i2*50, null);
        		 g2d.drawImage(celownikImg, mouseLocationX, mouseLocationY, null);
        	}
        }
        g2d.drawImage(playerImg, player.getX()*50+160, player.getY()*50+10, null);
       
    }
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==btn) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					startLevel();
					player.setX(1);
					player.setY(1);
					repaint();
					mainFrame.validate();
				}
			});
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent ke){
		
		if (ke.getKeyCode()==KeyEvent.VK_RIGHT) {
			movement(player.getX()+1, player.getY());
		}
		if (ke.getKeyCode()==KeyEvent.VK_LEFT) {
			movement(player.getX()-1, player.getY());
		}
		if (ke.getKeyCode()==KeyEvent.VK_UP){
			movement(player.getX(), player.getY()-1);
		}
		if (ke.getKeyCode()==KeyEvent.VK_DOWN){
			movement(player.getX(), player.getY()+1);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent ke){
	}
	@Override
	public void  keyTyped(KeyEvent ke){
	}
	
	@Override
	public void mouseDragged(MouseEvent me){}
	@Override
	public void mouseMoved(MouseEvent me){
		int x = me.getX();
		int y = me.getY();
		
		for (int i=150; i<boardSizeX+150;i+=50){
			if (i<x&x<i+50) {
				mouseLocationX=i;					
			}
			
		for (int i2=0; i2<windowSizeY;i2+=50){
			if (i2<y&y<i2+50) {
				mouseLocationY=i2;			
			}
		}
		}
		repaint();
	}
	/*	@Override
	public void mouseEntered(MouseEvent me){}
	@Override
	public void mouseExited(MouseEvent me){}
	@Override
	public void mouseClicked(MouseEvent me){}
	@Override
	public void mousePressed(MouseEvent me){}
	@Override
	public void mouseReleased(MouseEvent me){}
	*/
	public void movement(int x, int y){
		if (board.getTile(x, y).isWall()){
			System.out.println("nie mogê isc bo MUR!");
		} else if (monsterBoard.isMonster(x, y)){
			monsterBoard.hitMonster(x, y, player.getAtk());
			try{
			playHitSound();
			} catch (Exception e) {
				System.err.println("problem z odczytaniem dzwieku hit1");
				e.printStackTrace();
			}
			if (monsterBoard.checkIfDead(x, y)){
				monsterPics[x][y].killMonster();
				monsterPics[x][y].setImg();
			}
		} else {
			player.setX(x);
			player.setY(y);
			repaint();
		}
	}
	
	private void playHitSound() throws Exception {
		    String gongFile = "hit1.wav";
		    InputStream in = new FileInputStream(gongFile);
		    AudioStream audioStream = new AudioStream(in);
		    AudioPlayer.player.start(audioStream);
	 }
	
	
	
}


