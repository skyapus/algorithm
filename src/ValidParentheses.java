import java.util.Stack;

public class ValidParentheses {
	
	
	public static String valid(String s) {
		
		Stack<Integer> stk = new Stack<Integer>();
		int longest =0;
		String l="";
		int reset_point=-1;
		
		for (int i=0;i<s.length();i++) {
			char z = s.charAt(i);
			int j=0;
			if (z=='(')
				stk.push(i);
			else if(stk.size()>0) {
				j = stk.pop();
				
				if(stk.size()==0) {
					if(i-reset_point > longest) { 
				    	longest=i-reset_point;
				    	l=s.substring(reset_point+1,i+1);
				    }
				} else {
				    if(i-j+1 > longest) { 
				    	longest=i-j+1;
				    	l=s.substring(j,i+1);
				    }
				}
			    
			} else {
				//reset the new check which keep the invariant the left parentheses number is greater than right parentheses
				reset_point=i;
				
			}
				
				
		}
		
		return l;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(valid("())"));
		System.out.println(valid(")()())"));

	}

}
