import java.io.*;
import java.util.*;

public class Main_9466 {

	static int N;
	static int[] lovecall;

	static boolean[] visited;
	static boolean[] finished;

	static int team;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			N = Integer.parseInt(br.readLine());

			lovecall = new int[N + 1];
			visited = new boolean[N + 1];
			finished = new boolean[N + 1];

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			team = 0;
			for (int i = 1; i <= N; i++) {
				lovecall[i] = Integer.parseInt(st.nextToken());

				if (lovecall[i] == i) {
					visited[i] = true;
					finished[i] = true;
					team++;
				}
			}

			for (int i = 1; i <= N; i++) {
				if (finished[i]) continue;

				teamDFS(i);
			}

			sb.append(N - team).append('\n');

		} // end of tc for

		System.out.print(sb.toString());

	} // end of main

	public static void teamDFS(int cur) {
		if (visited[cur]) return;

		visited[cur] = true;
		int next = lovecall[cur];

		if (!visited[next])
			teamDFS(next);

		else if (!finished[next]) {
			// 이번 탐색 루틴에서 이미 발견한 적이 있다. == 사이클이 형성된다.
			// 2->4->7->6->2 일 때, 마지막 6에서 2가 이미 방문된 것을 체크,
			// 2부터 시작해서 6에서 끝나는 사이클을 검사하게 된다.

			// 이전에 이미 검사가 끝난 다른 사이클들은 무관한 결과를 가진다.

			team++; // 현재 node는 사이클에 포함된다.
			for (int i = next; i != cur; i = lovecall[i]) {
				team++;
			}

		}

		// 앞에서 스택에 쌓여있는 이전 DFS들은 모두 검사 종료된다
		finished[cur] = true;

	}

}

/*
어떤 한 idx에서는 한쪽 방향으로만 진행이 됨.

여러 방향으로의 가능성이 있다면, BFS를 사용하는것이 어울리지만,

무조건 한 방향으로만 간다면 DFS를 사용하는것이 더 어울리는듯 함

-> 중요 : O(N) 의 복잡도로 들어오도록 구현

 */
