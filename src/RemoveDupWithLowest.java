
public class RemoveDupWithLowest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	

	
	public static String solve(String a) {
		int N = a.length();
		int[] count= new int[26];
		for (char x:a.toCharArray()) {
			count[x-'a']++;
		}
		
		int k=0;
		int pos=0;
		for(char x: a.toCharArray()) {			
			if(x<a.charAt(pos)) pos=k;
			count[x-'a']--;
			if (count[x-'a']==0) break;
			k++;
		}
		
		char t = a.charAt(pos);
		a = a.substring(pos+1).replace(String.valueOf(t), "");
		return t + solve(a);
		
	}

}
