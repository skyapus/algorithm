import java.util.Random;

public class KthPair {

	/*
	 * kth pair distance
	 * how many pair ? N*(N-1)/2
	 * brute force: calculate all pairs and then do select K,  that is O(N^2)
	 * is there any way to not calculate all pair difference ? if sort first, then now we need to find absolute difference. 
	 * 
	 * case study:
	 * how to break down the cases ? 
	 *    if sort by absolute value, would that help ?
	 *    
	 *     The typical sort and push from two end is good to find max or min something (such as max of sum of two ), however, this problem need 
	 *     to find kth.  would it still help to do two end push, let us say the array is a
	 *     we know the max distance is a[N-1]-a[0], however, you can not basically remove a[N-1] or a{0], why, because the next max can be
	 *     produce from  a[N-1] - a[x] or a[x]-a[0], this would not work. 
	 *     
	 *     the problem seems can not be better than N^2 on the worse case unless something work to prune batch of numbers consistently which I can not 
	 *     see.
	 *     
	 *      Need to find different approach
	 *     
	 *      
	 *     
	 *     1. The naive solution is create all combination and then do a select K , total elements is N * (N-1) /2, select K take O(N^2)
	 *        space O(N^2)
	 *     
	 *     2. or using streaming select K solution. create a 2K pool and keep do select median on the 2k and remove half ,and add half
	 *        this will run on N^2/K * O(K), space O(K)
	 *     
	 *     3. The other option is use heap option, and it will take N^2 * log(K), space O(K)   
	 *      
	 *     4. remember the famous painters problem ?
	 *      do binary search based on the distance because the distance is between [0, max-min]
	 *      take a middle guess of distance which is middle of hi-lo/2, and check if it is k element, if there are more than k elements, mean 
	 *      the guessed middle distance too big, because the absolute distance are in a mono increase trend, therefore hi=mid-1
	 *      similarly, if less than k elements, then the guess num is too small, move lo mid +1;
	 *      
	 *       
	 *      
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/*
	 * painter problem intuition
	 * 
	 * let us form a function f(n,k,a) represent the cost to finish the work, what really mean ? let us say we can somehow to split the work arbitrarily
	 * 
	 * for example k =3
	 * the partition to 3 parts 1../.../...n, to finish the whole work and 3 painter to work parallel, then the overall time to finish the work will the 
	 * slowest one, which in this case will be the one have most work because every painter is assumed to have same skill and speed.
	 * 
	 * so for any given partition, the overall cost will be:
	 *  f(n,3,a) = max(f(1), f(2), f(3))
	 *  
	 * the problem now become to optimize the partition split, that will minimize the overall cost
	 *  
	 * minimize f(n,k,a) for all possible partitions
	 * 
	 * let us say f(n,k,a) is the minimized cost then:
	 * f(n,k,a)=min (j=1...n) max(f(n-j, k-1, a), sum(j..n)), which j is the split position to cut one partition for painter kth 
	 * 
	 * 
	 *  
	 *  
	 *  
	 * 
	 */
	public static void solve_p(int[] boards, int k) {
		
		
		
	}
	
	//  j is the length left, this is bad case.
	public static int solve_p1(int[] boards, int k, int j) {
		//base case:
		int best=Integer.MAX_VALUE;
		if(j==0) return 0;
		if(k==0) return best;
		
		//HERE ACTUALLY HAVE PROBLEM, WHICH i will always take a valid index, but should take even =j-1 
		//for (int i=0;i<j-1;i++) {
		
		//and use index also have problem to handle the case that the kth painter take nothing because it can not handle that in the loop. 
		//the following loop did not handle that case and therefore is incorrect
		for (int i=0;i<j;i++) {
			int s=0;
			for (int m=i+1;m<j-1;m++) {
				s +=boards[m];
			}
			s = Math.max(s, solve_p1(boards,k-1,i+1));
			best=Math.min(s, best);
		}		
		return best;
	}
	
	//use length as the mark
	public static int solve_p2(int[] boards, int k, int N) {
		int best = Integer.MAX_VALUE;
		if(N==0) return 0;
		if(k==0) return best;
		
		// l is the length for the array
		for (int l=0;l<=N;l++) {
			int s=0;
			for (int m=l;m<N;m++) {
				s+=boards[m];
			}
			s=Math.max(s, solve_p2(boards,k-1,l));
			best=Math.min(best, s);			
		}
		
		return best;
	}
	
	// solve using dyn	
	public static int solve_dyn(int[] boards, int k) {
		int N=boards.length;
		int[][] b= new int[N+1][k+1];
		//base case N=0, no boards
		for (int i=0;i<=k;i++) {
			b[0][i]=0;
		}
		
		//base case k=0, no painter
//		for (int i=1;i<=boards.length;i++) {
//			b[i][0] = Integer.MAX_VALUE;
//		}
		
		//BASE CASE PROBLEM
		// the above two base case seems to cover the situation, but they are not, for example when painter k=1
		// b[x][1] will use max(b[x][0], sum(x,l)), which will always to get the max Integer.MAX_VALUE
		// since we are doing max operation, we need to change the init value to Integer.MIN_vALUE
		
		for (int i=1;i<=boards.length;i++) {
			b[i][0] = Integer.MIN_VALUE;
		}
		
		//to get sum by length, therefore sum[0] represent 0 length sum , sum [N] is sum length of N (all)
		// this will make it easy to calculate the sum by length
		int[] sum =new int[N+1];		
		for (int l=1;l<=N;l++) {
			sum[l]= sum[l-1]+boards[l-1];
		}
		
		
		//why starting from k, not l, that is because the recursive function which can resolve b[l][k] easily
		for (int kk=1;kk<=k;kk++) {
			for (int l=1;l<=boards.length;l++) {
				for (int x=0;x<=l;x++) {
					// problem with the b[x][1] here.
					//sum[x-1] is not correct too.
				   b[l][kk] = Math.min(b[l][kk], Math.max(b[x][kk-1], sum[l]-sum[x]));
				}   
			}						
		}
		
		return b[N][k];
				
	}
	
