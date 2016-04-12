/**
 * Tests the sort and search functions in an arrayders program. Prints out the sort times for my own sort function, Professor Case's bubble sort
 * and the Java.sort() function.
 * 
 * Also performs a search on arrays with a length of 100000 using linear search, binary search, and java search and reports the time required
 * for each search.
 * 
 * @author David Estrich
 *
 *bubbleSort method taken from code given in class
 *linearSearch method taken from class 
 *The longer the array gets the longer it takes to sort and search respectively. An individual search takes significantly less time than sorting
 *an entire array but as the array lengthens and the number of searches increases to 100,000 the total time for searches can surpass the time
 *it takes to sort. This is partly due to the randomness involved of the numbers being searched for actually being in the array or not and where
 *in the array the element may be after being sorted. 
 *Bubble sort takes a significantly larger amount of time than the other two sorts because it iterates over every element each repetition, even
 *if the elements are in order. There is randomness involved though because sometimes the array was sorted faster using bubble sort, this is
 *likely due to the randomness of the elements in the array generated.
 *
 *For searches the overall time for each was nearly the same. At 100,000 searches for random numbers the search time between the 3 seemed to
 *be about the same at every array length. Again depending on the elements being searched for there is a chance of any one search being 
 *significantly faster or slower than the others on any given iteration. 
 *
 */
import java.util.Arrays;

public class ArraydersTest {
	static final int TEN = 10;
	static final int HUNDRED = 100;
	static final int THOUSAND = 1000;
	static final int TENTHOUSAND = 10000;
	static final int HUNDREDTHOU = 100000;
	// static final int RANGE = (Integer.MAX_VALUE - Integer.MIN_VALUE); //
	// creates the range of numbers that can populate arrays
	static final int RANGE = (1000000); // creates the range of numbers that can
										// populate arrays

	/**
	 * Controls the program. Calls each function respectively after the previous
	 * one finishes.
	 * 
	 * @param args
	 *            ignore params in main function
	 */
	public static void main(String[] args) {
		arrayRandom(TEN);
		System.out.println("");
		arrayRandom(HUNDRED);
		System.out.println("");
		arrayRandom(THOUSAND);
		System.out.println("");
		arrayRandom(TENTHOUSAND);
		System.out.println("");
		arrayRandom(HUNDREDTHOU);
	}

