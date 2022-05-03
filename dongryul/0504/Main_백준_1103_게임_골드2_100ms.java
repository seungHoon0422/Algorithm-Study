import java.io.*;
import java.util.*;

public class Main_백준_1103_게임_골드2_100ms {

	// 상하좌우
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int N, M, mult, ans;
	private static boolean flag = false;
	private static char[][] map;
	private static int[][] dp;

	public static void main(String[] args) throws Exception {

		/*
		 * 왔다갔다 할 수 있는 지점이 생기면 무한. --> -1 출력 나머지는 BFS틀을 사용해서 몇 턴을 움직일 수 있는지 카운팅하자. -->
		 * 메모리 초과
		 * 
		 * 이미 한번 방문한 좌표에 대한 값을 어떻게 사용할까 메모이제이션인데 어떻게 쓸까.
		 * 
		 * 예전에 했던 DFS + DP 방식..
		 * 예전에 했던 방식도 어떻게 했는지 까먹어서 결국 검색,, 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		ans = 0;
		go(0, 0, 1, new boolean[N][M]);

		System.out.println(flag ? -1 : ans);

	} // end of main

	private static void go(int row, int col, int count, boolean[][] vis) {
		// 사이클이 형성되었으면 진행하지 마라. --> 했는데 시간이 줄진 않았다.
		if(flag) return;
		// 최대값 갱신
		if(ans<count) ans = count;
		// 메모이제이션
		dp[row][col] = count;
		// 갈 수 있는 좌표로 이동한다.
		for (int i = 0; i < 4; i++) {
			mult = map[row][col] - '0';
			int nr = row + dr[i] * mult;
			int nc = col + dc[i] * mult;
			// 배열의 범위를 벗어나거나 구멍이라면 가지마라.
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 'H') continue;
			// 이미 방문했던 자리 중 더 큰 자릿수를 가진 좌표는 거른다.
			if(dp[nr][nc]>dp[row][col]) continue;
			// 이미 방문했던 좌표라면 사이클
			if (vis[nr][nc]) {
				// 사이클이니까 -1 반환
				flag = true;
				return;
			}
			vis[nr][nc] = true;
			go(nr, nc, count+1, vis);
			vis[nr][nc] = false;
		}
		// 방문 초기화 
	} // end of go 

} // end of class
