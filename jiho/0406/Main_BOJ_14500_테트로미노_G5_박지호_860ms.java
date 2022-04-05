import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14500_테트로미노_G5_박지호_860ms {

	private static int[] dx = { -1, 1, 0, 0 }; // 상 하 좌 우
	private static int[] dy = { 0, 0, -1, 1 };
	private static int[][] pan;
	private static boolean[][] visited;
	private static int N, M, max;

	public static void tetromino() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, 0, 0);
				exception(i, j);
			}
		}

	}

	public static int dfs(int r, int c, int depth, int sum) { // ㅗ 모양 제외 구현

		if (depth == 4) {
			max = Math.max(max, sum);
			return 0;
		}

		for (int i = 0; i < 4; i++) {
			int fx = r + dx[i];
			int fy = c + dy[i];

			if (isAvailable(fx, fy) && !visited[fx][fy]) {
				visited[fx][fy] = true;
				dfs(fx, fy, depth + 1, sum + pan[fx][fy]);
				visited[fx][fy] = false;
			}

		}
		return sum;
	}

	public static boolean isAvailable(int a, int b) {

		if (a >= 0 && a < N && b >= 0 && b < M) {
			return true;
		} else {
			return false;
		}

	}

	public static void exception(int r, int c) { // ㅗ 모양 구현
		int wing = 4; // 가운데에서의 상하좌우 날개
		int min = Integer.MAX_VALUE;
		int sum = pan[r][c];
		for (int i = 0; i < 4; i++) {
			int nextRow = r + dx[i];
			int nextCol = c + dy[i];

			// 날개가 2개이상 없다면 ㅗ 모양이 아니다. 그러므로 함수를 종료한다.
			if (wing <= 2)
				return;
			// 날개가 맵 바깥에 있는 경우
			if (!isAvailable(nextRow, nextCol)) {
				wing--;
				continue;
			}
			min = Math.min(min, pan[nextRow][nextCol]);
			sum = sum + pan[nextRow][nextCol];
		}
		// 날개가 4개일때 가장 작은 날개를 없애야 ㅗ,ㅏ,ㅓ,ㅜ 모양이 나온다.
		if (wing == 4) {
			sum = sum - min;
		}
		max = Math.max(max, sum);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 종이 세로 크기
		M = Integer.parseInt(st.nextToken()); // 종이 가로 크기

		pan = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		tetromino();
		System.out.println(max);
	}
}
