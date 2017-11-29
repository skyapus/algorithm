import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class H2O {

	/*
	 * this solution to partition the possibility to only :
	 * case 1: (   this case will start a new nesting layer, therefore, push the current layer into the stack and get a new map
	 * case 2: Upper case this case start a new token and then will get the complete token and repeating times, currently assume each layer only get unique token 
	 *         once, however, can be easily to handle multiple occurrences
	 * case 3: )  this end a nesting layer and will handle the repeating times and merge with previous layer
	 * case 4: others current ignore and bump the pointer
	 */
	public static String solve(String formula) {
//		Map<String, Integer> tokens = new HashMap<String, Integer>();
//		LinkedList<Character> para = new LinkedList<Character>();
		int begin=0;
		
		LinkedList<Map<String, Integer>> group = new LinkedList<Map<String, Integer>>();
		Map<String, Integer> current = new HashMap<String, Integer>();
	    for (int i=0;i<formula.length();) {
	    	char z = formula.charAt(i);
	    	if(z=='(') {
//	    		para.push('(');
	    		group.push(current);
	    		current = new HashMap<String, Integer>();
	    		i++;
	    	} else if (z>='A' && z<='Z') {
	    		begin = i;
	    		i++;	    		
	    		while(i<formula.length() && formula.charAt(i)>='a' && formula.charAt(i)<='z') {
	    			i++;
	    		}
	    		String key = formula.substring(begin,i);
	    		begin=i;
	    		while(i<formula.length() && formula.charAt(i)>='0' && formula.charAt(i)<='9') {
	    			i++;
	    		}
	    		int times=1;
	    		if(i>begin) {
	    			times=Integer.valueOf(formula.substring(begin, i));
	    		}
	    		//handle multiple occurence of tokens
	    		if (current.containsKey(key)) {
	    			times+= current.get(key);
	    		}
	    		
	    		current.put(key, times);	    			    		
	    	}
	    	else if (z==')') {
	    		i++;
	    		begin=i;
	    		while(i<formula.length() && formula.charAt(i)>='0' && formula.charAt(i)<='9') {
	    			i++;
	    		}
	    		int times=1;
	    		if(i>begin) {
	    			times=Integer.valueOf(formula.substring(begin, i));
	    		}
	    		for(String k:current.keySet()) {
	    			current.put(k, current.get(k) * times);
	    		}	    		
	    		
	    		//merge 
	    		if(group.size()>0) {
	    			Map<String, Integer> m= group.pop();
	    			for (String k: current.keySet()) {
	    				if (m.containsKey(k)) {
	    					m.put(k, m.get(k) + current.get(k));	    					
	    				} else {
	    					m.put(k, current.get(k));
	    				}
	    			}
	    			current = m;
	    		}	    			    			    		
	    	} else {
	    		//invalid while the formula is not well formatted. for right now, just skip that may throw exception or return null
	    		i++;
	    	}
	    	
	    	
	    }
		
		//read the tokens
		
		for (Map.Entry<String, Integer> m:current.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
	    
		
		return ""; 
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//solve("Mg(OH)2");
		solve("K$N2O14S4");

	}
	

}
