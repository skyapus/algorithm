import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	static void range(List<List<Integer>> r) {
		/*
		 * since each list is sorted,  will compare the first element of each list, and pick the smallest element. The question to ask is
		 * let us say a is the smallest element, and c is the largest and b is the middle element, therefore the range to cover all element so far is
		 * [a,c], can we safely purge a and move to next element a1 where a1>=a ? well, to answer the question, instead, we are going to ask us slightly
		 * different question, can a form a better range with elements from other list ?
		 * apparently, let us say, [a,bx,cx], cx>=c, therefore it will not, how about bx >=b, well, if bx<=c, then it is same as [a,c], if bx>=c, the new
		 * range will [a,bx] well, it is worse than [a,c] because bx>c. therefore, we can safely purge a and move to a1.
		 */
		
		PriorityQueue<int[]> x = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return r.get(a[0]).get(a[1]) - r.get(b[0]).get(b[1]);
			}
		});
		
		x.offer(new int[]{1,2});
		
		PriorityQueue<List<Integer>> y = new PriorityQueue<List<Integer>>(new Comparator<List<Integer>>() {
			public int compare(List<Integer> a, List<Integer> b) {
				return r.get(a.get(0)).get(a.get(1)) - r.get(b.get(0)).get(b.get(1));
			}
		});
		
		y.offer(Arrays.asList(1,2));

		
		//this one use a different array to store 
		int[] current= new int[r.size()];
		PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return r.get(a).get(current[a])-r.get(b).get(current[b]);
			}
		});
		
//		int[] pointer = new int[r.size()];
		int bestDistance=Integer.MAX_VALUE;
				
		int max = Integer.MIN_VALUE;
		boolean invalid=false;
		for (int i=0;i<current.length;i++) {
			//get element and put in the minpq
			if (current[i]==r.get(i).size()) {
				invalid=true;
				break;
			}
			max =Math.max(max, r.get(i).get(0));
			minPQ.offer(i);
		}
		
		if (invalid) return;
		
		while (true) {
			int j = minPQ.poll();
			int c = r.get(j).get(current[j]);
			
            bestDistance=Math.min(bestDistance, max-c);
			
			current[j]++;
			if(current[j]==r.get(j).size())
				break;
			max=Math.max(r.get(j).get(current[j]), max);
			minPQ.offer(j);			
		}
		
		
	}

}
