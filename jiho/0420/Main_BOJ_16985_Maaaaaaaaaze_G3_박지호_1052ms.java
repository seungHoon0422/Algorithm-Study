package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16985_Maaaaaaaaaze_G3_박지호_1052ms {

	static final int INF = 987654321;
	static final int N = 5;
	static int MIN = INF;
	static boolean[] visitFloor = new boolean[N];
	static int[][][] map = new int[N][N][N];
	static List<Integer> floorList = new ArrayList<>();
	static int[] dz = { -1, 1, 0, 0, 0, 0 };
	static int[] dy = { 0, 0, -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 0, 0, 1, -1 };

	static class Pos {
		int z;
		int y;
		int x;
		int dist;

		Pos(int z, int y, int x, int dist) {
			this.z = z;
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("a.txt"));

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		searchMinPath(0);

		if (MIN == INF)
			System.out.println(-1);
		else
			System.out.println(MIN);
	}

	// 회전 정하기
	private static void searchMinPath(int cnt) {
		if (cnt == 5) {
			stackFloor(0);
			return;
		}
		for (int i = 0; i <= 3; i++) {
			rotate(cnt);
			searchMinPath(cnt + 1);
		}
	}

	// 쌓는 순서 정하기
	private static void stackFloor(int idx) {
		if (idx == N) {
			bfs();
			return;
		}
		for (int i = 0; i < N; i++) {
			if (visitFloor[i])
				continue;
			visitFloor[i] = true;
			floorList.add(i);
			stackFloor(idx + 1);
			floorList.remove(floorList.size() - 1);
			visitFloor[i] = false;
		}
	}

	private static void bfs() {
		int[][][] updateMap = new int[N][N][N];
		// 쌓는 순서에 따라 쌓고 bfs
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[floorList.get(i)], 0, updateMap[i], 0, map[floorList.get(i)].length);
		}

		if (updateMap[0][0][0] == 0 || updateMap[4][4][4] == 0)
			return;

		boolean[][][] visit = new boolean[N][N][N];
		visit[0][0][0] = true;
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(0, 0, 0, 0));

		while (!q.isEmpty()) {
			Pos now = q.poll();
			int z = now.z;
			int y = now.y;
			int x = now.x;
			int dist = now.dist;

			if (z == 4 && y == 4 && x == 4) {
				if (MIN > dist) {
					MIN = dist;
				}
				continue;
			}

			for (int d = 0; d < 6; d++) {
				int nz = z + dz[d];
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (nz < 0 || nz >= N || ny < 0 || ny >= N || nx < 0 || nx >= N)
					continue;
				if (visit[nz][ny][nx] || updateMap[nz][ny][nx] == 0)
					continue;
				visit[nz][ny][nx] = true;
				q.add(new Pos(nz, ny, nx, dist + 1));
			}
		}
	}

	private static void rotate(int idx) {
		int[][] temp = new int[N][N];
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				temp[y][x] = map[idx][N - x - 1][y];
			}
		}
		System.arraycopy(temp, 0, map[idx], 0, temp.length);
	}

}

// 5x5 판 5개를 쌓아서 만든 큐브형 미로
// 각 판을 시계 or 반시계로 회전시켜 조합한다 => 판 하나당 4가지 경우의 수 / 중복순열
// 판 쌓는 순서는 마음대로 => 5P5 = 5! = 120가지
// 이후 6방탐색으로 대각하단까지 이동하면 된다

// 1. 순열로 판 쌓을 순서 정한다
// 2. 각 판의 회전별 경우의 수를 조합으로 정한다
// 3. 육방탐색 진행

// 입구랑 출구가 1인지 확인하고 돌리자
