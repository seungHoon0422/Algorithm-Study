import java.io.*;
import java.util.*;

/*

판 5개를 쌓는 순서 : 5!
각 판을 회전시키는 경우의 수 : 4^5
입구 위치를 고르는 경우 : 4 (8이 아닌 이유는, 판 5개가 순열로 쌓이기 때문에, 위 아래 대칭된 모양이 생기기 때문.)

-> 5! * 4^5 * 4 = 491,520

최소도착 시간은 12칸 움직였을때이다. 즉시 종료해버릴 수 있다.

 */

public class Main_16985 {

	static int[] dh = { -1, 1, 0, 0, 0, 0 }; // 위아래 상하 좌우
	static int[] dr = { 0, 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 0, -1, 1 };

	static int N = 5; // N*N*N 

	static int[] sp_r = { 0, 0, 4, 4 }; // 뽑을 수 있는 시작위치
	static int[] sp_c = { 0, 4, 0, 4 };

	static int[] panels = new int[N];
	static int[] rotates = new int[N];

	static int[][][] map = new int[N][N][N];
	static int[][][] copied = new int[N][N][N];

	static Queue<int[]> queue = new LinkedList<int[]>();

	static int min_time;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int h = 0; h < N; h++) { // 높이

			for (int row = 0; row < N; row++) { // 세로 행
				String line = br.readLine();

				for (int col = 0, idx = 0; col < N; col++, idx += 2) { // 가로 열
					map[h][row][col] = line.charAt(idx) - '0';
				}
			}
		} // 입력 종료

		min_time = Integer.MAX_VALUE;

		permutation(0, 0);

		// 값이 한번도 바뀐적이 없다면, 탈출이 불가능
		if (min_time == Integer.MAX_VALUE) {
			System.out.print(-1);
		}
		else {
			System.out.print(min_time);
		}

	} // main()

	/** 5! 가지 판을 쌓는 순서를 정하는 함수 */
	static void permutation(int cnt, int flag) {
		if (cnt == N) { // 판의 순서를 모두 정했다

			subset(0);

			return;
		}

		for (int i = 0; i < N; i++) {

			// 이미 뽑은 애는 뽑지 않는다
			if ((flag & 1 << i) != 0) continue;

			panels[cnt] = i;

			permutation(cnt + 1, flag | 1 << i);

		}

	} // escape()

	/** 4^5 가지 판을 돌리는 경우의 수 */
	static void subset(int cnt) {
		if (cnt == 5) { // 각 판의 회전 순서를 모두 정했다

			// 판을 하나씩 회전시킨 후 복사해서 임시 맵을 만든다
			for (int i = 0; i < N; i++) {
				rotate_panel(i, rotates[i]);
			}

			// 시작 위치를 정하고 BFS를 진행한다
			for (int i = 0; i < 4; i++) {
				int r = sp_r[i];
				int c = sp_c[i];

				if (copied[0][r][c] == 0) continue; // 시작 위치 막혀있으면 건너뛴다

				int local_time = bfs(new int[] { 0, r, c }); // bfs를 진행한다

				if (local_time != -1) min_time = Math.min(min_time, local_time);

				// 만약 움직임 횟수가 12라면 최솟값, 즉시 종료 가능
				if (min_time == 12) {
					System.out.print(12);
					System.exit(0);
				}
			}

			return;
		}

		for (int i = 0; i < 4; i++) { // 0, 90, 180, 270

			rotates[cnt] = i;

			subset(cnt + 1);

		}

	} // subset()

	/** 판을 회전시켜서 반영하는 메소드 */
	static void rotate_panel(int idx, int rad) {

		switch (rad) {

		case 0: // 0도 회전, 그대로 복사
			for (int row = 0; row < N; row++) {
				copied[panels[idx]][row] = map[idx][row].clone();
			}

			break;

		case 1: // 90도 회전
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					copied[panels[idx]][col][(N - 1) - row] = map[idx][row][col];
				}
			}

			break;

		case 2: // 180도 회전
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					copied[panels[idx]][(N - 1) - row][(N - 1) - col] = map[idx][row][col];
				}
			}

			break;

		case 3: // 270도 회전
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					copied[panels[idx]][(N - 1) - col][row] = map[idx][row][col];
				}
			}

			break;
		}

	} // rotate()

	static int bfs(int[] start) {

		copied[start[0]][start[1]][start[2]] = 0; // 방문 체크
		queue.offer(start);

		int time = 0; // 움직인 시간

		while (!queue.isEmpty()) {

			int size = queue.size();

			while (--size >= 0) {

				int[] cur = queue.poll();

				// 도착지에 도착한 경우 걸린 시간 반환
				if (cur[0] == N - 1 && cur[1] == (N - 1) - start[1] && cur[2] == (N - 1) - start[2]) {
					queue.clear();
					return time;
				}

				for (int i = 0; i < 6; i++) { // 위아래상하좌우
					int newH = cur[0] + dh[i];
					int newR = cur[1] + dr[i];
					int newC = cur[2] + dc[i];

					// 범위를 넘어가거나, 방문 불가능한 곳인 경우 제낀다
					if (newH < 0 || newR < 0 || newC < 0 || newH >= N || newR >= N || newC >= N
							|| copied[newH][newR][newC] == 0)
						continue;

					copied[newH][newR][newC] = 0;
					queue.offer(new int[] { newH, newR, newC });

				}

			}

			time++;

		}

		return -1; // 탈출 못하면 -1

	} // bfs()

}

/*

회전하는 패턴에 따라 중복되는 조합이 나올 수 있는데..

어떻게 처리할지는 잘 모르겠다


*/
