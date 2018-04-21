import java.util.Map;
import java.util.HashMap;

public class Zum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(solv("WRRBBW", "RB", 0));
		Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
		System.out.println(solv(map, "RBYYBBRRB", "YRBGB", 0));

	}
	
	public static int solv(Map<String, Map<String, Integer>> map, String a, String b, int k) {
		if(a.length()==0) return 0;
		if (k>=b.length()) return Integer.MAX_VALUE;
		int cached = cache(map, a, b.substring(k));
		if (cached>0) return cached;
		
		int best=Integer.MAX_VALUE;
        char c=b.charAt(k);        
        //do not use current char
        best=Math.min(best, solv(map, a, b, k+1));
		for (int i=0;i<=a.length();i++) {
			//insert current char to make a new string
			String s1= a.substring(0,i)+ c + a.substring(i);
			s1 = form(s1);

			int other = solv(map, s1, b, k+1);
			if (other<Integer.MAX_VALUE) {
				other++;
			}
			best=Math.min(best, other);

		}
//		Map<String, Integer> cx=map.get(a);
		//This way to print a null check won't work because the + will make it always false if cx==null is true
//		System.out.println(a + " " + cx==null);
		//correct way to print the null check
//		System.out.println(cx==null);
		if (!map.get(a).containsKey(b.substring(k))) {
			map.get(a).put(b.substring(k), best);
		}
		
		return best;
	}
	
	public static String form(String a) {
		int i=0;
		String r="";
		while(i<a.length()) {
			char c=a.charAt(i);
			int j=i+1;
			while(j<a.length() && a.charAt(j)==c) {
				j++;
			}
			if (j-i>2) {
				
			} else {
				r +=a.substring(i,j);
			}
			
			i=j;						
		}
		
		if(r.length()<a.length()) {
			return form(r);
		}
        return r;
	}
	
	public static int cache(Map<String, Map<String, Integer>> map, String a, String b) {
		Map<String, Integer> m=map.getOrDefault(a, new HashMap<String, Integer>());
		//Need explicitly put m back because it only returns the default, it does not automatically put that back
		map.put(a,  m);
		if(m.containsKey(b)) 
			return m.get(b);
		else 
			return -1;
		
	}
	
	public static int divide(String a, String b) {
		
		return 0;
		
	}
	

}