	/**
	 * the main function of the program. Creates an array of a predetermined
	 * size and creates 2 copies of that array. The copies are sent to different
	 * sort functions and their times are compared. Then using 1 sorted array
	 * 100,000 searches of random numbers is completed using 3 different search
	 * methods and the time it takes is compared. All results are printed.
	 * 
	 * @param arraySize
	 *            Takes a parameter that is a power of 10 that denotes the
	 *            number of elements that the arrays will have.
	 */
	public static void arrayRandom(int arraySize) {
		int[] array = new int[arraySize]; // Create the array
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) Math.random() * (RANGE + 1); // populates the array
															// with random
															// values
		}
		int[] firstCopy = Arrays.copyOf(array, arraySize); // creates copies of
															// the array
		int[] secondCopy = Arrays.copyOf(array, arraySize);

		long bubbleTime = bubbleSort(array);
		long arraydersTime = Arrayders.sort(firstCopy);
		long javaStart = System.nanoTime();
		Arrays.sort(secondCopy);
		long javaEnd = System.nanoTime();
		long javaTime = (javaEnd - javaStart);
		System.out.println("Comparing sort functions with an array length of: "
				+ arraySize);
		System.out
				.println("___________________________________________________");
		System.out.println("Bubble Sort Time: " + secondConversion(bubbleTime)
				+ " seconds.");
		System.out.println("Arrayders Sort Time: "
				+ secondConversion(arraydersTime) + " seconds.");
		System.out.println("Java Sort Time: " + secondConversion(javaTime)
				+ " seconds.");

		System.out.println("\nSearching for a random number...\n"); // now
																	// depicting
																	// the
																	// search
																	// times

		long linearTotal = linearTime(array);
		long arrayderTotal = arraydersTime(array);
		long javaTotal = javaTime(array);
		System.out.println("100,000 searches for Linear Search takes: "
				+ secondConversion(linearTotal) + " seconds.");
		System.out.println("100,000 searches for Arrayders Search takes: "
				+ secondConversion(arrayderTotal) + " seconds.");
		System.out.println("100,000 searches for Java Search takes: "
				+ secondConversion(javaTotal) + " seconds.");
	}

	/**
	 * secondConversion takes a long count of nanoseconds and converts it into
	 * regular seconds.
	 * 
	 * @param nano
	 *            receives a an element that is type long, to convert to double
	 *            the number received represents the number of nano seconds a
	 *            process takes and is converted
	 * @return returns a double value of the number of actual seconds a process
	 *         takes
	 */
	public static double secondConversion(long nano) {
		double seconds = (double) nano / 1000000000.0;
		return seconds;
	}

	/**
	 * Takes the first array and finds a random value to search for in the
	 * linear search program given to us.
	 * 
	 * @param array
	 *            recieves an array to be timed in a search
	 * @return returns the time it took to search for a random number.
	 */
	public static long linearTime(int[] array) {
		int count = 0;
		long linearStart = System.nanoTime();
		while (count < HUNDREDTHOU) {
			int random = (int) Math.random() * (RANGE + 1);
			linearSearch(array, random);
			count++;
		}
		long linearEnd = System.nanoTime();
		return (linearEnd - linearStart);
	}

	/**
	 * Takes the first sorted array and finds a random value to search for in
	 * the linear search program written in arrayders. This is repeated 100000
	 * times and the time taken is returned.
	 * 
	 * @param array
	 *            receives an array to be timed in a search
	 * @return returns the time it took to search for a random number.
	 */
	public static long arraydersTime(int[] array) {
		long linearStart = System.nanoTime();
		int count = 0;
		while (count < HUNDREDTHOU) { // creates loop for 100000 searches
			int random = (int) Math.random() * (RANGE + 1);
			Arrayders.binarySearch(array, random);
			count++;
		}
		long linearEnd = System.nanoTime();
		return (linearEnd - linearStart);
	}

	/**
	 * Takes the first sorted array and finds a random value to search for in
	 * the linear search program given to us.
	 * 
	 * @param array
	 *            receives an array to be timed in a search
	 * @return returns the time it took to search for a random number.
	 */
	public static long javaTime(int[] array) {
		long linearStart = System.nanoTime();
		int count = 0;
		for (count = 0; count < HUNDREDTHOU; count++) { // Another way to loop
														// 100,000 times
			int random = (int) Math.random() * (RANGE + 1);
			Arrays.binarySearch(array, random);
		}
		long linearEnd = System.nanoTime(); // ends time counter after a full
											// hundred thousand searches have
											// been completed.
		return (linearEnd - linearStart);
	}

	/**
	 * A bubble sort function that takes a value and moves it to the end of the
	 * array by comparisons of 2 at a time Taken From Professor Case's code
	 * provided
	 */
	public static long bubbleSort(int[] array) {
		// Bubble n-1 times so that all highest values have migrated to their
		// position
		long start = System.nanoTime();
		for (int sortedCount = 0; sortedCount < array.length - 1; sortedCount++) {
			// Compare current element to every other element
			for (int index = 0; index < array.length - 1; index++) {
				// Swap if necessary
				if (array[index + 1] < array[index]) {
					// Swap (maybe this would be good in it's own function!)
					int temp = array[index + 1];
					array[index + 1] = array[index];
					array[index] = temp;
				}
			}
		}
		long endTime = System.nanoTime();
		long time = endTime - start;
		return time;

	}

	/**
	 * Use a basic linear search to try and find <code>key</code> in
	 * <code>array</code>
	 * 
	 * @param array
	 *            the array to search
	 * @param key
	 *            the item to search for
	 * @return returns -1 if the value isn't found, otherwise the index of
	 *         <code>key</code>
	 */
	public static int linearSearch(int[] array, int key) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return i;
			}
		}
		return -1; // returns negative 1 if the number was not in the array
	}
}
