import java.util.Comparator;
import java.util.PriorityQueue;

public class MultiplicationTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(solve(3,3,5));
		System.out.println(solve(2,3,6));
		
		System.out.println(solveTwo(3,3,5));
		System.out.println(solveTwo(2,3,6));

	}
	
	public static int solve(int n, int m, int k) {

		if (m>n) {
			return solve (m, n, k);
		} 
		
		
		int x=Integer.MIN_VALUE;
		
		if(m*n<k) return x;
		
		int[] next = new int[m];
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return (a+1) * (next[a]+1)-(b+1) * (next[b]+1);				
			}
		});

		for(int i=0;i<m;i++) {
			q.offer(i);
		}
		
		for (int i=0;i<k && q.size()>0;i++) {
			int j=q.poll();
//			System.out.println(j);
			x =  (j+1) * (next[j]+1);
//			System.out.println("x=" + x);
			//here next[j] represents the pointer of n, since we always add 1 to get the number, so next[j]'s pointer upbound is n-1
			//only when next[j] is less n-1, it add 1 will be bound to n-1
			if(next[j]<(n-1)) {
				next[j]++;
				q.offer(j);
			}
		}
		
		return x;
		
	}
	
	public static int solveTwo(int n, int m, int k) {
		if(m>n) return solveTwo(m,n, k);
				
		int x = Integer.MIN_VALUE;
		if(m*n <k) return x;
		
		int[] pointers=new int[m+1];
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>() 
		   {
			  public int compare(Integer i, Integer j) {
				return i * (pointers[i]+1) - j * (pointers[j]+1);
			  }
		   }
		);
		
		for (int i=1;i<=m;i++) {
			q.offer(i);
		}
		
		for (int i=0;i<k && q.size()>0;i++) {
			int j = q.poll();
			pointers[j]++;
			x=j*pointers[j];
		    if(pointers[j]<n) {
		    	q.offer(j);
		    }			
		}

		return x;
		
	}

}
