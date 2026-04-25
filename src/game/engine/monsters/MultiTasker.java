package game.engine.monsters;
import game.engine.Role;
import game.engine.Constants;

public class MultiTasker extends Monster {
	  public int normalSpeedTurns;

	public int getNormalSpeedTurns() {
		return normalSpeedTurns;
	}

	public void setNormalSpeedTurns(int normalSpeedTurns) {
		this.normalSpeedTurns = normalSpeedTurns;
	}
	  public MultiTasker(String name, String description, Role role, int energy){
		  super(name, description, role, energy);
		  this.normalSpeedTurns=0;
	  }
	  
	    public void setEnergy(int energy) {
	        int current = getEnergy();
	        int change = energy - current;
	        super.setEnergy(current + change + Constants.MULTITASKER_BONUS); 
	    }

	    @Override
	    public void executePowerupEffect(Monster opponent) {
	        setNormalSpeedTurns(2); 
	    }
	 //move at 1/2 the dice roll for 2 turns is in boards
}
