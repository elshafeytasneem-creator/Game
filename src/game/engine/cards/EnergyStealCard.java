package game.engine.cards;

import game.engine.monsters.Monster;

public class EnergyStealCard extends Card {

	private int energy;
	
	public EnergyStealCard(String name, String description, int rarity, int energy){
		
		super(name,description,rarity,true);
		this.energy=energy;
		
	}
	public int getEnergy(){
		return energy;
	}
	
	
	public void performAction(Monster player, Monster opponent) {

	    // Step 1: max possible steal
	    int stealAmount = Math.min(opponent.getEnergy(), getEnergy());

	    // Step 2: store energy before
	    int before = opponent.getEnergy();

	    // Step 3: attempt to steal (shield may block)
	    opponent.alterEnergy(-stealAmount);

	    // Step 4: calculate ACTUAL stolen amount
	    int actualStolen = before - opponent.getEnergy();

	    // Step 5: give player only what was actually stolen
	    player.alterEnergy(actualStolen);
	}
	
}
