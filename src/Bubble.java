
public class Bubble {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solve(2,2);

	}
	
	public static int solve1(int n, int t) {
		int i=1;
		int x=1;
		for (;i<=n;) {
			int y=x;
			for (int j=1;j<=t && i<=n;j++) {
				x=x*2;
				i++;
			}
			
			if (i<=n) {
				x=x-y;
			}
		}
		
		System.out.println(x);
		return x;
	}
	
	public static int solve(int n, int t) {
		int i=1;
		int x=1;
		int[] count = new int[t];
		for (;i<=n;i++) {
			if(i>=t) {
			   x-=count[i%t];
			}
			x=x*2;
			count[i%t]=x;			
		}
		
		System.out.println(x);
		return x;
	}

}
