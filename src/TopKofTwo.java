
public class TopKofTwo {
	
	static int selectK(int[] a, int[] b, int k) {
		assert (a.length+b.length>=k);		
		return selectK(a, 0, Math.min(k, a.length)-1, b, 0, Math.min(k, b.length)-1, k);
	}
	
	static int selectK_it(int[] a, int[] b, int k) {
		assert (a.length+b.length>=k);		
		return iterative(a, 0, Math.min(k, a.length)-1, b, 0, Math.min(k, b.length)-1, k);
	}
	
	static int selectK(int[] a, int al, int ah, int[] b, int bl, int bh, int k) {
		if(al>ah) return b[bl+k-1]; 
		if(bl>bh) return a[al+k-1];
		
		/*
		 * ########## Important BASE CASE
		 */
		if (k==1) return Math.min(a[al], b[bl]);
		
	    int mid1 = Math.min(al+ k/2-1, ah);

	    int mid2 = Math.min(bl+ (k/2-1), bh);
	    
	    if(a[mid1]==b[mid2]) {
	    	return a[mid1];
	    } else if (a[mid1]>b[mid2]) {
	    	return selectK(a, al, ah, b, mid2+1, bh, k-(mid2-bl+1));
	    } else {
	    	return selectK(a, mid1+1, ah, b, bl, bh, k-(mid1-al+1));
	    }	    	    		
	}
	
	static int iterative(int[] a, int al, int ah, int[] b, int bl, int bh, int k) {
		
		while (al<=ah && bl<=bh) {
			//#### DO NOT typo al , bl here
			if(k==1) return Math.min(a[al], b[bl]);
			int mid1 =Math.min(al+ k/2 -1, ah);
			int mid2 =Math.min(bl + k/2 -1, bh);
			if (a[mid1]==b[mid2]) {
				return a[mid1];
			} else if(a[mid1]>b[mid2]) {
				//####HERE IS THE MISTAKE, YOU NEED TO UPDATE k first, i make mistake to update k after bl update, probably should store len separately
				k-=mid2-bl+1;
				bl=mid2+1; 
				System.out.println("mid2=" + mid2 + " bl= "  + bl + " bh= "  + bh +" k= " + k);
			} else {
				k-=(mid1-al+1);
				al=mid1+1; 
				System.out.println("mid1=" + mid1 + " al= "  + al + " ah= "  + ah +" k= " + k);
			}						
		}
		
		if(al>ah)
			return b[bl+k-1];
		else //bl>bh
			return a[al+k-1];
		
	}
	
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5};
		int[] b = {6, 7, 8, 9, 10};
		System.out.println("result " + selectK(a, b, 3));
		System.out.println("result select iterative " + selectK_it(a, b, 3));
	}

}
