/**
 * This class serves as a skeleton for all commands. This class provides execute method
 * so to execute the command.
 * 
 * @author A0134155M
 */
public abstract class Command {
	//Some commands need file path informations and command parameters to execute.
	protected String filePath, commandParameter;
	
	protected Command(String commandParameter) {
		this.commandParameter = commandParameter;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * This method serves as a skeleton for all commands' execute method.
	 * 
	 * @param texts
	 * 				TextList that is being operated on
	 * @param currentState
	 * 				program state to be modified (if modified)
	 */
	public abstract void execute(TextList texts, ProgramState currentState);
}
