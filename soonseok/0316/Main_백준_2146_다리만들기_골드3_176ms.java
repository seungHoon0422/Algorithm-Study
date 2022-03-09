import java.io.*;
import java.util.*;

public class Main_2146 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N;

	static int[][] map;

	static int mintime;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int row = 0; row < N; row++) {
			String line = br.readLine();
			for (int col = 0, idx = 0; col < N; col++, idx += 2) {
				map[row][col] = line.charAt(idx) - '0';
			}
		}

		int isle = 2;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (map[row][col] == 1) {
					fillisle(new int[] { row, col }, isle);
					isle++;
				}
			}
		}

		mintime = 200;
		// 섬의 번호는 2부터 시작한다.
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (map[row][col] < 0) {
					bridge(new int[] { row, col }, -1 * map[row][col]);
				}
			}
		}

		System.out.print(mintime);

	}

	public static void fillisle(int[] start, int isle) {
		Queue<int[]> queue = new LinkedList<int[]>();
		map[start[0]][start[1]] = isle;
		queue.offer(start);

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int newR = cur[0] + dr[i];
				int newC = cur[1] + dc[i];

				if (newR < 0 || newC < 0 || newR >= N || newC >= N) continue;

				if (map[newR][newC] == 0) {
					map[cur[0]][cur[1]] = -1 * isle;
					continue;
				}

				if (map[newR][newC] == 1) {
					map[newR][newC] = isle;
					queue.offer(new int[] { newR, newC });
				}

			}
		}

	} // fillisle()

	public static void bridge(int[] start, int isle) {
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][N];
		visited[start[0]][start[1]] = true;
		queue.offer(start);

		int time = 0;
		while (!queue.isEmpty()) {

			int size = queue.size();

			while (--size >= 0) {
				int[] cur = queue.poll();

				for (int i = 0; i < 4; i++) {
					int newR = cur[0] + dr[i];
					int newC = cur[1] + dc[i];

					if (newR < 0 || newC < 0 || newR >= N || newC >= N || visited[newR][newC] || map[newR][newC] == isle
							|| map[newR][newC] == -1 * isle)
						continue;

					if (map[newR][newC] != 0) {
						mintime = Math.min(mintime, time);
						return;
					}

					visited[newR][newC] = true;
					queue.offer(new int[] { newR, newC });
				}
			}

			time++;

			if (time >= mintime) return;
		}
	}

}

/*
 * 시간초과가 나온다면, 알고리즘 자체가 잘못된 경우가 있고(복잡도 고려하여 다른 알고리즘으로 교체)
 * 혹은 방문체크등의 사소한 처리를 하지 않아서 무한루프에 빠지는 경우가 있다.
 */
