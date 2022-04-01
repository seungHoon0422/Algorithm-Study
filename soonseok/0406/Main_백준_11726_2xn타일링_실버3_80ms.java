import java.io.*;

public class Main_11726 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if (N < 3) {
			System.out.println(N);
			return;
		}

		int[] dp = new int[N + 1];

		dp[1] = 1; // 2 x 1 타일을 채우는 경우는 1개
		dp[2] = 2; // 2 x 2 타일은 2개

		for (int i = 3; i <= N; i++) {
			// 세로로 놓은 경우에는, 크기가 2 x (i-1) 인 경우의 수와 같다
			// 가로로 놓은 경우에는, 크기가 2 x (i-2) 인 경우의 수와 같다
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
		}

		System.out.print(dp[N]);

	}
}
