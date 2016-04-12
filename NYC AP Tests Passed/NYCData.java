/**
 * NYCData reads the data from a specific Open Data set from NYC Open Data.
 * The specific Data Set being used is AP  College Board 2010 School Level Results
 * The program reads data from a CSV file and prints out data in small sections at a time
 * To display more data is controlled by user input.
 * 
 * The data is in ascending order from least passing scores by school to the most.
 * Only valid data is used, any schools with less than 5 passing AP scores were removed from data to present.
 * There are 152 valid data entries after removing invalid schools. (excluding the first line of header)
 * All columns are used but the data is printed based on the number of passing tests from each school
 * It is based on an increasing order, starting small and reaching the most passed tests at a school.
 *  
 * @author David Estrich - dme280@nyu.edu
 *
 * URL of the public data is: https://data.cityofnewyork.us/Education/AP-College-Board-2010-School-Level-Results/itfs-ms3e
 */

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NYCData {

	public static int lineCount = 1; // initialize line count at the first line
										// of actual data.
	public static ArrayList<String[]> allStrings = new ArrayList<String[]>(); // initialize
																				// an
																				// ArrayList
																				// of
																				// string
																				// arrays.
	public static String userChoice; // string representation of minimum number
										// of passing test scores user wants to
										// see
	public static int maxMinusMin;
	public static final int LIMIT_ARRAY_LIST = 152; // 152 valid entries in the
													// array list
	public static final int LINE_ADVANCEMENT = 10; //after every display, advances 10 items in arrayList.
	public static final int MOST_PASSED = 2687;
	
	public static void main(String[] args) {

		java.io.File file = new java.io.File(
				"src/AP__College_Board__2010_School_Level_Results3.csv");
		System.out.println("Does file exist? " + file.exists());
		if (file.exists()) {
			try {
				readData(file); // try to read file and catch any IOE exception
			} catch (IOException ex) {
				System.out.println("File does not exist.");
			}
		}
		menu(); // control
		findInitial(); // finds first piece of data in search
		display(); // creates the display to be displayed to the user
		System.out.println("Thank you for data mining.");
	}

	/**
	 * Reads the data from the file
	 * 
	 * @param file
	 *            - the csv file to be read
	 * @throws IOException
	 *             - error of file not existing.
	 */
	public static void readData(File file) throws IOException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			String stringTaken = scanner.nextLine();
			String[] pieces = stringTaken.split(","); //take each CSV from a line and add it to an array.
			allStrings.add(pieces); //add each assembled array to the allStrings arrayList
		}

	}

	/**
	 * Prints a menu for the user. Asks the user to input the minimum number of
	 * passing scores to display. If the user does not input an integer has the
	 * user input a valid int.
	 * 
	 * @return - returns a string version of the user input to be used in
	 *         search.
	 */
	public static void menu() {
		int userInput = 0;
		System.out.println("2010 AP Test Scores in New York by school!");
		System.out
				.println("This app displays data from https://data.cityofnewyork.us/Education/AP-College-Board-2010-School-Level-Results/itfs-ms3e");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		boolean inputInt = true;
		do {
			System.out
					.println("Please enter the minimum number of passing scores for a school to display:");
			try {
				userInput = scan.nextInt();
				if (userInput >= 0 && userInput <= MOST_PASSED) {
					inputInt = false; // sets the flag to false to exit loop if
										// userInput is a valid number.
				} else {
					System.out.println("Please enter a number between 0 and "
							+ MOST_PASSED);
				}
			} catch (InputMismatchException ex) { // catches an input mismatch
													// exception, guaranteeing
													// the input is a valid
													// integer.
				System.out.println("Please input a valid integer.");
				scan.nextLine(); // ignores previous input and asks user to
									// reenter a valid integer.
			}
		} while (inputInt);
		userChoice = Integer.toString(userInput); // change integer once
													// determined to be valid to
													// a string
		// scan.close();
	}

	/**
	 * Displays the mined data in a chart form. First displays the number of
	 * total queries available. Then in a chart displays 10 pieces of data at a
	 * time.
	 */
	public static void display() {
		@SuppressWarnings("resource")
		Scanner continueDisplay = new Scanner(System.in);
		System.out.println("Found " + maxMinusMin + " valid data entries.");
		printIt();
		do {
			String continuation = continueDisplay.next();
			if (continuation.equals("yes")) { // recalls the printIt function,
												// only if the user has input to
												// print a new iteration
				System.out.println("");
				printIt(); // and the limit has not been reached.
			} else if (continuation.equals("exit")) {
				break;
			} else {
				System.out.println("Please enter a valid input.");
			}
		} while (lineCount < LIMIT_ARRAY_LIST); // only prints until the limit
												// of valid data is reached.
	}

	/**
	 * Loops through each element in allStrings and finds the lowest instance of
	 * an object whose number of passing scores is equal to or greater than the
	 * users choice.
	 */
	public static void findInitial() {
		for (int i = 1; i < LIMIT_ARRAY_LIST; i++) { // ignore first line in csv
														// since it is a header.
			if (Integer.valueOf(allStrings.get(i)[4]) >= Integer
					.valueOf(userChoice)) {
				lineCount = i; // set begining line count to a variable.
				maxMinusMin = LIMIT_ARRAY_LIST - lineCount; // determine how
															// many pieces of
															// valid data are in
															// the range.
				break;
			}
		}
	}

	/**
	 * Prints out the 10 iterations of the allStrings Array List After it prints
	 * the 10 strings, it displays instructions to print another 10.
	 * J is the element withing the string object in allStrings.
	 * I is the single digit place of the string number.
	 */
	public static void printIt() {
		System.out
				.println("\t\tDBN\t\tSchool\t\t\t\tAP Test Takers\t\t\tTotalExams\t\tNumber Passed"); // prints
		//a header for each time printIt() gets called. Some schools have extra long names and push data out 1 tab.
																									
		for (int i = 0; i < 10; i++) { // for item 0 - 9 in the set of 10
			for (int j = 0; j <= 4; j++) {
				if (lineCount + i >= LIMIT_ARRAY_LIST) {
					break;
				} else {
				//System.out.print(allStrings.get(lineCount + i)[j]); // linecount
																	// will be
																	// used in
																	// iterations of
																	// 10 and i
																	// representins
				System.out.printf("%20s", allStrings.get(lineCount + i)[j]);
				System.out.print("\t"); // the single digit of the tens place.
				}
			}
			System.out.println("");
			// lineCount++; //check to see the overall limit has not been
			// reached.
		}
		if (lineCount < LIMIT_ARRAY_LIST) {
			lineCount += LINE_ADVANCEMENT; // check to see the overall limit has not been
								// reached.
			System.out.println("");
			System.out.println("ENTER 'yes' TO PRINT THE NEXT 10 or 'exit' to leave.");
		}
	}
}
