package game.engine;

import java.util.ArrayList;
import java.util.Collections;

import game.engine.cards.Card;
import game.engine.cells.*;
import game.engine.exceptions.InvalidMoveException;
import game.engine.monsters.Monster;
import game.engine.Constants;

public class Board {
	 private Cell[][] boardCells;
	 private static ArrayList<Monster> stationedMonsters;
	 private static ArrayList<Card> originalCards;
	 public static ArrayList<Card> cards;
	public Cell[][] getBoardCells() {
		return boardCells;
	}
	
	public static ArrayList<Monster> getStationedMonsters() {
		return stationedMonsters;
	}
	public static void setStationedMonsters(ArrayList<Monster> stationedMonsters) {
		Board.stationedMonsters = stationedMonsters;
	}
	public static ArrayList<Card> getOriginalCards() {
		return originalCards;
	}
	
	public static ArrayList<Card> getCards() {
		return cards;
	}
	public static void setCards(ArrayList<Card> cards) {
		Board.cards = cards;
	}
	
	public Board(ArrayList<Card> readCards){
		
		this.boardCells = new Cell[Constants.BOARD_ROWS][Constants.BOARD_COLS];
		
		this.stationedMonsters = new ArrayList<>();
		this.cards = new ArrayList<>();
		this.originalCards = readCards;

		setCardsByRarity();
		reloadCards();
		
		
	}
	 
	private int[] indexToRowCol(int index){
		//zig zag pattern
		int row = index / Constants.BOARD_COLS;
		int col = index % Constants.BOARD_COLS;
		if (row % 2 == 1) { // odd row, reverse column order
			col = Constants.BOARD_COLS - 1 - col;
		}
		return new int[]{row, col};

	}

	private Cell getCell(int index){
		int[] rowCol = indexToRowCol(index);
		return boardCells[rowCol[0]][rowCol[1]];
	}

	private void setCell(int index, Cell cell){
		int[] rowCol = indexToRowCol(index);
		boardCells[rowCol[0]][rowCol[1]] = cell;
	}
	 
	public void initializeBoard(ArrayList<Cell> specialCells){
		int door_index = 1;
		int conveyor_index = 0;
		int card_index = 0;
		int contamination_index = 0;
		

		int[] monsterIndexes = Constants.MONSTER_CELL_INDICES;
		int[] conveyorIndexes = Constants.CONVEYOR_CELL_INDICES;
		int[] cardIndexes = Constants.CARD_CELL_INDICES;
		int[] contaminationIndexes = Constants.SOCK_CELL_INDICES;

		for(Cell cell: specialCells){
			if(cell instanceof DoorCell){
				setCell(door_index, cell);
				door_index+=2;
			}else if(cell instanceof ConveyorBelt){
				setCell(conveyorIndexes[conveyor_index], cell);
				conveyor_index++;
			}else if(cell instanceof CardCell){
				setCell(cardIndexes[card_index], cell);
				card_index++;
			}else if(cell instanceof ContaminationSock){
				setCell(contaminationIndexes[contamination_index], cell);
				contamination_index++;
			}
		}

		for(int i : monsterIndexes){
			
				MonsterCell monsterCell = new MonsterCell(stationedMonsters.get(i).getName(), stationedMonsters.get(i));
				stationedMonsters.get(i).setPosition(monsterIndexes[i]);
				setCell(monsterIndexes[i], monsterCell);
			
		}

		
	 }

	 private void setCardsByRarity(){
		ArrayList<Card> new_originalCards = new ArrayList<>();
		for(Card card: originalCards){
			for(int i=0; i<card.getRarity(); i++){
				new_originalCards.add(card);
			}
		}
		originalCards = new_originalCards;
	 }

	 
	 // Assuming 'activeDeck' is the name of your playable deck
public static void reloadCards() {
    // 1. Create a fresh copy of your expanded 'originalCards'
    // This ensures 'originalCards' stays in its current state
    ArrayList<Card> shuffledCopy = new ArrayList<>(originalCards);
    
    // 2. Shuffle the copy
    Collections.shuffle(shuffledCopy);
    
    // 3. Set your active deck to this new shuffled list
    cards = shuffledCopy;
}

	 public static Card drawCard(){
		 if(cards.isEmpty()){
			 reloadCards();
		 }
		 return cards.remove(0);
	 }

	 public void moveMonster(Monster currentMonster, int roll, Monster opponentMonster) throws InvalidMoveException{
		 int currentPosition = currentMonster.getPosition();
		 int newPosition = (currentPosition + roll) % Constants.BOARD_SIZE;//wrap around the board
		 Cell newCell = getCell(newPosition);
		 
		 if (newCell.isOccupied()) {
	            throw new InvalidMoveException("The cell is occupied by another monster.");
	        }
		 
		 Cell currentCell = getCell(currentPosition);
		 currentCell.setMonster(null); // Clear the current cell
		 
		 newCell.onLand(currentMonster, opponentMonster); // Land on the new cell

		 if(currentMonster.isConfused() && opponentMonster.isConfused()) {
			 currentMonster.decrementConfusion(); // Decrease confusion turns if the monster is confused
			 opponentMonster.decrementConfusion(); // Decrease confusion turns for the opponent as well, if they are confused
		 }
		 
		 updateMonsterPosition(currentMonster, opponentMonster); // Update positions of monsters if needed
	 }

	 private void updateMonsterPosition(Monster monster, Monster opponent) {
		for (Cell[] row : boardCells) {
		    for (Cell cell : row) {
		        //Clear all the cell monster references
		        if (cell.getMonster() != null) {
		            cell.setMonster(null);
		        }
		    }
		}

		Cell monsterCell = getCell(monster.getPosition());
		monsterCell.setMonster(monster); // Set the monster on the new cell
		Cell opponentCell = getCell(opponent.getPosition());
		opponentCell.setMonster(opponent); // Set the opponent on the new cell
	 }
		
	}
