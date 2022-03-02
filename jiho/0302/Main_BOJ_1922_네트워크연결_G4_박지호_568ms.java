import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1922_네트워크연결_G4_박지호_568ms {
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}

	}

	static int N;
	static int[] parents;
	static Edge[] edgeList;

	// 단위집합 생성
	public static void makeSet() {
		parents = new int[N+1];
		// 자신의 부모 노드를 자신의 값으로 세팅
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

	}

	// a의 집합 찾기 : a의 대표자 찾기
	public static int findSet(int a) {
		if (a == parents[a]) {
			return a;
		}

		return parents[a] = findSet(parents[a]); // path compression

	}

	// a, b 두 집합 합치기
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) { // 루트가 같으면 같은 집합 => union 불가
			return false;
		}

		parents[bRoot] = aRoot; // b집합의 대표자의 부모를 a집합의 대표자로
		return true;

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine()); // 노드 수
		int M = Integer.parseInt(br.readLine()); // 간선 수
		
		edgeList = new Edge[M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList); // 간선비용의 오름차순 정렬
		makeSet();

		int result = 0;
		int cnt = 0;

		for (Edge edge : edgeList) { // 간선을 하나씩 꺼내서 쓴다
			if (union(edge.from, edge.to)) {
				result += edge.weight;
				if (++cnt == N - 1) {
					break;
				}
			}
		}
		System.out.println(result);

	}
}
