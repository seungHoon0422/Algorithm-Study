package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_7465_창용마을무리의개수_D4_박지호 {

	private static int N;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= TC; testCase++) {
			sb.append("#").append(testCase).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] people = new int[N + 1][N + 1];

			for (int i = 0; i < M; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st2.nextToken());
				if (!st2.hasMoreTokens())
					continue;
				int to = Integer.parseInt(st2.nextToken());

				people[from][to] = people[to][from] = 1;
			}

			visited = new boolean[N + 1];
			int result = 0;

			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					result++;
					bfs(people, i);
				}
			}

			sb.append(result).append("\n");

		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	public static void bfs(int[][] adjMatrix, int start) {
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
//			System.out.println(current);

			// current 정점의 인접 정점을 처리(단, 방문하지 않은 인접 정점만)
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && adjMatrix[current][i] != 0) {
					queue.offer(i);
					visited[i] = true;
				}
			}

		}

	}
}
// 아는 사람이다 = 서로가 알고있다 = 양방향그래프
// 그래프 문제
// 사람 수  N 
// 서로를 알고 있는 사람의 관계 수 M
// bfs를 통해서 이어져있는 모든 사람들에 대해 방문처리
// 반복문 돌리면서 모든 방문처리 배열이 true가 될때까지 카운트 세줌