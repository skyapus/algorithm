import java.util.HashMap;
import java.util.Map;

public class IntegerToWords {

	
	
	static String readUnder1000(int n) {
		assert n<1000;
		if (n>=100) {
			readUnder10(n/100);
			System.out.println(" hundred ");
		}

		readUnder100(n%100);
		return "hundred";
	}
	
	static void readUnder100(int n) {
//		assert 20 <= n && n<100;
		Map<Integer, String> m = new HashMap<Integer, String>() {{
			put(20, "twenty");
			put(30, "thirty");
			put(40, "forty");
			put(50, "fifty");
			put(60, "sixty");
			put(70, "seventy");
			put(80, "eighty");
			put(90, "ninty");						
			}};
			
		if(n>=20) {
			int x = n/10;			
			System.out.println(m.get(x * 10));
			readUnder10(n%10);
		} else { 
			readUnder20(n);
		}
		
	}
	
	static void readUnder20(int n) {
		Map<Integer, String> m = new HashMap<Integer, String>() {{
			put(10, "ten");
			put(11, "eleven");
			put(12, "twelve");
			put(13, "thirteen");
			put(14, "fourteen");
			put(15, "fifteen");
			put(16, "sixteen");
			put(17, "seventeen");
			put(18, "eighteen");
			put(19, "nineteen");						
			}};	
		if(n>=10) {			
			System.out.println(m.get(n ));
		}
		else {
		    readUnder10(n);
	    }
			
	}
	
	static void readUnder10(int n) {
		assert n<10;
		Map<Integer, String> m = new HashMap<Integer, String>() {{
			put(0, "zero"); //special case
			put(1, "one");
			put(2, "two");
			put(3, "three");
			put(4, "four");
			put(5, "five");
			put(6, "six");
			put(7, "seven");
			put(8, "eight");
			put(9, "nine");						
			}};
			
			if (n>0) {
	  	       System.out.println(m.get(n));
			}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n=300;
		readUnder1000(n);
		

	}
	
	

}
