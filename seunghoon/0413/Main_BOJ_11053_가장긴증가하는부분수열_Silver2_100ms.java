import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11053_가장긴증가하는부분수열_Silver2_100ms {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static int[] arr;
	private static int N;
	private static int[] dp;

	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine()," ");
		
		arr = new int[N];
		dp = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		    
			// 0 ~ i 이전 원소들 탐색
			for(int j = 0; j < i; j++) {
		    
				if(arr[j] < arr[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;	
				}
			}
		}
		int max = -1;
		for(int i = 0; i < N; i++) {
			max = dp[i] > max ? dp[i] : max;
		}
		System.out.println(max);
		
		
		
	} // end of main
	
	
	
	
} // end of class








