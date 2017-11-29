import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class RedundantConnection {


	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
        solve2(new int[][] { 
        	{1,2},
        	{1,3},
        	{2,3}        	
        }, 3);
        
        solve2(new int[][] { 
        	{1,2},
        	{2,3},
        	{3,4},
        	{4,1},
        	{1,5}
        }, 5);
		
	}
	
	
	public static void solve2(int[][] a, int N) {
		
		List<Integer>[] adj = (List<Integer>[]) new LinkedList[N];
		int[] parent = new int[N];
		for (int k=0;k<N;k++) {
			parent[k]=-1;
			adj[k]=new LinkedList<Integer>();
		}
		
		int parent2=-1, parent1=-1;
		int doublehead=-1;
		for(int k=0;k<a.length;k++) {
			int from = a[k][0]-1;
			int to = a[k][1]-1;
			adj[from].add(to);
			if(parent[to]!=-1) {
			   parent2=from;
			   doublehead=to;
			   parent1=parent[to];
			} else {
				parent[to] =from;
			}
		}

       List<Integer> cycles = findCycle(N, adj);
       
		//CAN THIS ALGORITHM FIND THE HEAD OF CYCLE ?
        int from =-1;
        int to=-1;
		if(cycles.size()>0) {
			
		   if (parent1==-1 || parent2==-1) {
			   to =cycles.get(0);
			   from = cycles.get(cycles.size()-1);
		   } else {
			   int prev=-1;
			   int k=0;
			   while(k<cycles.size()) {
			   //when there are two parents, need to remove the parent that is in cycle
				  int x = cycles.get(k);
				  if (x==parent1 || x==parent2) {
					  from=x;
					  to = prev;
					  break;
				  }
				  prev=x;
				  if (to==-1) {
					  to=cycles.get(cycles.size()-1);
				  }
				  k++;
				  
			   }
		   }
		} else {
			//IF THERE IS NO CYCLE, NEED TO FIND THE DOUBLE HEAD OF NODE
			//you can disconnect parent1 or parent2
			from=parent2;
			to = doublehead;			
		}
		
		System.out.println("from " + (from+1));
		System.out.println("to "+ (to+1));
				
	}
	
	public static List<Integer> findCycle(int N, List<Integer>[] adj) {
		int i=0;
		boolean[] marked = new boolean[N];
		boolean[] onstack = new boolean[N];
		int[] edgeTo=new int[N];
		
		boolean cycle=false;		
		LinkedList<Integer> cycles = new LinkedList<Integer>();
		for (i=0;i<N && !cycle;i++) {
			if (!marked[i]) {
				dfs(i, marked, onstack, edgeTo, cycles, adj);
				if (cycles.size()>0) 
					cycle=true;
			}
		}
		return cycles;
		
	}
		
	public static void dfs(int v, boolean[] marked, boolean[] onstack, int[] edgeTo, LinkedList<Integer> cycles, List<Integer>[] adj) {
		marked[v]=true;
		onstack[v]=true;
		for (int w:adj[v]) {
			
			//THIS WILL stop the chain of DFS call (for loop) in each of the path  and return all the way up, not only the current v
			if(cycles.size()>0) {
				return;
			}
			
			if((!marked[w])) {
				//this is key to track the way
				edgeTo[w]=v;
				dfs(w, marked, onstack, edgeTo, cycles, adj);
			} else if(onstack[w]) {
				//edgeTo store the parent which lead to current node
				//by build this cycle stack, the top of stack is the head of the cycle after we push the w in it. 
				//if we want to find the last section which link back to the head. that will be the v->w, now v is on the bottom of the stack.
				//one alternative is to push v at the end like the princeton code. here we did not because we want to find the double head. but we use list that will 
				// help direct access head and tail like queue.
				//so it will be like this in the list; v-2-1-0-w   (which represent v<-2<-1<-0<-w)  
				//if we push v at end, the list : v-2-1-0-w-v  (which represent v<-2<-1<-0<-w<-v)    (v->w is the last section we want, but will be easiy to get in this case)
				//so the list has child first then parent.
				
				for(int x=v;x!=w;x=edgeTo[x]) {
//					System.out.println(x);
					cycles.push(x);
				}
				cycles.push(w);
				System.out.println(w);
			}
		}
		//this will reset the recursive call chain for the current call
		onstack[v]=false;
	}
		
}
