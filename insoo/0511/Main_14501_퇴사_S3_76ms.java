package Algorithm.BOJ._0511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14501_퇴사_S3_76ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] days = new int[N];
        int[] costs = new int[N];
//        int[][] DP = new int[N][N];
        int[] dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            days[i] = Integer.parseInt(st.nextToken());
            costs[i] = Integer.parseInt(st.nextToken());
            // 다음 가능한 일자 업데이트
            if (i + days[i] <= N) {
                dp[i + days[i]] = Math.max(dp[i + days[i]], dp[i] + costs[i]);
            }
            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
        }


        System.out.println(dp[N]);

    }
}
