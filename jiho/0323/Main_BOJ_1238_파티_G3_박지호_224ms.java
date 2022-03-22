import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1238_파티_G3_박지호_224ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 학생 수 = 노드 수
		int M = Integer.parseInt(st.nextToken()); // 도로 수 = 간선 수
		int X = Integer.parseInt(st.nextToken()); // 모일 마을 = 목적지

		int[][] pan = new int[N + 1][N + 1];
		int[][] Rpan = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			pan[start][end] = time;
			Rpan[end][start] = time;

		}

		int[] distance = new int[N + 1]; // 출발지에서 자신으로 오는 최소비용
		boolean[] visited = new boolean[N + 1]; // 최소비용 확정 여부

		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[X] = 0;

		for (int i = 1; i < N + 1; i++) {
			int min = Integer.MAX_VALUE;
			int current = 0;

			for (int j = 1; j < N + 1; j++) {
				if (!visited[j] && min > distance[j]) {
					min = distance[j];
					current = j;
				}
			}

			visited[current] = true;

			for (int j = 1; j < N + 1; j++) {
				if (!visited[j] && pan[current][j] != 0 && distance[j] > distance[current] + pan[current][j]) {
					distance[j] = distance[current] + pan[current][j];
				}
			}

		}

		int[] Rdistance = new int[N + 1]; // 출발지에서 자신으로 오는 최소비용
		boolean[] Rvisited = new boolean[N + 1]; // 최소비용 확정 여부

		Arrays.fill(Rdistance, Integer.MAX_VALUE);
		Rdistance[X] = 0;

		for (int i = 1; i < N + 1; i++) {
			int min = Integer.MAX_VALUE;
			int current = 0;

			for (int j = 1; j < N + 1; j++) {
				if (!Rvisited[j] && min > Rdistance[j]) {
					min = Rdistance[j];
					current = j;
				}
			}

			Rvisited[current] = true;

			for (int j = 1; j < N + 1; j++) {
				if (!Rvisited[j] && Rpan[current][j] != 0 && Rdistance[j] > Rdistance[current] + Rpan[current][j]) {
					Rdistance[j] = Rdistance[current] + Rpan[current][j];
				}
			}

		}

		int max = Integer.MIN_VALUE;
		for (int i = 1; i < N + 1; i++) {
			if (max < distance[i] + Rdistance[i]) {
				max = distance[i] + Rdistance[i];
			}

		}

		System.out.println(max);

	}
}

// 모든 정점에서 X로 오는 최소비용 distance 배열 작업 후
// 방향을 반대로 넣은 배열로 같은 작업을 다시하면 => X에서 모든 정점으로의 최소비용인 Rdistance 구해짐
// 따라서 distance + Rdistance 의 최대값을 구한다
