package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7569_토마토_G5_박지호_748ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int M = Integer.parseInt(st.nextToken()); // 가로 칸 수
		int N = Integer.parseInt(st.nextToken()); // 세로 칸 수
		int H = Integer.parseInt(st.nextToken()); // 높이

		Queue<int[]> queue = new LinkedList<int[]>();
		int[][][] box = new int[H][N][M];
		int[] dx = { -1, 1, 0, 0, 0, 0 }; // 상 하 좌 우 위 아래
		int[] dy = { 0, 0, -1, 1, 0, 0 }; // 상 하 좌 우 위 아래
		int[] dz = { 0, 0, 0, 0, 1, -1 }; // 상 하 좌 우 위 아래

		for (int i = 0; i < H; i++) { // 배열에 토마토 담기
			for (int j = 0; j < N; j++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					int temp = Integer.parseInt(st2.nextToken());
					box[i][j][k] = temp;
					if (temp == 1) {
						queue.offer(new int[] { i, j, k }); // 큐에 익은 토마토 좌표정보 저장
					}

				}
			}
		}

		while (!queue.isEmpty()) {
			int idx = 0;
			int[] info = queue.poll();
			while (idx < 6) {
				int fx = info[2] + dx[idx];
				int fy = info[1] + dy[idx];
				int fz = info[0] + dz[idx];

				if (fx >= 0 && fy >= 0 && fz >= 0 && fx <= M - 1 && fy <= N - 1 && fz <= H - 1) {
					// 범위내
					// box, map은 H N M
					// M x : 가로 N y : 세로 H z : 높이

					if (box[fz][fy][fx] == 0) { // 인접토마토가 영향받는다면
						box[fz][fy][fx] = box[info[0]][info[1]][info[2]] + 1; // 익는 날짜를 계속 올려준다
						queue.offer(new int[] { fz, fy, fx }); // 익은토마토좌표 큐에 넣기
					}
				}
				idx++;
			} // end of while
		}

		int result = Integer.MIN_VALUE;

		ex: for (int i = 0; i < H; i++) { // 배열 출력
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (box[i][j][k] == 0) {
						result = -1;
						break ex;
					}
					result = Math.max(result, box[i][j][k]);

				}

			}
		}

		if (result == 1)
			result = 0;
		else if (result != -1)
			result = result - 1;

		System.out.println(result);

	}
}

// 배열의 사방탐색을 응용해보자
// 익음 = 1 / 안익음 = 0 / 토마토없음 = -1
// fx = x + dx 를 먼저 해놓고 fx의 범위를 검사 => 배열을 벗어나는지 확인하고 시작

// 배열로 하면 시간초과가 난다 => 큐 사용할 것
// 큐에 익은 토마토의 좌표를 3차원배열의 형태로 저장
// 큐에서 하나씩 꺼내서 상자와 비교 => 영향받은 원소들을 큐에 넣고 원래배열에 적용시킨다
// 큐의 사이즈가 0이다 => 영향받은 토마토가 없다 => 반복문종료
