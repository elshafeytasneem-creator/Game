package game.engine.monsters;
import game.engine.Role;

abstract public class Monster implements Comparable<Monster> {
	 private String name;
	 private String description;
	 private Role role;
	 private Role originalRole;
	 private int energy;
	 private int position;
	 private boolean frozen;
	 private boolean shielded;
	 
	 public Monster(String name, String description, Role originalRole, int energy) {
			this.name = name;                     
		    this.description = description;       
		    this.originalRole = originalRole;     
		    this.role = originalRole;             
		    this.position = 0;                     
		    this.confusionTurns = 0;              
		    this.frozen = false;                  
		    this.shielded = false;  
		    if (energy >= 0) {                    
		        this.energy = energy;
		    } else {
		        this.energy = 0;                  // in the constructor or Attributes ?
		    }
		}
	 
	 public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}
	private int confusionTurns;
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Role getOriginalRole() {
		return originalRole;
	}
	
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		 if (energy >= 0) {
		        this.energy = energy;
		    }
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		 if (position >= 0 && position <= 99) {
		        this.position = position;
		    }
	}
	public boolean isShielded() {
		return shielded;
	}
	public void setShielded(boolean shielded) {
		this.shielded = shielded;
	}
	public int getConfusionTurns() {
		return confusionTurns;
	}
	public void setConfusionTurns(int confusionTurns) {
		this.confusionTurns = confusionTurns;
	}
	
	public int compareTo(Monster o) {
	    if (this.position > o.position) {
	        return -1; 
	    } else if (this.position < o.position) {
	        return 1;  
	    } else {
	        return 0; 
	    }
	}
}
