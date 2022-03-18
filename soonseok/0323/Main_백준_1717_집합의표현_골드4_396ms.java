import java.io.*;
import java.util.*;

public class Main_1717 {

	static int N;
	static int M;

	static int[] parents;
	static Edge[] edges;

	public static class Edge {
		int a;
		int b;

		public Edge(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];

		int[] ops = new int[M];
		Edge[] edges = new Edge[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			ops[i] = Integer.parseInt(st.nextToken()); // op 저장

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(a, b);
		}

		init();

		for (int i = 0; i < M; i++) {

			switch (ops[i]) {
			case 0: // 합집합
				union(edges[i].a, edges[i].b);
				break;
			case 1: // 집합 체크
				if (union_check(edges[i].a, edges[i].b)) {
					sb.append("YES\n");
				}
				else {
					sb.append("NO\n");
				}
				break;
			default:
				break;
			}
		}

		System.out.print(sb.toString());

	}

	// 1. init
	public static void init() {
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
	}

	// 2. findSet
	public static int findSet(int a) {
		if (a == parents[a]) return a;

		// 부모가 존재하므로, 해당 부모의 조상이 있는지 체크
		// path compression도 동시에 진행한다
		return parents[a] = findSet(parents[a]);
	}

	// 3. union
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		// 같은 집합에 속해있다
		if (aRoot == bRoot) return false;

		// aRoot 밑에 bRoot 붙여준다
		parents[bRoot] = aRoot;
		return true;
	}

	// 4. union check (합집합 진행은 안하고 체크만, T,F가 union이랑 반대)
	public static boolean union_check(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot) return true;

		return false;
	}

}
