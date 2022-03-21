import java.io.*;
import java.util.*;

public class Main_9372 {

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

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			edges = new Edge[M];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				edges[i] = new Edge(a, b);
			}

			// 1. 간선 비용 오름차순 정렬 (비용 없으니 필요없다)

			// 2. 배열 초기화
			init();

			// 3. 모든 간선에 대해 진행, 국가의 개수 - 1 개가 될때까지(최소신장트리)
			int cnt = 0;
			for (Edge edge : edges) {
				// 간선 하나씩 체크
				if (union(edge.a, edge.b)) {
					cnt++; // 연결 성공시

					if (cnt == N - 1) break; // 모든 간선 연결했을 때
				}
			}
			sb.append(cnt).append('\n');

		} // end of tc for

		System.out.print(sb.toString());

	} // end of main

	// 1. 초기화
	public static void init() {
		parents = new int[N + 1]; // 각 나라의 부모 node 초기화
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	// 2. a 집합의 대표자 찾기
	public static int findSet(int a) {
		// 기저조건 : 집합의 대표자가 자신이다
		if (a == parents[a]) return a;

		// 대표자가 아니라면, 본인 부모가 대표자인지 체크한다.
		return parents[a] = findSet(parents[a]);
	}

	// 3. union
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		// 같은 집합에 속해있다면, 사이클을 생성할 수 없게 return false
		if (aRoot == bRoot) return false;

		// a 밑에 b를 붙인다
		parents[bRoot] = aRoot;
		return true;
	}

}
