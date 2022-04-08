import java.io.*;
import java.util.*;

public class Main_11053 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] num = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N];

		int max = 0;
		// dp 배열의 처음부터 끝까지 반복하며 채운다 (bottom-up)
		for (int i = 0; i < N; i++) {

			dp[i] = 1; // 기본값으로 1

			// 처음부터 i 까지 반복
			for (int j = 0; j < i; j++) {

				// 1. 이번에 체크하는 숫자가 기존 숫자보다 크면서
				// 2. 최대 수열을 갱신 가능하다면
				if (num[j] < num[i] && dp[i] < dp[j] + 1) dp[i] = 1 + dp[j];
			}
			if (max < dp[i]) max = dp[i];

		}

		System.out.print(max);

	}
}
