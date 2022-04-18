import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static long[] dp;
	static List<Integer>[] list; // 각 노드에 연결된 섬의 번호를 저장할 리스트 배열

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 노드 개수

		dp = new long[N + 1]; // dp 배열 초기화

		list = new ArrayList[N + 1]; // 배열 한 칸마다 ArrayList 초기화
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		StringTokenizer st;
		// 2번 노드부터 입력받아서 값 넣기
		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			char animal = st.nextToken().charAt(0);
			int number = Integer.parseInt(st.nextToken());
			int parent = Integer.parseInt(st.nextToken());

			// 부모에게 이번 노드를 연결해준다
			list[parent].add(i);
			if (animal == 'W') number *= -1; // 늑대인 경우 음수로 받는다
			dp[i] = number; // 해당 섬에 있는 동물의 숫자를 반영한다

		}

		dfs(1, -1);
		System.out.print(dp[1]);

	} // main()

	static void dfs(int idx, int parent) {
		// leaf node까지 진행한다.
		for (int next : list[idx]) {
			dfs(next, idx); // 현재 진행하던 애를 부모로 leaf까지 반복
		}

		if (parent != -1) { // Root 노드는 더해줄 dp 배열이 없다

			// 현재 칸이 양이라면(양은 이동 가능, 늑대는 이동이 불가능)
			if (dp[idx] > 0) dp[parent] += dp[idx];

		}

	} // dfs()

}
