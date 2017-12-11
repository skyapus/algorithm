import java.util.HashSet;
import java.util.Set;

public class NumOfPalin {

	public static void main(String[] args) {		
		// TODO Auto-generated method stub
        solve2("aba");
	}
	
	/*
	 * count number of sequence which are palin
	 * 
	 * 1. naive solution that can solve this problem, for each position which take or not take, similar like combination check  and then count unique palin, which 
	 * is O(2^N) 
	 * 
	 * 2. Dynamic programming that can be used to check palin sequence.
	 */
	
	
	public static void solve(String s) {
		
	}
	
	/*
	 * This attempt using longest common subsequence by comparing itself to its reverse to find all palin within a string is incorrect
	 *  for example. aba and its reverse aba, the results are:
	 * "","aa","a", "aba", "b","ba", apparently ba is not a palins within aba, but it is common subsequence between aba and aba.
	 */
	public static int solve2(String s) {
		String s1 = new StringBuilder(s).reverse().toString();
		Set<String> r = solve2(s, s.length(), s1, s1.length());
		for(String c:r) {
			System.out.println(c);
		}
		
		return 0;
		
	}
	
	/*
	 * find all common subsequence between two string
	 */
	public static Set<String> solve2(String s1, int M, String s2, int N) {
		Set<String> r = new HashSet<String>();
		if(M==0 || N==0) {
			r.add("");
			return r;
		}
		
		Set<String> x = solve2(s1.substring(1), M-1, s2, N);
		Set<String> y = solve2(s1,M, s2.substring(1), N-1);
		r.addAll(x);
		r.addAll(y);
        if(s1.charAt(0)==s2.charAt(0)) {
        	Set<String> z = solve2(s1.substring(1), M-1, s2.substring(1), N-1);
        	for (String c:z) {
        		r.add(s1.charAt(0)+c);
        	}
        	r.addAll(z); 
        }
        
        return r;		
 	}
	
	/*
	 * Initial Values : i= 0, j= n-1;

CountPS(i,j)
// Every single character of a string is a palindrome 
// subsequence 
if i == j
   return 1 // palindrome of length 1

// If first and last characters are same, then we 
// consider it as palindrome subsequence and check
// for the rest subsequence (i+1, j), (i, j-1)
Else if (str[i] == str[j)]
   return   countPS(i+1, j) + countPS(i, j-1) + 1;

else
   // check for rest sub-sequence and  remove common
   // palindromic subsequences as they are counted
   // twice when we do countPS(i+1, j) + countPS(i,j-1)
   return countPS(i+1, j) + countPS(i, j-1) - countPS(i+1, j-1)               
	 */

	/*
	 * in the case when head and tail character is same, and +1, why ? actually that +1 is for the sequence make of head and tail character 
	 * if the string is axxxxa, then the 1 sequence is aa. and other are taken care of by the count(i+1, j) and (i, j-1) why ?
	 * 
	 * for the case axxxxa pattern, let us f(xxxx) represents the number of palin sequence  for xxxx, then f(axxxxa) = f(axxxx) + f(xxxxa) + f(xxxx) - f(xxxx) +1
	 * why substract f(xxxx), because f(xxxx) is count twice by f(axxxx) and f(xxxxa) (can be seen if drawing recursive tree)
	 * why add f(xxxx), that is because the numbers of those sequence created by head 'a' and tail 'a' is exactly same as what xxxx will created, it will attached 
	 * a ... a to any subsequence created by xxxx. since we do not count the blank as palin, that is reason we need to add 1 too as above. 
	 * 
	 * therefore f(axxxxa) = f(axxxx) + f(xxxxa)  +1
	 * 
	 * for f(axxxxb), since a!=b, so it will not have the extra f(xxxx) and extra 1 palin, therefore f(axxxxba) = f(axxxx) + f(xxxxb) - f(xxxx)
	 * 
	 *  
	 */
	
