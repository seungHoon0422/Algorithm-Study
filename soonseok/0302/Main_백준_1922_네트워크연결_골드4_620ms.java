import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1922_네트워크연결_골드4_620ms {

	public static class Edge implements Comparable<Edge> {
		int from;
		int to;

		int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		// kruskal을 사용하기위해 가중치 오름차순 정렬
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수 N (1 ≤ N ≤ 1,000)
		int M = Integer.parseInt(br.readLine()); // 간선의 수 M (1 ≤ M ≤ 100,000)

		int[] parents = new int[N + 1];
		Edge[] edgeList = new Edge[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			// 간선 생성

			edgeList[i] = new Edge(a, b, w);
		} // 입력 종료 - - -

		// 1. 독립 집합 생성 및 간선 가중치 오름차순 정렬
		for (int i = 1; i <= N; i++) {
			parents[i] = i; // 1~N 까지의 컴퓨터의 부모는 자기 자신이다
		}
		Arrays.sort(edgeList);

		// 2. 사이클을 체크하며 간선 선택
		int cnt = 0; // V-1개의 간선 선택했는지 체크
		int result = 0; // 최소신장트리의 비용을 저장

		for (Edge edge : edgeList) {
			// 합집합에 성공했다면
			if (union(edge.from, edge.to, parents)) {
				cnt++;
				result += edge.weight;
			}
		}

		// 3. MST의 비용을 출력
		System.out.print(result);

	} // end of main

	// set의 대표자 찾기
	public static int findSet(int a, int[] parents) {
		if (parents[a] == a)
			return a; // root를 만나게 된다면 반환

		// 한칸 상위로 올라가면서 path compression 진행
		return parents[a] = findSet(parents[a], parents);
	}

	// 합집합 만들기
	public static boolean union(int a, int b, int[] parents) {
		int rootA = findSet(a, parents);
		int rootB = findSet(b, parents);

		if (rootA == rootB)
			return false; // 합집합 실패

		parents[rootB] = rootA; // b의 대표자를 a로 갱신해준다
		return true;

	}

} // end of class
