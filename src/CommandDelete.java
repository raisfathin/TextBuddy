/**
 * This class provides execute method for delete commands
 * 
 * @author A0134155M
 */
public class CommandDelete extends Command {

	private static final String INVALID_TEXT_INDEX_ERROR = "Invalid text index\n";
	private static final String DELETE_MSG = "deleted from %s: \"%s\"\n";
	
	public CommandDelete(String commandParameter) {
		super(commandParameter);
	}

	/**
	 * This method is used to check whether a particular text index is valid in
	 * our list of texts.
	 * 
	 * @param textIndex
	 *            is the text index given by the user.
	 * @return boolean, whether the text index is valid in our list of texts.
	 */
	private boolean validTextIndex(TextList texts, int textIndex) {
		return 0 <= textIndex && textIndex < texts.size();
	}

	/**
	 * This method checks whether a string can be parsed into a number
	 * 
	 * @param textIndexString
	 *            is the string to be parsed as number.
	 * @return whether we can parse the string as number.
	 */
	private boolean isANumber(String textIndexString) {
		try {
			Integer.parseInt(textIndexString);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * This method provides delete text functionality for TextBuddy
	 * 
	 * @param texts
	 * 			    TextList that is being operated on
	 * @param currentState
	 * 				program state to be modified (if modified)
	 */
	@Override
	public void execute(TextList texts, ProgramState currentState) {
		if (!isANumber(commandParameter)) {
			System.out.printf(INVALID_TEXT_INDEX_ERROR);
		} else {
			// texts are numbered from zero in this implementation
			int textIndex = Integer.parseInt(commandParameter) - 1;
			if (!validTextIndex(texts, textIndex)) {
				System.out.printf(INVALID_TEXT_INDEX_ERROR);
			} else {
				System.out.printf(DELETE_MSG, filePath, texts.get(textIndex));
				texts.remove(textIndex);
			}
		}
	}

}
