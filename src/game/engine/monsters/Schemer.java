package game.engine.monsters;
import game.engine.Role;
import game.engine.Constants;
import game.engine.Board;
public class Schemer extends Monster{
	public Schemer(String name, String description, Role role, int energy) {
		
		super(name,description,role,energy);
		
	}
	private int stealEnergyFrom(Monster target){
		 int StealAmount =Constants.SCHEMER_STEAL;
		 int  targetEnergy =target.getEnergy();
		 int stolen;
		 if (targetEnergy>StealAmount)
			 stolen= StealAmount;
		 else 
			stolen =targetEnergy;
		 target.setEnergy(targetEnergy - stolen);

		 return stolen;
			
	}
	
    public void setEnergy(int energy) {
        int current = getEnergy();
        int change = energy - current;
        super.setEnergy(current + change + Constants.SCHEMER_STEAL); 
    }

   
   public void executePowerupEffect(Monster opponent) {
    int totalStolen = 0;

    // 1. Steal from the primary opponent
    totalStolen += stealEnergyFrom(opponent);

    // 2. Steal from all other stationed monsters (excluding the opponent we just hit)
    for (Monster m : Board.getStationedMonsters()) {
        if (m != opponent && m != this) { // Ensure we don't steal from ourselves or the primary opponent again
            totalStolen += stealEnergyFrom(m);
        }
    }

    // 3. Apply the single total bonus. 
    // This calls setEnergy internally, triggering your +10 passive once.
    alterEnergy(totalStolen);
}
    
	

}
