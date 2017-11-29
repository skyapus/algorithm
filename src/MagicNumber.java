import java.util.LinkedList;

//2 3 5
public class MagicNumber {
	
	public static void main(String...args) {
		System.out.println(solve(5));
		System.out.println(solveTwo(5));
	}
	
	//Queue solution
	public static int solve(int n) {
		//can use one single priority Queue and push next into the queue, that will have a cost of nlog(bound_size)
		//if creating 3 seperate queue and each queue is ascending order, it is not necessary to pay cost min queue
		LinkedList<Integer> q2 = new LinkedList<>();
		LinkedList<Integer> q3 = new LinkedList<>();
		LinkedList<Integer> q5 = new LinkedList<>();
		q2.offer(2);q3.offer(3);q5.offer(5);
		int x = Integer.MAX_VALUE;
		
		for (int i=0;i<n;i++) {
			x=Math.min(q2.peek(), Math.min(q3.peek(), q5.peek()));
			if(x==q2.peek()) {
				q2.poll();
				q2.offer(x*2);
				q3.offer(x*3);
				q5.offer(x*5);
			} 
			
			if(x==q3.peek()) {
				q3.poll();
				q3.offer(x*3);
				q5.offer(x*5);
			}
			
			if(x==q5.peek()) {
				q5.poll();
				q5.offer(x*5);
			}
		
		}
		
		return x;
		
	}
	
	/*
	 * this solution use the result q and separate pointers to remember where to get next 
	 */
	public static int solveTwo(int n) {
		LinkedList<Integer> q = new LinkedList<>();
		int i=0,j=0,k=0;
        q.offer(1);
        int x= Integer.MAX_VALUE;
        for (int m=0;m<n;m++) {
        	int a,b,c;
        	a=q.get(i) * 2; b=q.get(j)*3; c=q.get(k)*5;
        	x=Math.min(a, Math.min(b, c));
        	if(x==a) {
        		i++;
        	} else if(x==b) {
        		j++;
        	} else {
        		k++;
        	}
        	q.offer(x);
        }
		
		return x;
		
	}
	
//	public static double solveThree(int n) {
//		LinkedList<Double> q = new LinkedList<>();		
//		double sqrt2 = Math.sqrt(2);
//		int a=1, b=1;
//		//calculate nth a+b*sqrt(2)
//		double x =Double.MAX_VALUE;
//		/*
//		 * although for each a and b can be monotonically increase, however, it is hard to partition the increment, for example, from a=1 to a=2, you can not exaust a=1 because
//		 * b can be unlimited grow, however, we can use the minimum a+b*qrt which is 1+1*qrt and it can exhaust growth like: 
//		 * (a+1) + b*qrt, a+(b+1)*qrt, (a+1) + (b+1) * qrt, but what is the challenge for the second solution ? you can use a min priority queue, every time extend the 3 numbers,
//		 * compare it with the current min element in queue, if any of 3 element  is small or equal to current min, it can be throw away because it should have been processed, why?
//		 * let us assume current min element in queue is c+d*sqr and one of 3 element is a+b*sqr < c+d*sqr, that could be a<c or b<d. the reason c+d*sqrt is queue is because
//		 * (c-1 + d*sqrt is processed and c+d-1 *sqrt, c-1 + d-1 *sqrt are processed, otherwise c+d*sqrt can not be min element because we always exhaust the queue add.
//		 * 
//		 *   This solution can also be used to find a+b*sqrt == c+d*sqrt where a!=c and b!=d.
//		 */
//		
//		
//	}

}
