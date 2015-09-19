import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 * This class is used for TextBuddy's main functionality. This class can be
 * constructed using List<String> and String denoting the original text file and
 * the path to the file.
 * 
 * @author A0134155M
 */
public class TextBuddyRunner {

	private static final String INPUT_REQUEST_MSG = "command: ";
	private static final String UNRECOGNIZED_COMMAND_ERROR = "Error. Unrecognized command\n";
	private static final String ADD_MSG = "added to %s: \"%s\"\n";
	private static final String DELETE_MSG = "deleted from %s: \"%s\"\n";
	private static final String EMPTY_TEXT_MSG = "%s is empty\n";
	private static final String CLEAR_MSG = "all content deleted from %s\n";
	private static final String TEXT_DISPLAY_MSG = "%d: %s\n";
	private static final String SORT_MSG = "%s has been successfully sorted\n";
	private static final String INVALID_TEXT_INDEX_ERROR = "Invalid text index\n";
	private static final String CANNOT_SAVE_TO_FILE_ERROR = "Cannot save to file\n";
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	// This is to indicate whether the program should still run
	private ProgramState currentProgramState;

	// This List is used to store all texts in the file
	private TextList texts = null;

	// This String is used to store the path to the text file
	private String filePath = null;

	// This scanner is used by the program to interact with users using command
	// line interface.
	private Scanner userCommandScanner = new Scanner(System.in);

	/**
	 * Create a runner for TextBuddy with list of texts and file path prepared
	 * beforehand.
	 * 
	 * @param texts
	 *            the content of original text file
	 * @param filePath
	 *            string containing the path to the text file
	 */
	public TextBuddyRunner(TextList texts, String filePath) {
		this.texts = texts;
		this.filePath = filePath;
	}

	/**
	 * This method is used as a starting point for TextBuddy's core
	 * functionality. This method also serves as the main loop of the program as
	 * TextBuddy always waits for the next command from the user. This method
	 * also determines when to terminate for the program.
	 */
	public void run() {
		currentProgramState = ProgramState.RUNNING;

		while (currentProgramState != ProgramState.STOP) {
			requestInput();
			String userCommand = readUserCommand();
			parseAndRunCommand(userCommand);
			saveToFile();
		}
	}

	/**
	 * This method is used to parse the command given by the user, and run it.
	 * 
	 * @param userCommand
	 *            is the full command given by the user.
	 */
	private void parseAndRunCommand(String userCommand) {
		CommandType commandType = getCommandTypeFromUserInput(userCommand);
		String commandParameter;

		switch (commandType) {
			case ADD :
				commandParameter = getCommandParameter(userCommand, commandType);
				addText(commandParameter);
				break;
			case DISPLAY :
				displayTexts();
				break;
			case DELETE :
				commandParameter = getCommandParameter(userCommand, commandType);
				deleteText(commandParameter);
				break;
			case CLEAR :
				deleteAllTexts();
				break;
			case EXIT :
				stopProgram();
				break;
			case SORT :
				sortAllTexts();
				break;
			case UNRECOGNIZED :
				printUnrecognizedCommandError();
				break;
			default :
				throw new AssertionError("There is no other type of command");
		}
	}

	private void sortAllTexts() {
		texts.sort();
		System.out.printf(SORT_MSG, filePath);
	}

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
		}

		return commandParameter;
	}

	/**
	 * This method is used to save the text file currently saved in the memory
	 * to the file that the user has specified.
	 */
	private void saveToFile() {
		try {
			File outputFile = new File(filePath);
			FileWriter fileWriter = new FileWriter(outputFile, false);

			for (int i = 0; i < texts.size(); i++) {
				fileWriter.write(texts.get(i));
				fileWriter.write(LINE_SEPARATOR);
			}

			fileWriter.close();
		} catch (IOException e) {
			System.out.printf(CANNOT_SAVE_TO_FILE_ERROR);
		}
	}

	/**
	 * This method is used to print an error whenever the user enters a command
	 * that is not a part of TextBuddy's standard command set.
	 */
	private void printUnrecognizedCommandError() {
		System.out.printf(UNRECOGNIZED_COMMAND_ERROR);
	}

	/**
	 * This is to indicate to the main loop that the program needs to stop after
	 * the user enters an exit command.
	 */
	private void stopProgram() {
		currentProgramState = ProgramState.STOP;
	}

	/**
	 * This method is used to clear all texts currently saved in memory.
	 */
	private void deleteAllTexts() {
		texts.clear();
		System.out.printf(CLEAR_MSG, filePath);
	}

	/**
	 * This method is used to serve the delete function of TextBuddy. This
	 * method accepts a string containing the index of the text. No assumption
	 * of the string is made (i.e. it is possible that the string is not a valid
	 * string containing the index of the text).
	 * 
	 * @param textIndexString
	 *            is a string containing the index of the text that the user
	 *            wanted to delete.
	 */
	private void deleteText(String textIndexString) {
		if (!isANumber(textIndexString)) {
			System.out.printf(INVALID_TEXT_INDEX_ERROR);
		} else {
			// texts are numbered from zero in this implementation
			int textIndex = Integer.parseInt(textIndexString) - 1;
			if (!validTextIndex(textIndex)) {
				System.out.printf(INVALID_TEXT_INDEX_ERROR);
			} else {
				System.out.printf(DELETE_MSG, filePath, texts.get(textIndex));
				texts.remove(textIndex);
			}
		}
	}

	/**
	 * This method is used to check whether a particular text index is valid in
	 * our list of texts.
	 * 
	 * @param textIndex
	 *            is the text index given by the user.
	 * @return boolean, whether the text index is valid in our list of texts.
	 */
	private boolean validTextIndex(int textIndex) {
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
	 * This method is used to serve the display text function of TextBuddy. This
	 * method displays all the texts stored in memory.
	 */
	private void displayTexts() {
		if (texts.size() == 0) {
			System.out.printf(EMPTY_TEXT_MSG, filePath);
		} else {
			for (int i = 0; i < texts.size(); i++) {
				System.out.printf(TEXT_DISPLAY_MSG, i + 1, texts.get(i));
			}
		}
	}

	/**
	 * This method is used to serve the add text function of TextBuddy. This
	 * method adds a text to the list of texts in the memory.
	 * 
	 * @param textToBeAdded
	 *            the string that the user wanted to add.
	 */
	private void addText(String textToBeAdded) {
		System.out.printf(ADD_MSG, filePath, textToBeAdded);
		texts.addText(textToBeAdded);
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
				case "exit" :
					return CommandType.EXIT;
				default :
					return CommandType.UNRECOGNIZED;
			}
		}
	}

	/**
	 * This method is used to print a message to prompt the user for input
	 */
	private void requestInput() {
		System.out.printf(INPUT_REQUEST_MSG);
	}

	/**
	 * This method is used to read the command given by the user (currently
	 * through CLI)
	 * 
	 * @return the command given by the user.
	 */
	private String readUserCommand() {
		return userCommandScanner.nextLine().trim();
	}

}
