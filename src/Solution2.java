
import java.io.FileInputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Solution2 {
	static int T;
	static int N, F;
	static int K[], L[];
	
	public static int maxBottles(int[] K, int[] L, int N, int F) {
		
		int[] max={0};
		HashSet<String> cached = new HashSet<String>();
		Instant t1, t2, d;
	    t1= Instant.now();	    
		solve(K, L, N, F, 0, 0, max, cached);
		System.out.println("how long "+ ChronoUnit.MILLIS.between(t1, Instant.now()));
		return max[0];
	}
	
	public static void solve(int[] K, int[] L, int N, int F, int i, int total, int[] max, HashSet<String> cached) {
		int[] s = {i,F,total};
		String key = Arrays.toString(s);
		System.out.println("solve " + key);
		
		if (i==N) {
			System.out.println("reach end");
			if(total>max[0]) max[0]=total;
			return;
		}
		

		for (String m: cached) {
			System.out.println(" elements " +  m);
		}
		if(cached.contains(s)) {
			System.out.println("cached used " + i + " " + F +" " + total );
			return;		
		}
		
		System.out.println("try to use NO Help");
		//case no help with given day
		int bottleSold=Math.min(K[i],L[i]);
		solve(K, L, N, F, i+1, total+bottleSold, max, cached);
		
		//case with help
		if(F>0 & L[i] > K[i]) {
			System.out.println("try to use one Help");
		    bottleSold = Math.min(K[i] * 2, L[i]);
		    solve(K, L, N, F-1, i+1, total+bottleSold,max, cached);
		}	
		
		cached.add(key);
		
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
			int x = maxBottles( K,  L, N, F);
			System.out.println("#"+test_case+" " + x);
			/////////////////////////////////////////////////////////////////////////////////////////////

		}
	}
}
