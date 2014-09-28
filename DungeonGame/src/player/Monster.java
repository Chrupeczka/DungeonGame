package player;

public class Monster {

	private int level;
	private int hp;
	private int atk;
	private MonsterType monsterType;
	
	public Monster(int level, MonsterType monsterType){
		this.monsterType = monsterType;
		this.level = level;
		setStats();
	}
	
	public Monster(){}
	
	public void setStats(){
		switch (monsterType) {
		case GOBLIN:{
			this.hp = 10+4*level;
			this.atk = 3+2*level;
		} break;
		case SKELETON:{
			this.hp = 7+3*level;
			this.atk = 4+3*level;
		} break;
		case GOLEM:{
			this.hp = 15+5*level;
			this.atk = 3+2*level;
		} break;
		case BEHOLDER:{
			this.hp = 14+3*level;
			this.atk = 5+3*level;
		} break;
		}		
	}

		
	
	public int getLevel(){
		return level;
	}
	
	public int getHp(){
		return hp;		
	}
	
	public void setHp(int hp){
		this.hp=hp;
	}
	
	public int getAtk(){
		return atk;
	}
	
	public void setAtk(int atk){
		this.atk=atk;
	}
	
	public MonsterType getMonsterType(){
		return monsterType;
	}
	
	public void setMonsterType(MonsterType type){
		this.monsterType=type;
	}
	
	public void hitMonster(int atk){
		this.hp=hp-atk;
	}

	
}
