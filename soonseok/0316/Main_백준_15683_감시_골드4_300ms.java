import java.io.*;
import java.util.*;

public class Main_15683 {

	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };

	static int N;
	static int M;

	static int[][] map;
	static int[][] test_map;

	static int[] selected;

	static int min_blind;

	static List<Node> cctv_list = new ArrayList<Node>();

	public static class Node {
		int type; // CCTV : 1 2 3 4 5
		int row;
		int col;

		public Node(int type, int row, int col) {
			super();
			this.type = type;
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();

		N = line.charAt(0) - '0';
		M = line.charAt(2) - '0';

		map = new int[N][M];
		test_map = new int[N][M];

		for (int row = 0; row < N; row++) {
			line = br.readLine();
			for (int col = 0, idx = 0; col < M; col++, idx += 2) {
				map[row][col] = line.charAt(idx) - '0';

				if (map[row][col] == 0 || map[row][col] == 6) continue;

				cctv_list.add(new Node(map[row][col], row, col));
			}
		}
		/*
		 * 
		 * cctv 타입에 따라서, 각 방향 종류가 있고, 각 cctv에서 어떤 선택을 하느냐를 우선 정하고,
		 * 정해진 경우의수 하나마다 맵을 체크하여, 최종 사각지대를 계산하여 갱신.
		 * 
		*/

		selected = new int[cctv_list.size()];
		min_blind = N * M;

		select_dr(0);

		System.out.print(min_blind);

	}

	// cctv 종류마다 타입을 고를 것
	public static void select_dr(int cnt) {
		// 기저 조건 : cctv들의 방향을 모두 골랐다
		if (cnt == cctv_list.size()) {

			// selected에 각 cctv의 방향 고른 상황
			// 이제 map에 반영해서 체크하면 된다
			total_watch();

			int blind = 0;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					if (test_map[row][col] == 0) blind++;
				}
			}

			min_blind = Math.min(min_blind, blind);

			return;
		}

		// 이번 순서인 cctv의 종류를 파악, 방향 지정 (0123 = 상하좌우)
		switch (cctv_list.get(cnt).type) {
		case 1: // 1 : 상 / 하 / 좌 / 우 = 4
		case 3: // 3 : 좌상 / 우상 / 좌하 / 우하 = 4
		case 4: // 4 : 좌상우 / 상우하 / 좌하우 / 상좌하 = 4

			for (int i = 0; i < 4; i++) {
				selected[cnt] = i;
				select_dr(cnt + 1);
			}

			break;

		case 2: // 2 : 좌우 / 상하 = 2

			for (int i = 0; i < 2; i++) {
				selected[cnt] = i;
				select_dr(cnt + 1);
			}

			break;
		case 5: // 5 : 상하좌우 = 1
			selected[cnt] = 0;
			select_dr(cnt + 1);
			break;
		default:
			break;
		}

	} // select_dr()

	public static void total_watch() {
		for (int row = 0; row < N; row++) {
			test_map[row] = map[row].clone();
		}

		// cctv 마다 반복, 감시 완료 구역을 9로 채운다
		for (int i = 0; i < cctv_list.size(); i++) {

			Node cur = cctv_list.get(i);

			switch (cur.type) {

			case 1: // 1 : 상 / 하 / 좌 / 우 = 4

				watch(cur, selected[i]);

				break;

			case 2: // 2 : 좌우 / 상하 = 2

				if (selected[i] == 0) {
					watch(cur, 2);
					watch(cur, 3);
				}
				else {
					watch(cur, 0);
					watch(cur, 1);
				}

				break;

			case 3: // 3 : 좌상 / 우상 / 좌하 / 우하 = 4

				if (selected[i] == 0 || selected[i] == 1) {
					watch(cur, 0);

					if (selected[i] == 0) watch(cur, 2);
					else watch(cur, 3);
				}
				else {
					watch(cur, 1);

					if (selected[i] == 2) watch(cur, 2);
					else watch(cur, 3);
				}

				break;

			case 4: // 4 : 좌하우 / 좌상우 / 상하우 / 상하좌 = 4

				for (int j = 0; j < 4; j++) {
					if (selected[i] == j) continue;

					watch(cur, j);
				}

				break;

			case 5: // 5 : 상하좌우 = 1

				for (int j = 0; j < 4; j++) {
					watch(cur, j);
				}

				break;

			default:
				break;

			}

		}

	} // total_watch()

	public static void watch(Node cur, int to) { // 0 1 2 3 상하좌우
		int newR = cur.row + dr[to];
		int newC = cur.col + dc[to];

		while (newR >= 0 && newC >= 0 && newR < N && newC < M && test_map[newR][newC] != 6) {

			if (test_map[newR][newC] == 0) test_map[newR][newC] = 9;

			newR += dr[to];
			newC += dc[to];
		}

	} // watch

}
