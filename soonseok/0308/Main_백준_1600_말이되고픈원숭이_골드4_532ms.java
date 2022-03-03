import java.io.*;
import java.util.*;

public class Main_1600 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[] jr = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] jc = { -1, 1, -2, 2, -2, 2, -1, 1 };

	static int K;
	static int N;
	static int M;

	static int[][] map;

	public static class Node {
		int row;
		int col;
		int jump;

		public Node(int row, int col, int jump) {
			super();
			this.row = row;
			this.col = col;
			this.jump = jump;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int row = 0; row < N; row++) {
			String line = br.readLine();
			for (int col = 0, idx = 0; col < M; col++, idx += 2) {
				map[row][col] = line.charAt(idx) - '0';
			}
		}

		monkeyBFS(new Node(0, 0, 0));

	}

	public static void monkeyBFS(Node start) {
		Queue<Node> queue = new LinkedList<Node>();
		boolean[][][] visited = new boolean[N][M][K + 1]; // 말의 움직임을 소모한 상태에 대한 방문 체크

		queue.offer(start);
		for (int i = 0; i < K + 1; i++)
			visited[0][0][i] = true;

		int move = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			while (--size >= 0) {
				Node cur = queue.poll();

				if (cur.row == N - 1 && cur.col == M - 1) {
					System.out.print(move);
					return;
				}

				for (int i = 0; i < 4; i++) {
					int newR = cur.row + dr[i];
					int newC = cur.col + dc[i];

					if (newR < 0 || newC < 0 || newR >= N || newC >= M || map[newR][newC] == 1) continue;

					if (visited[newR][newC][cur.jump]) continue;

					visited[newR][newC][cur.jump] = true;
					queue.offer(new Node(newR, newC, cur.jump));
				}

				if (cur.jump >= K) continue; // 점프 횟수가 남아있지 않다면 진행 불가

				for (int i = 0; i < 8; i++) {
					int newR = cur.row + jr[i];
					int newC = cur.col + jc[i];

					if (newR < 0 || newC < 0 || newR >= N || newC >= M || map[newR][newC] == 1) continue;

					if (visited[newR][newC][cur.jump + 1]) continue;

					visited[newR][newC][cur.jump + 1] = true;
					queue.offer(new Node(newR, newC, cur.jump + 1));
				}

			}

			move++;

		}

		System.out.print(-1);

	}

}
