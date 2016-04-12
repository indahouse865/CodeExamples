//import byteLists.*;

 
public class LargeInt1
{
  private SpecializedList numbers;    // Holds digits
 
  // Constants for sign variable
  private static final boolean PLUS = true;
  private static final boolean MINUS = false;
 
  private boolean sign;
 
  public LargeInt1()
  // Instantiates an "empty" large integer.
  {
    numbers = new SpecializedList();
    sign = PLUS;
  }
 
  public LargeInt1(String intString)
  // Precondition: intString contains a well-formatted integer
  //
  // Instantiates a large integer as indicated by intString
  {
    numbers = new SpecializedList();
	 sign = PLUS;

    int aDigitPosition;          // Position of a digit in intString
    int lastDigitPosition;           // Position of last digit in intString

    // Used to translate character to byte
    char digitChar;
    int digitInt;
    byte digitByte;
 
    aDigitPosition = 0;
    if (intString.charAt(0) == '+')   //  Skip leading plus sign
      aDigitPosition = 1;
    else
    if (intString.charAt(0) == '-')   // Handle leading minus sign
    {
      aDigitPosition = 1;
      sign = MINUS;
    }
 
    lastDigitPosition = intString.length() - 1;
 
    for (int count = aDigitPosition; count <= lastDigitPosition; count++)
    {
      digitChar = intString.charAt(count);
      digitInt = Character.digit(digitChar, 10);
      digitByte = (byte)digitInt;
      addDigit(digitByte);
    }
  }

  public void setNegative()
  {
    sign = MINUS;
  }
 
  public void addDigit(byte digit)
  {
    numbers.addEnd(digit);
  }
  
  public String toString()
  {
    String largeIntString;
    if (sign == PLUS)
      largeIntString = "+";
    else
      largeIntString = "-";
 
    int length;
    length = numbers.size();
    numbers.resetForward();
    for (int count = length; count >= 1; count--)
    {
      largeIntString = largeIntString + numbers.getNextElement();
      if ((((count - 1) % 3) == 0) && (count != 1))
       largeIntString = largeIntString + ",";
    }
    return(largeIntString);
  }

  private static boolean greaterList(SpecializedList a, 
                                     SpecializedList b)
  // Precondition: a and b have no leading zeros
  //
  // Returns true if a represents a larger number than b;
  // otherwise, returns false
 
  {
    boolean greater = false;
    if (a.size() > b.size())
      greater = true;
    else
    if (a.size() < b.size())
      greater = false;
    else
    {
      byte digita;
      byte digitb;
      a.resetForward();
      b.resetForward();
 
      // Set up loop
      int length = a.size();
      boolean keepChecking = true;
      int count = 1;
 
      while ((count <= length) && (keepChecking))
      {
        digita = a.getNextElement();
        digitb = b.getNextElement();
        if (digita > digitb)
        {
          greater = true;
          keepChecking = false;
        }
        else
        if (digita < digitb)
        {
          greater = false;
          keepChecking = false;
        }
        count++;
      }
    }
    return greater;
  }

  private static SpecializedList addLists(SpecializedList larger,
                                          SpecializedList smaller)
  // Precondition: larger >= smaller
  //
  // Returns a specialized list that is a byte-by-byte sum of the two 
  // argument lists
  {
    byte digit1;
    byte digit2;
    byte temp;
    byte carry = 0;
 
    int largerLength = larger.size();
    int smallerLength = smaller.size();
    int lengthDiff;
 
    SpecializedList result = new SpecializedList();
 
    larger.resetBackward();
    smaller.resetBackward();
    // Process both lists while both have digits
    for (int count = 1; count <= smallerLength; count++)
    {
      digit1 = larger.getPriorElement();
      digit2 = smaller.getPriorElement();
      temp = (byte)(digit1 + digit2 + carry);
      carry = (byte)(temp / 10);
      result.addFront((byte)(temp % 10));
    }
 
    // Finish processing of leftover digits
    lengthDiff = (largerLength - smallerLength);
    for (int count = 1; count <= lengthDiff; count++)
    {
      digit1 = larger.getPriorElement();
      temp = (byte)(digit1 + carry);
      carry = (byte)(temp / 10);
      result.addFront((byte)(temp % 10));
    }
    if (carry != 0)
      result.addFront((byte)carry);
 
    return result;
  }

