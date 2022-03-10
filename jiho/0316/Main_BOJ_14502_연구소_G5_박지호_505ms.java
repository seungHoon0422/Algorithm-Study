import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_14502_연구소_G5_박지호_505ms {
	private static int N, M;
	private static int max = Integer.MIN_VALUE;
	private static int[] dx = { -1, 1, 0, 0 }; // 상 하 좌 우
	private static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기 = 행의 수
		M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기 = 열의 수
		int[][] map = new int[N + 2][M + 2];
		pos[] wallPlace = new pos[3];
		for (int i = 0; i < N + 2; i++) {
			Arrays.fill(map[i], 1);
		}

		Queue<pos> virus = new LinkedList<pos>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;

				if (temp == 2) {
					virus.offer(new pos(i, j));
				}
			}
		}

		combination(0, new int[] { 1, 1 }, map, wallPlace, virus);

		System.out.println(max);

	}

	public static void combination(int cnt, int[] start, int[][] map, pos[] wallPlace, Queue<pos> virus) { // 벽선택

		if (cnt == 3) {

			diffusion(map, wallPlace, virus);
//			max = result > max ? result : max;
//			if(wallPlace[0].x == 1) {
//				System.out.println(wallPlace[0].x + ", " + wallPlace[0].y + " || " + wallPlace[1].x + ", " + wallPlace[1].y
//						+ " || " + wallPlace[2].x + ", " + wallPlace[2].y);
//				
//			}
			return;

		}

		boolean flag = false;
		for (int i = start[0]; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (i == start[0] && !flag) {
					flag = true;
					j = start[1] - 1;
					continue;
				}
				if (map[i][j] == 0) {

					wallPlace[cnt] = new pos(i, j);
					if (j == M) {

						combination(cnt + 1, new int[] { i + 1, 1 }, map, wallPlace, virus); // 다음자리는 현재 뽑은 i의 다음수부터
																								// 시작하도록 전달

					} else {

						combination(cnt + 1, new int[] { i, j + 1 }, map, wallPlace, virus); // 다음자리는 현재 뽑은 i의 다음수부터
																								// 시작하도록 전달

					}
				}

			}
		}
	} // end of combi

	public static void diffusion(int[][] map, pos[] wallPlace, Queue<pos> virus) {
		int[][] copyMap = new int[N + 2][M + 2];
//		pos[] copyWall = new pos[wallPlace.length];
		Queue<pos> copyVirus = new LinkedList<pos>();

		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < M + 2; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		int k = virus.size();
		for (int i = 0; i < k; i++) {
			pos temp = virus.poll();
			
			copyVirus.offer(temp);
			virus.offer(temp);
			
		}
		

		for (int i = 0; i < 3; i++) {
			copyMap[wallPlace[i].x][wallPlace[i].y] = 1;
		}

		int safty = 0;

		while (!copyVirus.isEmpty()) {
//			System.out.println("virusTest");
			pos temp = copyVirus.poll();
			int x = temp.x;
			int y = temp.y;

			for (int i = 0; i < 4; i++) {
				int fx = x + dx[i];
				int fy = y + dy[i];

				if (copyMap[fx][fy] == 0) {
					copyMap[fx][fy] = 2;
					copyVirus.offer(new pos(fx, fy));
				}

			}

		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (copyMap[i][j] == 0) {
//					System.out.println("saftyPlus");
					safty++;
				}
//				System.out.print(map[i][j]);
			}
//			System.out.println();
		}

//		System.out.println(safty);
	
		max = safty > max ? safty : max;

	}

	static class pos {
		int x;
		int y;

		public pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "pos [x=" + x + ", y=" + y + "]";
		}

	}

}

// 조합 + BFS
// 
// 조합 리턴조건 부분에서 매개 변수들을 모두 복사해서 사용해야 원본의 값이 유지된다!!