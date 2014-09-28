package board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Board {

	private int x;
	private int y;
	
	private Kratka[][] board; 
	
	public BufferedImage mur;
	public BufferedImage tile;
	
	public Board (int x, int y) {
		this.x=x;
		this.y=y;
		
		try{
			File kratkaMur = new File("wall.png");
			File kratkaTile = new File("tile.jpg");
			mur = ImageIO.read(kratkaMur);
			tile = ImageIO.read(kratkaTile);
		} catch (IOException e) {
			System.err.println("blad z wczytaniem murku!");
		}
		
		
		
		
		board = new Kratka[x][y];

		for (int i=0 ; i<x ; i++){
			for (int i2=0 ; i2<y ; i2++){
				board[i][i2]=new Kratka();
			}
		}
		
		for (int i=0 ; i<x ; i++){
			board[i][0].setWall();
			board[i][y-1].setWall();
		}
		
		for (int i=0 ; i<y ; i++){
			board[0][i].setWall();
			board[x-1][i].setWall();
		}
		
		for (int i=1 ; i<x-1 ; i++){
			for (int i2=1 ; i2<y-1 ; i2++){
				Random random = new Random();
				
				board[i][i2].buildWall(random.nextBoolean()&&random.nextBoolean());
			}
		}
		
		board[1][1].destroyWall();
		
	}
	public boolean isWall(int x, int y){
		return board[x][y].isWall();
	}
	
	public String getBoardSize(){
		return x+", "+y;
	}
	
	public Kratka getTile(int x, int y){
		return board[x][y];
	}
	
	public BufferedImage getImg(int x, int y){
		if (board[x][y].isWall()) return mur;
		return tile;
	}
	
}
