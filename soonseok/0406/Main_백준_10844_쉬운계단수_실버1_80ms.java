import java.io.*;

public class Main_10844 {

	final static long mod = 1_000_000_000;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		long[][] dp = new long[N + 1][10];

		// 첫 번째 자릿수는 오른쪽 맨 끝의 자릿수이므로 경우의 수가 1개밖에 없음 
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}

		// 두 번째 자릿수부터 N까지 탐색 
		for (int i = 2; i <= N; i++) {

			// i번째 자릿수의 자릿값들을 탐색 (0~9) 
			for (int j = 0; j <= 9; j++) {

				// i번째 자릿값이 0이라면, 경우의 수가 늘어나지 않는다. 진행 가능한 수가 하나밖에 없기 떄문
				if (j == 0) {
					dp[i][0] = dp[i - 1][1] % mod;
				}
				// i번째 자릿값이 9인 경우에도 마찬가지다.
				else if (j == 9) {
					dp[i][9] = dp[i - 1][8] % mod;
				}
				// 그 외에는 -1, +1 두 가지 경우로 분화할 수 있기 때문에 경우의 수가 늘어난다
				else {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
				}
			}
		}

		long result = 0;
		// 각 자릿값마다의 경우의 수를 모두 더해준다. 
		for (int i = 0; i <= 9; i++) {
			result += dp[N][i];
		}

		System.out.print(result % mod);
	}
}
