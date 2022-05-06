import java.io.*;
import java.util.*;

public class Main_15486 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 2];
		int[] time = new int[N + 2];
		int[] price = new int[N + 2];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N + 1; i++) {

			if (dp[i] < dp[i - 1]) dp[i] = dp[i - 1]; // 이전 칸이 더 크면 값을 가져온다

			int next = i + time[i];

			if (N + 1 < next) continue; // 불가능한 일정인 경우

			int newPrice = dp[i] + price[i];

			if (dp[next] < newPrice) dp[next] = newPrice;

		}

		System.out.print(dp[N + 1]);

	}
}
