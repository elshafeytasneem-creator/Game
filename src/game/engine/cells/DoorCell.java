package game.engine.cells;
import game.engine.Role;
import game.engine.interfaces.CanisterModifier;
import game.engine.monsters.*;
import game.engine.Board;

public class DoorCell extends Cell implements CanisterModifier {
private Role role;
private int energy;
private boolean activated;

public Role getRole() {
	return role;
}

public int getEnergy() {
	return energy;
}

public boolean isActivated() {
	return activated;
}
public void setActivated(boolean activated) {
	this.activated = activated;
}
public DoorCell(String name, Role role, int energy){
	super(name);
	this.role=role;
	this.energy=energy;
	activated=false;
	
}
@Override
public void onLand(Monster landingMonster, Monster opponentMonster){
	super.onLand(landingMonster, opponentMonster);
	if (!this.isActivated()){
		int value=(landingMonster.getRole()== role)? energy :-energy;
		boolean changed=false;
		int before=landingMonster.getEnergy();
		modifyCanisterEnergy(landingMonster,value);
		
		if(landingMonster.getEnergy()!=before)
			changed=true;
		
		 for (Monster m : Board.getStationedMonsters()) {

	            if (m.getRole() == landingMonster.getRole()) {

	                int prev = m.getEnergy();

	                modifyCanisterEnergy(m, value);

	                if (m.getEnergy() != prev) {
	                    changed = true;
	                }
	            }
	        }
		
		if (changed)
			activated=true;
	}

}

@Override
public void modifyCanisterEnergy(Monster monster, int canisterValue) {
	monster.alterEnergy(canisterValue);
	
}
}
