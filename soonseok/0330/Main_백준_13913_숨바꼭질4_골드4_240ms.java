import java.io.*;
import java.util.*;

public class Main_13913 {

	static int N;
	static int K;

	static int[] visited = new int[100_001];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// start
		bfs();

		System.out.print(sb.toString());
	}

	public static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[K] = -100; // 시작점 -100으로 표기
		queue.offer(K);

		int cnt = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();

			while (--size >= 0) {
				int cur = queue.poll();

				// 언니 발견
				if (cur == N) {
					sb.append(cnt).append('\n');

					while (visited[cur] != -100) {
						sb.append(cur).append(' ');

						switch (visited[cur]) {
						case -1:
							cur++;
							break;
						case 1:
							cur--;
							break;
						case 2:
							cur *= 2;
							break;
						}
					}
					sb.append(K); // 마지막에 자기 위치 append

					return;
				}

				int newL = cur - 1;
				int newR = cur + 1;
				int newT = cur / 2;

				// 좌로 이동
				if (newL >= 0 && visited[newL] == 0) {
					visited[newL] = -1;
					queue.offer(newL);
				}
				// 우로 이동
				if (newR <= 100_000 && visited[newR] == 0) {
					visited[newR] = 1;
					queue.offer(newR);
				}
				// 텔레포트(newT로 만들기 전 값이 짝수일때만 진행해야함)
				if (newT > 0 && (cur % 2 == 0) && visited[newT] == 0) {
					visited[newT] = 2;
					queue.offer(newT);
				}
			}

			cnt++; // 1초 경과
		}

	}

}

/*

1. 처음에는 진행하는 bfs 마다 지나온 길을 기록하는 list 만들었지만, 당연히 시간초과

2. 두번째는 string build를 거꾸로 했더니 2928ms 걸렸다. -> 반대로 시작해서 찾아보자

3. 동생->언니 순서로 찾았더니 240ms

*/
