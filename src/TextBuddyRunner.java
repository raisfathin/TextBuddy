import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 * This class is used for TextBuddy's main functionality. This class can be
 * constructed using TextList and String denoting the original text file and
 * the path to the file.
 * 
 * @author A0134155M
 */
public class TextBuddyRunner {

	private static final String INPUT_REQUEST_MSG = "command: ";
	private static final String CANNOT_SAVE_TO_FILE_ERROR = "Cannot save to file\n";
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	// This is to indicate whether the program should still run
	private ProgramState currentProgramState = new ProgramState();

	// This List is used to store all texts in the file
	private TextList texts = null;

	// This String is used to store the path to the text file
	private String filePath = null;

	// This scanner is used by the program to interact with users using command
	// line interface.
	private Scanner userCommandScanner = new Scanner(System.in);
	
	Parser parser = new Parser();

	/**
	 * Create a runner for TextBuddy with list of texts and file path prepared
	 * beforehand.
	 * 
	 * @param texts
	 *        	  the content of original text file
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
		currentProgramState.setState(ProgramStateType.RUNNING);

		while (currentProgramState.getState() != ProgramStateType.STOP) {
			requestInput();
			String userCommand = readUserCommand();
			parseAndRunCommand(userCommand);
			saveToFile();
		}
	}

	/**
	 * This method is used to run the parsed command that is returned by
	 * Parser class.
	 * 
	 * @param userCommand
	 * 			  the command given by user
	 */
	private void parseAndRunCommand(String userCommand) {
		Command parsedCommand = parser.parseCommand(userCommand);
		parsedCommand.setFilePath(filePath);
		parsedCommand.execute(texts, currentProgramState);
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
