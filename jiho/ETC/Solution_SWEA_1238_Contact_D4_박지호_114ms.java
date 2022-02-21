package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_1238_Contact_D4_박지호_114ms {

	private static int[][] adjMatrix;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 10; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());

			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

			adjMatrix = new int[101][101];

			for (int j = 0; j < N / 2; j++) {
				int from = Integer.parseInt(st2.nextToken());
				int to = Integer.parseInt(st2.nextToken());

				adjMatrix[from][to] = 1; // 무향이니까 반대로 뒤집어서도 넣어줘야함
			}

			sb.append("#").append(i).append(" ").append(bfs(S)).append("\n");

		}
		System.out.println(sb);

	}

	public static int bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] visited = new int[101];
		int max = 0;
		int result = 0;
		queue.offer(start);
		visited[start] = 1;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			// current 정점의 인접 정점을 처리(단, 방문하지 않은 인접 정점만)
			for (int i = 1; i <= 100; i++) {
				if (adjMatrix[current][i] != 1 || visited[i] != 0)
					continue;

				visited[i] = visited[current] + 1;
				queue.offer(i);

			}
			max = visited[current];

		}
		for (int i = 1; i < 101; i++) {
			if (max != visited[i])
				continue;
			result = result > i ? result : i;
		}

		return result;
	}

}
