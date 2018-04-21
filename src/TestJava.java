import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TestJava {
	
	public static void main(String[] args) {
		int[] x = {1, 2};
//		System.out.println(Arrays.toString(x));
		
		System.out.println(x);
		List<Integer> a = Arrays.asList(1, 2, 3);
		List<Integer> b = Arrays.asList(1, 2, 3);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(a.equals(b));
		
		int[] c = {1,2,3};
		int[] d = {1,2,3};
		System.out.println(c);
		System.out.println(d);
		System.out.println(c.equals(d));
		
		ArrayList<Integer> e = new ArrayList<Integer>();
		e.add(1); e.add(2);e.add(3);
		System.out.println(e);
		
		
		List<Integer> f = new LinkedList<Integer>();
		f.add(1); f.add(2);f.add(3);
		System.out.println(f);
		
		List<Integer> g = new LinkedList<Integer>();
		g.add(1); g.add(2);g.add(3);
		System.out.println(g);
		
		System.out.println(e.equals(g));
		
		int[] x1 = new int[3];
		for (int i=0;i<x1.length;i++) {
			System.out.println(x1[i]);
		}
	}
	
//	public static int testBinarySearch () {
//		int[] a = {1, 2, 3, 4, 5};
//		Arrays.binarySearch(a, -1);
//	}

}
