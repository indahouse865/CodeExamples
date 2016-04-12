/**
 * Gradebook - Creates a menu for user to interact with that can edit a gradebook for a student. Choices include, adding, deleting, updating,
 * printing, and printing statistics, gives the user the option to exit the program at will. 
 * @author David Estrich - dme280
 * 
 */
import java.util.Scanner;

public class Gradebook {

	static Scanner input = new Scanner(System.in); // creates a global scanner for the class
	static final int leaveGradebook = 6; // hardcoded value for exiting program at menu
	static final int minGradebook = 1; // minimun number possible to choose for gradebook in menu
	static final int minimumGrade = 0;
	static final int maximumGrade = 100;

	/**
	 * Main function to control the program.
	 * Uses switch to determine which method the user wants to call
	 * initializes an array that will create your gradebook.
	 * 
	 */
	public static void main(String[] args) {
		int[] array = new int[1];
		int Number = menu();
		while (Number != 6) {
			switch (Number) {

			case 1:
				Number = 1;
				array = addGrade(array);
				break;

			case 2:
				Number = 2;
				array = deleteGrade(array);
				break;

			case 3:
				Number = 3;
				System.out.println("What grade do you want to update: (0-" + array.length + "): ");
				int index = input.nextInt();
				System.out.println("The updated grade is: ");
				int value = input.nextInt();
				array[index] = value;
				break;

			case 4:
				Number = 4;
				System.out.println("Num | Grade");
				System.out.println("--------------");
				for (int i = 0; i < array.length; i++) {
					System.out.println("[" + i + "] | " + array[i]);
				}
				break;

			case 5:
				Number = 5;
				statistics(array);
				break;

			}

			Number = menu();
		}
	}

	/**
	 * menu - prints the menu for the user. Calls a check on whether user
	 * entered a valid number and returns the chosen option to the main function
	 * 
	 * @return <code>userChoice</code> returns a valid integer choice for the main function
	 *         to choose which method to execute in gradebook.
	 */

	public static int menu() {
		System.out.println("Welcome To Gradebook v3.14");
		System.out.println("-----------------------------------");
		System.out.println("1) Add a Grade\n2) Delete A Grade\n3) Update A Grade\n4) Print Grades\n5) Print Statistics\n6) Quit");
		System.out.print("Choose an option (1-6): "); // prints out options in form of table and prompts user actions
		int userChoice = input.nextInt();
		while (userChoice < minGradebook || userChoice > leaveGradebook) {
			System.out.println("Entry is invalid, please try again: ");
			userChoice = input.nextInt();
		}
		return userChoice;
	}

	/**
	 * Takes a grade and adds a new grade to the end of an array by calling a
	 * function
	 * 
	 * @param <code>array</code> takes the array that will have a grade added.
	 * @return newArray returns the array with the grade added
	 */

	public static int[] addGrade(int[] array) {
		System.out.println("Enter a grade to add: ");
		int grade = input.nextInt();
		while (grade < minimumGrade || grade >= maximumGrade) {
			System.out.println("This is not a valid grade, please try again: ");
			grade = input.nextInt();
		}
		int[] newArray = Arrayders.insert(array, grade);
		// System.out.println(newArray[0]);
		return newArray;
	}

	/**
	 * deletedGrade take an array, asks the user to choose an item to delete from their gradebook and returns an array with all but the
	 * chose item deleted
	 * @param <code>array</code> The current array represents the grades that have already been inputed and will be manipulated
	 * @return <code>newArray</code> The duplicate of the original array but with the chosen item removed.
	 */
	public static int[] deleteGrade(int[] array) {
		int newLength = array.length - 1;
		System.out.println("Enter a grade to delete (0-" + newLength + "): ");
		int grade = input.nextInt();
		while (grade < minimumGrade || grade > array.length) {
			System.out.println("This is not a valid grade, please try again: ");
			grade = input.nextInt();
		}
		int[] newArray = Arrayders.delete(array, grade);
		return newArray;
	}

	/**
	 * Takes an array and sends it to multiple methods to determine: Average,
	 * Median, Mode, Highest, Lowest, and Range. Then prints all of them out in a table format.
	 * 
	 * @param array
	 *            Receives the array that is used to determine all the
	 *            statistics about grades.
	 */
	public static void statistics(int[] array) {
		int[] highLowFunction = Arrayders.highLow(array);
		System.out.println("Average: " + Arrayders.average(array));
		System.out.println("Median: " + Arrayders.median(array));
		System.out.println("Mode: " + Arrayders.mode(array));
		System.out.println("Highest: " + highLowFunction[1]);
		System.out.println("Lowest: " + highLowFunction[0]);
		System.out.println("Range: " + Arrayders.range(array));

	}

}
