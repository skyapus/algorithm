import java.util.Stack;

class TreeNodee {
	TreeNodee left;
	TreeNodee right;
	int count;
	int value;
	int index;
	TreeNodee(TreeNodee left, TreeNodee right, int count, int value, int index) {
		this.left=left;
		this.right=right;
		this.count=count;
		this.value=value;
		this.index=index;
	}
}

public class CountSmaller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       solve(new int[]{5, 2, 6, 1    	   
       });
       
       solveTree(new int[]{5, 2, 6, 1    	   
       });

       
	}
	
	public static void solve (int[] a) {
		
		int N= a.length;
		int[] b = new int[N];
		int[] c = new int[N];
		for(int i=0;i<N;i++) {
			b[i]=i;
		}
		
		int[] t = new int[N];
		
	    divide(a, b, c, t, 0, N-1);
	    for(int i=0;i<N;i++) {
	    	System.out.println(c[i]);
	    }
		
		
	}
	
	
	public static void  divide(int[] a, int[] b, int[] c, int[] t, int l, int h) {
		if (l>=h) return;
		int m = l+(h-l)/2;
		divide(a, b, c, t, l, m);
		divide(a, b, c, t, m+1, h);
		merge(a, b, c, t, l, m, h);		
	}
	
	
	public static void merge(int[] a, int[] b, int[] c, int[] t,  int l, int m, int h) {
		for(int i=l;i<=h;i++) {
			t[i]=b[i];
		}
		
		int k=l;
		int j=m+1;

        for (int i=l;i<=h;i++) {
			if(k>m) {
				b[i]=t[j++];
				/*
				 * incorrect to bump here
				 */
//				for(int x=l;x<=m;x++) {
//					   c[t[x]]++;	
//				}
				
			}
			else if(j>h) {
				b[i] = t[k++];				
			} else {

				if(a[b[k]] <= a[b[j]]) {
					b[i]=t[j++];
				}
				else {
					/*
					 * need to add up first before bump k
					 */
				    c[t[k]] +=h-j+1;	
					b[i]=t[k++];

				}													
			}			
		}				
	}
	

	public static void solveTree(int[] a) {
		    TreeNodee root=null;
	    	for (int i=0;i<a.length;i++) {
	    		root = insertTree(root, a[i], i);
	    	}
	    	
	        Stack<TreeNodee> s = new Stack<TreeNodee>();
	        if(root!=null) {
	        	s.push(root);
	        	while(!s.isEmpty()) {
	        		TreeNodee t=s.pop();
	        		System.out.println(t.index + " " + t.count);
	        		if(t.right!=null) {
	        			// can not update this count because it is already too late because the count on the parent may be added (therefore on the left
	        			//side of  current right node
//	        			t.right.count += t.count;
	        			s.push(t.right);	        			
	        		} 
	        		
	        		if(t.left!=null) {
	        			s.push(t.left);
	        		}
	        	}
	        }
	        
	    	
	    		    		
	}
	
	public static void progagate(TreeNodee root) {
		if (root==null) return;
		root.count++;
		progagate(root.left);
		progagate(root.right);
	}
	
	
	public static TreeNodee insertTree(TreeNodee root, int x, int index ) {
		
		if (root==null) {
			root = new TreeNodee(null, null, 0, x, index);
			return root;
		}
		
		if (x<=root.value) {
			/*
			 * however this will update the count for node that a child node is below its left tree, however, the count for those existing node on the right 
			 * is not updated, need a way to propogate the count real time, because the order and time is critical
			 */
			if(x<root.value) {
				root.count++;
				progagate(root.right);
			}
			
			root.left = insertTree(root.left, x, index);						
		} else {
			root.right = insertTree(root.right, x, index);
		}
		
		return root;
	}
	
	public static void solveSearch(int[] a) {
		int N = a.length;
		int[] order = new int[N];
		int L= 0;
		
		
		
	}

    public static void insertOrder(int[] order, int l, int h, int L, int[] a, int [] count, int j) {
    	
    	
    	
    }
	
}



