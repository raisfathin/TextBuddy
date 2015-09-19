/**
 * This class is used to wrap ProgramStateType so that it can be modified
 * outside the scope where it is declared.
 * 
 * @author A0134155M
 */
public class ProgramState {
	private ProgramStateType currentState = ProgramStateType.STOP;
	
	public void setState(ProgramStateType state) {
		currentState = state;
	}
	
	public ProgramStateType getState() {
		return currentState;
	}
}
