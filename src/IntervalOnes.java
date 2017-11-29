
public class IntervalOnes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MAX_VALUE>>1);
		intervalNum(5);

	}
	
	public static void intervalNum(int K) {
		//KEY to look at the recursive tree, when start with n=0, will cause stack overflow, that is because when append 0 to 0, it will always be 0
		//so start with with n=1
		intervalNum(K, 1);
	}
	
	public static void intervalNum(int K, int n) {
		if (n>K  || n <=0) return;
		System.out.println(n);
		
		if(n>(Integer.MAX_VALUE>>1)) return;
		
		if ((n&1) == 0) {
			//USE OR, not AND
			intervalNum(K, (n<<1) | 1);
		} 
		
 	    intervalNum(K, n<<1);
		
		
	}

}
