
public class CommandSearch extends Command {

	private static final String SEARCH_NO_RESULT_MSG = "No results found\n";
	private static final String TEXT_DISPLAY_MSG = "%d: %s\n";
	
	public CommandSearch(String commandParameter) {
		super(commandParameter);
	}
	
	
	/* (non-Javadoc)
	 * @see Command#execute(TextList, ProgramState)
	 */
	@Override
	public void execute(TextList texts, ProgramState currentState) {
		Integer[] result = texts.search(commandParameter);
		if (result.length == 0) {
			System.out.printf(SEARCH_NO_RESULT_MSG);
		} else {
			for (int i = 0; i < result.length; i++) {
				System.out.printf(TEXT_DISPLAY_MSG, result[i] + 1, texts.get(result[i]));
			}
		}
	}

}
