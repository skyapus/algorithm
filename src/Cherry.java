import java.util.Arrays;

public class Cherry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] a = {
				{0,1,-1},
				{1,0,-1},
				{1,1,1}				
		};
//		System.out.println(back(a, a.length,0,0,true));
		dp(a, a.length);
	}
	
	public static void solve() {
		
		
	}
	
	/*
	 * just wonder if there is a backtrack solution for this problem. 
	 */
	
	public static int back(int[][] a, int N, int r, int c, boolean left_right) {
		
		if(r==0 && c==0 && !left_right) return 0;
		
		if(r<0 || r>=N || c<0 || c>=N) return Integer.MIN_VALUE;
		
		if (r==N-1 && c==N-1 && left_right) { 
			left_right=false;		
		}
		
		if(a[r][c]==-1) {
			return Integer.MIN_VALUE;
		}
		
	   int current=a[r][c];
	   
	   if(current>0) {
	     a[r][c]=0;
	   }

	   int z = Integer.MIN_VALUE;	
	   if(left_right) {
		   int x =back(a, N, r+1, c, left_right);		
		   int y = back(a, N, r, c+1, left_right);
		   z =  Math.max(x, y);
	   } else {
		   int x =back(a, N, r-1, c, left_right);		
		   int y = back(a, N, r, c-1, left_right);
		   z =  Math.max(x, y);
	   }
		
	   if(current>0){
		 a[r][c]=1;
	   }
	   
	   if(z>=0) z+=current;
	   return z;		   				
	}
	
	
	public static void dp(int[][] a, int N)  {
		
		
//		int step = N + N-1;

		int[][][] rs= new int[N+N][N][N];
		for (int i=0;i<rs.length;i++) {
			for (int j=0;j<rs[i].length;j++) {
				Arrays.fill(rs[i][j], Integer.MIN_VALUE);
			}
		}
		
		rs[0][0][0]=a[0][0];
		int level =1;
		for (int step=1;step<N;step++, level++) {
			for (int r=step;r>=0;r--) {
				for (int i=r; i>=0;i--) {
				   // it ends up to use the row number to pick up two spots, because column can be deducted from the level
				   // by the way, the two pickers can be in same spots.
				   // the relationshp between level and level-1. 
				   // what is the possible spot in level-1 that can reach to current [r][i] ?
				   // let us say, currently spots in level is [r1][c1] and [r2][c2], r1+c1=r2+c2=level
				   // the previous level could be [r1-1, c1], [r1,c1-1] to get [r1][c1], and [r2-1][c2] and [r2][c2-1], therefore the possible combination could be 
				   // 4. [r1-1, c1], [r2-1][c2], [r1-1, c1], [r2][c2-1]
				   //	 [r1, c1-1], [r2-1][c2], [r1, c1-1], [r2][c2-1]
					
//                   rs[level][r][i] = Math.max(Math.max(rs[level-1][r-1][i-1], rs[level-1][r-1][i]), Math.max(rs[level-1][r][i-1], rs[level-1][r][i]));
		            rs[level][r][i] = Math.max(Math.max(get(rs, level-1, r-1, i-1, N), 
		            		                            get(rs, level-1, r-1, i, N)), 
		            		                   Math.max(get(rs, level-1, r, i-1, N), 
		            		                		    get(rs, level-1, r, i, N))
		            		                  );
                   //plus currently, if current level two spots are same, just add once
	               System.out.println("step " + step); 
                   System.out.println("level " + level);
                   System.out.println("r1 " + r);
                   System.out.println(" r2 " + (level-r));
                   rs[level][r][i] += a[r][level-r];
                   if(r!=i) {
                	   rs[level][r][i] += a[i][level-i];
                   }                   
				}
			}						
		}
		

		for(int step=1;step<N;step++, level++) {

			for(int r=N-1, c=step;c<N;r--, c++) {
				for(int i=r, j=c;j<N;i--, j++) {
		            rs[level][r][i] = Math.max(Math.max(get(rs, level-1, r-1, i-1, N), 
                                                        get(rs, level-1, r-1, i, N)), 
                                               Math.max(get(rs, level-1, r, i-1, N), 
                		                                get(rs, level-1, r, i, N))
                                              );
//	                   rs[level][r][i] = Math.max(Math.max(rs[level-1][r-1][i-1], rs[level-1][r-1][i]), Math.max(rs[level-1][r][i-1], rs[level-1][r][i]));
	                   //plus currently, if current level two spots are same, just add once
	                   
		               System.out.println("r " + r + " " + i);
		               System.out.println("level " + level);
	                   rs[level][r][i] += a[r][level-r];
	                   if(r!=i) {
	                	   rs[level][r][i] += a[i][level-i];
	                   } 
				}
			}
//			level++;
		}
		
		/* 
		 * the result is the 2*N-2, N-1, N-1, make sure to use a concrete table to walk through
		 */
		System.out.println("dp "+ rs[2*N-2][N-1][N-1]);
	}
	
	
	public static int get(int[][][] dp, int level, int i, int j, int N) {
		if(i<0 || i>=N || j<0 || j>=N)
			return Integer.MIN_VALUE;
		return dp[level][i][j];
	}
	
	public static void dp_test_diaganal(int[][] a, int N)  {
		
		int[][] t = {
				{0, 2,  5,  9},
				{1, 4,  8,  12},
				{3, 7,  11, 14},
				{6, 10, 13, 15}
				
		};
		
//		int step = N + N-1;
		
		for (int step=0;step<=N-1;step++) {
						
			for (int r=step, c=0;r>=0;r--, c++) {
				for (int i=r-1, j=c+1;i>=0;i--, j++) {
					System.out.println(t[i][j]);
				}
			}			
			
		}
		
		for(int step=1;step<=N-1;step++) {
			for(int r=N-1, c=step;c<N;r--, c++) {
				for(int i=r-1, j=c+1;j<N;i--, j++) {
					System.out.println(t[i][j]);
				}
			}
		}
		
		
	}
}
