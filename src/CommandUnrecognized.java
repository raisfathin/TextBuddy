/**
 * This class provides execute method to unrecognized commands.
 * 
 * @author A0134155M
 */
public class CommandUnrecognized extends Command {

	private static final String UNRECOGNIZED_COMMAND_ERROR = "Error. Unrecognized command\n";

	public CommandUnrecognized(String commandParameter) {
		super(commandParameter);
	}
	
	@Override
	public void execute(TextList texts, ProgramState currentState) {
		System.out.printf(UNRECOGNIZED_COMMAND_ERROR);
	}

}
