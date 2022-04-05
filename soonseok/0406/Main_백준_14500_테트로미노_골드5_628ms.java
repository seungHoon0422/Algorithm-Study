import java.io.*;
import java.util.*;

public class Main_14500 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;

	static int max;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		max = 0;

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		} // 입력 - -

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				// 모든 지점에 대하여
				// 시작점 방문
				visited[row][col] = true;

				// 해당 위치에서 테트로미노 진행
				dfs(1, map[row][col], row, col);

				// 시작점 방문 초기화
				visited[row][col] = false;

				max = Math.max(max, h(row, col));
			}
		}

		System.out.print(max);

	} // main()

	public static void dfs(int cnt, int sum, int row, int col) {
		// 4개의 위치 모두 선점
		if (cnt == 4) {

			max = Math.max(max, sum);

			return;
		}

		for (int i = 0; i < 4; i++) {
			int newR = row + dr[i];
			int newC = col + dc[i];

			if (newR < 0 || newC < 0 || newR >= N || newC >= M || visited[newR][newC]) continue;

			visited[newR][newC] = true; // 해당 위치로 진행

			dfs(cnt + 1, sum + map[newR][newC], newR, newC); // 새 위치로 진행

			visited[newR][newC] = false; // 진행 위치 초기화
		}

	} // dfs()

	public static int h(int sr, int sc) {
		int local_max = 0;
		int sum = 0;
		// ㅗ
		if (sr - 1 >= 0 && sc + 2 < M) {
			sum = map[sr][sc] + map[sr][sc + 1] + map[sr][sc + 2] + map[sr - 1][sc + 1];
			local_max = Math.max(local_max, sum);
		}
		// ㅜ
		if (sr + 1 < N && sc + 2 < M) {
			sum = map[sr][sc] + map[sr][sc + 1] + map[sr][sc + 2] + map[sr + 1][sc + 1];
			local_max = Math.max(local_max, sum);
		}
		// ㅓ
		if (sc - 1 >= 0 && sr + 2 < N) {
			sum = map[sr][sc] + map[sr + 1][sc] + map[sr + 2][sc] + map[sr + 1][sc - 1];
			local_max = Math.max(local_max, sum);
		}
		// ㅏ
		if (sc + 1 < M && sr + 2 < N) {
			sum = map[sr][sc] + map[sr + 1][sc] + map[sr + 2][sc] + map[sr + 1][sc + 1];
			local_max = Math.max(local_max, sum);
		}

		return local_max;
	} // ㅗ()
}

/*

ㅗ 모양을 제외하고 나머지 모양들은

길이 4인 DFS를 이루고있다.


*/
