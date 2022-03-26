import java.io.*;
import java.util.*;

public class Main_11559 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static char[][] map = new char[12][];

	static char[] temp = new char[12];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int row = 0; row < 12; row++) {
			String line = br.readLine();
			map[row] = line.toCharArray();
		}
		Arrays.fill(temp, '.');
		// - - 입력 및 초기조건 종료 - -

		// 뿌요뿌요 진행
		int puyo_cnt = 0;
		while (true) {

			boolean flag = false;

			// 전체 맵에 대하여 뿌요뿌요 터질게 있는지 체크
			for (int row = 0; row < 12; row++) {
				for (int col = 0; col < 6; col++) {

					if (map[row][col] == '.') continue; // 빈칸

					int[] cur = { row, col };

					// 해당 지점에서 뿌요가 가능하다면
					if (isPuyo(cur)) {
						doPuyo(cur);
						flag = true; // 변화가 있으므로 플래그 체크
					}
				}
			}

			// 한번 돌고나서 아무런 변화가 없다면, 종료
			if (!flag) break;

			// 변화가 있다면, 기존 뿌요들 밑으로 내려주고
			downPuyo();
			// 연쇄 뿌요 증가
			puyo_cnt++;
		}

		System.out.print(puyo_cnt);

	}

	/** 뿌요 체크 */
	public static boolean isPuyo(int[] start) {
		Queue<int[]> queue = new LinkedList<int[]>();
		int cnt = 1; // 현재 뿌요 개수 카운팅
		char target = map[start[0]][start[1]]; // 대문자를 찾을 것
		map[start[0]][start[1]] += 32; // 소문자로 바꾸면서 방문 체크. 
		queue.offer(start);

		while (!queue.isEmpty()) {

			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {

				int newR = cur[0] + dr[i];
				int newC = cur[1] + dc[i];

				if (newR < 0 || newC < 0 || newR >= 12 || newC >= 6 || map[newR][newC] != target) continue;

				map[newR][newC] += 32; // 소문자로 바꾸면서 방문체크
				queue.offer(new int[] { newR, newC }); // 큐에 추가
				cnt++; // 인접한 뿌요 개수 체크
			}

			if (cnt >= 4) return true; // 연결된 뿌요가 4개 이상일시 return
		}

		// 위에서 진행했지만, 뿌요가 될 수 없었던 경우
		target = map[start[0]][start[1]]; // 소문자를 찾을 것
		map[start[0]][start[1]] -= 32; // 대문자로 바꾸면서 방문체크
		queue.offer(start);

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {

				int newR = cur[0] + dr[i];
				int newC = cur[1] + dc[i];

				if (newR < 0 || newC < 0 || newR >= 12 || newC >= 6 || map[newR][newC] != target) continue;

				map[newR][newC] -= 32; // 대문자로 바꾸면서 방문체크
				queue.offer(new int[] { newR, newC }); // 큐에 추가
			}
		}

		return false;
	}

	/** 뿌요 실행 */
	public static void doPuyo(int[] start) {
		// 실행하는 칸의 알파벳이 대문자인지 소문자인지 체크하여 저장
		char[] target = new char[2];

		if (map[start[0]][start[1]] <= 90) {
			// 대문자
			target[0] = map[start[0]][start[1]];
			target[1] = (char) (target[0] + 32);
		}
		else {
			// 소문자
			target[0] = map[start[0]][start[1]];
			target[1] = (char) (target[0] - 32);
		}

		Queue<int[]> queue = new LinkedList<int[]>();
		map[start[0]][start[1]] = '.';
		queue.offer(start);

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int newR = cur[0] + dr[i];
				int newC = cur[1] + dc[i];

				if (newR < 0 || newC < 0 || newR >= 12 || newC >= 6) continue;

				// 대문자, 소문자중 아무거나 같은 뿌요 만나면 
				if (map[newR][newC] == target[0] || map[newR][newC] == target[1]) {
					map[newR][newC] = '.'; // 방문처리
					queue.offer(new int[] { newR, newC }); // bfs
				}
			}
		}
	}

	/** 뿌요 진행이 끝나고, 바닥으로 밀어준다 */
	public static void downPuyo() {

		// 6개의 세로줄에 대하여, 12칸의 뿌요를 모두 바닥으로 밀어준다
		for (int col = 0; col < 6; col++) {

			Arrays.fill(temp, '.');

			int idx = 11;

			for (int row = 11; row >= 0; row--) {
				// 바닥부터 문자들을 임시 배열에 순서대로 저장해준다
				if (map[row][col] == '.') continue; // 빈칸 제끼고

				temp[idx--] = map[row][col];
			}

			// 바닥에 모두 밀었다면, map에 옮겨서 저장해준다
			for (int row = 11; row >= 0; row--) {
				map[row][col] = temp[row];
			}
		}
	}

}

/*

어떻게든 visited 배열 추가로 선언 안하고 구현하려고 고민했음

*/
