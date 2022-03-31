		import java.io.BufferedReader;
		import java.io.IOException;
		import java.io.InputStreamReader;
		import java.util.StringTokenizer;
		
		public class Main_BOJ_11726_2N타일링_Silver3_84ms {
		
		
			static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			static StringTokenizer st;
			static StringBuilder sb = new StringBuilder();
			private static int N;
		
			public static void main(String[] args) throws NumberFormatException, IOException {
				
				N = Integer.parseInt(br.readLine());
				
				int[] dp = new int[1_001];
				dp[0] = 0;
				dp[1] = 1;
				dp[2] = 2;
				
				for(int i=3; i<=N; i++) {
					dp[i] = (dp[i-1] + dp[i-2])%10_007;
				}
				System.out.println(dp[N]);
				
				
				
			} // end of main
		} // end of class
		
		
		
		
		
		
		
		
