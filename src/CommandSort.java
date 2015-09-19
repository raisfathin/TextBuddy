
public class CommandSort extends Command {

	private static final String SORT_MSG = "%s has been successfully sorted\n";

	public CommandSort(String commandParameter) {
		super(commandParameter);
	}
	
	@Override
	public void execute(TextList texts, ProgramState currentState) {
		texts.sort();
		System.out.printf(SORT_MSG, filePath);
	}

}
