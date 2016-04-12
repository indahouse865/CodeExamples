import java.io.PrintWriter;
import java.util.Random;
import java.io.FileNotFoundException;

public class MaxMinHeap implements MaxMinPQ{
	
	public static int mm, nn, ss1, ss2;
	private static Comparable[] elements;
	private static int lastIndex;
	private int maxIndex;
	public static int[] heap;
	public static int num;
	public static int count = 0;
	public static int countDeq = 0;
	public static PrintWriter write;
	
	/**
	 * Default contructor for heap
	 * @param maxSize - integer for defining size of array
	 */
	public MaxMinHeap(int maxSize) {
		elements = new Comparable[maxSize];
		lastIndex = -1;
		maxIndex = maxSize - 1;
	}
	
	/**
	 * determines if the heap is empty, only returns true if size is -1
	 */
	@Override
	public boolean isEmpty() {
		return (lastIndex == -1);
	}

	/**
	 * Determines if heap is full. Only  returns true if last element is in slot of heap array.
	 */
	@Override
	public boolean isFull() {
		return (lastIndex == maxIndex);
	}

	/**
	 * adds generic of Comparable type to the heap array.
	 * @param element - specific element being added.
	 */
	@Override
	public void enqueue(Comparable element) {
		if (lastIndex == maxIndex) {
			System.out.print("Error: cannot enque a full que.");
		} else {
			lastIndex++;
			reheapUp(element);
		}
	}
	
	private void reheapUp(Comparable element) {
		int hole = lastIndex;
		while ((hole > 0) && (element.compareTo(elements[(hole -1) / 2]) >0 )) {
			elements[hole] = elements[ ( hole - 1) /2];
			hole = (hole - 1) / 2;
		}
		elements[hole] = element;
	}

	/**
	 * removes first element of the heap.
	 */
	@Override
	public Comparable deqeueMax() {
		Comparable hold;
		Comparable toMove;
		
		if (isEmpty()) {
			System.out.println("Cannot remove from an empty que.");
			return -1;
		} else {
			hold = elements[0];
			toMove = elements[lastIndex];
			lastIndex = lastIndex - 1;
			reheapDown(toMove);
			return hold;
		}
	}
	
	private void reheapDown(Comparable element) {
		int hole = 0;
		int newhole;
		newhole = newHole(hole, element);
		while (newhole != hole) {
			elements [hole] = elements[newhole];
			hole = newhole;
			newhole = newHole(hole, element);
		}
	}
	
	
	public Comparable deqeueMin() { //need to finish dequeue min: 
		Comparable hold;
		Comparable toMove;
		
		if (isEmpty()) {
			countDeq++;
			System.out.println("Cannot remove from an empty que.");
			return -1;
		} else {
			hold = elements[lastIndex];
			lastIndex--;
			return hold;
		}
	}

	
	private int newHole(int hole, Comparable element) {
		int left = (hole * 2) + 1;
		int right = (hole * 2) + 2;
		
		if (left > lastIndex) {
			return hole;
		} else {
			if (left == lastIndex) {
				if (element.compareTo(elements[left]) < 0) {
					return left;
				} else {
					return hole;
				}
			} else {
				if (elements[left].compareTo(elements[right]) < 0 ) {
					if (elements[right].compareTo(element) <= 0) {
						return hole;
					} else {
						return right;
					}
				}  else {
					if (elements[left].compareTo(element) <= 0) {
						return hole;
					} else {
						return left;
					}
				}
			}
		}
	}
	
	/**
	 * displats all elements in the heap
	 */
	@Override
	public void dump() {
		for(int i=0; i<elements.length; i++){
			System.out.printf(" %s ",elements[i]);
			write.printf(" %s ",elements[i]);
		}
		lastIndex=-1;
		
	}


 public static void main (String[] args) throws FileNotFoundException {
	 
	 mm = 20;
	 nn = 20;
	 ss1 = 111;
	 ss2 = 0;
	 if (args.length>0) mm = Integer.parseInt(args[0]);
	 if (args.length>1) nn = Integer.parseInt(args[1]);
	 if (args.length>2) ss1 = Integer.parseInt(args[2]);
	 if (args.length>3) ss2 = Integer.parseInt(args[3]);
	 
	 
	 Random rg1;
	 Random rg2;
	 
	 rg1 = new Random(ss1);
	 if (ss2 == 0) {
		 rg2 = new Random();
	 } else {
		 rg2 = new Random(ss2);
	 }
	 
	 MaxMinHeap heap = new MaxMinHeap(mm);//initalize the heap
	 
	 write = new PrintWriter("MaxMinOutput1.txt");
	 	write.println("Part 1:");
	 	System.out.println("Part 1");
		for(int i=0; i<nn; i++){
			num=rg1.nextInt(10);
				if(num==0)write.printf("DEQUEUE MIN: %-15s\n", heap.deqeueMin());
				if(num==1)write.printf("DEQUEUE MAX: %-15s\n", heap.deqeueMax());
				if(num==2){
					heap.reheapUp(elements[lastIndex]);
					write.print("DUMP: "); heap.dump();
					}
			else {
				num=rg2.nextInt(mm);
				write.printf("Enque: %-10s\n", num);
				heap.enqueue(num);
			}
		}
		write.printf("\nPart 1 Results: overflows: %s, underflows: %s\n\n", count, countDeq);
	 	write.println("Part 2:");
		for(int i=0; i<nn; i++){
			num=rg1.nextInt(10);
				if(num==0||num==1||num==2)write.printf("DEQUEUE MIN: %-15s\n", heap.deqeueMin());
				if(num==3||num==4||num==5)write.printf("DEQUEUE MAX: %-15s\n", heap.deqeueMax());
				if(num==6){write.print("DUMP: "); heap.dump();}
			else {
				num=rg2.nextInt(mm);
				write.printf("ENQUEUE: %-10s\n", num);
				heap.enqueue(num);
			}
		}
		write.printf("\nPart 2: overflows: %s, underflows: %s\n\n", count, countDeq);
		write.close();
	 
 }

}