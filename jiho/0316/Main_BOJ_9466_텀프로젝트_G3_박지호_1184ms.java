import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_9466_텀프로젝트_G3_박지호_1184ms {

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static int n;
	static int[] arr;
	static int count = 0;
	static boolean[] visited;
	static boolean[] finished;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = stoi(br.readLine());

		for (int t = 0; t < tc; t++) {
			n = stoi(br.readLine());
			arr = new int[n + 1];
			visited = new boolean[n + 1];
			finished = new boolean[n + 1];
			count = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < n + 1; i++)
				arr[i] = stoi(st.nextToken());

			for (int i = 1; i < n + 1; i++)
				dfs(i);

			sb.append(n - count).append("\n");
			
		}
		System.out.println(sb);
	}

	static void dfs(int now) {
		if (visited[now])
			return;

		visited[now] = true;
		int next = arr[now];

		if (visited[next] != true)
			dfs(next);
		else {
			if (finished[next] != true) {
				// 노드가 끝나려면 싸이클을 무조건 거쳐야한다.
				// 따라서 현재 노드가 아닌 다음 노드 기준으로 하면 싸이클이 무조건 존재
				count++;
				for (int i = next; i != now; i = arr[i])
					count++;
			}
		}

		// 모든 작업이 끝나서 더이상 사용하지 않음
		finished[now] = true;
	}
}
