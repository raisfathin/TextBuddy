
public class ProgramState {
	private ProgramStateType currentState = ProgramStateType.STOP;
	
	public void setState(ProgramStateType state) {
		currentState = state;
	}
	
	public ProgramStateType getState() {
		return currentState;
	}
}
