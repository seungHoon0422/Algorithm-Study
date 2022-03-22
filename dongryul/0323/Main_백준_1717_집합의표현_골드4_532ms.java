import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** Main_백준_1717_집합의표현_골드4_532ms*/
public class Main_백준_1717_집합의표현_골드4_532ms {
	
	private static int N;
	private static int M;
	private static int[] p;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// make set
		p = new int[N+1];
		for(int i=1; i<=N; i++) p[i] = i;
		
		int oper = 0;
		int a = 0;
		int b = 0;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			oper = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			// union
			if(oper==0) {
				union(a, b);
			} 
			// find
			else {
				sb.append(sameSet(a, b)).append("\n");
			}
		}
		System.out.println(sb.toString());
		
	} // end of main

	private static int findset(int a) {
		if(p[a]==a) return a;
		return p[a] = findset(p[a]);
	} // end of find set

	private static void union(int a, int b) {
		int ar = findset(a);
		int br = findset(b);
		if(ar==br) return;
		p[br] = ar;
	} // end of union
	
	private static String sameSet(int a, int b) {
		int ar = findset(a);
		int br = findset(b);
		if(ar==br) return "YES";
		return "NO";
	} // end of same set
	
} // end of class 
