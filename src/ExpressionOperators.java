import java.util.Iterator;
import java.util.LinkedList;

public class ExpressionOperators {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			eval("123", 6);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static void eval(String n, int t) throws Exception {
		if(n.length()==0) return ;
		LinkedList<Integer> stk = new LinkedList<Integer>();
		LinkedList<Character> opk = new LinkedList<Character>();
		char[] op={'+','-','*'};
		// pay attention to the rank of operator
        stk.push(n.charAt(0)-'0');  //best way to get the real int value
//        char[] m = {n.charAt(0)};
//        String xy = String(m);
        System.out.println("what is value Integer valueOf  a char" +  Integer.valueOf(n.charAt(0)));
        System.out.println("what is value char itself within the character set 0-256  " +  n.charAt(0));
        System.out.println("what is value Integer Value of a String " +  Integer.valueOf(String.valueOf(n.charAt(0))));
        System.out.println("what int cast int to  char " + (int)(n.charAt(0)));
		evalthat(n.substring(1), op, stk, opk, t);
				
	}
	
	static void evalthat(String n, char[] op, LinkedList<Integer> stk, LinkedList<Character> opk, int t) throws Exception {
		if(n.length()==0) {
			if (cal(stk, opk, t)==t) 
				System.out.println("find one combination");
//			System.out.println("no find one combination");;
			return;
		}
			
		int z = n.charAt(0)-'0';		

		for(char o:op) {
			
			if(o=='*') {
				// since * has the precedence, therefore, they can be process first if ever found that. 
				int y =stk.pop();
				stk.push(y * z);
				evalthat(n.substring(1), op, stk, opk, t);
				stk.pop();
				//restore the state of previous before * operator
				stk.push(y);				
			} else {
				stk.push(z);				
				opk.push(o);
				evalthat(n.substring(1), op, stk, opk, t);
				opk.pop();
				stk.pop();
			}
		}

	}
	
	/*
	 * When calculate, need to iterator from first in first out because the order of operator -
	 * Secondly we can use the iterator to avoid the creation and copy of new stacks
	 */
	static int cal(LinkedList<Integer> stk, LinkedList<Character> opk, int t) throws Exception {
		
		if(stk.size()==0) {
			return Integer.MIN_VALUE;
		}
		
		for (int x: stk) {
			System.out.print(x + " ");
		}
		System.out.println();
			
		Iterator<Integer> stk_it =stk.iterator();
		Iterator<Character> opk_it =opk.iterator();
		
		int z = stk_it.next();
		while(stk_it.hasNext()) {
		    z = op(z, stk_it.next(), opk_it.next());		    
		}
		
		
		return z;
	}
	
	static int cal_copy(LinkedList<Integer> stk, LinkedList<Character> opk, int t) throws Exception {
		LinkedList<Integer> stk_b = new LinkedList<Integer>();
		LinkedList<Character> opk_b = new LinkedList<Character>();
		stk_b.addAll(stk);
		opk_b.addAll(opk);
		
		if(stk_b.size()==0) {
			return Integer.MIN_VALUE;
		}
		
		for (int x: stk) {
			System.out.print(x + " ");
		}
		System.out.println();
			
		
		int z = stk_b.removeFirst();
		while(stk_b.size()>0) {
		    z = op(z, stk_b.removeFirst(), opk_b.removeFirst());		    
		}
		
		
		return z;
	}	
	
	static int op(int a, int b, char o) throws Exception {
		int c=0;
		switch (o) {
			case '+':
				c=a+b;
				break;
			case '-':
				c=a-b;
				break;
			case '*':
				c=a*b;
				break;
			default:
				throw new Exception("invalid op");
		}
		return c;
	}
	
}
