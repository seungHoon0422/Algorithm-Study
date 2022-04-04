package DP;

import java.util.Scanner;

public class Main_BOJ_11726_2xn타일링_S3_박지호_108ms {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] memo = new int[N + 2];
		memo[1] = 1;
		memo[2] = 2;

		for (int i = 3; i <= N; i++) {
			memo[i] = (memo[i - 1] + memo[i - 2]) % 10007;

		}

		System.out.println(memo[N]);

	}
}
