package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 		  M=5
 *      0 0 0 0 0 
 * N=5  0 0 0 0 0   0=빈칸
 * 		0 0 0 0 0
 * 		0 0 0 0 0
 * 		1 1 1 1 1   1= 적
 * 		성 성 성 성 성	성이 있는 칸중 3군데에 배치 가능, MC3 조합
 * 
 * 궁수의 공격거리 제한 D, D이하의 거리에 있는 적 중 가장 가깝고 왼쪽에 있는 적을 공격(맨하탄거리 기준)
 * 같은 적이 여러 궁수에게 공격당할 수 있음. 모든 궁수는 동시에 공격함.
 * 
 * 턴 진행) 궁수 공격-> 적이 한칸 아래로 이동-> 성으로 가게 되는 경우 게임에서 제외.
 * 		모든 적이 격자판에서 사라지면 게임 끝 (적 개수를 사전에 체크하면서 진행)
 * 
 */
public class Main_백준_17135_캐슬디펜스_골드4_184ms {

	private static int[] dr = { 0, -1, 0, 1 }; // 좌상우 하(가장 왼쪽 찾으려면 이 순서대로 탐색)
	private static int[] dc = { -1, 0, 1, 0 };

	private static int N;
	private static int M;
	private static int D;

	private static int[][] copyMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // row (3 ≤ N ≤ 15)
		M = Integer.parseInt(st.nextToken()); // col (3 ≤ M ≤ 15)
		D = Integer.parseInt(st.nextToken()); // range (1 ≤ D ≤ 10)

		int[][] map = new int[N][M];
		int totalEnemy = 0;

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == 1)
					totalEnemy++; // 입력 받으면서 적의 수 체크하기
			}
		} // 입력 종료

		// 모든 궁수 위치중 가장 적을 많이 죽였을때를 기록
		int maxKilledEnemy = 0;

		// M C R 에서 R=3 으로 항상 고정 (궁수의 수가 3으로 고정)
		// 반복문으로 그냥 구현하는게 더 편할 것
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					// 궁수위치마다 시뮬레이션을 새로 하므로, 기존 데이터를 초기상태로 써야함
					// 2차원배열 깊은 복사안해서 오류생김. 깊복 ㄱㄱ
					copyMap = deepCopy(map, N, M);

					int remainEnemy = totalEnemy; // 남아있는 적의 수
					int killedEnemy = 0; // 궁수가 죽인 적 수

					// 한 궁수 위치에 대하여 모든 적이 없어질때까지 반복
					while (remainEnemy > 0) {
						int[][] archers = new int[3][2];

						// 1. 세 궁수에 대하여 가장 가까운 적 찾기
						archers[0] = findClosestTarget(N - 1, i); // row, col 순서로 입력, 이때 궁수위치 i j k는 col이다.
						archers[1] = findClosestTarget(N - 1, j); // row는 성의 궁수 위치에서 한칸 위로 올라간곳에서 시작할 것이기 때문에
						archers[2] = findClosestTarget(N - 1, k); // N-1 idx를 주면 된다

						// 2. 가장 가까운 적 제거하기( 한번에 처리해야함 ), 킬수 +, total 수 -
						// 가까운 적이 중복인 경우를 체크
						List<int[]> list = new ArrayList<int[]>();
						// null 체크
						if (archers[0] != null)
							list.add(archers[0]);
						if (archers[1] != null && !isPairSame(archers[0], archers[1]))
							list.add(archers[1]);
						if (archers[2] != null && !isPairSame(archers[0], archers[2])
								&& !isPairSame(archers[1], archers[2]))
							list.add(archers[2]);

						int tempSize = list.size(); // 조건문에 size() 넣으면 매번 호출해서 별로 안좋다
						for (int l = 0; l < tempSize; l++) {
							copyMap[list.get(l)[0]][list.get(l)[1]] = 0; // 적을 죽이고 카운트
							killedEnemy++;
							remainEnemy--;
						}

						// 3. 적들의 위치 아래로 한칸 옮기기. 성에 가까운애들부터 옮겨야 겹쳐서 에러나는 일 없음.
						// 맨 아랫줄에서 1의 개수를 remain에서 빼주면서 모두 0으로 만들어준다
						for (int col = 0; col < M; col++) {
							if (copyMap[N - 1][col] == 1) {
								copyMap[N - 1][col] = 0;
								remainEnemy--;
							}
						}
						// 그 후 위에있는 나머지 줄을 아래로 한칸씩 땡겨준다
						for (int row = N - 2; row >= 0; row--) {
							for (int col = 0; col < M; col++) {
								copyMap[row + 1][col] = copyMap[row][col];
								copyMap[row][col] = 0; // 그리고 옮겼으면 0으로 만든다
							}
						}
					} // end of while
					maxKilledEnemy = Math.max(maxKilledEnemy, killedEnemy); // 최대 킬 수 갱신
				}
			}
		}
		System.out.print(maxKilledEnemy);
	} // end of main

	// BFS로 탐색의 depth를 이용해 진행
	public static int[] findClosestTarget(int r, int c) {
		// 초기 값 검사, 한칸 위로 움직인 상태라 바로 검사해 줌
		if (copyMap[r][c] == 1) // 현재 칸에서 만약 적을 발견했으면
			return new int[] { r, c };

		Queue<int[]> queue = new LinkedList<int[]>();

		queue.add(new int[] { r, c }); // 현재 들어온 좌표 큐에 넣어둔다
		int moveCount = 1; // 현재 움직인 횟수 1, 최대 움직일수 있는 횟수 D
		// 큐가 비었거나, 이미 최대로 움직였다면 탐색 불가
		while (!queue.isEmpty() && (moveCount < D)) {
			for (int q = queue.size(); q > 0; q--) { // queue에 있는 애들 대상으로
				int[] cur = queue.poll();

				for (int i = 0; i < 3; i++) { // 좌 상 우 세방향으로만 계속 가면 된다
					int newR = cur[0] + dr[i];
					int newC = cur[1] + dc[i];
					// 범위 체크해서, 나간 경우에는 그 방향으로 체크 못함. 다른 방향으로
					if (newC < 0 || newR < 0 || newC >= M || newR >= N)
						continue;
					// 적을 찾았다면 무조건 최단 적이다. return 해주면 된다.
					if (copyMap[newR][newC] == 1)
						return new int[] { newR, newC };
					// 아닌 경우는 0만 있는 경우다. queue 에 넣어주면 된다
					else
						queue.add(new int[] { newR, newC });
				}
			}
			moveCount++;
		}
		// 만약 위에서 return을 못했다면, 탐색을 다 하고도 적을 못 찾은 것
		return null;
	} // end of findClosestTarget()

	public static int[][] deepCopy(int[][] orig, int N, int M) {
		if (orig == null)
			return null;

		int[][] result = new int[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(orig[i], 0, result[i], 0, M);
		}

		return result;
	} // end of deepcopy()

	public static boolean isPairSame(int[] a, int[] b) {
		if (a == null && b == null)
			return true;

		if (a == null && b != null)
			return false;

		if (b == null && a != null)
			return false;

		return (a[0] == b[0] && a[1] == b[1]);
	} // end of isPairSame()
} // end of class
