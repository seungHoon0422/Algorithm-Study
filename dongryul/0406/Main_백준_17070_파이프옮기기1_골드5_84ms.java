import java.io.*;
import java.util.*;

/** Main_백준_17070_파이프옮기기1_골드5_84ms*/
public class Main_백준_17070_파이프옮기기1_골드5_84ms {
	
	private static int N;
	private static int[][] map;
	private static int[][][] DP;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		/*
		 * IDEA
		 * 
		 * 모든 좌표에서 다른 좌표로 갈 수 있는 총 3가지 방법 이 있음 
		 * 단, 90도로 꺾을 수 없음
		 *  
		 * 대각으로 이어질 땐 3가지가 다 가능
		 * 가로로 이어질 땐 대각과 가로만 가능
		 * 세로로 이어질 땐 대각과 세로만 가능
		 * 
		 * 3차원 DP 테이블? 
		 * 가로로 이어진거면 이전-가로 + 대각선-대각선의 경우의 수 
		 * 세로로 이을거면 대각선-대각선 + 이전-세로
		 * 대각선이면 이전-가로+이전-세로+이전-대각선 ( 모든 경우에 대각선으로 연결될 수 있으니까 ) 
		 * 0 가로 1 세로 2 대각 
		 * 
		 * 벽이면 그냥 못가게 하자
		 * + 이전 좌표가 1이면 갱신도 못하게 하자
		 */
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		DP = new int[3][N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(map[1][i]==1) break; 
			DP[0][1][i] = 1;
		}
		
		for(int i=2; i<=N; i++) {
			for(int j=3; j<=N; j++) {
				// 빈칸일 때만 고려
				if(map[i][j]==0) {
					if(map[i][j-1]!=1) DP[0][i][j] = DP[0][i][j-1]+DP[2][i][j-1];
					if(map[i-1][j]!=1) DP[1][i][j] = DP[1][i-1][j]+DP[2][i-1][j];
					if(map[i-1][j]!=1 && map[i][j-1]!=1 && map[i-1][j-1]!=1)
						DP[2][i][j] = DP[0][i-1][j-1] + DP[1][i-1][j-1] + DP[2][i-1][j-1];
				} 
			}
		}
		
		
		System.out.println(DP[0][N][N]+DP[1][N][N]+DP[2][N][N]);
		
	} // end of main 
} // end of class 
