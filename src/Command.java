
public abstract class Command {
	private String filePath, commandParameter;
	
	public Command(String commandParameter) {
		this.commandParameter = commandParameter;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public abstract void execute(TextList texts, ProgramState currentState);
}
