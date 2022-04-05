import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_10844_쉬운계단수_Silver1_76ms {


	private static final long mod = 1_000_000_000;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine());
		long[][] dp = new long[N + 1][10];
		
		// 첫 번째 자릿수는 오른쪽 맨 끝의 자릿수이므로 경우의 수가 1개밖에 없음 
		dp[1][0] = 0;
		for(int i = 1; i < 10; i++) {
			dp[1][i] = 1; 
		}
				
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j < 10; j++) {
				if(j == 0) dp[i][0] = dp[i - 1][1];
				else if (j == 9) dp[i][9] = dp[i - 1][8];
				else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1])%mod;
				
			}
		}
		
		long sum = 0;
		for(int i=0; i<=9; i++) sum  += dp[N][i] % mod;
		System.out.println(sum%mod);

		
	} // end of main
} // end of class








