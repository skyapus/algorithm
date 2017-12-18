import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * find how many intersection interval
 * 1. naive solution, create a array to store each interval, when adding an interval, add first and then do intersection check and count for all 
 * intervals in the array. add is O(1), intersection count and check will be : O(N), why ? probably not. let us say, we find intersection for any two 
 * pairs of intervals. that is O(N^2), then needs to process those share intersections. 
 */

class Interval {
		int x, y;
		Interval(int x, int y) {
			this.x=x;
			this.y=y;
		}
}

class IntervalCount {
	int x, y, count;
	IntervalCount(int x, int y, int count) {
		this.x=x;
		this.y=y;
		this.count=count;
	}
}

	
public class CalendaryBook {


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] in = {
				{10, 20},
				{50, 60},
				{10, 40},
				{5, 15},
				{5, 10},
				{25, 55}
		     };
		Interval[] v = new Interval[in.length];
		
		for (int i=0;i<in.length;i++) {
			v[i]= new Interval(in[i][0], in[i][1]);
		}
		
		solve(v);

	}
	
	public static  boolean overlap(int i, int j, Interval[] v) {
//		if ((v[i].x <v[j].y) && (v[j].x < v[i].y)) {
//			return true;
//		}
//		return false;
		
		if (v[i].x>=v[j].y || v[j].x>=v[i].y) return false;
		return true;
		
	}
	
	public static  boolean overlap(IntervalCount a, IntervalCount b) {		
		if (a.x>=b.y || b.x>=a.y) return false;
		return true;
		
	}
	
	
	
	/*
	 *  count based on range of data points 
	 */
	public static int solve(Interval[] v) {
		//assume the range of data points 0-99
		int N =100;
		int[] count= new int[N];
		Arrays.fill(count, 0);
		for (int i=0;i<v.length;i++) {
		    for (int j=v[i].x;j<(v[i].y-1);j++) {
				count[j]++;
			}			
		}
		
		// search the max 
		
		int max = -1;
		for (int i=0;i<count.length;i++) {
			max=Math.max(max, count[i]);
		}
		
		System.out.print("max " + max);
		
		return max;
	}
	
	public static List<IntervalCount> merge(LinkedList<IntervalCount> v, IntervalCount e) {
				
		if(v.size()==0) {
			v.add(e);
			return v;
		}
		
		List<IntervalCount> rs = new LinkedList<IntervalCount>();
		
		for(IntervalCount s: v) {
			if(e!=null && overlap(s, e)) {
				int x0 = Math.min(s.x, e.x);
				int x1 = Math.max(s.x, e.x);
				int y1 = Math.min(s.y, e.y);
				int y2 = Math.max(s.y, e.y);
				
				if(x0!=x1) {
				  rs.add(new IntervalCount(x0, x1, s.count));
				}
				//will take care x0=x1
				rs.add(new IntervalCount(x1, y1, s.count+e.count));
				//when y1==y2, it can not overlap with s anymore
				if (y1==y2) {
					e=null;
				} else {
					//depends on which 
					if(e.y<s.y)
				      e = new IntervalCount(y1, y2, s.count);
					else {
					  e=  new IntervalCount(y1, y2, e.count);
					}
				}
			} else  {
				//may it is critical when create the result list, will have the order interval by x
				if(e!=null && e.y<=s.x) {
					rs.add(e);
					e=null;
				}
				rs.add(s);				
			} 
		}
		
		if (e!=null) {
			rs.add(e);
		}
		
		return rs;

		
	}
	
	
	/* this is a correct algorithm, but it will require all data points
	 * 
     *  count based on the individual data point
	 */
	public static int solve2(Interval[] v) {
		//assume the range of data points 0-99
		int N =100;
		int[] count= new int[N];
		Arrays.fill(count, 0);
		for (int i=0;i<v.length;i++) {
			    for (int j=v[i].x;j<(v[i].y-1);j++) {
					count[j]++;
				}			
		}
		
		// search the max 
		
		int max = -1;
		for (int i=0;i<count.length;i++) {
			max=Math.max(max, count[i]);
		}
		
		System.out.print("max " + max);
		
		return max;
	}
	
	/*
	 * this solve seems to solve the problem, but actually it counts how many overlaps for each individual interval, NOT the highest common interval
	 * to count that, need to count based on the individual data point
	 * 
	 * for example, (3,6) overlap (5,7), (3,6) overlap (1,4), then the count will be 3 for interval (3,6), but that is not highest common overlap
	 */
	public static int solve1( Interval[] v) {
		int[] count= new int[v.length];
		Arrays.fill(count, 1);
		for (int i=0;i<v.length;i++) {
			for (int j=i+1;j<v.length;j++) {
				
				if (overlap(i, j, v)) {
					count[i]++;
					count[j]++;
				}
			}
		}
		
		// search the max 
		
		int max = -1;
		for (int i=0;i<count.length;i++) {
			max=Math.max(max, count[i]);
		}
		
		System.out.print("max " + max);
		
		return max;
	}

}
