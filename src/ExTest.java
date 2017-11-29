import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Integer> label = new HashMap<String, Integer>();
		Set<String> duplicate = new HashSet<String>();
		
		int Answer1=0, Answer2=0;
		String[] lab={"B", "OB","T", "D", "S"};
		int[] factors ={50, 25, 3, 2, 1};
		for (int i=0;i<lab.length;i++) {
			label.put(lab[i], factors[i]);
		}
//		String[] S1 = {"T11", "S5", "S7", "T9", "D4"};
//		String[] S2 = {"S19", "T9", "S19", "D11", "D4"};
		
//		String[] S1 = {"S4", "D20", "T8", "S13", "OB", "D13"};
//		String[] S2 = {"OB", "D10", "S9", "S16", "D16", "T9"};
		
		String[] S1 = {"S14", "D15", "D2", "D4", "D19"};
		String[] S2 = {"S16", "B", "D17", "B", "S13"};		
		int N = S1.length;
		
		for (int i=0;i<N;i++) {
			for (int k=0;k<2;k++) {
				String input = S1[i];
				if(k==1) input =S2[i];
				int v =0;
				
			    if (!duplicate.contains(input)) {
			    	duplicate.add(input);
			    	int j=0;
			    	for (j=0;j<input.length();j++) {
			    		if(!(input.charAt(j)>='A' && input.charAt(j)<='Z')) {
			    			break;
			    		}
			    	}			    
			    
				    String head =input.substring(0,j);
				    String score = input.substring(j);
				    if (score.length()>0) {
				    	v = Integer.valueOf(score) * label.get(head);
				    } else {
				    	v = label.get(head);
				    }
			    
			    }
			    if (k==1) {
			    	Answer2 +=v;			    	
			    } else {
			    	Answer1 +=v;
			    }
			}
		}
		
		System.out.println("Answer1 " +  Answer1);
		System.out.println("Answer2 " +  Answer2);

	}

}
