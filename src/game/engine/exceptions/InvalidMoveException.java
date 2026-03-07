package game.engine.exceptions;

public class InvalidMoveException extends GameActionException {
	private static final String MSG =  "Action done on wrong turn";
	
	public  InvalidMoveException(){
		super(MSG);
	}
	
	public InvalidMoveException(String message){
		super(message);
	}
}
