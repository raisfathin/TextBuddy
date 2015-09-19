/**
 * This class provides execute method for exit commands.
 * 
 * @author A0134155M
 */
public class CommandExit extends Command {

	public CommandExit(String commandParameter) {
		super(commandParameter);
	}
	
	/**
	 * This method provides exit functionality for TextBuddy
	 * 
	 * @param texts
	 * 			    TextList that is being operated on
	 * @param currentState
	 * 				program state to be modified (if modified)
	 */
	@Override
	public void execute(TextList texts, ProgramState currentState) {
		currentState.setState(ProgramStateType.STOP);
	}

}
