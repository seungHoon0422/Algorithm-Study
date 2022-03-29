import java.io.*;
import java.util.*;

public class Main_1520_bottomup {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N;
	static int M;

	static int[][] map;
	static int[][] visited;

	public static class Node implements Comparable<Node> {
		int row;
		int col;
		int val;

		public Node(int row, int col, int val) {
			super();
			this.row = row;
			this.col = col;
			this.val = val;
		}

		@Override
		public int compareTo(Node o) {
			return o.val - this.val; // 내림차순으로 정렬
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		// 특정 지점에 방문한 횟수를 카운트
		visited = new int[N][M];
		for (int row = 0; row < N; row++) {
			Arrays.fill(visited[row], 0);
		}

		// BFS
		bfs();
		System.out.print(visited[N - 1][M - 1]);

//		for (int row = 0; row < N; row++) {
//			System.out.println(Arrays.toString(visited[row]));
//		}

	}

	public static void bfs() {
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		visited[0][0] = 1; // 시작지점의 방문횟수 1
		queue.offer(new Node(0, 0, map[0][0]));

		while (!queue.isEmpty()) {

			Node cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int newR = cur.row + dr[i];
				int newC = cur.col + dc[i];

				// 범위 검사
				if (newR < 0 || newC < 0 || newR >= N || newC >= M) continue;
				// 내리막길 검사
				if (map[cur.row][cur.col] <= map[newR][newC]) continue;

				// 최초로 탐색하는 지점일때만, 해당 지점으로 탐색 진행하도록 한다
				// 이 조건이 없으면 똑같은 지점을 계속 탐색해야해서 시간 초과 가능성
				if (visited[newR][newC] == 0) {
					queue.offer(new Node(newR, newC, map[newR][newC]));
				}
				// 이전 지점까지의 방문 가능성을 누적합으로 더해준다
				visited[newR][newC] += visited[cur.row][cur.col];

			}

		}

	}

}

/*
Bottom up을 통해, 시작점에서 한 칸씩 종료지점까지 채워나간다

*/
