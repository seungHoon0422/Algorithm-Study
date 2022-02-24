import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*

BFS? DFS? 시작 지점에 따라 결과가 달라질 수 있다.

25 C 7 조합 뽑기?...


 */

public class Main_백준_1941_소문난칠공주_골드3_356ms {

	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	static char[][] map;
	static int howManyPa;
	static int selected[];
	static boolean[][] visited;
	static boolean[][] adjCheck;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[5][];
		adjCheck = new boolean[5][5];
		selected = new int[7]; // 고른 7명을 저장

		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		} // 입력 종료 - - -

		visited = new boolean[5][5];

		howManyPa = 0; // 경우의 수 초기화

		combination(0, 0, 0, 0);

		System.out.println(howManyPa);

	} // end of main

	public static void combination(int cnt, int start, int sompa, int yunpa) {
		// 기저 1 : 이다솜파가 4명을 유지하지 못하였음.
		if (yunpa > 3)
			return;

		/*
		 *
		 * 
		 * 여기서 기저조건을 걸면 틀리게된다..
		 * 
		 * 
		 * 
		 */

		// 기저 2 : 상하좌우에 인접하지 않은걸 뽑았음
//		if (cnt > 1) {
//			for (int i = 0; i < cnt - 1; i++) {
//				int newR = selected[i] / 5;
//				int newC = selected[i] % 5;
//
//				// 좌표에서 순서대로 근처 탐색해서 인접 체크
//				if (!adjBFS(new int[] { newR, newC }, cnt))
//					return;
//			}
//
//		}

		// 기저 3 : 7명을 모두 뽑았음
		if (cnt == 7) {

			int newR = selected[0] / 5;
			int newC = selected[0] % 5;

			if (!adjBFS(new int[] { newR, newC }, 7))
				return;

//			System.out.println(Arrays.toString(selected));

			howManyPa++;

			return;
		}

		for (int i = start; i < 25; i++) {
			selected[cnt] = i;

			int newR = i / 5;
			int newC = i % 5;

			// 방문하면 다른 boolean 배열에 방문한 상태를 체크해둔다
			if (map[newR][newC] == 'Y') {
				adjCheck[newR][newC] = true;
				combination(cnt + 1, i + 1, sompa, yunpa + 1);
				adjCheck[newR][newC] = false;
			} else {
				adjCheck[newR][newC] = true;
				combination(cnt + 1, i + 1, sompa + 1, yunpa);
				adjCheck[newR][newC] = false;
			}
		}
	}

	public static boolean adjBFS(int[] pos, int cnt) {
		Queue<int[]> queue = new LinkedList<int[]>();

		// 방문 배열 초기화
		for (int k = 0; k < 5; k++) {
			Arrays.fill(visited[k], false);
		}

		// 초기 값 방문처리 후 큐에 삽입
		visited[pos[0]][pos[1]] = true;
		queue.add(pos);

		int cntCheck = 1; // 맨 처음에 하나 넣고 시작하므로 총 7개의 탐색을 성공해야 한다

		while (!queue.isEmpty()) {

			int[] cur = queue.poll(); // 현재 큐 값 뽑는다

			// 4방향에 대해 탐색
			for (int i = 0; i < 4; i++) {
				int newR = cur[0] + dr[i];
				int newC = cur[1] + dc[i];

				// 범위 벗어나거나, 이미 방문했었던 곳이거나, 인접하지 않은 곳이라면 탐색이 불가능
				if (newR < 0 || newC < 0 || newR >= 5 || newC >= 5 || visited[newR][newC] || !adjCheck[newR][newC])
					continue;

				visited[newR][newC] = true;
				queue.add(new int[] { newR, newC });
				cntCheck++;
			}
		}

		if (cnt == cntCheck)
			return true;
		else
			return false;
	}

} // end of class
