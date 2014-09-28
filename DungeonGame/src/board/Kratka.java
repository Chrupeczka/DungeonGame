package board;

public class Kratka {

	private boolean wall;
	private boolean door;
	
	public Kratka(){
		this.wall=false;
		this.door=false;
	}
	
	public boolean isWall(){
		return wall;
	}
	
	public void setWall(){
		this.wall = true;
	}
	
	public void destroyWall(){
		this.wall = false;
	}
	
	public void setDoor(){
		this.door = true;
	}
	
	public void destroyDoor(){
		this.door = false;
	}
	
	public void buildWall(boolean bl){
		this.wall=bl;
	}
	
	
}