	/*
	 * class Solution {
    int[][] memo, prv, nxt;
    byte[] A;
    int MOD = 1_000_000_007;

    public int countPalindromicSubsequences(String S) {
        int N = S.length();
        prv = new int[N][4];
        nxt = new int[N][4];
        memo = new int[N][N];
        for (int[] row: prv) Arrays.fill(row, -1);
        for (int[] row: nxt) Arrays.fill(row, -1);

        A = new byte[N];
        int ix = 0;
        for (char c: S.toCharArray()) {
            A[ix++] = (byte) (c - 'a');
        }

        int[] last = new int[4];
        Arrays.fill(last, -1);
        for (int i = 0; i < N; ++i) {
            last[A[i]] = i;
            for (int k = 0; k < 4; ++k)
                prv[i][k] = last[k];
        }

        Arrays.fill(last, -1);
        for (int i = N-1; i >= 0; --i) {
            last[A[i]] = i;
            for (int k = 0; k < 4; ++k)
                nxt[i][k] = last[k];
        }

        return dp(0, N-1) - 1;
    }

    public int dp(int i, int j) {
        if (memo[i][j] > 0) return memo[i][j];
        int ans = 1;
        if (i <= j) {
            for (int k = 0; k < 4; ++k) {
                int i0 = nxt[i][k];
                int j0 = prv[j][k];
                if (i <= i0 && i0 <= j) ans++;
                if (-1 < i0 && i0 < j0) ans += dp(i0 + 1, j0 - 1);
                if (ans >= MOD) ans -= MOD;
            }
        }
        memo[i][j] = ans;
        return ans;
    }
}        
	 * 
	 * 
	 * 
	 * how to count number of unique palins ?  let us take a look at the trees
	 * let us give a string like this: aaabcaaa
	 * 
	 * aaabcaaa
	 *     |
	 * a_aabcaa_a, b, c, ""
	 * a_f(aabcaa)_a, 
	 *       |
	 *     a_f(abc)_a, b, c, ""
	 *        |
	 *        a, b, c, ""
	 *        
	 * f(S, i, j) = a_f(S1, i+1, j-1)_a + f(a) + f(b) + f(c) + f("")
	 * 
	 * This is a little bit weird recursive relationship, how this can work ?
	 * 
	 * let us say, we find a pair a_S1_a, f(S) represent unique plains with the given string S, usually, the f(S1) is all the unique palins with S1, and f(a_S1_a) is supposed
	 * to be equal f(a_S1_a) * 2, why, because it will double the unique number when attach the head and tail a, this sounds make sense, because assume unique palins and 
	 * attached head and tail, should be unique, right ? yes, the strings in new group are unique, but there can be duplicate with the nested strings (before attached) for 
	 * example, original palin strings are , "",a, aa, aaa, aaaa, after attaching, it is aa, aaa, aaaa, aaaaa, aaaaaa, you can see a, aa, aaa, aaaa are duplicate. therefore,
	 * the ending result is not just double the numbers. However, just counting the new groups, which will be guaranteed unique.
	 * 
	 *  then how to count other forms of palins ? well, for the nesting, it always, will be for single nesting on a_x_a, the x is single unique character, and blank within a 
	 *  given range. keep attached a_  _a around "" and unique character within nesting range will only count once of those forms, the blank is imporant because it will 
	 *  generate the nesting pairs and head_tail pairs.
	 *  
	 *  therefore the recursive relationship is the above with a generalized form:
	 *  
	 *  f(S, i, j) = top level (a_f(S1, i(a)+1, j(a)-1)_a + b_f(S2, i(b)+1, j(b)-1)_b + c_f(S3, i(c)+1, j(c)-1)_c + d_f(S4, i(d)+1, j(d)-1)_d) +
	 *               f(a) + f(b) + f(c) + f(d) + f("")
	 *     
	 * 
	 * 
	 * 
	 *         T
	 *        
	 *        
	 */
	public static int solve_unique(String s) {
		char[] sc = s.toCharArray();
		int N = sc.length;
		int M = 4; //a, b, c, d
		int[][] next = new int[N][M];
		int[][] prev = new int[N][M];
		
		for(int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				prev[i][j]=-1;
				next[i][j] =-1;
			}
		}
		
		
		for (int i=0;i<N;i++) {
			int j = sc[i]-'a';
			prev[i][j] = i;
		}
		
