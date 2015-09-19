/**
 * This class provides clear method for clear commands
 * 
 * @author A0134155M
 */
public class CommandClear extends Command {

	private static final String CLEAR_MSG = "all content deleted from %s\n";

	public CommandClear(String commandParameter) {
		super(commandParameter);
	}
	
	/**
	 * This method provides clear text functionality for TextBuddy
	 * 
	 * @param texts
	 * 			    TextList that is being operated on
	 * @param currentState
	 * 				program state to be modified (if modified)
	 */
	@Override
	public void execute(TextList texts, ProgramState currentState) {
		texts.clear();
		System.out.printf(CLEAR_MSG, filePath);
	}

}
