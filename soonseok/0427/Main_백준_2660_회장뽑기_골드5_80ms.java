import java.io.*;
import java.util.*;

public class Main_2660 {

	static final int MAX = 100_000;

	static int N;

	static int[][] map;

	static int[] score;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];

		score = new int[N + 1];

		// 배열의 초기화되지 않은 값을 INF로 채운다
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= N; col++) {

				if (row != col) map[row][col] = MAX;

			}
		}

		StringTokenizer st;
		while (true) {

			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (a == -1 && b == -1) break; // 종료

			map[a][b] = map[b][a] = 1; // 직접 연결된 친구 ( 양방향 ) 체크

		}

		// 플로이드 경->출->도
		for (int k = 1; k <= N; k++) {

			for (int s = 1; s <= N; s++) {
				for (int e = 1; e <= N; e++) {

					if (map[s][e] > map[s][k] + map[k][e]) {
						map[s][e] = map[s][k] + map[k][e];
					}

				}
			}
		}

		// 배열 돌면서 얻은 애들의 최댓값 중에 최솟값을 저장
		int total_min = MAX;
		for (int row = 1; row <= N; row++) {
			int local_max = 0;
			for (int col = 1; col <= N; col++) {
				local_max = Math.max(map[row][col], local_max);
			}
			// local_max 값을 배열에 저장
			score[local_max]++;
			total_min = Math.min(local_max, total_min);

		}

		// 회장 후보의 점수와, 수를 출력
		sb.append(total_min).append(' ').append(score[total_min]).append('\n'); // 첫 줄

		// 회장 후보를 출력
		for (int row = 1; row <= N; row++) {
			int local_max = 0;
			for (int col = 1; col <= N; col++) {
				local_max = Math.max(map[row][col], local_max);
			}

			if (local_max == total_min) sb.append(row).append(' ');

		}

		System.out.print(sb.toString());

	}
}
