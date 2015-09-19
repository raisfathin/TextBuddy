/**
 * This class provides execute method for add commands
 * 
 * @author A0134155M
 */
public class CommandAdd extends Command {

	private static final String ADD_MSG = "added to %s: \"%s\"\n";
	
	public CommandAdd(String commandParameter) {
		super(commandParameter);
	}
	
	/**
	 * This method provides add text functionality for TextBuddy
	 * 
	 * @param texts
	 * 			    TextList that is being operated on
	 * @param currentState
	 * 				program state to be modified (if modified)
	 */
	@Override
	public void execute(TextList texts, ProgramState currentState) {
		System.out.printf(ADD_MSG, filePath, commandParameter);
		texts.addText(commandParameter);
	}
}
