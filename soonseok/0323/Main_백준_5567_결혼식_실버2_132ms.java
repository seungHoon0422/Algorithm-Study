import java.io.*;
import java.util.*;

public class Main_5567 {

	static int N;
	static int M;

	static Vertex[] vertices;

	static boolean[] visited;

	public static class Vertex {
		int idx;
		List<Integer> adj;

		public Vertex(int idx) {
			super();
			this.idx = idx;
			adj = new ArrayList<Integer>();
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		// 1. 각 vertex 초기화, idx는 1번부터 시작		
		vertices = new Vertex[N + 1];
		for (int i = 1; i <= N; i++) {
			vertices[i] = new Vertex(i);
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			vertices[a].adj.add(b);
			vertices[b].adj.add(a);
		}

		// 2. 방문 체크 배열 초기화 및 함수 호출
		visited = new boolean[N + 1];
		System.out.print(bfs());

	}

	public static int bfs() {
		// 2. vertex를 depth 2까지 탐색하며 이웃을 체크
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		visited[1] = true;

		int step = 0;
		int cnt = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();

			while (--size >= 0) {

				int cur = queue.poll();

				for (Integer next : vertices[cur].adj) {

					if (visited[next]) continue;

					// 방문되지 않은 상태라면
					visited[next] = true;
					cnt++;
					queue.offer(next);
				}
			}

			step++;

			if (step == 2) {
				return cnt;
			}
		}

		return 0;
	}

}
