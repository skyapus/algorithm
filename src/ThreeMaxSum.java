import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ThreeMaxSum {

	public static void main(String[] args) {
         solve(new int[] {
        		 1,2,1,2,6,7,5,1        		 
         }, 2);

	}
	
	public static void solve(int[] a, int k) {
		//  create a new array to store k-sum array because it is likely this will be always needed. 
		//1. naive solution,  search sequentially typical i, j, k, take o(n) space and o(n)^3 time complexity
		//2. or sort the new array and try to find largest 3 element, by search from largest to lowest for non-overlapping, but need to figure out the lower lexilcographically if there is 
		// multiple solution. but this will still need at least nlogn because of sort.  and the search or top 3 will need some search no more than 3k element.
		//3. the elegant solution is like rectange problem which geniously partition the problems as to form a trio, just check each element, then to include the element into 
		// the trio, it need to find best on its left and best on its right, then it will have best_left + element + best_right, then we will find the max of each this trio
		// the best left and best of right can be calculated and cached, therefore it will take o(n) space. and the max will be get use o(n)
		
		Integer[] b = new Integer[a.length-3];
		Integer[] d=new Integer[b.length];
		for (int i=0;i<b.length;i++) {
			b[i]=a[i]+a[i+1]+a[i+2];
			d[i]=i;
		}		
		
        List<Integer> c = Arrays.asList(d);
		Comparator<Integer> cm = new Comparator<Integer>() {
        	public int compare(Integer i, Integer j) {
        		if(b[i]<b[j]) {
                  return 1;        			
        		} else if (b[i]==b[j]) {
        			if (i<=j) 
        				return 1;
        			else 
        				return -1;
        		} else {
        			return -1;
        		}
        	}
        };
        
		Collections.sort(c, cm );
        for(int x: c) {
        	System.out.println(b[x]);
        }
        
        //here you can attempt to find the top 3.
        
	}
	
	public static void solve2(int[] a, int k) {
		int[] b = new int[a.length-k];

		for (int i=0;i<k && i<a.length;i++) {
			b[0] +=a[i];
		}
		int j=k;
		for (;j<a.length;j++) {
			b[j-k+1]=b[j-k]-a[j-k]+a[j];
		}
		
		int[] left = new int[b.length];
		int maxL = Integer.MIN_VALUE;
		for (int i=0;i<b.length;i++) {
			if (b[i]>maxL) {
				maxL=b[i];
			}
			left[i]=maxL;
		}
		
		int[] right= new int[b.length];
		maxL=Integer.MIN_VALUE;
		for (int i=b.length-1;i>=0;i--) {
			//since we are looking for lowest index, the lowest index will take precedence
			if(b[i]>=maxL) {
				maxL=b[i];
			}
			right[i]=maxL;			
		}
		
		int best=Integer.MIN_VALUE;
		for (int i=k;i<b.length-k;i++) {
			int x=b[i]+left[i-k]+right[i+k];
			if (x>best) {
				best=x;
			}
		}		
		
	}

}
