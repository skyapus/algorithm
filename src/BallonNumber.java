
public class BallonNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a= {3, 1, 5, 8};
		int N=a.length;
		boolean[] b=new boolean[N];
		System.out.println(solve(a, b, N,""));
		
		int[] c= new int[N+2];
        c[0]=1;c[N+1]=1;
        for(int i=1;i<N+1;i++) {
        	c[i]=a[i-1];
        }
        
        System.out.println(divide(c, 0, N+1));
	}
	
	
	public static int solve(int[] a, boolean[] b, int N, String chain) {
        int best=0;
		for (int i=0;i<N;i++) {
			if(!b[i]) {
				String ch=chain+"-"+i;
//				System.out.println(ch);
				b[i]=!b[i];
				//init c as the current element
				// remember it is a[j] * c * a[k], NOT a[j] * c + c * a[k]
                int c=a[i];
                
//                if(i==1) {
//                	for (int s=0;s<N;s++) {
//                		System.out.println(b[s]);
//                	}
//                }
				int k=i+1;				
				while(k<N && b[k]) k++;
				if (k<N) c*= a[k];		

			    int j=i-1;
			    while(j>=0 && b[j]) j--;
			    if(j>=0) c*= a[j];
			    			    
				c +=solve(a, b, N, ch);
				best=Math.max(c, best);
				b[i]=!b[i];
			}
			
		}
		return best;
	}
	
	public static int divide(int[] a, int i, int j) {
		if (i+1==j) return 0;
		int best=0;
		for (int k=i+1;k<j;k++) {
			best=Math.max(best, divide(a, i, k) + divide(a, k, j) + a[i] * a[k]  * a[j]);
		}
		
		return best;
		
	}

}
