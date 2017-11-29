import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PreorderTraversal {
	
	Queue<TreeNode> q = new LinkedList<TreeNode>();
	
	void traversal_recur(TreeNode root) {
		if (root==null) return;
		q.offer(root);
		traversal(root.left);
		traversal(root.right);		
	}
	
	void traversal(TreeNode root) {
		
		Stack<TreeNode> s = new Stack<TreeNode>();
		
		TreeNode current = root;
		
		s.push(current);
		
		/*
		 * preorder traversal, use the stack as the node that has not been processed, push the right before left. push the right and then left node will eventually
		 * clear separate the left and right tree node. 
		 */
		while(!s.isEmpty()) {			
			current=s.pop();
			q.offer(current);
			
			if(current.right!=null) {
				s.push(current.right);
			}
			
			if(current.left!=null) {
				s.push(current.left);
			}			 			
		}
		
		
	}
	
	public static void main(String[] args) {
		int[] input = {0, 1, 2, 3, 4, 5, 6};
		TreeNode r = TreeNode.createBinaryTree(input, 0, input.length-1);
		
		PreorderTraversal p1 = new PreorderTraversal();		
		p1.traversal(r);
		
		PreorderTraversal p2 = new PreorderTraversal();
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
