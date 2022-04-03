package Algorithm.BOJ._0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9251_LCS_G5_112ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();

        int N = str1.length;
        int M = str2.length;
        int[][] DP = new int[N + 1][M + 1];

//        for (int i = 0; i < N; i++) {
//            boolean isEqual = str1[i] == str2[0];
//            if (i == 0) {
//                if (isEqual) DP[0][i] += 1;
//            } else {
//                if (isEqual) DP[0][i] = DP[0][i - 1] + 1;
//                else DP[0][i] = DP[0][i - 1];
//            }
//        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                boolean isEqual = str1[i - 1] == str2[j - 1];

                if (isEqual) {
                    DP[i][j] = DP[i - 1][j - 1] + 1;
                } else {
                    DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
                }
            }
        }


//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(DP[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(DP[N][M]);
    }
}
