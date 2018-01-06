
class Node {
	int R=27;
	Node[] next = new Node[R];
}

class Ptree {
	Node root=new Node();
	
	boolean insert(Node x, String s, int i) {
		if(i<s.length()) {
			int c = s.charAt(i)-'a';
			if (s.charAt(i)=='#') {
				c=26;
			}
			if(c<0 || c>=27) return false;
			if (x.next[c]==null)
				x.next[c] = new Node();
			x=x.next[c];
			return insert(x, s, i+1);
		}
		return true;
	}
	
	public boolean  insert(String s) {
		return insert(root, s, 0);
	}
	
	public boolean search(String s) {
		return search(root, s, 0);		
	}
	
	public boolean search (Node x, String s, int i) {
		if(i==s.length()) return true;
		int c =s.charAt(i)-'a';
		if (s.charAt(i)=='#') {
			c=26;
		}
		if(c<0 || c>=27) return false;
		if (x.next[c]==null) return false;
		x=x.next[c];
		return search (x, s, i+1);				
	}
}

public class PresuffixMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solve(new String[] {"abc", "efg"}, "abc", "abc");

	}
	
	public static void solve(String[] w, String p, String s) {
		Ptree pt = new Ptree();
		insert(w, pt);
		System.out.println(search(p, s, pt));
	}
	
	public static void  insert (String[] w, Ptree pt) {
		for (String word: w) {
			// to support empty suffix, use <= to insert a empty suffix
			for (int i=0;i<=word.length();i++) {
				boolean b =pt.insert(word.substring(i)+"#"+word);
				if (!b) {
					System.out.println(word + " has unsupported character");
				}
			}
		}				
	}
	
  public static boolean search (String p, String s, Ptree pt) {
	  return pt.search(s+"#"+p);
  }
}
