
public class SuperWashingMachine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] total ={0};
		char[] letters ={'L','A','P'};
		place ("", 3, total, letters);
		System.out.println ("how many " + total[0]);

	}
	
	
	
	static void place(String prefix, int N, Integer[] total, char[] letters)  {
		if (prefix.length()==N) {
			total[0] +=1;
			System.out.println(prefix);
			return;
		}
		
		for(char x: letters) {
			int n = prefix.length();
			if((n>=2 && prefix.charAt(n-2)=='L' && prefix.charAt(n-1)=='L' && x=='L') ||
			   (n>=1 && prefix.charAt(n-1)=='A' && x=='A')) 
			{
				continue;
			}
						
			place(prefix + String.valueOf(x), N, total, letters);			
		}
		
	}

}