	public static int solve_search(int[] boards, int k) {
		int N=boards.length;
		int[] sum = new int[N];
		int y=0;
		int lo=0, hi=-1;
		for (int i=0;i<N;i++) {
			y +=boards[i];
			lo=Math.max(lo, boards[i]);
			sum[i]=y;
		}
		
		/*
		 * this solution is based on the guess largest partition can be assigned to a single painter that will minimize the overall time ? 
		 * assume all the painters are same.
		 * what is the most largest partition a painter can take, it is the total of boards.
		 * what is least largest partition a painter to take that will minimize the overall time ? that is the largest board.
		 *  
		 */
		int mid = Integer.MIN_VALUE;
		while (lo<hi) {
			mid = lo + (hi-lo)/2;
			int p=get(boards, mid);
			if(p<k) {
				//too large guess. but may be still good
				hi=mid;
			} else if(p==k) {
				return mid;
			} else {  //p>k, guess too small
				lo=mid+1;
			}			
		}
		
		return lo;
				
	}
		
   public static int get(int[] boards, int mid) {
	   int p=0;
	   int s=0;
	   for (int i=0;i<boards.length;i++ ) {
		   s+=boards[i];
		   if(s>mid) {
			   s=boards[i];
			   p++;
		   }
	   }
	   
	   if(s>0) {
		   p++;
	   }
	   
	   return p;
   }
   

   
   /*
    * SHOULD BE GOOD
    */
   public static int partition(int[] a, int lo, int hi) {
	   int v = a[lo];
	   lo++;
	   //what is the invariant
	   // how about keeping every element before lo (exclusive) to be less or equal to v,
	   //    if lo move pass after end of array, then all elements in the array are >=v 
	   //    if lo does not pass end of array, then lo should pointer to first element > v
	   //should we keep some invariant for hi ?
	   //how about keep every elements after hi to be greater than v (exclusively) ?
	   //    if hi does not pass lo, then all elements after v should > v, of coz if hi is last of array, it still counts
	   //    if hi pass lo, then  hi should point to last elements <=v, and hi will be the pivot position.
	   // hi is guarantee with lo - hi scope
	   
	   // BOTH lo and hi are exclusively marker.
	   
	   while (lo<=hi) {
		   if (a[lo]<=v) {
			   lo++;
		   } else if(a[hi]>v) {
			   hi--;
		   } else {
			   swap(a, lo, hi);
			   lo++; hi--;
		   }
	   }
	   swap(a, 0, hi);
	   return hi;
   }
   
   public static int ThreeWayPartition(int[] a, int lo, int hi) {
	   /*
	    * three ways need have range lt, gt mark pointer the invariant are:
	    * all elements before lt (exclusively) are <v
	    * all elements after gt (eclusively) are > v
	    * all elements between lt...gt (inclusively) == v, but this may not be an invariant to keep during the process, it is end result.
	    */
	   
	   int lt=lo; // this one starts with good invariants.
	   int gt=hi; // this one starts with good invariants 
	   int v = a[lo];
	   lo++;       // lo point to the element to be processed
	   while (lo<=hi) {
		   if (a[lo]>v) lo++;
		   else if (a[lo]<v) {
			   swap(a, lo, lt);
			   // move the lt only and rely on the next iteration to check ==v and move the elements to right position.
			   // however, this break the invariant temporarily if there is only one element =v and both lt and gt point to that position and rely on the next
			   // iteration to restore the invariant, which may not be good for debug, instead should keep the invariants in this step.
			   lt++;
			   //restore the invariant, which basically do the a[lo]=v process as the following:
//			   gt++;
//			   swap(a, lo, gt);
//			   lo++;
			   
		   } else { //a[lo]==v
			   gt++;
			   swap(a, lo, gt);
			   lo++;
		   }		   
	   }	   
	   
	   return gt;
   }
   
   public static int partition2(int[] a, int lo, int hi) {
	      int v=a[lo];
	      int i=lo+1;
	      while (i<hi) {
	    	  if(a[i]<=v) {
	    		  i++;
	    	  }
	    	  else if(a[hi]<=v) {
	    		  swap(a, i, hi);
	    		  i++;
	    		  //probably it is important to reduce hi here, although it is possible to get hi reduce in the next iteration, but i<hi may not hold any more and 
	    		  //then hi may never get --, but this algorithm works to hold the invariant that hi will point to the next hi side (but no processed item) or point to 
	    		  // processed that case happen i cross hi. 
	    		  // but this algorithm need to find the insert point for v. therefore hi must not point to a item > v when loop stopped. which in the above case II will 
	    		  //happen if it does not hi --
	    		  // hence do this:
	    		  hi--;
	    	  } else {
	    		  hi--;
	    	  }
	      }
		  
	      // hi here should now point to the insert point of v
	      // normally will swich the lo and hi to move there
	      swap(a, 0, hi);
	      return hi;
	   }
   
   static void swap(int[] a , int i, int j) {
	   int x= a[i];
	   a[i]=a[j];
	   a[j]=x;
   }

}
