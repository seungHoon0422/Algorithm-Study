import java.io.*;
import java.util.*;

public class Main_백준_14501_15486_퇴사_퇴사2_실버3_1_76ms {
	
	public static void main(String[] args) throws Exception {
		
		/*
		 * 앞에서 진행하는거보다 뒤에서 진행하는게 더 쉽다.
		 * 모든 자리에서 시작해서 다음 자리로 가서.. 최대를 계속 갱신하는? 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+2];
		int[][] interview = new int[N+2][2];
		
		StringTokenizer st = null;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			interview[i][0] = Integer.parseInt(st.nextToken());
			interview[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int curr = 0;
		for(int i=N; i>0; i--) {
			curr = i + interview[i][0];
			// 퇴사 이후 종료되는 일정이면, 이전에 연산한 값 따라가기
			if(curr>N+1) dp[i] = dp[i+1];
			// 이번 일정을 하는 것과 이전 것 중 더 큰 값을 따라감.
			else dp[i] = Math.max(dp[i+1], dp[curr]+interview[i][1]);
		}
		
		System.out.println(dp[1]);
		
	} // end of main 
	
} // end of class 
