import java.io.*;
import java.util.*;

public class Main_5427 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N;
	static int M;

	static char[][] map;

	static StringBuilder sb = new StringBuilder();
	static Queue<int[]> queue = new LinkedList<int[]>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			map = new char[N + 2][M + 2];

			for (int row = 1; row <= N; row++) {
				String line = br.readLine();
				for (int col = 1; col <= M; col++) {
					map[row][col] = line.charAt(col - 1);

					if (map[row][col] == '@') queue.offer(new int[] { row, col });
				}
			}

			for (int row = 1; row <= N; row++) {
				for (int col = 1; col <= M; col++) {
					if (map[row][col] == '*') queue.offer(new int[] { row, col });
				}
			}

			BFS();

		}

		System.out.print(sb.toString());

	}

	public static void BFS() {

		int time = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			while (--size >= 0) {
				int[] cur = queue.poll();

				for (int i = 0; i < 4; i++) {
					int newR = cur[0] + dr[i];
					int newC = cur[1] + dc[i];

					if (map[cur[0]][cur[1]] == '@') {

						if (map[newR][newC] == '\u0000') {
							sb.append(++time).append('\n');
							queue.clear();
							return;
						}

						if (map[newR][newC] == '.') {
							map[cur[0]][cur[1]] = '@';
							map[newR][newC] = '@';
							queue.offer(new int[] { newR, newC });
							continue;
						}
					}
					else {

						if (map[newR][newC] == '.' || map[newR][newC] == '@') {
							map[newR][newC] = '*';
							queue.offer(new int[] { newR, newC });
							continue;
						}

					}

				}

			}

			time++;
		}

		sb.append("IMPOSSIBLE\n");

	}

}