  private static SpecializedList subtractLists(SpecializedList larger,
                                               SpecializedList smaller)
  // Precondition: larger >= smaller
  //
  // Returns a specialized list that is the difference of the two argument lists
  {
    byte digit1;
    byte digit2;
    byte temp;
    boolean borrow = false;
 
    int largerLength = larger.size();
    int smallerLength = smaller.size();
    int lengthDiff;
 
    SpecializedList result = new SpecializedList();
 
    larger.resetBackward();
    smaller.resetBackward();
 
    // Process both lists while both have digits.
    for (int count = 1; count <= smallerLength; count++)
    {
      digit1 = larger.getPriorElement();
      if (borrow)
      {
        if (digit1 != 0)
        {
          digit1 = (byte)(digit1 - 1);
          borrow = false;
        }
        else
        {
          digit1 = 9;
          borrow = true;
        }
      }
 
      digit2 = smaller.getPriorElement();
  
      if (digit2 <= digit1)
        result.addFront((byte)(digit1 - digit2));
      else
      {
        borrow = true;
        result.addFront((byte)(digit1 + 10 - digit2));
      }
    }
 
    // Finish processing of leftover digits
    lengthDiff = (largerLength - smallerLength);
    for (int count = 1; count <= lengthDiff; count++)
    {
      digit1 = larger.getPriorElement();
      if (borrow)
      {
        if (digit1 != 0)
        {
          digit1 = (byte)(digit1 - 1);
          borrow = false;
        }
        else
        {
          digit1 = 9;
          borrow = true;
        }
      }
      result.addFront(digit1);
    }
 
    return result;
  }

  public static LargeInt1 add(LargeInt1 a, LargeInt1 b)
  // Returns a LargeInt that is the sum of the two argument LargeInts
  {
    LargeInt1 sum = new LargeInt1();
 
    if (a.sign == b.sign)
    {
      if (greaterList(a.numbers, b.numbers))
        sum.numbers = addLists(a.numbers, b.numbers);
      else
        sum.numbers = addLists(b.numbers, a.numbers);
      sum.sign = a.sign;
    }
    else   // Signs are different
    {
      if (greaterList(a.numbers, b.numbers))
      {
        sum.numbers = subtractLists(a.numbers, b.numbers);
        sum.sign = a.sign;
      }
      else
      {
        sum.numbers = subtractLists(b.numbers, a.numbers);
        sum.sign = b.sign;
      }
    }
 
    return sum;
  }

