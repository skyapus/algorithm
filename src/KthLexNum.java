
public class KthLexNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static int solve(int N, int k) {
		int H=0;
		int i=1;
		while (i<N) {
			H++;
			i*=10;
		}
		
		int[] num= new int[H+1];
		num[1]=1;
		int R=0;
		for (i=2;i<=H;i++) {
			num[i]=num[i-1] + num[i-1] * 10;
		}
		
		while(k>1) {
			k--;
			H--;
			int x = k % num[H];
			int y = k / num[H];
			R = R *10 + x;
			k-=y * num[H];
		}
		
		return R;
		
	}

}
