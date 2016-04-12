/***************************************************
 *
 * MergeSort
 *
 * We started developing this code in class:
 * 	The main loop was outlined on the blackboard.
 *
 *	Please debug and complete this for yourself.
 *
 * Data Structures (CS102 Spring 2015)
 * Prof.Yap
 * April 27
 *
 ***************************************************/

import java.util.Random;


class MergeSort0 {

	static int max = 20;
	static int[]  A;
	static int[]  B;


    static void swap(int i, int j, int[] A){
	int tmp = A[i];
	A[i] = A[j];
	A[j] = tmp;
    }

    static void dump(int[] A){
	for (int i=0; i< A.length; i++)
	    System.out.printf("%d ", A[i]);
	System.out.printf("\n ********************\n ");
    }

//    static void merge(int i, int k, int j){
//
//	int imin =i, jmin = k+1;
//	int kmin =i;
//
//	while (imin<=k && jmin<=j){
//	    if (A[imin]< A[jmin]) {
//		B[kmin++] = A[imin++];
//	    } else {
//		B[kmin++] = A[jmin++];
//	    }
//	}
//	
//	while (jmin<=j) {
//		B[kmin++]=A[jmin++];
//	}
//	while (imin<=i){
//		B[kmin++]=A[imin++];
//	}
//	for (int n = i; n<=j; n++) {
//		A[n] = B[n];
//	}

//	if (imin>k) 
//	    for ( ; jmin <=j; jmin++)
//		B[kmin++] = A[jmin];
//	else
//	    for ( ; imin <=k; imin++)
//		B[kmin++] = A[imin];
//
//    }//merge
    
    static void merge(int i, int k, int j){
    	int imin =i, jmin = k+1;
    	int kmin =i;

    	while (imin<=k && jmin<=j){
    	    if (A[imin]< A[jmin]) {
    			B[kmin++] = A[imin++];
   		    } else {
    			B[kmin++] = A[jmin++];
   		    }
    	}	
    	while (jmin<=j){
    		B[kmin++]=A[jmin++];
    	}
    	while (imin<=k){
    		B[kmin++]=A[imin++];
    	}

    	for (int n=i; n<=j; n++){
    		A[n]=B[n];
   		}
 	}//merge


    static void mergeSort(int i, int j){
	if (j == i) return;
	mergeSort(i, (i+j)/2);
	mergeSort(1+(i+j)/2 , j);
	merge(i, (i+j)/2, j);
    }//mergeSort



    ////////////////////////////////////////////////////
    // Main Method
    ////////////////////////////////////////////////////
    public static void main (String[] args) {

	max=20;
	if (args.length>0) max = Integer.parseInt(args[0]);

	A = new int[max];
	B = new int[max];

	Random rg = new Random();

	// We will populate array A with random integers:
	for (int i=0; i<max; i++){
	    A[i] = rg.nextInt(max);
		System.out.printf(" %d ", A[i]);
	}
		System.out.printf("\n\n============ \n\n ");


	// MAIN LOOP:
		mergeSort(0,max-1);
		dump(B);
    }

}
