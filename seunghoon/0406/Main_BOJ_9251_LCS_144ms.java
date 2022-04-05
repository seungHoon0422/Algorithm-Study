import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



/**
 * 최장 공통 부분수열 LCS(Longest Common Subsequence) 
 * 
 * 
 * 1. 반복문으로 작성(Bottum up) => 144ms
 * 2. 재귀로 작성(Top Down) => 188ms
 * 
 *
 */
public class Main_BOJ_9251_LCS_144ms {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static String str1;
	private static String str2;
	private static Integer[][] dp;

	public static void main(String[] args) throws IOException {
		
		
		char[] str1 = in.nextLine().toCharArray();
		char[] str2 = in.nextLine().toCharArray();
		
		int length_1 = str1.length;
		int length_2 = str2.length;
		
		// 공집합 표현을 위해 인덱스가 한 줄씩 추가 됨 
		int[][] dp = new int[length_1 + 1][length_2 + 1];
		
		// 1부터 시작 (index 0 은 공집합이므로 0의 값을 갖고있음) 
		for(int i = 1; i <= length_1; i++) {
			for(int j = 1; j <= length_2; j++) {
				
				// (i-1)과 (j-1) 번째 문자가 서로 같다면  
				if(str1[i - 1] == str2[j - 1]) {
					// 대각선 위 (i-1, j-1)의 dp에 +1 한 값으로 갱신 
					dp[i][j] = dp[i - 1][j - 1] + 1;	
				}
				
				// 같지 않다면 이전 열(i-1)과 이전 행(j-1)의 값 중 큰 것으로 갱신  
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
        
		// Top-Down 때와는 다르게 dp 인덱스가 한 줄씩 추가되었으므로 -1을 하지 않음
		System.out.println(dp[length_1][length_2]);
		
//		2. 재귀로 풀이
//		str1 = br.readLine();
//		str2 = br.readLine();
//		dp = new Integer[str1.length()+1][str2.length()+1];
//		System.out.println(LCS(str1.length(),str2.length()));
		
		
	} // end of main

	private static int LCS(int i, int j) {
		if(i==0 || j==0) return 0;
		
		if(dp[i][j] == null) {
			
			dp[i][j] = 0;
			
			// 마지막 문자부터 비교?
			if(str1.charAt(i-1) == str2.charAt(j-1)) dp[i][j] = LCS(i-1, j-1) + 1;
			else dp[i][j] = Math.max(LCS(i-1,j), LCS(i, j-1));
		}
		
		
		return dp[i][j];
		
	}
	
} // end of class








