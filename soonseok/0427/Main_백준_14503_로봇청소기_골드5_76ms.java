import java.io.*;
import java.util.*;

/*

입력 값에 테두리가 정해져 있어서
배열 범위 초과 조건은 안 넣어도 된다.

*/

public class Main_14503 {

	static int[] dr = { -1, 0, 1, 0 }; // 북동남서
	static int[] dc = { 0, 1, 0, -1 };

	static int N;
	static int M;

	static int[][] map;

	static int clean;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		st = new StringTokenizer(br.readLine());

		int[] start = new int[3];
		start[0] = Integer.parseInt(st.nextToken()); // r
		start[1] = Integer.parseInt(st.nextToken()); // c
		start[2] = Integer.parseInt(st.nextToken()); // direction

		for (int row = 0; row < N; row++) {

			String line = br.readLine();

			for (int col = 0, idx = 0; col < M; col++, idx += 2) {
				map[row][col] = line.charAt(idx) - '0';
			}
		}

		clean = 0;
		go(start);

		System.out.print(clean);

	} // main()

	static void go(int[] start) {

		int r = start[0]; // 시작 정보 저장
		int c = start[1];
		int d = start[2];

		int streak = 0; // 연속 회전 횟수

		while (true) {

			// 1. 현재 위치를 청소한다, 청소한 칸 카운팅, streak 초기화
			if (map[r][c] == 0) {
				map[r][c] = 2;
				clean++;
			}

			// 2-a. 현재 방향 기준으로 왼쪽으로 회전(항상 회전한다)
			//	1. 북=0 -> 서=3
			//	2. 동=1 -> 북=0
			//	3. 남=2 -> 동=1
			//	4. 서=3 -> 남=2
			boolean flag = false; // 회전시키다가 빈 칸을 발견했는가?
			while (!flag && streak < 4) {

				d = (d - 1) >= 0 ? (d - 1) : 3; // 왼쪽으로 돌려서, 양수인 경우에는 그대로 쓰고, 음수면 북->서

				int newR = r + dr[d]; // 왼쪽으로 회전한 방향으로 진행한 좌표
				int newC = c + dc[d];

				if (map[newR][newC] == 0) {
					r = newR; // 그 칸으로 전진
					c = newC;

					streak = 0; // 연속 회전 수 초기화가 가능
					flag = true;
				}
				else {
					streak++;
				}
			}

			// 종료 조건 검사 : 연속해서 2-a 를 4번 실행, (d+2)%4 = 뒤 방향
			if (streak == 4) {
				// 뒤가 벽으로 막혀있다면 종료,
				if (map[r + dr[(d + 2) % 4]][c + dc[(d + 2) % 4]] == 1)
					return;
				// 아니면 뒤로 한 칸 후진
				else {
					r = r + dr[(d + 2) % 4];
					c = c + dc[(d + 2) % 4];
					streak = 0;
				}
			}

		}

	} // go()

}
