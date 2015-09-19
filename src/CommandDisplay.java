
public class CommandDisplay extends Command {

	private static final String EMPTY_TEXT_MSG = "%s is empty\n";
	private static final String TEXT_DISPLAY_MSG = "%d: %s\n";

	public CommandDisplay(String commandParameter) {
		super(commandParameter);
	}
	
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
