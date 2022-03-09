import java.io.*;
import java.util.*;

public class Main_14502 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N;
	static int M;

	static int[][] orig_map;
	static int[][] test_map;
	static boolean[][] visited;

	static List<Node> virus_list = new ArrayList<Node>();
	static List<Node> wall_list = new ArrayList<Node>();
	static Node[] selected = new Node[3];

	static int max_safe;

	public static class Node {
		int row;
		int col;

		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();

		N = line.charAt(0) - '0';
		M = line.charAt(2) - '0';

		orig_map = new int[N][M];
		test_map = new int[N][M];
		visited = new boolean[N][M];

		for (int row = 0; row < N; row++) {
			line = br.readLine();
			for (int col = 0, idx = 0; col < M; col++, idx += 2) {
				orig_map[row][col] = line.charAt(idx) - '0';

				if (orig_map[row][col] == 2) virus_list.add(new Node(row, col));

				else if (orig_map[row][col] == 0) wall_list.add(new Node(row, col));
			}
		}

		// 해당 과정이 끝나면, 바이러스에 대해 벽을 세울 위치 후보군이 wall_list 에 들어있다.
		// 벽을 세울 세 가지 경우의 수를 골라준다
		max_safe = 0;
		combination(0, 0);

		System.out.print(max_safe);

	} // main()

	public static void combination(int cnt, int start) {
		// 기저 조건 : 벽을 세울 세 곳을 정했을 경우
		if (cnt == 3) {

			// 해당 위치에 벽을 세우고, BFS를 돌린다.
			// 1. 맵을 복사해서 사용해야 한다.
			for (int row = 0; row < N; row++) {
				test_map[row] = orig_map[row].clone();
			}

			// 2. 선택된 위치 세 군데에 벽을 세운다.
			for (int i = 0; i < 3; i++) {
				test_map[selected[i].row][selected[i].col] = 1;
			}

			// 3. 모든 바이러스에 대하여 BFS를 돌린다.
			wall_BFS();

			int safe = 0;
			// 4. 안전구역을 세서 갱신한다.
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					if (test_map[row][col] == 0) safe++;
				}
			}

			max_safe = Math.max(max_safe, safe);

			return;
		}

		for (int i = start; i < wall_list.size(); i++) {

			selected[cnt] = wall_list.get(i);

			combination(cnt + 1, i + 1);
		}
	} // combination()

	public static void wall_BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		// 방문 체크는 해당 칸을 바이러스(2)로 바꾸면서 한다.
		// 모든 바이러스 위치를 넣는다.
		for (Node virus : virus_list) {
			queue.offer(virus);
		}

		while (!queue.isEmpty()) {
			Node cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int newR = cur.row + dr[i];
				int newC = cur.col + dc[i];

				if (newR < 0 || newC < 0 || newR >= N || newC >= M || test_map[newR][newC] != 0) continue;

				test_map[newR][newC] = 2;
				queue.offer(new Node(newR, newC));
			}
		}

	}
}

/*

3C3 ~ 10C3 범위의 조합 브루트포스 + BFS

시행착오 1) 처음에 바이러스 인접 칸만 후보에 넣는게 best일줄 알았는데 아니었음.


*/
