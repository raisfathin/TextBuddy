
public class CommandExit extends Command {

	public CommandExit(String commandParameter) {
		super(commandParameter);
	}
	
	@Override
	public void execute(TextList texts, ProgramState currentState) {
		currentState.setState(ProgramStateType.STOP);
	}

}
