import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_백준_2665_미로만들기_골드4_88ms {

	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	static int N;
	static int[][] map;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // NxN map (1 ≤ N ≤ 50)
		map = new int[N][N]; // 패딩 안 쓸 것

		for (int row = 0; row < N; row++) {
			String line = br.readLine();
			for (int col = 0; col < N; col++) {
				map[row][col] = line.charAt(col) - '0';
			}
		} // 입력 종료 - - -

		mazeBFS(0, 0);

	} // end of main

	public static void mazeBFS(int row, int col) {
		Queue<int[]> queue = new LinkedList<int[]>();

		int[][] cost = new int[N][N]; // 각 지점으로 가기위한 최소의 벽 부수기 개수를 저장
		for (int i = 0; i < N; i++) {
			Arrays.fill(cost[i], 2600); // 가능한 벽 부수기 최고 개수는 2500개
		}

		queue.offer(new int[] { row, col });
		cost[row][col] = 0; // 시작 지점은 벽 부수기가 필요없다

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			// 사방 탐색
			for (int i = 0; i < 4; i++) {
				int newR = cur[0] + dr[i];
				int newC = cur[1] + dc[i];

				// 범위를 벗어난 경우 넘긴다
				if (newR < 0 || newC < 0 || newR >= N || newC >= N)
					continue;

				if (map[newR][newC] == 0) { // 이동할 자리가 검은색 방인 경우
					if (cost[newR][newC] > cost[cur[0]][cur[1]] + 1) { // 이 루트로 가는게 더 벽을 덜 부술 수 있다면,
						cost[newR][newC] = cost[cur[0]][cur[1]] + 1; // 이 루트를 선택하고, 다음 탐색을 위해 큐에 넣는다
						queue.offer(new int[] { newR, newC });
					}
				} else { // 이동할 자리가 흰색 방인 경우
					if (cost[newR][newC] > cost[cur[0]][cur[1]]) { // 마찬가지, 하지만 +1 안해줘도 된다.
						cost[newR][newC] = cost[cur[0]][cur[1]];
						queue.offer(new int[] { newR, newC });
					}
				}
			}

		} // end of while

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(cost[i]));
//		}

		// 도착 지점의 비용 출력
		System.out.println(cost[N - 1][N - 1]);

	} // end of mazeBFS()

} // end of class
