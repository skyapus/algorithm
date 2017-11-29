import java.util.ArrayList;

class Rectangle {
	int x1, x2, y;
	Rectangle(int x1, int x2, int y) {
		this.x1=x1;
		this.x2=x2;
		this.y=y;
	}
}

public class Skyline {
	
	
	ArrayList<Rectangle> process(ArrayList<Rectangle> r, int l, int h) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		if(l>=h) {
		   if(l==h) {
			 result.add(r.get(l));
		   }
		   return result;
		}
		int mid=l+(h-l)/2;
		ArrayList<Rectangle> a = process(r, l, mid);
		ArrayList<Rectangle> b = process(r, mid+1, h);
		result = mergeGOODCODE(a, b);
		return result;
	}
	
	/* OK Code */
	ArrayList<Rectangle> merge0(ArrayList<Rectangle> r1, ArrayList<Rectangle> r2) {
        System.out.println(" merge #####  ################### ");				
		if(r1.size()==0) return r2;
		if(r2.size()==0) return r1;
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		
		Rectangle rr1=null;
		Rectangle rr2=null;
		for (int i=0, j=0;i<r1.size() || j<r2.size() || rr1!=null || rr2!=null ;) {

				if(rr1==null && i< r1.size()) {				    
				   rr1 = r1.get(i++);
				}
				
				if(rr2==null && j<r2.size()) {
				   rr2 = r2.get(j++);
				}   

				if (rr1 ==null ||  rr2==null) {
	                if (rr1 !=null) {
	                	result.add(rr1);
	                	rr1=null;
	                }
	                if (rr2!=null){
	                	result.add(rr2);
	                	rr2=null;
	                }
				} else {

				/*
				 * The first assumption that the rr2.x1 is always greater than or equal rr1.x1 is not correct, which I made mistake to have that assumption. 
				 * The reason is after merge, the cross point  between any two rectangles can be somewhere that is more than the original x1 from second part.
				 * therefore, when merge, the rr1 and rr2 need to be treated same way. the best is to move that to a separate routine and then call accordingly.
				 * 
				 * 
				 */
				
					Rectangle[] r_1 = {rr1};
					Rectangle[] r_2 = {rr2};
					
					if(rr1.x1<=rr2.x1) {
						mergeThat(r_1, r_2, result);
					} else {
						mergeThat(r_2, r_1, result);
					}
											
					rr1=r_1[0];
					rr2=r_2[0];
				}
					
		}
		
//		for (Rectangle s:result) {
//			System.out.println("in the result* " + s.x1 + " " +  s.x2 + " " +  s.y);
//		}
		return result;						
	}

	ArrayList<Rectangle> mergeGOODCODE(ArrayList<Rectangle> r1, ArrayList<Rectangle> r2) {				
		if(r1.size()==0) return r2;
		if(r2.size()==0) return r1;
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		
		Rectangle rr1=null;
		Rectangle rr2=null;
		int i=0, j=0;
		
		/* this logic has problem. for example, if r2.size is 1 [5,12,12], r1.size is 3 [2,3,10], [3,7,15], [7,9,10]
		 * after first round, rr2 is read as [5,12,12], and it compare with [2,3,10], then [2,3,10] is added to result. 
		 * then it supposed comeback to read another one rr1 that is [3,7,15], but unforetunely, now j =  size r2, and it will not
		 * go into the loop to merge [3,7,15] and [5,12,12]. this end up rr2 will be push to the result and the rest of rr1 list. which is not 
		 * correct 
		 * 
		 */
		
		for (;i<r1.size() && j<r2.size();) {
			
			if(rr1==null) {				    
			   rr1 = r1.get(i);
			}
			
			if(rr2==null) {
			   rr2 = r2.get(j);
			}   
			
			Rectangle[] r_1 = {rr1};
			Rectangle[] r_2 = {rr2};
			
			if(rr1.x1<=rr2.x1) {
				mergeThat(r_1, r_2, result);
			} else {
				mergeThat(r_2, r_1, result);
			}
									
			rr1=r_1[0];
			rr2=r_2[0];
			
			/*
			 * IMPORTANT, only move the pointer when the rectangle is processed and then get next one. 
			 */
			if (rr1==null) i++;
			if (rr2==null) j++;

		}
		
        if (rr1 !=null) {
	       	result.add(rr1);
	       	rr1=null;
	       	i++; // Need to bounce the pointer because this current element is just processed
	    }
        
        if (rr2!=null){
	       	result.add(rr2);
	       	rr2=null;
	       	j++; // Need to bounce the pointer because this current element is just processed
	    }
     
        // now add the rest. the one left above is the one has more elements.
	    while (i<r1.size()) {
	    	result.add(r1.get(i++));
	    }
	    
	    while (j<r2.size()) {
	    	result.add(r2.get(j++));
	    }
		

		return result;						
	}
	
	ArrayList<Rectangle> mergeBADCODE(ArrayList<Rectangle> r1, ArrayList<Rectangle> r2) {				
		if(r1.size()==0) return r2;
		if(r2.size()==0) return r1;
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		
		Rectangle rr1=null;
		Rectangle rr2=null;
		int i=0, j=0;
		
		/* this logic has problem. for example, if r2.size is 1 [5,12,12], r1.size is 3 [2,3,10], [3,7,15], [7,9,10]
		 * after first round, rr2 is read as [5,12,12], and it compare with [2,3,10], then [2,3,10] is added to result. 
		 * then it supposed comeback to read another one rr1 that is [3,7,15], but unforetunely, now j =  size r2, and it will not
		 * go into the loop to merge [3,7,15] and [5,12,12]. this end up rr2 will be push to the result and the rest of rr1 list. which is not 
		 * correct
		 * 
		 *  TO FIX THIS: You can not move the pointer unless the element has been processed completely. 
		 * 
		 */
		
		/*
		 * ##############VERY BAD CODE HERE  
		 */
				
		for (;i<r1.size() && j<r2.size();) {

				if(rr1==null) {				    
				   rr1 = r1.get(i++);
				}
				
				if(rr2==null) {
				   rr2 = r2.get(j++);
				}   
				
				Rectangle[] r_1 = {rr1};
				Rectangle[] r_2 = {rr2};
				
				if(rr1.x1<=rr2.x1) {
					mergeThat(r_1, r_2, result);
				} else {
					mergeThat(r_2, r_1, result);
				}
										
				rr1=r_1[0];
				rr2=r_2[0];
					
		}
		
        if (rr1 !=null) {
	       	result.add(rr1);
	       	rr1=null;
	    }
        
        if (rr2!=null){
	       	result.add(rr2);
	       	rr2=null;
	    }
     
        // what is the problem here ? well,  one of rr1 or rr2 
	    while (i<r1.size()) {
	    	result.add(r1.get(i++));
	    }
	    
	    while (j<r2.size()) {
	    	result.add(r2.get(j++));
	    }
		
		
		
//		for (Rectangle s:result) {
//			System.out.println("in the result* " + s.x1 + " " +  s.x2 + " " +  s.y);
//		}
		return result;						
	}	
	
	void mergeThat(Rectangle[] r1, Rectangle[] r2, ArrayList<Rectangle> result) {		
		Rectangle rr1 =r1[0];
		Rectangle rr2= r2[0];
		System.out.println("rr1* " + rr1.x1 + " " +  rr1.x2 + " " +  rr1.y);
		System.out.println("rr2* " + rr2.x1 + " " +  rr2.x2 + " " +  rr2.y);
		if(rr2.x1>rr1.x2) {
			result.add(rr1);
			rr1=null;
		} else if (rr2.x2 <= rr1.x2 && rr2.y<=rr1.y) { 
		    rr2=null;
		}
		else {
			if (rr2.x2< rr1.x2) {
				if (rr2.x1>rr1.x1) {						
					result.add(new Rectangle(rr1.x1, rr2.x1, rr1.y));
				}
				result.add(rr2);
				rr1=new Rectangle(rr2.x2, rr1.x2, rr1.y);
				rr2=null;					
				
			} else if(rr2.x2 == rr1.x2) {
				if (rr2.x1>rr1.x1) {
					result.add(new Rectangle(rr1.x1, rr2.x1, rr1.y));
				}						
				rr1=null;								
			} else {	//rr2.x2 > rr1.x2			
					if (rr2.y<rr1.y) {
						  result.add(rr1);
						  rr2=new Rectangle(rr1.x2,rr2.x2,rr2.y);
					}	  
				    else if (rr2.y==rr1.y){
					  rr2 = new Rectangle(rr1.x1,rr2.x2,rr2.y);
				    }
					else {
					  if (rr1.x1 != rr2.x1) {
					    result.add(new Rectangle(rr1.x1, rr2.x1, rr1.y));
					  }
					}
					rr1=null;
			}

		}
		
		r1[0]=rr1;
		r2[0]=rr2;

	}	
	
	
	public static void main(String[] args) {
	    int[][] a = {
//				      {2, 9, 10}, {3, 7, 15}, {5, 12, 12}};
	                  {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8} };
	    
	    
	    ArrayList<Rectangle> b = new ArrayList<Rectangle>();
	    
	    for (int i=0;i<a.length;i++) {
	    	b.add(new Rectangle(a[i][0], a[i][1],a[i][2]));
	    }
	    
	    ArrayList<Rectangle> c = new Skyline().process(b, 0, b.size()-1);
	    for (Rectangle x:c) {
	    	System.out.println (x.x1 + " " + x.x2 + " "+ x.y);
	    	
	    }
		
	}

}
