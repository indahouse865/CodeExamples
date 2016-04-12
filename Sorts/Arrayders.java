/**
 * A library to provide lots of extra array functionality.
 * @author David Estrich - dme280@nyu.edu
 * 
 * @version 1.5 Added Sorting and Binary Search methods
 * For Median method, used JAVA API to base creation of copied array
 * http://docs.oracle.com/javase/7/docs/api/java/lang/System.html#arraycopy(java.lang.Object,%20int,%20java.lang.Object,%20int,%20int)
 * 
 * 
 *
 */

import java.util.Arrays;

public class Arrayders {

    /**
     * Get the smallest value in <code>array</code>
     * 
     * @param array
     *            the array to analyze
     * @return the smallest value in <code>array</code>
     */
    public static int min(int[] array) {
    	int minimumNumber = Integer.MAX_VALUE; // minimum possible integer, as shown by Professor Monday
        for (int i=0; i < array.length; i++) {
        	if (array[i] < minimumNumber) {
        		minimumNumber = array[i];        		
        	}
        }
        return minimumNumber;
    }

    /**
     * Get the largest value in <code>array</code>
     * 
     * @param array
     *            the array to analyze
     * @return the largest value in <code>array</code>
     */
    public static int max(int[] array) {
    	int maximumNumber = Integer.MIN_VALUE; //highest possible integer value as shown by Professor Case Monday
        for (int i = 0; i < array.length; i++) {
        	if (array[i] > maximumNumber) {
        		maximumNumber = array[i];        		
        	}
        }
        return maximumNumber;
    }

    /**
     * Computes the sum of all values in <code>array</code>
     * 
     * @param array
     *            array to sum the values of
     * @return the sum of all values in <code>array</code>
     */
    public static int sum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    /**
     * Computes the average of all values in <code>array</code>
     * 
     * @param array
     *            array to average the values of
     * @return the average of all values in <code>array</code>
     */
    public static double average(int[] array) {
    	//takes sum from function and divides it be length to get average
        int length = array.length;
        int sumOfArray = sum(array);
        double Average = sumOfArray/length;
        return Average;
    }

    /**
     * Given an array (of possibly unsorted values), it will return the median
     * of the numbers (as if they were sorted) in the array. If the median is
     * two different numbers, it will return the average of those two numbers
     * rounded down. The original array is not modified.
     * 
     * A median is defined as given as given a list of sorted numbers, the
     * number that is the absolute middle position. If there is an even number
     * of numbers the median is averaged between the two.
     * 
     * @param array
     *            the array to analyze
     * @return the median of <code>array</code>
     */
    public static int median(int[] array) {
    	int [] functionArray = new int [array.length];
    	System.arraycopy(array, 0, functionArray, 0, array.length);
    	Arrays.sort(functionArray);
    	int divisor = functionArray.length;
    	if (divisor % 2 == 0) { // determines whether or not the length is even or odd
    		int mid1 = (divisor / 2);
    		int mid2 = (divisor / 2  - 1);
    		int median = (functionArray[mid1] + functionArray[mid2]) / 2; // 2 for average of 2 variables
    		return median;
    	} else {
    		int middle = (divisor/2); // finds the median length by dividing by 2 adding 1 so that dropping the decimal will give correct number
    		int median = functionArray[middle];
    		return median;
    	}
    }

