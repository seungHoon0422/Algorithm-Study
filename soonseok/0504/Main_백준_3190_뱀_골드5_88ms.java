import java.io.*;
import java.util.*;

public class Main_3190 {

	static int[] dr = { 0, 1, 0, -1 }; // 동남서북(반시계 회전 : -1, 시계 회전 : +1)
	static int[] dc = { 1, 0, -1, 0 };

	static int N;
	static int[][] map;

	static int K;
	static Deque<Node> queue;

	static int L;
	static char[] rotate;

	static class Node {

		int row;
		int col;
		int d;

		public Node(int row, int col, int d) {
			super();
			this.row = row;
			this.col = col;
			this.d = d;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 맵 초기화
		map = new int[N + 2][N + 2];

		K = Integer.parseInt(br.readLine()); // 사과 표시
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			map[r][c] = 1;
		}

		L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환
		rotate = new char[10_001];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());

			int t = Integer.parseInt(st.nextToken());
			rotate[t] = st.nextToken().charAt(0);
		}
		// 입력 종료 - - -

		System.out.print(go() + 1); // 게임 종료 시간을 출력한다 (종료 시에 실행하지 못한 + 1)

	} // main()

	/** 뱀의 실제 진행을 체크 */
	static int go() {
		queue = new LinkedList<Node>();

		map[1][1] = 2; // 뱀이 존재하는 칸 체크
		queue.add(new Node(1, 1, 0)); // 시작 위치 넣는다

		int time = 0; // 초기 시간 : 0

		// 뱀 게임 진행 시작
		while (true) {

			// 다음칸에 진행할 수 있는지 체크한다
			int curD = queue.peekFirst().d;
			int newR = queue.peekFirst().row + dr[curD];
			int newC = queue.peekFirst().col + dc[curD];

			// 벽이나 몸통을 만나면 종료한다
			if (newR < 1 || newC < 1 || newR > N || newC > N || map[newR][newC] == 2) break;

			// 다음 진행 칸이 사과가 아니면 꼬리를 잘라야한다.
			if (map[newR][newC] != 1) {
				Node last = queue.pollLast(); // 마지막 뱀 꼬리 칸
				map[last.row][last.col] = 0; // 비워준다
			}

			map[newR][newC] = 2; // 머리는 진행해준다
			queue.addFirst(new Node(newR, newC, curD));

			time++; // 시간이 증가한다

			// 이번 실행이 끝난 경우, 회전해야 한다면 회전한다.
			if (rotate[time] != '\u0000') {

				if (rotate[time] == 'L') { // L : 반시계 회전
					int next = (queue.peekFirst().d - 1) < 0 ? 3 : queue.peekFirst().d - 1;
					queue.peekFirst().d = next;
				}
				else { // D : 시계 회전
					int next = (queue.peekFirst().d + 1) % 4;
					queue.peekFirst().d = next;
				}
			}
		}

		return time;

	} // go()

}
