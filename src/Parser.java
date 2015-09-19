
public class Parser {
	
	/**
	 * This operation splits the command entered by user to separate the command
	 * type from the parameter. Since only add and delete has parameter, if the
	 * command given is neither add nor delete, this method will return null.
	 * 
	 * @param userCommand
	 *            is the full command given by the user
	 * @param commandType
	 *            is the command type of the whole user command
	 * @return command parameter if exists, null otherwise
	 */
	private String getCommandParameter(String userCommand, CommandType commandType) {
		String commandParameter = null;

		if (commandType == CommandType.ADD) {
			commandParameter = userCommand.replaceFirst("\\s*add\\s*", "");
		} else if (commandType == CommandType.DELETE) {
			commandParameter = userCommand.replaceFirst("\\s*delete\\s*", "");
		} else if (commandType == CommandType.SEARCH) {
			commandParameter = userCommand.replaceFirst("\\s*search\\s*", "");
		}

		return commandParameter;
	}
	

	/**
	 * This method is used to get the command type of the full user command.
	 * 
	 * @param userInput
	 *            is the full command given by the user.
	 * @return the command type if the command is recognized, unrecognized
	 *         otherwise.
	 */
	private CommandType getCommandTypeFromUserInput(String userInput) {
		String[] splitUserInput = userInput.split("\\s+", 2);
		if (splitUserInput.length == 0) {
			return CommandType.UNRECOGNIZED;
		} else {
			String userCommandType = splitUserInput[0];
			switch (userCommandType.toLowerCase()) {
				case "add" :
					return CommandType.ADD;
				case "display" :
					return CommandType.DISPLAY;
				case "delete" :
					return CommandType.DELETE;
				case "clear" :
					return CommandType.CLEAR;
				case "sort" :
					return CommandType.SORT;
				case "search" :
					return CommandType.SEARCH;
				case "exit" :
					return CommandType.EXIT;
				default :
					return CommandType.UNRECOGNIZED;
			}
		}
	}
	
	/**
	 * This method is used to parse the command given by the user, and return a
	 * command object that can be run.
	 * 
	 * @param userCommand
	 *            is the full command given by the user.
	 */
	public Command parseCommand(String userCommand) {
		CommandType commandType = getCommandTypeFromUserInput(userCommand);
		String commandParameter = getCommandParameter(userCommand, commandType);

		switch (commandType) {
			case ADD :
				return new CommandAdd(commandParameter);
			case DISPLAY :
				return new CommandDisplay(commandParameter);
			case DELETE :
				return new CommandDelete(commandParameter);
			case CLEAR :
				return new CommandClear(commandParameter);
			case EXIT :
				return new CommandExit(commandParameter);
			case SORT :
				return new CommandSort(commandParameter);
			case SEARCH :
				commandParameter = getCommandParameter(userCommand, commandType);
				return new CommandSearch(commandParameter);
			case UNRECOGNIZED :
				return new CommandUnrecognized(commandParameter);
			default :
				throw new AssertionError("There is no other type of command");
		}
	}
}
