package utility;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener{
	
	public MyKeyListener(){
		super();
	}	
	
	@Override
	public void keyPressed(KeyEvent ke){
		if (ke.getKeyCode()==KeyEvent.VK_RIGHT) System.out.println("prawo");
		if (ke.getKeyCode()==KeyEvent.VK_LEFT) System.out.println("prawo");
		if (ke.getKeyCode()==KeyEvent.VK_UP) System.out.println("prawo");
		if (ke.getKeyCode()==KeyEvent.VK_DOWN) System.out.println("prawo");
	}
	@Override
	public void keyReleased(KeyEvent ke){
	}
	@Override
	public void  keyTyped(KeyEvent ke){
	}
	
}
