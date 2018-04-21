import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ArithmSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void sol(int[] a) {
		int N=a.length;
		Set<List<Integer>> tro = new HashSet<List<Integer>>();
		for (int i=1;i<N;i++) {
			for (int j=i-1;j>=0;j--) {
				int g1=a[i]-a[j];
				for(int k=j-1;k>=0;k--) {
					int g2=a[j]-a[k];
					if(g1==g2) {
						tro.add(Arrays.asList(i,j,k));
					}
				}
			}
		}
	}

}
