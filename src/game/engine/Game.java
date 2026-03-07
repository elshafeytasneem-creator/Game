package game.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.math.*;

import game.engine.dataloader.Dataloader;
import game.engine.monsters.Monster;

public class Game {
	
	private  Board board;
	private ArrayList<Monster> allMonsters;
	private Monster player;
	private Monster opponent;
	private Monster current;
	public Board getBoard() {
		return board;
	}
	
	public ArrayList<Monster> getAllMonsters() {
		return allMonsters;
	}
	public Monster getPlayer() {
		return player;
	}
	public Monster getOpponent() {
		return opponent;
	}
	public Monster getCurrent() {
		return current;
	}
	public void setCurrent(Monster current) {
		this.current = current;
	}
	
	 Game(Role playerRole) throws IOException{
		 this.board = new Board(Dataloader.readCards());
		 this.allMonsters = Dataloader.readMonsters();
		 this.player = this.selectRandomMonsterByRole(playerRole);
		 if(playerRole.equals(Role.SCARER)){
			 this.opponent = this.selectRandomMonsterByRole(Role.LAUGHER);
		 }else{
			 this.opponent = this.selectRandomMonsterByRole(Role.SCARER);
		 }
		 
		 this.current = this.player;
	 }
	
	
	private Monster selectRandomMonsterByRole(Role role){
			int index = ((int)Math.random()*this.allMonsters.size());
			Monster m = this.allMonsters.get(index);
			
			while(m.getRole()!=role){
				index = ((int)Math.random()*this.allMonsters.size());
				m = this.allMonsters.get(index);
			}
			
			return m;
		
	}
	

}
