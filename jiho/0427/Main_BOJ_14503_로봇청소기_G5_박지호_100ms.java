import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14503_로봇청소기_G5_박지호_100ms {
	private static int result;
	private static int[] dx = { -1, 0, 1, 0 }; // 상 좌 하 우
	private static int[] dy = { 0, -1, 0, 1 }; // 상 좌 하 우

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로 크기 = 행 수
		int M = Integer.parseInt(st.nextToken()); // 가로 크기 = 열 수

		st = new StringTokenizer(br.readLine());
		int start_r = Integer.parseInt(st.nextToken()); // 시작 x값
		int start_c = Integer.parseInt(st.nextToken()); // 시작 y값
		int start_d = Integer.parseInt(st.nextToken()); // 방향
		result = 0;

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(start_r, start_c, start_d, map, 0);
		
	}

	public static void dfs(int r, int c, int d, int[][] map, int cnt) {

		int left_x = r;
		int left_y = c;
		int left_d = d;

		int back_x = r;
		int back_y = c;

		switch (d) {
		case 0: // 북
			left_x = r;
			left_y = c - 1;
			left_d = 3;
			back_x = r + 1;
			back_y = c;
			break;
		case 1: // 동
			left_x = r - 1;
			left_y = c;
			left_d = 0;
			back_x = r;
			back_y = c - 1;
			break;
		case 2: // 남
			left_x = r;
			left_y = c + 1;
			left_d = 1;
			back_x = r - 1;
			back_y = c;
			break;
		case 3: // 서
			left_x = r + 1;
			left_y = c;
			left_d = 2;
			back_x = r;
			back_y = c + 1;
			break;
		}

		if (cnt >= 4) {
			if (map[back_x][back_y] == 1) {
				System.out.println(result);
				System.exit(0);
				
			} else {
				dfs(back_x, back_y, d, map, 0);
			}
		}
		// 1. 청소한다
		if (map[r][c] == 0) {
			map[r][c] = 2;
			result++;
			cnt = 0;
		}

		// 2. 왼쪽이 빈공간인지 확인
		if (map[left_x][left_y] == 0) {
			dfs(left_x, left_y, left_d, map, 0);
		} else { // 아니면 그냥 회전
			dfs(r, c, left_d, map, cnt + 1);
		}

	}

}

// DFS
// 0북 1동 2남 3서
// 로봇청소기가 반시계방향으로 돌면서 청소한다
// 제자리에서 4번 돌거나 이동해서 청소한 경우 => 한칸후진