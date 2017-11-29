import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class InorderTraversal {
	
	Queue<TreeNode> q = new LinkedList<TreeNode>();
	
	
	void traversal_recur(TreeNode root) {
		if (root==null) return;		
		traversal_recur(root.left);
		q.offer(root);
		traversal_recur(root.right);
	}
	
	void traversal(TreeNode root) {
		Stack<TreeNode> s = new Stack<TreeNode>();
		/*
		 * current is the one TO BE DONE., the one on stack is the visited node (serve as marked node too like dfs 
		 * and it should never be go the "while left loop" again
		 * 
		 * This is a little bit different from post order, which there is track UP/DOWN traversal
		 */
		TreeNode current = root;
		while (current!=null || !s.isEmpty()) {
			//Going down			
			while (current!=null) {
				s.push(current);
				current=current.left;
			}
			current = s.pop();
			q.offer(current);
			// go to right
  		    current=current.right;
						
		}
		
	}
	
	public static void main(String[] args) {
		int[] input = {0, 1, 2, 3, 4, 5, 6};
		TreeNode r = TreeNode.createBinaryTree(input, 0, input.length-1);
		
		InorderTraversal p1 = new InorderTraversal();		
		p1.traversal(r);
		
		InorderTraversal p2 = new InorderTraversal();
		p2.traversal_recur(r);
		
		
		Iterator<TreeNode> a1 = p1.q.iterator();
		Iterator<TreeNode> a2 = p2.q.iterator(); 


		while (a2.hasNext()){
			TreeNode x =  a2.next();
			System.out.print(x.val);
		}

		
		System.out.println("");
		
		while (a1.hasNext()){
			TreeNode x =  a1.next();
			System.out.print(x.val);
		}
		
	}

}
