import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_5567_결혼식_S2_박지호_132ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine()); // 동기 수
		int M = Integer.parseInt(br.readLine()); // 리스트 길이
		int result = 0;

		int[][] friends = new int[N + 1][N + 1];
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[1] = true;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			friends[start][end] = 1;
			friends[end][start] = 1;

			if (start == 1) {
				queue.offer(end);
//				result++;
			}

		}

		for (int i = 0, end = queue.size(); i < end; i++) {
			int temp = queue.poll();
			visited[temp] = true;
			for (int j = 1; j < N + 1; j++) {

				if (!visited[j] && friends[temp][j] == 1) {
					visited[j] = true;
				}

			}

		}

		for (int i = 0; i < visited.length; i++) {
			if (visited[i]) {
				result++;
			}
		}

		System.out.println(result - 1);
	}

}
