import java.util.*;
public class Chemical {
	String[] x;

	public static void main(String[] args) {
		(new Chemical()).begin();
	}

	Chemical() {}

	void begin() {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()) {
			String chem = sc.nextLine();
			if (chem.charAt(0) == '>') {
				x = new String[10]; 
				Int p = new Int();
				p.v = 2;
				Frame dn = don(chem, p);
				dn.print();
			} else {
				System.out.println("invalid");
			}
		}
	}

	int idx(String chem) {
		int i=0;for(String k:x){if(k==null)break;if(k.equals(chem))return i;i++;}
		x[i]=chem; return i;
	}

	Frame don(String c, Int p) {
		Frame f = new Frame();
		Stack s = new Stack();

		while (p.v < c.length()) {
			char q = c.charAt(p.v);
			if (q == '(') s.push(don(c, p.i()));
			else if (q == ')') return s.merge();
			else if (q >= 'A' && q <= 'Z') s.push(resolve(c, p));
			else if (q >= '0' && q <= '9') s.push(s.pop().multiple(count(c, p)));
			p.i();
		}

		return s.merge();
	}

	Frame resolve(String c, Int p) {
		//p("resolve");
		Frame f = new Frame();
		char[] d = new char[]{c.charAt(p.v), 0};
		int l=1;
		if (c.length()-1 > p.v) {
			d[1] = c.charAt(p.v+1);
			if (d[1] >= 'a' && d[1] <= 'z') {
				l++; p.i();
			}
		}
		
		f.cnt[idx(new String(d, 0, l))]++;
		//f.print();
		return f;
	}

	int count(String c, Int p) {
		//p("count");
		int v = c.charAt(p.v) - '0';
		while (p.v+1 < c.length()) {
			char q = c.charAt(p.v+1);
			if (q >= '0' && q <= '9') {
				v = v*10 + (q - '0'); p.i();
			} else break;
		}
		//p("    " + v);
		return v;
	}

	void p(String m) {
		System.out.println(m);
	}

	class Int {
		int v=0;
		Int i() {v++; return this;}
	}

	class Frame {
		int[] cnt = new int[10];

		Frame merge(Frame f) {
			int j=0;
			for (int i : f.cnt) cnt[j++]+=i;
			return this;
		}

		Frame multiple(int m) {
			for (int i=0;i<cnt.length;) {
				cnt[i++]*=m;
			}
			return this;
		}

		void print() {
			for (int i=0;i<cnt.length;i++) {
				if (x[i] != null) 
					System.out.println(x[i] + ": " +  cnt[i]);
			}
		}
	}

	class Stack {
		Vector<Frame> q = new Vector();
		int top = -1;
		void push(Frame p) {
			q.add(p);
			top++;
		}
		Frame pop() {
			top--;
			return q.remove(top+1);
		}
		Frame merge() {
			Frame f = new Frame();
			for (Frame p : q) {
				f.merge(p);
			}
			return f;
		}
	}
}
