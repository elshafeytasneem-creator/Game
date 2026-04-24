package game.engine.cells;

import game.engine.monsters.Monster;

public class MonsterCell extends Cell{
private	Monster cellMonster;


public Monster getCellMonster() {
	return cellMonster;
}

public MonsterCell(String name, Monster cellMonster){
	super(name);
	this.cellMonster=cellMonster;
	
}
public void onLand(Monster landingMonster, Monster opponentMonster){
	super.onLand(landingMonster, opponentMonster);
	if (landingMonster.getRole() == cellMonster.getRole()) {

        landingMonster.executePowerupEffect(opponentMonster);

    } 
	 else {

	        if (landingMonster.getEnergy() > cellMonster.getEnergy()) {

	            int landingEnergy = landingMonster.getEnergy();
	            int cellEnergy = cellMonster.getEnergy();

	            // If landing monster is shielded
	            if (landingMonster.isShielded()) {

	                // Landing monster keeps its energy
	                // Cell monster still gains the higher energy
	                cellMonster.setEnergy(landingEnergy);

	            } 
	            else {
	                // Normal swap
	                landingMonster.setEnergy(cellEnergy);
	                cellMonster.setEnergy(landingEnergy);
	            }
	        }
	    }
}
}
