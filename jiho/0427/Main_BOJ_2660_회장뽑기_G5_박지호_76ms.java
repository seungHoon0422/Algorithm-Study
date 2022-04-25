import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2660_회장뽑기_G5_박지호_76ms {

	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine()); // 회원 수
		int INF = 987654321;

		int[][] map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j) {
					map[i][j] = INF;
				}
			}
		}

		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == -1)
				break;

			map[a][b] = 1;
			map[b][a] = 1;

		}

		// 플로이드-와샬
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}

		int reader = INF;
		int[] scoreArr = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			int temp = 0;
			for (int j = 1; j <= N; j++) {
				if (map[i][j] != INF) {
					temp = Math.max(temp, map[i][j]);
				}

			}

			scoreArr[i] = temp;
			reader = Math.min(reader, temp);

		}

		sb.append(reader).append(" ");
		StringBuilder sb2 = new StringBuilder();
		int dup = 0;

		for (int i = 1; i <= N; i++) {
			if (reader == scoreArr[i]) {
				dup++;
				sb2.append(i).append(" ");
			}
		}

		sb.append(dup);

		System.out.println(sb);
		System.out.println(sb2);
	}

}

// 양방향 그래프
