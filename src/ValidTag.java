import java.util.LinkedList;

public class ValidTag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s= "<DIV>This is the first line <![CDATA[<div>]]></DIV>";
        System.out.println(validTag(s));
        s = "<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>";
        System.out.println(s + " " + validTag(s));
        
        s="<A>  <B> </A>   </B>";
        System.out.println(s + " " + validTag(s));
        
        		
	}
	
	static boolean validTag(String s) {
		LinkedList<String> tagStack = new LinkedList<String>();
		
		int begin=0;
		int N = s.length();
		
		for (int i=0;i<N;) {
			//process starting tag
			if (s.substring(i).startsWith("</")) {
			  if(tagStack.size()==0) return false;				
			  i +=2;
			  begin=i;
			  System.out.println(s.substring(i));
			  while (i<N && !s.substring(i).startsWith(">") && s.charAt(i)>'A' && s.charAt(i)<'Z') {				  			  
				  i++;
			  }			  
			  if(i==N || !s.substring(i).startsWith(">")) return false;
			  String closetag=s.substring(begin, i);
			  if (closetag.length()>9 || closetag.length()<1) return false;
			  
			  System.out.println("close tag " + closetag);
			  String opentag = tagStack.pop();
			  System.out.println("open tag " + opentag);
			  if(!closetag.equals(opentag)) return false;
			  //#### REMEMBER TO BUMP THE POINTER TO MOVE PASS THE LAST IDENTIFIED CHARACTERS >
			  i++;
			  
					
			} else if (s.substring(i).startsWith("<![CDATA[")) {
				i+=9;
				begin=i;
				System.out.println(s.substring(i));
				while(i<N && !s.substring(i).startsWith("]]>")) {
					i++;
				}
				
				if(i==N) return false;

				if(tagStack.size()==0) return false;
				  //#### REMEMBER TO BUMP THE POINTER TO MOVE PASS THE LAST IDENTIFIED CHARACTERS ]]>
				i+=3;
			
				
			} else if (s.substring(i).startsWith("<")) {
				  i++;
				  begin=i;
				  System.out.println(s.substring(i));
				  while (i<N && !s.substring(i).startsWith(">") && s.charAt(i)>'A' && s.charAt(i)<'Z') {				  			  
					  i++;
				  }
				  // ### CRITICAL TO KNOW THE EXIT OF THE LOOP, IN THE ABOVE CONDITION. IT CAN EXIT THREE CONDITION. FOR EXAMPLE LOWER CASE.
				  // IF REACH END, OR LOWER CASE, FAIL TO IDENTIFY THE PATTERN 
				  if(i==N || !s.substring(i).startsWith(">")) return false;
				  String tag=s.substring(begin, i);
				  if (tag.length()>9 || tag.length()<1) return false;
			      tagStack.push(tag);
				  //#### REMEMBER TO BUMP THE POINTER TO MOVE PASS THE LAST IDENTIFIED CHARACTERS <
			      i++;
			}
			else {
//				System.out.println(" current char " + i +  " "+  s.substring(i));
//		        System.out.println("size " + tagStack.size());		
				
				// HERE MIGHT NEED TO DETECT ANY UNMACHED > BECAUSE THE PREVIOUS ONLY HANDLE MATCHED OPEN CHARACTER, BUT THERE ARE CASES UNMATCHED 
				// CHARACTERS SUCH AS <XX> >>> </XX>, WOULD THESE >>> BE VALID ONE OR ALLOWED ? IT DEPENDS ON THE REQUIREMENT. IN THIS PROBLEM, IT SEEM
				// ALLOWED
				if(tagStack.size()==0) return false;
				  //#### REMEMBER TO BUMP THE POINTER TO MOVE PASS THE LAST IDENTIFIED CHARACTERS
				i++;				
			}
				
		}
        System.out.println("size " + tagStack.size());			
		return tagStack.size()==0;	
		
	}

}
