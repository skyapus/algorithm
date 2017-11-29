
public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val =x;}
	
	static TreeNode createBinaryTree(int[] input, int l, int h) {
        if (l>h) return null;		
		int m = l + (h-l)/2;
		TreeNode r= new TreeNode(input[m]);
		r.left=createBinaryTree(input, l, m-1);
		r.right=createBinaryTree(input, m+1, h);
		return r;
		
	}
}