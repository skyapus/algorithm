
import java.util.Arrays;
import java.util.Comparator;

public class RangeSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public static void findBySearch(int[] a) {
		int N = a.length;
		int[] sum=new int[N];
		int prev=0;
		for (int i=0;i<N;i++) {
			prev+=a[i];
			sum[i]=prev;
		}
		
		Integer[] index=new Integer[N];
		for(int i=0;i<N;i++) index[i]=i;
		

		Arrays.sort(index, new Comparator<Integer>() {		
			public int compare(Integer a, Integer b) {
				return sum[a]-sum[b];
			}
		});
		
		//search floor and ceiling
		
		
	}
	
	/*
	 * interesting solution
	 */
	public static void find(int[] a) {
		
	}
	
	public static int divide(int[] a, int[] b, int low_bound, int high_bound, int l, int h) {
		if(l>=h) return 0;
		int m = l+ (h-l)/2;
		int c1 = divide(a, b, low_bound, high_bound, l, m);
		int c2 = divide(a, b, low_bound, high_bound, m+1, h);
		int c3 = merge (a, b, low_bound, high_bound, l, m, h);
		return c1+c2+c3;
		
	}
	
	public static int merge(int[] a, int[] b, int low_bound, int high_bound, int l, int m, int h) {
		for(int i=l;i<=h;i++) {
			b[i]=a[i];
		}
		
		int count=0;
        int k=m+1, j=m+1;
		for (int i=l;i<=m;i++) {
			while(k<=h && (b[k]-b[i]<low_bound)) k++; // find first b[k] greater equal b[i], before k, all b[k-] less than low_bound
			while(j<=h && (b[j]-b[i]<high_bound)) j++; // find first b[j] greater equal b[i]. before j, all b[j-] less than high bound.
			count += j-k;
		}
		
		k=m+1; j=l;
		for (int i=l;i<h;i++) {
			if(j>m) {
				a[i]=b[k++];
			} else if(k>h) {
				a[i]=b[j++];
			} else {
				if(b[j]<=b[k]) {
				  a[i]=b[j++];
				} else {
				  a[i]=b[k++];
				}				
			}			
		}
		
		return count;
	}
	
	

}
