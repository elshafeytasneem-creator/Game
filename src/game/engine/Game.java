package game.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.math.*;


import game.engine.dataloader.DataLoader;
import game.engine.monsters.Monster;
import game.engine.Board;
import game.engine.exceptions.*;

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
	
	
	public Game(Role playerRole) throws IOException{
		 this.board = new Board(DataLoader.readCards());
		 this.allMonsters = DataLoader.readMonsters();
		 this.player = this.selectRandomMonsterByRole(playerRole);
		 if(playerRole.equals(Role.SCARER)){
			 this.opponent = this.selectRandomMonsterByRole(Role.LAUGHER);
		 }else{
			 this.opponent = this.selectRandomMonsterByRole(Role.SCARER);
		 }
		 
		 this.current = this.player;

		 board.setStationedMonsters(allMonsters);
		 board.initializeBoard(DataLoader.readCells());
	 }
	
	
	private Monster selectRandomMonsterByRole(Role role){
			int index = (int)(Math.random()*this.allMonsters.size());
			Monster m = this.allMonsters.get(index);
			
			while(m.getRole()!=role){
				index = (int)(Math.random()*this.allMonsters.size());
				m = this.allMonsters.get(index);
			}

			allMonsters.remove(m);
			

			return m;
		
	}

	private Monster getCurrentOpponent() {
		if(this.current == this.player){
			return this.opponent;
		}else{
			return this.player;
		}
	}

	private int rollDice() {
		return (int)(Math.random()*6)+1;
	}

	public void usePowerup() throws OutOfEnergyException {
		if(this.current.getEnergy()>=Constants.POWERUP_COST){
			this.current.setEnergy(this.current.getEnergy()-Constants.POWERUP_COST);
			this.current.executePowerupEffect(getCurrentOpponent());
		} else {
			throw new OutOfEnergyException("Not enough energy to use power-up.");
		}
	}
	
	public void playTurn() throws InvalidMoveException{
		if(this.current.isFrozen()){
			this.current.setFrozen(false);
			switchTurn();
			return;
		}
		int diceRoll = rollDice();
		board.moveMonster(this.current, diceRoll, this.getCurrentOpponent());
		switchTurn();
	}

	private void switchTurn(){
		this.current = getCurrentOpponent();
	}

	private boolean checkWinCondition(Monster monster) {
		return monster.getPosition() == Constants.WINNING_POSITION && monster.getEnergy() >= Constants.WINNING_ENERGY;
	}

	public Monster getWinner() {
		if (checkWinCondition(player)) {
			return player;
		} else if (checkWinCondition(opponent)) {
			return opponent;
		} else {
			return null; // No winner yet
		}
	}
}
