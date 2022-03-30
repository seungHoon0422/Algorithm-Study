import java.io.*;
import java.util.*;

/** Main_백준_1520_내리막길_골드4_408ms*/
public class Main_백준_1520_내리막길_골드4_408ms {
	
	private static int max = 10001;
	private static int N, M, nr, nc;
	private static int[][] map;
	private static int[][] dp;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/*
		 * 1,1 ----> N, M까지 가는 길의 개수를 카운팅
		 * 상하좌우 4방향으로 이동 가능
		 * 좌표 이동은 다음 좌표의 값이 현재 좌표의 값보다 더 작아야 한다.
		 * 어차피 더 작은 좌표로만 이동하니 방문 체크는 따로 할 필요 없다.
		 * 
		 * BFS로 시도, 메모리 초과
		 * ++ 가지치기를 더 하던가, 다른 방식을 생각해보아야 한다.
		 * 안되는 길을 반복해서 접근 어떻게 막을까
		 * 
		 * boolean 변수를 두고, 4방이 다 불가한 지점을 max로 초기화 - 메모리 초과
		 * 
		 * DFS - 시간 초과, 가지치기를 더 하면 될 듯?
		 * 이미 탐색한 좌표 중복 탐색하지 않게 하기 어캐?
		 * 
		 * 질문 검색에서 DP 쓰는거 보고 거의 똑같이 했습니다.. 
		 * 탐색한 경로의 가능 여부를 갱신하는 방법은 생각했었는데,, DP를 못써서 못풀고 있었네요..! 
		 */
		
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+2][M+2];
		dp = new int[N+2][M+2];
		for(int i=0; i<=N+1; i++) Arrays.fill(dp[i], -1);
		
		Arrays.fill(map[0], max);
		Arrays.fill(map[N+1], max);
		for(int i=1; i<=N; i++) {
			map[i][0] = map[i][M+1] = max;
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(go(1, 1));
		
	} // end of main

	private static int go(int row, int col) {
		// 목표 지점 도착하면 경로 하나 추가
		if(row==N && col==M) return 1;
		// 이미 방문된 경로
		if(dp[row][col]!=-1) return dp[row][col];
		
		dp[row][col] = 0;
		// 상 하 좌 우 탐색
		for(int i=0; i<4; i++) {
			nr = row + dr[i];
			nc = col + dc[i];
			// 가능한 좌표면 다음 좌표로 이동
			if(map[nr][nc]<map[row][col]) 
				dp[row][col] += go(nr, nc);
		}
		// 새로운 좌표값으로 갱신
		return dp[row][col];
	}
} // end of class 