    /**
     * Count the number of occurrences of a value in an array
     * 
     * @param array
     *            the array to analyze
     * @param valueToFind
     *            the value to search for
     * @return the number of occurrences of <code>valueToFind</code> in
     *         <code>array</code>
     */
    public static int count(int[] array, int valueToFind) {
        int count = 0;
        for (int item : array) {
            if (item == valueToFind) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the number that occurs most frequently.
     * 
     * @param array
     *            the array to analyze
     * @return the mode of <code>array</code>
     */
    public static int mode(int[] array) {
    	int Mode = array[0] ;
    	for (int i = 0; i < array.length; i++) {
    		int Counter = count(array, array[i]);
    		if (Counter > Mode) { // only maintains 1 mode, loops for each item in array
    			Mode = array[i];
    		}
    		
    	}
        return Mode;
    }

    /**
     * Gets an array of two values representing the lowest and highest values in
     * the array passed in.
     * 
     * @param array
     *            the array to analyze
     * @return an array of two elements where index 0 is the lowest value in
     *         <code>array</code> and index 1 is the highest value in
     *         <code>array</code>.
     */
    public static int[] highLow(int[] array) {
        // Stores the highest and lowest values:
        // highLow[0] -> lowest value, highLow[1] -> highest value
        int[] highLow = new int[2];
        highLow[0] = min(array);
        highLow[1] = max(array);
        return highLow;
    }

    /**
     * Gets the difference between the highest and lowest values.
     * 
     * @param array
     *            the array to analyze
     * @return the difference between the highest and lowest values in an array
     */
    public static int range(int[] array) {
    	int[] functionArray = highLow(array);
    	int Difference = functionArray[1] - functionArray[0]; 
    	// from other function highlow [1] is max and highlow[0] is min
        return Difference;
    }

    /**
     * Creates a duplicate array that is identical to the original array, except
     * the element at the given index has been removed. All subsequent elements
     * are moved closer to the front of the array (i.e. each subsequent
     * element's index is reduced by 1). The original array is not modified. The
     * new array will have a length of 1 less than the original array.
     * 
     * @param array
     *            the array to remove a copy of
     * @param index
     *            the index of the element to remove
     * @return a new array with the element removed
     */
    public static int[] delete(int[] array, int index) {
    	int arrayLength = array.length - 1; // array length without the deleted item
    	int[] deletedArray = new int [arrayLength];
    	for (int i = 0; i < array.length; i++) {
    		int count = 0;
    		if (array[i] != array[index]) {
    			deletedArray[count] = array[i];
    			count++;
    		}
    	}
        return deletedArray;
    }

    /**
     * Creates a duplicate array that is identical to the original array, except
     * that a new element has been added to the end of the array. The original
     * array is not modified. The new array will have a length of 1 more than
     * the original array.
     * 
     * @param array
     *            the array to remove a copy of
     * @param value
     *            the value of the element to insert
     * @return a new array with the element removed
     */
    public static int[] insert(int[] array, int value) {
    	if (array[0] == 0) {
    		int[]functionArray = new int [array.length];
    		functionArray[0] = value;
    		return functionArray;
    	} else {
    		int[] functionArray = new int [array.length + 1]; // array length with space for the added item
    		for (int i = 0; i < array.length; i++) {
    			functionArray[i] = array[i];
    		}
    		int lastInLength = functionArray.length - 1;
    		functionArray[lastInLength] = value; //sets the last value in the array to the assigned value
    		return functionArray;
    	}
    }

    /**
     * Creates a duplicate array that is identical to the original array, except
     * that a new element has been appended to beginning of the array. All other
     * elements are moved up one index (value at index 0 moved to index 1). The
     * original array is not modified. The new array will have a length of 1
     * more than the original array.
     * 
     * @param array
     *            the original array
     * @param value
     *            the value of the element to add
     * @return a new array with an added element at the start of the array
     */
    public static int[] push(int[] array, int value) {
        // Create new array
        int[] newArray = new int[array.length + 1];

        // Assign new starting value
        newArray[0] = value;

        // Copy all remaining values
        for (int i = 1; i < newArray.length; i++) {
            newArray[i] = array[i - 1];
        }
        return newArray;
    }

    /**
     * Creates a duplicate array that is identical to the original array,
     * except that the first element from the array has been removed. All other
     * elements are moved down one index. The original array is not modified.
     * The new array will have a length of 1 less than the original array.
     * 
     * @param array
     *            the original array
     * @return a new array with first element removed.
     */
    public static int[] pop(int[] array) {
        // Create new array
        int[] newArray = new int[array.length - 1];

        // Copy all but the first element
        for (int i = 1; i < array.length; i++) {
            newArray[i - 1] = array[i];
        }
        return newArray;

    }
   
   /**
    * Takes an array as a parameter and sorts it from lowest to highest. Returns the original array, but sorted.
    * This sort is a style of Insertion Sort.
    * 
    * @param array
    * 			A reference to the original array and contains the data that will be sorted
    * 
    */
   public static long sort(int[] array) {
	   long start = System.nanoTime();
	   for (int i = 1; i < array.length; i++) {
		   int j = i; //creates second variable that will be used to compare indexes
		   while (j > 0 && array[j-1] > array[j]) {
			   int temp = array[j];
			   array[j] = array[j-1];
			   array[j-1] = temp;
		   }
	   }
	   long endTime = System.nanoTime();
	   long Time = endTime - start;
	   return Time;
   }
   
   /**
    * Binary Search - takes an already sorted array that finds the place in an array that a piece of data exists.
    * Compares two pieces of data at a time. It is accomplished by knowing that numbers with decimals alw
    * @param array
    * 			An array, that is assumed to be pre-sorted that will be searched for an element
    * @return <code>middle</code> is returned as the nubmer that was being searched for, because at the end of the algorithms process.
    */
   public static int binarySearch(int[] array, int value) {
	   int middleDivisor = 2; // used to divide array, add 2 numbers and divide by 2 for average. this is the same 2
	   int arrayMin = array[0];
	   int arrayMax = array[array.length-1]; // array.length is always 1 larger than the last element when calling the array ie array[99]
	   int invalid = -1; //number assigned to variable to signify number being searched for is not in array
	   int middle = array.length/middleDivisor;
	   while (value != array[middle]) {
		   if (value < array[middle]) {
			   arrayMax = middle - 1; // denotes the new max for the array
			   middle = (arrayMax + arrayMin)/middleDivisor;
		   } else if ( value > array[middle]) {
			   arrayMin = middle + 1; // denotes the new new min for the array
			   middle = (arrayMax + arrayMin)/middleDivisor;
		   } else {System.out.print("This number is not in the array.");
		   middle = invalid; //value assigned to number not being in array 
		   }
	   }
	   return middle;
   }
      
}

