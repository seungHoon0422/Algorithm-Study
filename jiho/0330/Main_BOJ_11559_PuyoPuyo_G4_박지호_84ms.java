import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BOJ_11559_PuyoPuyo_G4_박지호_84ms {
	static char[][] pan;
	static int[] dx = { -1, 1, 0, 0 }; // 상 하 좌 우 int[x][y] 꼴로 사용
	static int[] dy = { 0, 0, -1, 1 };
	static boolean flag;
	static int result = 0;

	static class yo {
		int x;
		int y;
		char color;

		public yo(int x, int y, char color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}

		@Override
		public String toString() {
			return "yo [x=" + x + ", y=" + y + ", color=" + color + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		pan = new char[12][6];

		for (int i = 0; i < 12; i++) {
			String line = br.readLine();
			for (int j = 0; j < 6; j++) {
				pan[i][j] = line.charAt(j);
			}
		} // end of for

		while (true) {
			flag = false;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (pan[i][j] != '.') {
						bfs(pan[i][j], i, j);
					}
				}
			} // end of for

			for (int i = 0; i < 6; i++) { // drop start
				for (int j = 11; j > -1; j--) {
					if (pan[j][i] == '.') {
						for (int j2 = j - 1; j2 > -1; j2--) {
							if (pan[j2][i] != '.') {
								pan[j][i] = pan[j2][i];
								pan[j2][i] = '.';
								break;
							}
						}

					}

				}
			} // end of drop

			if (!flag) {
				break;
			} else {
				result++;
			}
		} // end of while

		System.out.println(result);
	} // end of main

	public static void bfs(char color, int i, int j) {
		boolean[][] visited = new boolean[12][6];
		Queue<yo> queue = new LinkedList<yo>();
		Queue<int[]> removable = new LinkedList<int[]>();

		queue.offer(new yo(i, j, color));
		removable.offer(new int[] { i, j });
		visited[i][j] = true;

		while (!queue.isEmpty()) {
			yo temp = queue.poll();

			int x = temp.x;
			int y = temp.y;
			for (int k = 0; k < 4; k++) {
				int fx = x + dx[k];
				int fy = y + dy[k];

				if (fx > -1 && fy > -1 && fy < 6 && fx < 12 && pan[fx][fy] == color && !visited[fx][fy]) {
					visited[fx][fy] = true;
					queue.offer(new yo(fx, fy, color));
					removable.offer(new int[] { fx, fy });

				}

			}
		}

		if (removable.size() > 3) {
			while (!removable.isEmpty()) {
				int[] temp = removable.poll();
				flag = true;
				pan[temp[0]][temp[1]] = '.';
			}
			
		}

	} // end of bfs

} // end of class

// 입력 배열을 순뿌차적으로 탐색하면서 뿌요인지 아닌지 판단
// 뿌요라면 해당 뿌요를 큐에 넣고 돌려서 4이상일 경우 지워준다
//
