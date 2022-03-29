import java.io.*;
import java.util.*;

public class Main_1520 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N;
	static int M;

	static int[][] map;
	static int[][] dp;

	static int route;

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

		dp = new int[N][M];
		for (int row = 0; row < N; row++) {
			Arrays.fill(dp[row], -1);
		}

		// DFS
		route = dfs(0, 0);
		System.out.print(route);

//		for (int row = 0; row < N; row++) {
//			System.out.println(Arrays.toString(dp[row]));
//		}

	}

	public static int dfs(int r, int c) {
		// 기저 : 우측하단에 도착
		if (r == N - 1 && c == M - 1) {
			return 1;
		}

		// 메모이제이션 : 이미 종료지점까지의 거리를 구한 지점
		if (dp[r][c] != -1) {
			return dp[r][c];
		}

		// 현재지점에서 아직 거리를 구한적이 없으므로, 0으로 초기화
		dp[r][c] = 0;

		// 현재 방향에서 4방향 검사
		for (int i = 0; i < 4; i++) {
			int newR = r + dr[i];
			int newC = c + dc[i];

			// 예외 : 범위를 벗어나거나
			if (newR < 0 || newC < 0 || newR >= N || newC >= M) continue;
			// 예외 : 내리막이 아니거나
			if (map[newR][newC] >= map[r][c]) continue;

			// 이동하면서, 끝점까지 이동하는 거리를 누적합 시켜준다
			// (왼쪽 위에 있을수록, 이동할 수 있는 경로가 더 많아야 한다)
			dp[r][c] += dfs(newR, newC);
		}

		// 더는 진행할 수 있는 곳이 없으므로, 현재 칸에서 가능한 경로 개수를 반환
		return dp[r][c];

	} // dfs()

}

/*
매번 4방향으로의 가능성을 계산한다면, 최악의 경우 -> 4^(500*500)

dp를 이용하여 메모이제이션을 사용하자, 해당 지점에서 끝점까지 도달하는 경로의 개수를 저장

속도를 올리기 위한 방법 : 4방탐색 for문을 쪼개서, 범위검사 if문을 나누어주면 된다

*/
