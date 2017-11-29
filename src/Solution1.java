

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Solution1 {
	static int T;
	static int N, F;
	static int K[], L[];
	
	
	public static int maxBottles(int[] K, int[] L, int N, int F) {
				
		HashMap<String , Integer> cached = new HashMap<String, Integer>();
		return solve(K, L, N, F, 0, cached);						
	}
	
	public static int solve(int[] K, int[] L, int N, int F, int i, HashMap<String, Integer> cached) {
		
//		System.out.println(" solve " + i+ " " + F);		
		int[] s = {i,F};
		String c = Arrays.toString(s);
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
		if(F>0 && L[i]>K[i]) {
		    bottleSold2 = Math.min(K[i] * 2, L[i]);

			tot =  solve(K, L, N, F-1, i+1, cached);
			
			bottleSold2 +=tot;
		}	
		
		int best = Math.max(bottleSold1, bottleSold2);
		cached.put(c, best);
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