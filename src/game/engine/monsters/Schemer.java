package game.engine.monsters;
import game.engine.Role;
import game.engine.Constants;
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
	

}
