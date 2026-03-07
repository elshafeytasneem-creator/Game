package game.engine.dataloader;

import game.engine.Role;
import game.engine.cards.Card;
import game.engine.cards.ConfusionCard;
import game.engine.cards.EnergyStealCard;
import game.engine.cards.ShieldCard;
import game.engine.cards.StartOverCard;
import game.engine.cards.SwapperCard;
import game.engine.cells.Cell;
import game.engine.cells.ContaminationSock;
import game.engine.cells.ConveyorBelt;
import game.engine.cells.DoorCell;
import game.engine.monsters.Dasher;
import game.engine.monsters.Dynamo;
import game.engine.monsters.Monster;
import game.engine.monsters.MultiTasker;
import game.engine.monsters.Schemer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dataloader {
	private static final String CARDS_FILE_NAME = "cards.csv";
	private static final String CELLS_FILE_NAME = "cells.csv";
	private static final String MONESTERS_FILE_NAME = "monsters.csv";
	
	
	 public static ArrayList<Card> readCards() throws IOException{
		 ArrayList<Card> cards = new ArrayList<>();
		 try (BufferedReader br = new BufferedReader(new FileReader(CARDS_FILE_NAME))) {
		        String line = br.readLine();
		        
		        while ((line ) != null) {
		            String[] values = line.split(",");
		            String name="";
		            String description="";
		            int rarity=0;
		            int energy=0;
		            boolean lucky=false;
		            int duration=0;
		            
		            
		            switch (values[0]){
			            case "SWAPPER":
			            	name = values[1];
			            	description = values[2];
			            	rarity = Integer.parseInt(values[3]);
			            	Card newSwapperCard = new SwapperCard(name, description, rarity);
			            	cards.add(newSwapperCard);
			            	break;
			            case "SHIELD": 
			            	name = values[1];
			            	description = values[2];
			            	rarity = Integer.parseInt(values[3]);
			            	Card newShieldCard = new ShieldCard(name, description, rarity);
			            	cards.add(newShieldCard);
			            	break;
			            case "ENERGYSTEAL":
			            	name = values[1];
			            	description = values[2];
			            	rarity = Integer.parseInt(values[3]);
			            	energy = Integer.parseInt(values[4]);
			            	Card newEnergyStealCard = new EnergyStealCard(name, description, rarity, energy);
			            	cards.add(newEnergyStealCard);
			            	break;
			            case "STARTOVER":
			            	name = values[1];
			            	description = values[2];
			            	rarity = Integer.parseInt(values[3]);
			            	lucky = Boolean.parseBoolean(values[4]);
			            	Card newStartOverCard = new StartOverCard(name, description, rarity, lucky);
			            	cards.add(newStartOverCard);
			            	break;
			            case "CONFUSION":
			            	name = values[1];
			            	description = values[2];
			            	rarity = Integer.parseInt(values[3]);
			            	duration = Integer.parseInt(values[4]);
			            	Card newConfusionCard = new ConfusionCard(name, description, rarity, duration);
			            	cards.add(newConfusionCard);
			            	break;
		            }
		            
		        }
		 }
		 
		 return cards;
	 }
	 
	 
	 public static ArrayList<Cell> readCells() throws IOException{
		 ArrayList<Cell> cells = new ArrayList<>();
		 try (BufferedReader br = new BufferedReader(new FileReader(CELLS_FILE_NAME ))){
			 String line = br.readLine();
			 
			 while ((line ) != null) {
				 String[] values = line.split(",");
				 
				 
				 if(values.length == 3){
					 String name = values[0]; 
					 Role role = Role.valueOf(values[1]); 
					 int energy = Integer.parseInt(values[2]);
					 Cell newDoorCell = new DoorCell(name, role, energy);
					 cells.add(newDoorCell);
				 }else{
					 String name = values[0];
					 int effect = Integer.parseInt(values[1]);
					 
					 if(effect > 0){
						 Cell newConveyorBelt = new ConveyorBelt(name,effect);
						 cells.add(newConveyorBelt);
					 }else{
						 Cell newContaminationSock = new ContaminationSock(name,effect);
						 cells.add(newContaminationSock);
					 }
					 
				 }
			 }
			 
		 }
		 return cells;
	 }
	 
	 public static ArrayList<Monster> readMonsters() throws IOException{
		 ArrayList<Monster> monsters = new ArrayList<>();
		 try (BufferedReader br = new BufferedReader(new FileReader( MONESTERS_FILE_NAME))){
			 String line = br.readLine();
			 
			 while ((line ) != null) {
				 String[] values = line.split(",");
				 
				 String name="";
				 String description ="";
				 Role role = null;
				 int energy=0;
				 
				 switch (values[0]){
				 	case "DYNAMO":
				 		name = values[1];
				 		description = values[2];
				 		role = Role.valueOf(values[3]);
				 		energy = Integer.parseInt(values[4]);
				 		Monster newDynamo = new Dynamo( name, description, role,energy);
				 		monsters.add(newDynamo);
				 		break;
				 	case "DASHER":
				 		name = values[1];
				 		description = values[2];
				 		role = Role.valueOf(values[3]);
				 		energy = Integer.parseInt(values[4]);
				 		Monster newDasher = new Dasher( name, description, role,energy);
				 		monsters.add(newDasher);
				 		break;
				 	case "SCHEMER":
				 		name = values[1];
				 		description = values[2];
				 		role = Role.valueOf(values[3]);
				 		energy = Integer.parseInt(values[4]);
				 		Monster newSchemer = new Schemer( name, description, role,energy);
				 		monsters.add(newSchemer);
				 		break;
				 	case "MULTITASKER":
				 		name = values[1];
				 		description = values[2];
				 		role = Role.valueOf(values[3]);
				 		energy = Integer.parseInt(values[4]);
				 		Monster newMultiTasker = new MultiTasker( name, description, role,energy);
				 		monsters.add(newMultiTasker);
				 		break;
				 }
				 
			 }
		 }
		 
		 return monsters;
	 }
	 
	 
	
}
