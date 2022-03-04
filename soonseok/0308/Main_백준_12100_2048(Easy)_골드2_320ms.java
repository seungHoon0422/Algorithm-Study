import java.io.*;
import java.util.*;

public class Main_12100 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N;
	static int[][] map;
	static int[][] newmap;
	static int totalmax;

	static char[] direction = { 'L', 'R', 'U', 'D' };
	static int[] selected = new int[5];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		newmap = new int[N][];

		totalmax = 0;
		for (int row = 0; row < N; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());

				totalmax = Math.max(totalmax, map[row][col]);
			}
		}

		dup_permu(0);

		System.out.print(totalmax);
	}

	public static void dup_permu(int cnt) {
		// 기저조건 : 5번 움직임 정함, 4^5=1024 가지 경우의 수
		if (cnt == 5) {

			for (int row = 0; row < N; row++) {
				newmap[row] = map[row].clone();
			}

			// 5번의 움직임
			for (int i = 0; i < 5; i++) {

				slide(newmap, selected[i]);

			}

			// totalmax 갱신

			return;
		}

		for (int i = 0; i < 4; i++) {
			selected[cnt] = i;
			dup_permu(cnt + 1);
		}
	}

	public static void slide(int[][] map, int d) {
		// d = 0123 상하좌우
		int[] res = new int[N];

		// 위로 밀기
		if (d == 0) {
			// col마다 반복
			for (int col = 0; col < N; col++) {
				Arrays.fill(res, 0);
				int idx = 0;
				boolean flag = false;

				for (int row = 0; row < N; row++) {

					// 0인 경우(다음 칸으로 넘어간다)
					if (map[row][col] == 0) continue;

					if (map[row][col] != 0) {
						// 숫자가 있지만 idx칸에 옮겨줘야할 경우
						if (!flag) { // 기존에 들어온 숫자가 없는 경우
							res[idx] = map[row][col];
							flag = true;
						}
						// 숫자가 있고, idx칸에 있는 숫자와 비교해야할 경우
						else {
							// 같다면
							if (res[idx] == map[row][col]) {
								res[idx] += map[row][col];
								idx++;
								flag = false;
							}
							// 다르면
							else {
								res[++idx] = map[row][col];
							}
						}
					}
				}
				// 한 줄 밀었으면 초기화
				for (int row = 0; row < N; row++) {
					map[row][col] = res[row];

					totalmax = Math.max(totalmax, res[row]);
				}

			}

		}
		// 아래로 밀기
		else if (d == 1) {
			// col마다 반복
			for (int col = 0; col < N; col++) {
				Arrays.fill(res, 0);
				int idx = 0;
				boolean flag = false;

				for (int row = N - 1; row >= 0; row--) {

					// 0인 경우(다음 칸으로 넘어간다)
					if (map[row][col] == 0) continue;

					if (map[row][col] != 0) {
						// 숫자가 있지만 idx칸에 옮겨줘야할 경우
						if (!flag) { // 기존에 들어온 숫자가 없는 경우
							res[idx] = map[row][col];
							flag = true;
						}
						// 숫자가 있고, idx칸에 있는 숫자와 비교해야할 경우
						else {
							// 같다면
							if (res[idx] == map[row][col]) {
								res[idx] += map[row][col];
								idx++;
								flag = false;
							}
							// 다르면
							else {
								res[++idx] = map[row][col];
							}
						}
					}
				}
				// 한 줄 밀었으면 초기화
				for (int row = N - 1, temp = 0; row >= 0; row--, temp++) {
					map[row][col] = res[temp];

					totalmax = Math.max(totalmax, res[temp]);
				}

			}

		}
		// 좌로 밀기
		else if (d == 2) {
			for (int row = 0; row < N; row++) {
				Arrays.fill(res, 0);
				int idx = 0;
				boolean flag = false;

				for (int col = 0; col < N; col++) {
					// 0인 경우(다음 칸으로 넘어간다)
					if (map[row][col] == 0) continue;

					if (map[row][col] != 0) {
						// 숫자가 있지만 idx칸에 옮겨줘야할 경우
						if (!flag) { // 기존에 들어온 숫자가 없는 경우
							res[idx] = map[row][col];
							flag = true;
						}
						// 숫자가 있고, idx칸에 있는 숫자와 비교해야할 경우
						else {
							// 같다면
							if (res[idx] == map[row][col]) {
								res[idx] += map[row][col];
								idx++;
								flag = false;
							}
							// 다르면
							else {
								res[++idx] = map[row][col];
							}
						}
					}
				}

				for (int col = 0; col < N; col++) {
					map[row][col] = res[col];

					totalmax = Math.max(totalmax, res[col]);
				}

			}
		}
		// 우로 밀기
		else {
			for (int row = 0; row < N; row++) {
				Arrays.fill(res, 0);
				int idx = 0;
				boolean flag = false;

				for (int col = N - 1; col >= 0; col--) {
					// 0인 경우(다음 칸으로 넘어간다)
					if (map[row][col] == 0) continue;

					if (map[row][col] != 0) {
						// 숫자가 있지만 idx칸에 옮겨줘야할 경우
						if (!flag) { // 기존에 들어온 숫자가 없는 경우
							res[idx] = map[row][col];
							flag = true;
						}
						// 숫자가 있고, idx칸에 있는 숫자와 비교해야할 경우
						else {
							// 같다면
							if (res[idx] == map[row][col]) {
								res[idx] += map[row][col];
								idx++;
								flag = false;
							}
							// 다르면
							else {
								res[++idx] = map[row][col];
							}
						}
					}
				}

				for (int col = N - 1, temp = 0; col >= 0; col--, temp++) {
					map[row][col] = res[temp];

					totalmax = Math.max(totalmax, res[temp]);
				}

			}
		}

	}

}
