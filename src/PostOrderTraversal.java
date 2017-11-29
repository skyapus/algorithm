import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PostOrderTraversal {
	
	Stack<TreeNode> s=new Stack<>();
	Queue<TreeNode> q=new LinkedList<>();
	
	/*
	 * The simplest traversal is preorder iteratively because the root is processed and it does not need manage the current state up/down traversal
	 * the post order is hardest one and the inorder is in the middle
	 * 
	 * when using the stack to book keeping the unprocessed node, it needs also track if it is returned from its left child or right child
	 * if it is from left child, it need to handle right child first, if it is returned from its right child, it can process  itself. this is UP process
	 * 
	 * The DOWN process: 
	 * when handling root, first it is pushed in stack, then go to its left, mean left become current, 
	 * when returning from left, then will process right, which is also a down process. both use stacked root as pivot point to decide if it is up process 
	 * or down process, or right child, left child.
	 */
	void travel(TreeNode root) {
		
		TreeNode current = root, prev=null;
		
		while (current !=null || !s.isEmpty()) {
			
		   if(current==null) {
			   current=s.pop();
		   }	   
		
		   if (prev==null || prev.left==current || prev.right==current) {
				//we come down from left or right
				prev=current;
				if(current.left==null && prev.right==null) {
				   q.offer(current);
   				   current=null;	//set current to null indicator it need to pick up from stack			   
				} else if(current.left!=null) {
					s.push(current);
					current=current.left;
				} else {
					//push current again because it has right child
					s.push(current);
					current=current.right;
				}								
		   }

		   else if(prev==current.left) {
			   prev=current;
			   if(current.right!=null) {
				   s.push(current);
				   current=current.right;				   
			   } else {
				   q.offer(current);
   				   current=null;				   
			   }
		   }
		   else {//if(prev==current.right) {
			   q.offer(current);
			   prev=current;
   			   current=null;
		   }						
			
		}
								
	}
	
	void travel_recur(TreeNode root) {
		if (root==null) return;		
		travel_recur(root.left);
		travel_recur(root.right);
		q.offer(root);		
	}
	

		
	public static void main(String[] args) {
		int[] input = {0, 1, 2, 3, 4, 5, 6, 7};
		TreeNode r = TreeNode.createBinaryTree(input, 0, input.length-1);
		
		PostOrderTraversal p1 = new PostOrderTraversal();		
		p1.travel(r);
		
		PostOrderTraversal p2 = new PostOrderTraversal();
		p2.travel_recur(r);
		
		
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
		
//		System.out.println (p1.q);
//		System.out.println (p2.q);
	}
	

}
