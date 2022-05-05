import java.io.*;
import java.util.*;

public class Main_14501 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 1];

		int[] time = new int[N + 1];
		int[] price = new int[N + 1];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}

		int max = 0;

		for (int i = 0; i < N; i++) {

			int next = i + time[i];

			if (next > N) continue; // 퇴사했다면 넘어간다

			int newPrice = dp[i] + price[i]; // 현재까지 받은 값과 이번 일을 하면 얻을 수 있는 값

			for (int j = next; j < N; j++) {
				if (dp[j] < newPrice) dp[j] = newPrice; // 이번 일을 하는게 이득이라면 진행한다
			}

			if (max < newPrice) max = newPrice; // 최대값 갱신

		}

		System.out.print(max);

	}
}
