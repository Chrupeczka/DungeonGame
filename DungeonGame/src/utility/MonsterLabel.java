package utility;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import player.MonsterType;

public class MonsterLabel extends JLabel{
	
	public MonsterLabel(MonsterType monsterType, JFrame mainFrame){
		super();
		this.monsterType=monsterType;
		this.mainFrame=mainFrame;
		this.getImg();
	}
	
	MonsterType monsterType;
	private final JFrame mainFrame;
	
	public String getImg(){
		if (monsterType.equals(MonsterType.BEHOLDER)) return "beholder.gif";
		else if (monsterType.equals(MonsterType.DJINN)) return "djinn.gif";
		return "pusty.gif";
	}
	public void setImg(){
		this.setIcon(new ImageIcon(mainFrame.getClass().getResource(this.getImg())));
	}
	
	public void killMonster(){
		monsterType=MonsterType.NULL;
	}
}
