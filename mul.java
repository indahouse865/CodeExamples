
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import ch07.largeInts.LargeInt;

public class mul {

	public static String nn = "2", mm = "3";
	public static int pp = 4;
	double timeSeconds;
	
	public static void main(String[] args) throws IOException {	

//		try {
		if (args.length>0) nn=(args[0]);
		if (args.length>1) mm=(args[1]);
		if (args.length>2) pp=Integer.parseInt(args[2]); 
		
		File TRANSFORM = new File("TRANSFORM.txt");//t
		//If file doesnt exist make the file
		if (!TRANSFORM.exists()){ 
			TRANSFORM.createNewFile();
		}
		PrintWriter writer = new PrintWriter("TRANSFORM.txt"); //initalizes the PrintWriter to the file
		writer.printf("%10s: %50s", "[ab, a+b]", "Time elapsed (msec)"); //writes the header
		
		LargeInt first = new LargeInt(nn);
		LargeInt second = new LargeInt(mm);
		LargeInt[] mix = new LargeInt[2];
		mix[0] = first;
		mix[1] = second;
		transform(mix, writer);
		
		writer.close();
		} // End of try block
//		catch(IOException e)
//		{System.out.println(e.getMessage());}
//		catch(Exception e)
//		{System.out.println(e.getMessage());}
//	}	
	
	public static LargeInt[] transform(LargeInt[] tran, PrintWriter writer) throws IOException {
		
//		File TRANSFORM = new File("TRANSFORM.txt");//t
//		//If file doesnt exist make the file
//		if (!TRANSFORM.exists()){ 
//			TRANSFORM.createNewFile();
//		}
//		PrintWriter writer = new PrintWriter("TRANSFORM.txt"); //initalizes the PrintWriter to the file
//		writer.printf("%10s: %50s", "[ab, a+b]", "Time elapsed (msec)"); //writes the header
		
		for (int i=0; i<pp; i++) {
			System.out.println("hi" + i);
			long time1 = System.nanoTime(); //initalize time
			LargeInt temp = tran[0];
			tran[0] = LargeInt.mul(tran[0], tran[1]);
			tran[1] = LargeInt.add(temp, tran[1]);
			long time2 = System.nanoTime(); // get the end time
			double timeSeconds = (double) ((time2 - time1) / 1000000.0); // compute 
			writer.printf("\n["+tran[0]+", "+tran[1]+"]%50f", timeSeconds);
		} // end for loop
		return tran;
	}	
}
