package Algorithm.BOJ._0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main_11726_2xn타일링_S3_80ms {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        int[] dp = new int[N];
        if (N < 2) {
            System.out.println(1);
            return;
        }
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10_007;
        }

        System.out.println(dp[N - 1]);
    } // end of main

}
