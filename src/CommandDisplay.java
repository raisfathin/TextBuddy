/**
 * This class provides execute method for search commands.
 * 
 * @author A0134155M
 */
public class CommandDisplay extends Command {

	private static final String EMPTY_TEXT_MSG = "%s is empty\n";
	private static final String TEXT_DISPLAY_MSG = "%d. %s\n";

	public CommandDisplay(String commandParameter) {
		super(commandParameter);
	}
	
	/**
	 * This method provides display functionality for TextBuddy
	 * 
	 * @param texts
	 * 			    TextList that is being operated on
	 * @param currentState
	 * 				program state to be modified (if modified)
	 */
	@Override
	public void execute(TextList texts, ProgramState currentState) {
		if (texts.size() == 0) {
			System.out.printf(EMPTY_TEXT_MSG, filePath);
		} else {
			for (int i = 0; i < texts.size(); i++) {
				System.out.printf(TEXT_DISPLAY_MSG, i + 1, texts.get(i));
			}
		}
	}

}
