import java.util.Arrays;
import java.util.Scanner;

public class Solution5 {

	static int T;
	static int N, F;
	static int K[], L[];
	
	public static int solve(int[] K, int[] L, int N, int F) {
		int[] gains= new int[N];
		int total=0;
		for (int i=0;i<N;i++) {
		   gains[i] = L[i]-K[i];
		   gains[i]=Math.max(gains[i], 0);
		   gains[i]=Math.min(gains[i], K[i]);
		   total += Math.min(K[i], L[i]);
		}
		
		Arrays.sort(gains);
		for (int i=0;i<F && i<N;i++) {
			total += gains[N-i-1];
		}
		
		return total;
						
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
			int x = solve( K,  L, N, F);
			System.out.println(x);
		}
	}
}

