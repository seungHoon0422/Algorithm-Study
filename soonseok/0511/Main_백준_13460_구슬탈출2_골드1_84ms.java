import java.io.*;
import java.util.*;

public class Main_13460 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N, M;

	static char[][] map;
	static boolean[][][][] visited;

	static Node start = new Node();

	static class Node {

		int rr;
		int rc;
		int br;
		int bc;

		public Node() {
		}

		public Node(int rr, int rc, int br, int bc) {
			super();
			this.rr = rr;
			this.rc = rc;
			this.br = br;
			this.bc = bc;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M][N][M];

		for (int row = 0; row < N; row++) {

			String line = br.readLine();

			for (int col = 0; col < M; col++) {

				map[row][col] = line.charAt(col);

				if (map[row][col] == 'R') {
					start.rr = row;
					start.rc = col;
					map[row][col] = '.';
				}
				else if (map[row][col] == 'B') {
					start.br = row;
					start.bc = col;
					map[row][col] = '.';
				}
			}
		} // 입력 종료 - - -
		
		System.out.print(go());
		
	} // main()

	/** 구슬의 움직임을 진행할 bfs 함수 */
	static int go() {
		Queue<Node> queue = new LinkedList<Node>(); // 빨간 구슬, 파란 구슬 같이 담겨있는 Node
		visited[start.rr][start.rc][start.br][start.bc] = true;
		queue.offer(start); // 시작 위치

		int move = 1; // 구슬이 빠질때도 1칸 움직인 것임

		while (!queue.isEmpty()) {

			if (move > 10) return -1; // 실패

			int size = queue.size();

			while (--size >= 0) {

				Node cur = queue.poll();

				for (int i = 0; i < 4; i++) {
					int newRR = cur.rr;
					int newRC = cur.rc;
					int newBR = cur.br;
					int newBC = cur.bc;

					boolean goalR = false;
					boolean goalB = false;

					// 벽이 있으므로, 범위 초과 검사 안해도 된다
					
					// 빨간 구슬 이동 ( 다음 칸이 벽일 시 멈춘다 )
					while (map[newRR + dr[i]][newRC + dc[i]] != '#') {
						newRR += dr[i]; // 다음칸이 벽이 아니니 한칸 움직인다
						newRC += dc[i];

						if (map[newRR][newRC] == 'O') { // 골인
							goalR = true;
							break;
						}
					}

					// 파란 구슬 이동 ( 다음 칸이 벽일 시 멈춘다 )
					while (map[newBR + dr[i]][newBC + dc[i]] != '#') {
						newBR += dr[i]; // 다음칸이 벽이 아니니 한칸 움직인다
						newBC += dc[i];

						if (map[newBR][newBC] == 'O') { // 골인
							goalB = true;
							break;
						}
					}

					if (goalB) continue; // 파란 구슬이 들어가면 무조건 실패

					if (goalR && !goalB) return move; // 빨간 구슬만 들어갔을 때가 성공

					if (newRR == newBR && newRC == newBC) { // 둘의 이동 후 좌표 같은 경우
						switch (i) {
						case 0: // 북(원래 위치가 더 남쪽에 가까운 애가 한칸 뒤로)
							if (cur.rr < cur.br) newBR -= dr[i];
							else newRR -= dr[i];
							break;
						case 1: // 남
							if (cur.rr < cur.br) newRR -= dr[i];
							else newBR -= dr[i];
							break;
						case 2: // 서
							if (cur.rc < cur.bc) newBC -= dc[i];
							else newRC -= dc[i];
							break;
						case 3: // 동
							if (cur.rc < cur.bc) newRC -= dc[i];
							else newBC -= dc[i];
							break;
						}
					}

					if (visited[newRR][newRC][newBR][newBC]) continue; // 이미 방문한 경우 다음으로

					visited[newRR][newRC][newBR][newBC] = true; // 방문완료
					queue.offer(new Node(newRR, newRC, newBR, newBC));
				}
			}

			move++;

		}

		return -1; // 여기까지 도달했다면, 실패한것

	} // go()
}
