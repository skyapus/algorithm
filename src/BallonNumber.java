
public class BallonNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static int solve(int[] a, int i, int j) {
		if(i>j) return 1;
		if(i==j) return a[i];
		
		for (int k=i;k<=j;k++) {
			int z = a[k];
			if(k>i) z *=a[k-1];
			if(k<j) z *= a[k+1];
			
			
		}
		
		
	}

}
