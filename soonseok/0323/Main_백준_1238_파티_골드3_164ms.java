import java.io.*;
import java.util.*;

public class Main_1238 {

	static int N;
	static int M;
	static int X;

	public static class Vertex implements Comparable<Vertex> {
		int to;
		int weight;

		public Vertex(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weight - o.weight;
		}

	}

	static List<Vertex>[] list; // i번째 Vertex에 인접한 애들이 모여있는 list
	static List<Vertex>[] r_list;
	static int[] dist; // 거리를 저장
	static int[] r_dist;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // vertex 
		M = Integer.parseInt(st.nextToken()); // edge
		X = Integer.parseInt(st.nextToken()); // target

		// 1. 각 vertex가 지닌 자신에게 인접한 vertex list를 초기화
		list = new ArrayList[N + 1];
		r_list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Vertex>();
			r_list[i] = new ArrayList<Vertex>();
		}

		// 2. 각 vertex에서 vertex로의 최단 거리를 저장할 배열, max_val로 초기화
		dist = new int[N + 1];
		r_dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(r_dist, Integer.MAX_VALUE);

		// 3. Edge 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			// from 정점에서 to 정점으로 가는 가중치 저장
			list[from].add(new Vertex(to, weight));
			// to 정점에서 from정점으로 가는 가중치 저장
			r_list[to].add(new Vertex(from, weight));
		}

		// 4. dijkstra 실행
		dijkstra(list, dist);
		dijkstra(r_list, r_dist);

		// 5. 가장 오래 걸리는 학생 소요시간 출력
		int max_time = 0;
		for (int i = 1; i <= N; i++) {
			max_time = Math.max(max_time, dist[i] + r_dist[i]);
		}

		System.out.print(max_time);

	}

	public static void dijkstra(List<Vertex>[] list, int[] dist) {
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		boolean[] visited = new boolean[N + 1];

		queue.add(new Vertex(X, 0)); // X 정점에서 다른 정점까지의 최소 거리 계산
		dist[X] = 0; // 시작점의 거리는 0이다.

		while (!queue.isEmpty()) {
			Vertex cur = queue.poll();
			int curTo = cur.to;

			// 현재 정점에 이웃해있는데, 다른 정점에서 간 적이 없었다면
			if (!visited[curTo]) {
				visited[curTo] = true;

				// 이번에 방문한 정점에서(to) 새로 탐색할 수 있는 정점들 중에
				for (Vertex v : list[curTo]) {
					// 기존에 알던 거리 vs 이번 정점 탐색으로 뚫린 루트로의 거리
					if (dist[v.to] > dist[curTo] + v.weight) {
						dist[v.to] = dist[curTo] + v.weight;
						queue.add(new Vertex(v.to, dist[v.to]));
					}
				}
			}
		} // while

	}

}

/*
구할 것 : 
1) 모든 정점에서 X번 마을로 가는데 걸리는 최단 시간
	-> 그냥 dijkstra를 쓰면 X -> 다른 정점으로의 거리가 되지만
		입력 받을 때 edge 방향을 거꾸로 받는다면, X -> 다른 정점으로의 거리를 계산하면 다른정점 -> X로의 최단거리로 변환된다.


2) X번 마을에서 모든 정점으로 가는데 걸리는 최단 시간
	-> dijkstra로 해결 가능

*/
