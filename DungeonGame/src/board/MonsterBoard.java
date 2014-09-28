package board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

import player.Monster;
import player.MonsterType;

public class MonsterBoard {

	private int x;
	private int y;
	
	private Monster[][] monsterBoard; 
	
	public BufferedImage goblinImg;
	public BufferedImage skeletonImg;
	
	private Random random = new Random();
	
	public MonsterBoard (int x, int y) {
		this.x=x;
		this.y=y;
		
		monsterBoard = new Monster[x][y];
		
		for (int i=0 ; i<x ; i++){
			for (int i2=0 ; i2<y ; i2++){
				monsterBoard[i][i2]=new Monster(0,MonsterType.NULL);
			}
		}
		
	}
	public void createMonsters(int x, int y, boolean wall){
		if (!wall&random.nextBoolean()&random.nextBoolean()&random.nextBoolean()){
			monsterBoard[x][y]=new Monster(1, MonsterType.BEHOLDER);
		}
		if (!wall&random.nextBoolean()&random.nextBoolean()&random.nextBoolean()){
			monsterBoard[x][y]=new Monster(1, MonsterType.DJINN);
		}
		monsterBoard[1][1]=new Monster(1, MonsterType.NULL);
	}
	
	public String getBoardSize(){
		return x+", "+y;
	}
	
	public MonsterType getMonsterType(int x, int y){
		return monsterBoard[x][y].getMonsterType();
	}
	
	public boolean isMonster(int x, int y){
		if (!(monsterBoard[x][y].getMonsterType().equals(MonsterType.NULL))) {
			return true;
		}
		return false;
	}
	
	public void hitMonster(int x, int y, int atk){
		monsterBoard[x][y].hitMonster(atk);
	}
	
	public boolean checkIfDead(int x, int y){
		if (monsterBoard[x][y].getHp()<1){
			monsterBoard[x][y]=new Monster (0,MonsterType.NULL);
			return true;
		} else
			return false;
	}
	
}
