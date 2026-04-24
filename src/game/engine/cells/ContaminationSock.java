package game.engine.cells;
import game.engine.interfaces.CanisterModifier;
import game.engine.monsters.Monster;
import game.engine.*;

public class ContaminationSock extends TransportCell implements CanisterModifier {
public ContaminationSock(String name, int effect){
	super(name,effect);
}
public  void transport(Monster monster){
	monster.move(-Math.abs( this.getEffect()));
}
public void onLand(Monster landingMonster, Monster opponentMonster){
	super.onLand(landingMonster, opponentMonster);
	landingMonster.alterEnergy(-Constants.SLIP_PENALTY);
	this.transport(landingMonster);
	
}
@Override
public void modifyCanisterEnergy(Monster monster, int canisterValue) {
	// TODO Auto-generated method stub
	
}

}
