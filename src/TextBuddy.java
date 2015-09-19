import java.io.File;
import java.io.IOException;

import java.util.Scanner;

/**
 * Text Buddy is a simple command line program to manipulate text in a text
 * file. The text file is given by the user as command line parameter. If the
 * text file does not exist, Text Buddy will create it automatically. If it
 * exists, TextBuddy will load the content into the program. Error will be shown
 * if the text file is a directory. It is assumed that users do not need extra
 * whitespace surrounding their texts. Text Buddy saves any change to the text
 * file after every command.
 * There are 6 commands supported in this version :
 * 1. Add text to the end of file
 * 2. Delete text
 * 3. Display all texts
 * 4. Clear all texts
 * 5. Sort texts in file
 * 6. Search for keyword in file
 * 7. Exit program
 * 
 * Command format is given by the example interaction below :
 
c:>java TextBuddy mytextfile.txt
Welcome to TextBuddy. mytextfile.txt is ready for use
command: add little brown fox
added to mytextfile.txt: “little brown fox”
command: display
1. little brown fox
command: add jumped over the moon
added to mytextfile.txt: “jumped over the moon”
command: display
1. little brown fox
2. jumped over the moon
command: sort
mytextfile.txt has been successfully sorted
command: display
1. jumped over the moon
2. little brown fox
command: delete 1
deleted from mytextfile.txt: “jumped over the moon”
command: display
1. little brown fox
command: clear
all content deleted from mytextfile.txt
command: display
mytextfile.txt is empty
command: exit
c:>
 
 * This class prepares the necessary initialization like checking file validity,
 * loading the file into memory, and printing welcome message before running the
 * program. After the preparation is done, another class will then take over to
 * run TextBuddy's main functionality.
 * 
 * @author A0134155M
 */
public class TextBuddy {

	private static final String WELCOME_MSG = "Welcome to TextBuddy. %s is ready for use\n";
	private static final String HOW_TO_USE_MSG = "Usage: java TextBuddy <file>\n";
	private static final String UNABLE_TO_CREATE_FILE_ERROR = "Error. Cannot create file %s\n";
	private static final String FILE_IS_DIRECTORY_ERROR = "Error. %s is a directory\n";

	// This File object is used to represent the text file in our program
	private static File textFile;

	// This Scanner is used to read from the text file
	private static Scanner textFileScanner;

	// This TextList is used to store user texts in our program
	private static TextList texts = new TextList();

	// This TextBuddyRunner will run the core functionality of TextBuddy
	private static TextBuddyRunner runner;

	public static void main(String[] args) {
		checkArgument(args);
		processFile(args);
		printWelcomeMessage(args);
		runTextBuddy(args);
	}

	/**
	 * This method is used to run TextBuddy by creating TextBuddyRunner class
	 * 
	 * @param args
	 *            is the program parameter given by the user.
	 */
	private static void runTextBuddy(String[] args) {
		runner = new TextBuddyRunner(texts, getFilePath(args));
		runner.run();
	}

	private static void processFile(String[] args) {
		checkFileValidity(args);
		loadFileContent(args);
	}

	/**
	 * This method is used to load the content of the text file into the program.
	 * This method assumes that the filePath supplied is valid.
	 * 
	 * @param args
	 *            is the program parameter given by the user.
	 */
	private static void loadFileContent(String[] args) {
		try {
			String filePath = getFilePath(args);
			textFile = new File(filePath);
			textFileScanner = new Scanner(textFile);

			while (textFileScanner.hasNextLine()) {
				texts.addText(textFileScanner.nextLine());
			}
		} catch (IOException e) {
			assert(false);
		}
	}

	/**
	 * This method is used to check whether the text file specified by the user
	 * is not a directory. If the file does not exist, this method will create
	 * an empty file with the same name as specified by the user.
	 * 
	 * @param args
	 *            is the program parameter given by the user.
	 */
	private static void checkFileValidity(String[] args) {
		try {
			String filePath = getFilePath(args);
			textFile = new File(filePath);

			if (!textFile.exists()) {
				textFile.createNewFile();
			} else if (textFile.isDirectory()) {
				System.out.println(FILE_IS_DIRECTORY_ERROR);
				System.exit(0);
			}
		} catch (IOException e) {
			System.out.printf(UNABLE_TO_CREATE_FILE_ERROR);
		}
	}

	/**
	 * This method is used to extract file path from command line argument given
	 * by the user.
	 * 
	 * @param args
	 *            is the command line argument given by the user.
	 * @return file path string
	 */
	private static String getFilePath(String[] args) {
		return args[0];
	}

	private static void printWelcomeMessage(String[] args) {
		String filePath = getFilePath(args);
		System.out.printf(WELCOME_MSG, filePath);
	}

	/**
	 * This method is used to check whether the command line argument given by
	 * the user conforms to the format.
	 * 
	 * @param args
	 *            is the command line argument given by the user.
	 */
	private static void checkArgument(String[] args) {
		if (args.length != 1) {
			System.out.printf(HOW_TO_USE_MSG);
			System.exit(0);
		}
	}

}
