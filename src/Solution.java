
import java.io.FileInputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;


 class element implements Comparable<element> {
	int x, y;
	element(int x, int y) {
		this.x=x;
		this.y=y;
	}
	@Override
	public int compareTo(element o) {
		// TODO Auto-generated method stub
		if(this.x==o.x && this.y==o.y) return 0;
		else if(this.x>o.x && this.y > o.y) return 1; 
		else return -1;		
		
	}
	@Override
	public int hashCode() {
		int hash =1;
		hash= hash * 17;
		hash = hash * 31 + x;
		return x * 13 + y;
	}
	
	@Override
	public boolean equals(Object o)
	{
		element that = (element) o;
	    return this.x==that.x && this.y == that.y;
	}
	
	
}
public class Solution {
	static int T;
	static int N, F;
	static int K[], L[];
	
	
	public static int maxBottles(int[] K, int[] L, int N, int F) {
				
		HashMap<element , Integer> cached = new HashMap<element, Integer>();
		Instant t1, t2, d;
	    t1= Instant.now();	   
		int best = solve(K, L, N, F, 0, cached);	
		System.out.println("how long "+ ChronoUnit.MILLIS.between(t1, Instant.now()));
		return best;

	}
	
	public static int solve(int[] K, int[] L, int N, int F, int i, HashMap<element, Integer> cached) {
		
//		System.out.println(" solve " + i+ " " + F);		
		element c = new element(i, F);
		if(cached.containsKey(c)) {
			System.out.println("direct cache " +  (i) + " " + F);
			return cached.get(c);
		} 
		
		if (i==N) {
           return 0;
		}
		
//        for (element x: cached.keySet()) {
//    	  System.out.println("hash eles " +x.x + " " + x.y);
//	    }
		

		
		//case no help with given day
		int tot =0;
		int bottleSold1=0;

		bottleSold1=Math.min(K[i],L[i]);

		tot = solve(K, L, N, F, i+1, cached);

		
		bottleSold1 +=tot;
		
		tot=0;
		int bottleSold2=0;
		//case with help
		if(F>0  && L[i]>K[i]) {
		    bottleSold2 = Math.min(K[i] * 2, L[i]);

			tot =  solve(K, L, N, F-1, i+1, cached);
			
			bottleSold2 +=tot;
		}	
		
		int best = Math.max(bottleSold1, bottleSold2);
		cached.put(new element(i, F), best);
//		System.out.println("cache this element " + i + " " + F);
		

		return Math.max(bottleSold1, bottleSold2);
		
	}
	
	
	public static void main(String[] args) throws Exception {
		/*
		The method below means that the program will read from input file, instead of standard(keyboard) input.
		To test your program, you may save input data in input file,
		and call below method to read from the file when using nextInt() method.
		You may remove the comment symbols(//) in the below statement and use it.
		But before submission, you must remove the freopen function or rewrite comment symbols(//).
		*/
		// System.setIn(new FileInputStream("sample_input.txt"));


		/*
		Make new Scanner from standard input System.in, and read data.
		*/
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			// Read each test case from standard input.
			N = sc.nextInt();
			F = sc.nextInt();
			K = new int [N];
			L = new int [N];
			
			for(int i = 0 ; i < N ; i++)
			{
				K[i] = sc.nextInt();
				L[i] = sc.nextInt();
			}
            /////////////////////////////////////////////////////////////////////////////////////////////
			/*
			Implement your algorithm from this section.
			*/
			/////////////////////////////////////////////////////////////////////////////////////////////
//			maxBottles( K,  L, N, F);
			int x = maxBottles( K,  L, N, F);
			System.out.println(x);
		}
	}
}

/*
 *
5
4 2
2 1
3 5
2 3
1 5
6 2
9 7
9 2
9 1
8 5
2 6
2 8
1 0
1 9
3 2
1 6
8 1
5 6
3 2
7 5
3 1
8 4
 * 
 * 
 * 
*/