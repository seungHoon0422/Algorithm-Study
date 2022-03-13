import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_20055_컨베이어벨트위의로봇_G5_박지호_756ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] belt = new int[2][N];
		Queue<Integer> robot = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];

		int isZero = 0;
		int level = 0; 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				for (int j = 0; j < N; j++) {
					belt[i][j] = Integer.parseInt(st.nextToken());
				}
			} else {
				for (int j = N - 1; j > -1; j--) {
					belt[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		} // end of for

		while (true) {
			level++;

			// 1. 벨트 회전
			int up_last = belt[0][N - 1];
			int down_last = belt[1][0];
//			boolean up_last_check = visited[0][N - 1];
//			boolean down_last_check = visited[1][0];

			for (int tc = 0; tc < 2; tc++) {
				if (tc == 0) {
					for (int i = N - 1; i > 0; i--) {
						belt[tc][i] = belt[tc][i - 1];
						visited[i] = visited[i - 1];
					}
				} else {
					for (int i = 0; i < N - 1; i++) {
						belt[tc][i] = belt[tc][i + 1];
					}
				}

			}
			belt[1][N - 1] = up_last;
			belt[0][0] = down_last;

//			visited[0] = false;
			visited[N - 1] = false;

			for (int i = 0, end = robot.size(); i < end; i++) { // 회전만 하는 것 (내구도 감소X)
				int temp = robot.poll();

				if (temp < N - 2) {
					robot.offer(temp + 1);
				}

			}

			// 2. 로봇 움직이기
			for (int i = 0, end = robot.size(); i < end; i++) {
				int temp = robot.poll();

				if (belt[0][temp + 1] != 0 && !visited[temp + 1]) {
					belt[0][temp + 1]--;
					if (belt[0][temp + 1] == 0) {
						isZero++;
					}
					visited[temp] = false;
					visited[temp + 1] = true;
					if (temp + 1 < N - 1) {
						robot.offer(temp + 1);
					}
				} else {
					robot.offer(temp);
				}

			}

			// 3. 로봇 올리기

			if (belt[0][0] != 0) {
				belt[0][0]--;
				if (belt[0][0] == 0) {
					isZero++;
				}
				visited[0] = true;
				robot.offer(0);
			}

			// 4. 종료 확인
			if (isZero >= K) {
				break;
			}

		} // end of while

		System.out.println(level);

	}
}

// 1. 회전 2. 1번칸부터 로봇 한칸씩 이동 이동못하면제자리
// 3. 올리는 위치에 로봇 올리기 / 내구도 0이면 안올림

// 결국 로봇이 몇개 올라갔냐 출력하는게 목적

// up 큐 low 큐 두개로 돌린다

//우선 배열로 풀어보자 
//로봇은 큐에 넣자