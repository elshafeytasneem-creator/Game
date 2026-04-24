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
	//
    public void setEnergy(int energy) {
        int current = getEnergy();
        int change = energy - current;
        super.setEnergy(current + change + Constants.SCHEMER_STEAL); 
    }

   
    public void executePowerupEffect(Monster opponentMonster) {
        int totalStolen = 0;
        totalStolen += stealEnergyFrom(opponentMonster); 
        for (Monster m : Board.getStationedMonsters()) {  
            totalStolen += stealEnergyFrom(m);
        }
        alterEnergy(totalStolen); 
    }
    //
	

}
