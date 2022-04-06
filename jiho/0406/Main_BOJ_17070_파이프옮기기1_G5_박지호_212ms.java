package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17070_파이프옮기기1_G5_박지호_212ms {

	private static int result;
	private static int N;
	private static int[][] pan;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());

		pan = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = 0;
		dfs(1, 2, 0);
		System.out.println(result);
	}

	// 0 : 가로 1: 세로 2: 대각 방향
	public static void dfs(int r, int c, int dir) {
		if (r == N && c == N) {
			result++;
			return;
		}

		switch (dir) {
		case 0: // 가로방향에서 갈 수 있는 경우 : 동, 대각
			if (c + 1 <= N && pan[r][c + 1] == 0) { // 동쪽
				dfs(r, c + 1, 0);
			}
			break;
		case 1: // 세로방향에서 갈 수 있는 방향 : 남, 대각
			if (r + 1 <= N && pan[r + 1][c] == 0) { // 남쪽
				dfs(r + 1, c, 1);
			}
			break;
		case 2: // 대각방향에서 갈 수 있는 방향 : 동, 남, 대각
			if (c + 1 <= N && pan[r][c + 1] == 0) { // 동쪽
				dfs(r, c + 1, 0);
			}

			if (r + 1 <= N && pan[r + 1][c] == 0) { // 남쪽
				dfs(r + 1, c, 1);
			}
			break;
		}

		// 공통 부분인 대각 방향을 switch 바깥에서 검사
		if (c + 1 <= N && r + 1 <= N && pan[r][c + 1] == 0 && pan[r + 1][c] == 0 && pan[r + 1][c + 1] == 0) {
			dfs(r + 1, c + 1, 2);
		}
	}

}

// 파이프 오른쪽끝을 기준으로 계산
// DFS
