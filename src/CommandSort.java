/**
 * This class provides execute method for sort commands
 * 
 * @author A0134155M
 */
public class CommandSort extends Command {

	private static final String SORT_MSG = "%s has been successfully sorted\n";

	public CommandSort(String commandParameter) {
		super(commandParameter);
	}
	
	/**
	 * This method provides sort functionality for TextBuddy
	 * 
	 * @param texts
	 * 			    TextList that is being operated on
	 * @param currentState
	 * 				program state to be modified (if modified)
	 */
	@Override
	public void execute(TextList texts, ProgramState currentState) {
		texts.sort();
		System.out.printf(SORT_MSG, filePath);
	}

}
