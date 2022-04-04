import java.io.*;

public class Main_17070 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		char[][] map = new char[N + 2][N + 2];

		for (int row = 1; row <= N; row++) {
			String line = br.readLine();

			for (int col = 1, idx = 0; col <= N; col++, idx += 2) {
				map[row][col] = line.charAt(idx);
			}
		} // 입력

		int[][][] dp = new int[N + 2][N + 2][3]; // 0 가로, 1대각, 2세로

		dp[1][2][0] = 1; // (row,col) 에 도달할 수 있는 경우의 수

		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= N; col++) {

				if (map[row][col] == '1') continue; // 이번 칸이 '1' 이면 건너뛴다

				// 이번 칸에 가로로 도착한 경우(이전칸에 세로로 도착했을 일은 없다)
				dp[row][col][0] += dp[row][col - 1][0] + dp[row][col - 1][1];
				// 세로로 도착(이전칸에 가로로 도착했을 일은 없다)
				dp[row][col][2] += dp[row - 1][col][1] + dp[row - 1][col][2];

				if (map[row - 1][col] == '1' || map[row][col - 1] == '1') continue; // 벽 있으면 대각선 파이프 못둔다
				// 대각으로 도착(이전칸에 모든 경우가 가능하다)
				dp[row][col][1] += dp[row - 1][col - 1][0] + dp[row - 1][col - 1][1] + dp[row - 1][col - 1][2];

			}
		} // dp for

//		for (int row = 1; row <= N; row++) {
//			for (int col = 1; col <= N; col++) {
//
//				System.out.print(dp[row][col][0] + dp[row][col][1] + dp[row][col][2] + " ");
//
//			}
//			System.out.println();
//		}

		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);

	}
}
