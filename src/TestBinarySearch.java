import java.util.Arrays;

public class TestBinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       testBinarySearch();
	}
	
	public static void testBinarySearch () {
		int[] a = {0, 1, 2, 3, 4, 5};
		int x = Arrays.binarySearch(a, 6);
//		System.out.println(x);
//		
//		x = Arrays.binarySearch(a, 0, 1, 5);
//		System.out.println(x);
		
		int[] b = {0, 1, 1, 1, 1, 3, 3, 4, 5};
		x= Arrays.binarySearch(b, 1);
		System.out.println(x); 
		x= Arrays.binarySearch(b, 0, x, 1);
		System.out.println(x); 
		x= Arrays.binarySearch(b, 0, x, 1);
		System.out.println(x);
	
		/*
		 * when key is in the set, it will find the floor and ceiling
		 * when key is not in the set, it will find the floor and ceiling to be the insert point
		 * The insert point for floor can be -1, which translated to the index (-1+1) * (-1) = 0; 
		 *  which means it is smaller than the smallest element.
		 * The insert point for ceiling can be (-10), which translated to index (-10+1) * (-1) = 9, which is the length of array, mean 
		 * key is bigger than the biggest. 
		 */
		System.out.println("----- floor");
		int key = 6;
    
		x=floor(b,key);
		System.out.println("floor index is " + (x+1) * (-1));
		
		System.out.println("====== ceiling");
        x = ceiling(b, key); 
		System.out.println("ceiling index is " + (x+1) * (-1));
	}
	
	public static int floor(int[] b, int key) {
		int x=b.length;
		while(x>=0) {
			x=Arrays.binarySearch(b, 0, x, key);
			System.out.println(x);
		}
		
		return x;
	}

	
	public static int ceiling(int[] b, int key) {
		int x=0;
		while(x>=0) {
			x=Arrays.binarySearch(b, x, b.length, key);
			System.out.println(x);
			if(x>=0) x++;
		}
		return x;
	}

}
