package game.engine.monsters;
import game.engine.Role;

public class Dynamo extends Monster {
	 public Dynamo(String name, String description, Role role, int energy){
		 super(name,description,role,energy); 
	 }
	 
	 public void setEnergy(int energy) {
		    if (energy < 0) {
		        super.setEnergy(0); // Cap the energy at 0 if a negative value is provided
		    }
		}
}
