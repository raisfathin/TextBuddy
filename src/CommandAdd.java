
public class CommandAdd extends Command {

	private static final String ADD_MSG = "added to %s: \"%s\"\n";
	
	public CommandAdd(String commandParameter) {
		super(commandParameter);
	}
	
	@Override
	public void execute(TextList texts, ProgramState currentState) {
		System.out.printf(ADD_MSG, filePath, commandParameter);
		texts.addText(commandParameter);
	}
}
