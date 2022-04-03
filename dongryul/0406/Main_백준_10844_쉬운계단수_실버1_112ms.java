import java.util.*;
import java.io.*;

/** Main_백준_10844_쉬운계단수_실버1_112ms*/
public class Main_백준_10844_쉬운계단수_실버1_112ms {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		
		/*
		 * 이전 값이 0이면 1 하나의 가지수만 가능
		 * 이전 값이 9라면 8 하나의 가지수만 가능
		 * 나머지는 모두 각 -1, +1 의 가지수를 갖는다.
		 * 이를 통해 모든 경우를 연산하면 될 것 같다.
		 * 
		 * + 나머지 연산 들어가는 위치를 ㅋㅋㅋ 질문 검색을 통해 알았네요..
		 */
		
		// 1~9로 시작하는 모든 가지수 확인
		int[][] dp = new int[n+2][10];
		for(int i=1; i<=9; i++) dp[1][i] = 1;
		
		for(int i=2; i<=n; i++) {
			for(int j=0; j<=9; j++) {
				if(j==0) dp[i][j] = dp[i-1][1];
				else if(j==9) dp[i][j] = dp[i-1][8];
				else dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
				dp[i][j]%=1_000_000_000;
			}
		}
		
		int total = 0;
		for(int i=0; i<=9; i++) {
			total=(total+dp[n][i])%1_000_000_000;
		}
		
		System.out.println(total);
		
	} // end of main 
	
} // end of class 
