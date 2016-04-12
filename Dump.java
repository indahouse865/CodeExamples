import java.util.Random;




public class Dump {
	
	////////////////////////////////////////////////////
	// members:
	////////////////////////////////////////////////////
	static int[] qq;
	static int size=0;
	static int MAX;
	static Random rg;
	static int seed=0;

	static int overflows=0;
	static int underflows=0;
	
	////////////////////////////////////////////////////
	// Constructors:
	////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////
	// Methods:
	////////////////////////////////////////////////////

	static void init(int n){
	    if (n==0) MAX=0;
	    else MAX=n;
	    size=0;
	    qq=new int[MAX];
	}
	
	static int parent(int i){
		if (i==0) return 0;
		return (i-1)/2;
	}
	static int grandparent(int i){
		if (i<3) return i;
		return (i-3)/4;
	}
	static int level(int i){
	    if (i==0) return 0;
	    int l=0;
	    while (i>0) {
		i=parent(i);
		l++;
	    }
	    return l;
	}
	static boolean maxLevel(int i){
	    if (level(i)%2 == 0) return true;
	    return false;
	}
	static boolean minLevel(int i){
	    if (level(i)%2 == 0) return false;
	    return true;
	}

	////////////////////////////////////////////////////
	// SUPPORT METHODS:
	////////////////////////////////////////////////////
	

	// Is Power of 2?
	static boolean pow2(int i){
	    if (i==0 || i==1) return true;
	    while (i>1){
		if (i%2 == 1) return false;
		i = i/2;
	    }
	    return true;
	}

	// getRandomArray(int ss)
	static void getRandomArray(){
		for (int i=0; i<MAX; i++){
			qq[i] = rg.nextInt(2*MAX);
		}
		size = MAX;
	}
	
	// dump(msg)
	static void dump(String msg){
	    show(msg);
	    for (int i=0; i<size; i++)
		show(" " + qq[i]);
	    show("\n size of array = " + size + "\n");
	}

	// dumpHeap(msg)
	static void dumpHeap(String msg){
		show(msg);

		String[] mm = {"MAX: ", "MIN: "};
		for (int i=0; i<size; i++){
		    if (pow2(i+1)) show("\n" + mm[level(i)%2]);

			System.out.printf(" %4d", qq[i]);
		}
		System.out.printf("\n");
	}
	
	static void show(String msg){
		 System.out.printf(msg);
	}
	
	// returns 0 if the array is a heap
	// 	otherwise
	// returns
	// 	the index i where the maxHeap property is violated
	//
	static int isHeap(){
	    for (int i=1; i<size; i++)
		if (minLevel(i))
		    if (qq[i]<qq[grandparent(i)] || qq[i]>qq[parent(i)]) return i;
	        else
		    if (qq[i]>qq[grandparent(i)] || qq[i]<qq[parent(i)]) return i;
	    return 0;
	}



	// test() -- various tests
	static boolean test(){

	    // TESTING pow2:
	    show("Pow2 of 0, 1, 2, 3, 4, 5, 6, 7, 8:");
	    for (int i=0; i<9; i++)
	    	show("\n" + i + ": " + pow2(i));
	    show("\n");
	    // get and dump array:

	    getRandomArray();
	    dump("This is a random Array:\n");

	    dumpHeap("MaxMinHeap:\n");

	    return true;
	}


	
	////////////////////////////////////////////////////
	// MAIN METHOD:
	////////////////////////////////////////////////////

	public static void main(String[] args) {
		int n = 25;
		if (args.length>0) n = Integer.parseInt(args[0]);
	
		int seed = 0;
		if (args.length>1) seed = Integer.parseInt(args[1]);
		
		if (seed==0) rg = new Random();
		else rg= new Random(seed);
	
		init(n);

		test();
	
	}//main

}//MaxMinHeap class

