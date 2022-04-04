import java.io.*;

public class Main_9251 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();

		int[][] dp = new int[str1.length + 1][str2.length + 1];

		// bottom up으로 채워나간다. 1부터 시작하는 이유는 i-1 값과 비교해야하기 때문
		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				// 1 idx부터 쓰는데 char 배열에는 0부터 사용중이라 i-1, j-1
				// 일치하는 문자를 발견했다면, 대각선 위 값 + 1
				if (str1[i - 1] == str2[j - 1]) dp[i][j] += dp[i - 1][j - 1] + 1;
				// 일치하지 않는다면 행이나 열 중 최대값
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}

		System.out.print(dp[str1.length][str2.length]);

	} // main()
}
