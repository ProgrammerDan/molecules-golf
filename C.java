import java.util.*;
public class C {
	String[] x = new String[10]; 

	public static void main(String[] args) {
		new C(args[0]);
	}

	C(String c){
		Int p = new Int();
		Frame dn = don(c, p);
		for (int i=0;i<10;i++) if (x[i] != null) System.out.println(x[i] + ": " +  dn.cnt[i]);
	}

	Frame don(String c, Int p) {
		Frame f = new Frame();
		Stack s = new Stack();

		while (p.v < c.length()) {
			char q = c.charAt(p.v);
			if (q == '(') s.push(don(c, p.i()));
			else if (q == ')') return s.merge();
			else if (q >= 'A' && q <= 'Z') {
				Frame g = new Frame();
				char[] d = new char[]{c.charAt(p.v), 0};
				int l=1;
				if (c.length()-1 > p.v) {
					d[1] = c.charAt(p.v+1);
					if (d[1] >= 'a' && d[1] <= 'z') {
						l++; p.i();
					}
				}
				String h = new String(d, 0, l);
				int i=0;for(String k:x){if(k==null){x[i]=h;break;}if(k.equals(h))break;i++;}
				g.cnt[i]++;
				s.push(g);
			}
			else if (q >= '0' && q <= '9') {
				int v = c.charAt(p.v) - '0';
				while (p.v+1 < c.length()) {
					char t = c.charAt(p.v+1);
					if (t >= '0' && t <= '9') {
						v = v*10 + (t - '0'); p.i();
					} else break;
				}
				Frame z = s.q.get(s.top);
				for (int i=0;i<10;) z.cnt[i++]*=v;
			}
			p.i();
		}

		return s.merge();
	}

	class Int {
		int v=0;
		Int i() {v++; return this;}
	}

	class Frame {
		int[] cnt = new int[10];
	}

	class Stack {
		Vector<Frame> q = new Vector();
		int top = -1;
		void push(Frame p) {
			q.add(p);
			top++;
		}
		Frame merge() {
			Frame f = new Frame();
			for (Frame p : q) {
				int j=0;
				for (int i : p.cnt) f.cnt[j++]+=i;
			}
			return f;
		}
	}
}

