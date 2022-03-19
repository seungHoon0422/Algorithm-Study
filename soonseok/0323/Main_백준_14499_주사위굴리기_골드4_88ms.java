import java.io.*;
import java.util.*;

public class Main_14499 {

	static int[] dr = { 0, 0, -1, 1 }; // 동서북남
	static int[] dc = { 1, -1, 0, 0 };

	static int[] dice_w = { 0, 0, 0 }; // 주사위 가로 상태
	static int[] dice_h = { 0, 0, 0, 0 }; // 주사위 세로 상태

	static int N;
	static int M;
	static int[][] map;

	static int[] cur;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		cur = new int[2];

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		cur[0] = Integer.parseInt(st.nextToken());
		cur[1] = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(st.nextToken());

		for (int row = 0; row < N; row++) {
			String line = br.readLine();
			for (int col = 0, idx = 0; col < M; col++, idx += 2) {
				map[row][col] = line.charAt(idx) - '0';
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {

			int d = Integer.parseInt(st.nextToken());

			roll(d - 1); // 1234를 0123으로 변환
		}

		System.out.print(sb.toString());

	}

	public static void roll(int direction) {
		int newR = cur[0] + dr[direction];
		int newC = cur[1] + dc[direction];

		// 예외 : 범위를 벗어난 경우, 종료
		if (newR < 0 || newC < 0 || newR >= N || newC >= M) return;

		// [1] 주사위를 해당 방향으로 굴린다
		switch (direction) {

		case 0: // 동
			int temp = dice_w[2];
			for (int i = 2; i > 0; i--) {
				dice_w[i] = dice_w[i - 1];
			}
			dice_w[0] = dice_h[3]; // 바닥이 왼쪽으로
			dice_h[3] = temp; // 돌려질쪽에 있던 값이 바닥으로
			dice_h[1] = dice_w[1]; // 각자 1 위치에 뚜껑을 보관중이다
			break;

		case 1: // 서
			temp = dice_w[0];
			for (int i = 0; i < 2; i++) {
				dice_w[i] = dice_w[i + 1];
			}
			dice_w[2] = dice_h[3]; // 바닥이 오른쪽으로
			dice_h[3] = temp; // 왼쪽이 바닥으로
			dice_h[1] = dice_w[1];
			break;

		case 2: // 북
			temp = dice_h[0];
			for (int i = 0; i < 3; i++) {
				dice_h[i] = dice_h[i + 1];
			}
			dice_h[3] = temp;
			dice_w[1] = dice_h[1];

			break;

		case 3: // 남
			temp = dice_h[3];
			for (int i = 3; i > 0; i--) {
				dice_h[i] = dice_h[i - 1];
			}
			dice_h[0] = temp;
			dice_w[1] = dice_h[1];
			break;
		default:
			break;
		}

		// [2] 주사위와 맵을 상호작용 시킨다
		// 주사위의 바닥면(dice_h[3])을 맵에 복사
		if (map[newR][newC] == 0) {
			map[newR][newC] = dice_h[3];
		}
		// 맵을 주사위의 바닥면에 복사, 맵은 0
		else {
			dice_h[3] = map[newR][newC];
			map[newR][newC] = 0;
		}

		// [3] 주사위 윗면(dice_h[1]) 을 출력
		sb.append(dice_h[1]).append('\n');

		// 이동한 위치 반영
		cur[0] = newR;
		cur[1] = newC;
	}

}
