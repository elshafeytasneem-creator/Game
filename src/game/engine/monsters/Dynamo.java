package game.engine.monsters;
import game.engine.Role;
import game.engine.interfaces.CanisterModifier;

public class Dynamo extends Monster   {
	 public Dynamo(String name, String description, Role role, int energy){
		 super(name,description,role,energy); 
	 }
	 
<<<<<<< HEAD
	/* public void setEnergy(int energy) {
		    if (energy < 0) {
=======
	 public void setEnergy(int energy) {
		    if (energy < 0 ) {
>>>>>>> 6ea53b29d969b7e32bfc33eebcab0f3f948fee6a
		        super.setEnergy(0); // Cap the energy at 0 if a negative value is provided
		    }
		}*/

	 //
	 public void setEnergy(int energy) {
		 
	     int current = getEnergy();
	     int change = energy - current;
	     super.setEnergy(current + (change * 2)); 
	 } 
	 

	   
	    public void executePowerupEffect(Monster opponentMonster) {
	        opponentMonster.setFrozen(true); 
	    }
	
	// 
}
