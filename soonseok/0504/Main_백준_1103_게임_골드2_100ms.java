import java.io.*;
import java.util.*;

public class Main_1103 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N;
	static int M;

	static char[][] map;
	static int[][] dp;
	static boolean[][] visited;

	static int max;

	static boolean loopFlag;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];

		dp = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// 입력 종료 - - -

		max = 0;
		loopFlag = false;
		visited[0][0] = true; // 시작 위치 방문
		go(0, 0, 0); // 함수 실행

		if (loopFlag) System.out.print(-1);
		else System.out.print(max + 1); // 최댓값 + 게임이 종료될 때도 한번 움직인다

	} // main()

	static void go(int sr, int sc, int cnt) {

		if (cnt > max) max = cnt; // 최댓값 갱신

		dp[sr][sc] = cnt; // dp 기록

		int cur = map[sr][sc] - '0'; // 현재 칸의 수

		for (int i = 0; i < 4; i++) {
			int newR = sr + (dr[i] * cur); // 현재 칸의 수만큼 이동
			int newC = sc + (dc[i] * cur);

			// 범위 벗어나거나 구멍이라면 이동.
			if (newR < 0 || newC < 0 || newR >= N || newC >= M || map[newR][newC] == 'H') continue;

			// 방문을 시작한 자리로 돌아온 경우 루프 생성
			if (visited[newR][newC]) {
				loopFlag = true;
				return;
			}

			// 이미 더 큰 방문자리는 걸러준다
			if (dp[newR][newC] > dp[sr][sc]) continue;

			visited[newR][newC] = true;
			
			go(newR, newC, cnt + 1);

			visited[newR][newC] = false;

		}

	} // go()
}
