
public class CommandClear extends Command {

	private static final String CLEAR_MSG = "all content deleted from %s\n";

	public CommandClear(String commandParameter) {
		super(commandParameter);
	}
	
	@Override
	public void execute(TextList texts, ProgramState currentState) {
		texts.clear();
		System.out.printf(CLEAR_MSG, filePath);
	}

}
