package player;

public class Player {
	
	private String name;
	
	private int x=1;
	private int y=1;
	private int atk = 5;
	private int hp = 10;
	
	
	public Player(String name){
		this.name=name;
		
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getAtk(){
		return atk;
	}
}