		for (int i=N-1;i>=0;i--) {
			int j = sc[i] - 'a';
			next[i][j] = i;
		}
		
		return dyn(sc, 0, N-1, prev, next, N, M);
	}
	
	public static int dyn(char[] sc, int lo, int hi, int[][] prev, int[][] next, int N, int M) {
		
		if (lo>hi) return 0;
		
		int r = 1; //blank solution
		
		//enumerate each unique char
		for(int j=0;j<M;j++) { 
		   int s = next[lo][j];
		   if(-1<s && s<=hi) { //there is a unique char (a, b, c, d) number by j within this range
			   r++;
		   }
		   
		   // check if there is pair within this range for this char
		   int e = prev[hi][j];
		   if (-1 < s && s>e) { //when s=e>-1, it points to same char and which mean a single character, which is taken care above, we only deal with pair 
		      r+=dyn(sc,s+1, e-1, prev, next, N, M);
		   }   
		}
		return r;
	}
	
	
	// 3 dimen dyn
	
	/*
	 * Intuition and Algorithm

Let dp[x][i][j] be the answer for the substring S[i...j] where S[i] == S[j] == 'a'+x. Note that since we only have 4 characters a, b, c, d, thus 0 <= x < 4. The DP formula goes as follows:

If S[i] != 'a'+x, then dp[x][i][j] = dp[x][i+1][j], note that here we leave the first character S[i] in the window out due to our definition of dp[x][i][j].

If S[j] != 'a'+x, then dp[x][i][j] = dp[x][i][j-1], leaving the last character S[j] out.

If S[i] == S[j] == 'a'+x, then dp[x][i][j] = 2 + dp[0][i+1][j-1] + dp[1][i+1][j-1] + dp[2][i+1][j-1] + dp[3][i+1][j-1].
 When the first and last characters are the same, we need to count all the distinct palindromes (for each of a,b,c,d) within the sub-window S[i+1][j-1] 
 plus the 2 palindromes contributed by the first and last characters. <rz this should be blank and a_a rz>

Let n be the length of the input string S, The final answer would be dp[0][0][n-1] + dp[1][0][n-1] + dp[2][0][n-1] + dp[3][0][n-1] mod 1000000007.
	 */
	/*
	 * this is a similar solution as above dyn
	 * for each range, it will try to find top pair for each unique char (shrink until s[i]=s[j]=unique char
	 */
	
	/*
	class Solution {
		  public int countPalindromicSubsequences(String S) {
		    int n = S.length();
		    int mod = 1000000007;
		    int[][][] dp = new int[4][n][n];

		    for (int i = n-1; i >= 0; --i) {
		      for (int j = i; j < n; ++j) {
		        for (int k = 0; k < 4; ++k) {
		          char c = (char) ('a' + k);
		          if (j == i) {
		            if (S.charAt(i) == c) dp[k][i][j] = 1;
		            else dp[k][i][j] = 0;
		          } else { // j > i
		            if (S.charAt(i) != c) dp[k][i][j] = dp[k][i+1][j];
		            else if (S.charAt(j) != c) dp[k][i][j] = dp[k][i][j-1];
		            else { // S[i] == S[j] == c
		              if (j == i+1) dp[k][i][j] = 2; // "aa" : {"a", "aa"}
		              else { // length is > 2
		                dp[k][i][j] = 2;
	
		                for (int m = 0; m < 4; ++m) { // count each one within subwindows [i+1][j-1]
		                    dp[k][i][j] += dp[m][i+1][j-1];
		                    dp[k][i][j] %= mod;
		                  }
		                }
		              }
		            }
		          }
		        }
		      }

		      int ans = 0;
		      for (int k = 0; k < 4; ++k) {
		        ans += dp[k][0][n-1];
		        ans %= mod;
		      }

		      return ans;
		    }
		  }
	
	*/
}
