package Algorithm.BOJ._0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10844_쉬운계단수_S1_80ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[][] DP = new long[n + 1][10];
        for (int i = 1; i <= 9; i++) {
            DP[1][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    DP[i + 1][j + 1] = DP[i + 1][j + 1] % 1_000_000_000 + DP[i][j] % 1_000_000_000;
                } else if (j == 9) {
                    DP[i + 1][j - 1] = DP[i + 1][j - 1] % 1_000_000_000 + DP[i][j] % 1_000_000_000;
                } else {
                    DP[i + 1][j + 1] = DP[i + 1][j + 1] % 1_000_000_000 + DP[i][j] % 1_000_000_000;
                    DP[i + 1][j - 1] = DP[i + 1][j - 1] % 1_000_000_000 + DP[i][j] % 1_000_000_000;
                }
            }
        }

        long res = 0;
        for (int i = 0; i <= 9; i++) {
            res += DP[n][i];
        }
        System.out.println(res % 1_000_000_000);
    }
}