  public static LargeInt1 subtract(LargeInt1 a, LargeInt1 b)
  // Returns a LargeInt that is the difference of the two argument LargeInts
  {
    LargeInt1 diff = new LargeInt1();
 
    // Create an inverse of b
    LargeInt1 negb = new LargeInt1();
    negb.sign = !b.sign;
    b.numbers.resetForward();
    int length = b.numbers.size();
    for (int count = 1; count <= length; count++)
      negb.numbers.addEnd(b.numbers.getNextElement());
    // Add a to inverse of b
    diff = add(a, negb);
 
    return diff;
  }
  public static SpecializedList MulLists(SpecializedList a, SpecializedList b){
    byte digit1;
    byte digit2;
    byte temp;
    byte carry=0;
 
    int largerLength = a.size();
    int smallerLength = b.size();
 
    SpecializedList result = new SpecializedList();
    SpecializedList sum = new SpecializedList();
    sum.addFront((byte)0);
    int n;
    a.resetBackward();
    b.resetBackward();
  
    for (int count = 1; count <= smallerLength; count++)
    {
      carry=0;
      digit1 = b.getPriorElement();
      for(n=0; n<largerLength; n++){
        digit2 = a.getPriorElement();
        temp = (byte)(digit2*digit1+carry);
        carry = (byte)(temp / 10); 
        result.addFront((byte)(temp % 10));
      }
      for(int i=1; i<count; i++)result.addEnd((byte)0);
      if(n==largerLength && carry!=0)result.addFront((byte)carry);
      sum=addLists(result, sum);
      result=new SpecializedList();
    }
    return sum;
  }
  public static LargeInt1 mul(LargeInt1 a, LargeInt1 b){
      LargeInt1 mul = new LargeInt1();  
      if(a.sign == b.sign){
        if(greaterList(a.numbers, b.numbers))
            mul.numbers=MulLists(a.numbers, b.numbers);
        else 
            mul.numbers=MulLists(b.numbers, a.numbers);
        mul.sign=PLUS;
      }
      else{
        if(greaterList(a.numbers, b.numbers))
            mul.numbers=MulLists(a.numbers, b.numbers);
        else 
            mul.numbers=MulLists(b.numbers, a.numbers);
        mul.sign=MINUS;
      }
      return mul;
  }
  public static LargeInt1 kara(LargeInt1 a, LargeInt1 b){
      LargeInt1 kara = new LargeInt1();  
      if(a.sign==b.sign){ //if signs are the same
        if(greaterList(a.numbers, b.numbers)) //if a is the greater list
            kara.numbers=KaraLists(a.numbers, b.numbers);
        else 
            kara.numbers=KaraLists(b.numbers, a.numbers);  //if b is the greater list
        kara.sign=PLUS;
      }
      else{ //signs not the same
        if(greaterList(a.numbers, b.numbers))
            kara.numbers=KaraLists(a.numbers, b.numbers);
        else 
            kara.numbers=KaraLists(b.numbers, a.numbers);
        kara.sign=MINUS;
      }
      return kara;
  }
  public static SpecializedList KaraLists(SpecializedList a, SpecializedList b){
      
    int largerLength = a.size();
    int smallerLength = b.size();
    int length=largerLength;
    
    //Initalize all variables neeccessary for the Karatsuba method
    SpecializedList X0 = new SpecializedList();
    SpecializedList X1 = new SpecializedList();
    SpecializedList Y0 = new SpecializedList();
    SpecializedList Y1 = new SpecializedList();
    SpecializedList ZZ;
    SpecializedList Z0;
    SpecializedList Z1;
    SpecializedList Z2;
    SpecializedList Z;
    a.resetForward();
    b.resetForward();
    if(length!=1){
    if(length%2!=0){a.addFront((byte)0); largerLength+=1;}
    for(; smallerLength<largerLength; smallerLength+=1) b.addFront((byte)0);
    for(int num=0; num<largerLength/2; num++){
        X0.addEnd(a.getNextElement());
        Y0.addEnd(b.getNextElement());
    }
    for(int num=0; num<largerLength/2; num++){
        X1.addEnd(a.getNextElement());
        Y1.addEnd(b.getNextElement());
    }
    ZZ=KaraLists(addLists(X0, X1), addLists(Y0, Y1));
    Z0=KaraLists(X0, Y0);
    Z2=KaraLists(X1, Y1);
    Z1=subtractLists(ZZ, Z0); 
    Z1=subtractLists(Z1, Z2);
    for(int x=0; x<largerLength; x++)Z0.addEnd((byte)0);
    for(int x=0; x<largerLength/2; x++)Z1.addEnd((byte)0);
    Z=addLists(Z0, Z1);
    Z=addLists(Z, Z2);
    return Z;
    }
    else return Transform(a, b);
  } // end KaraLists

private static SpecializedList Transform(SpecializedList a, SpecializedList b) {
	// TODO Auto-generated method stub
	return null;
}
}