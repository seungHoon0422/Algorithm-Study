import java.io.*;
import java.util.*;

public class Main_20055 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int size = 2 * N;

		int[] belt = new int[size];
		boolean[] robot = new boolean[N]; // 로봇은 벨트 위에 N개밖에 존재하지 못함

		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < size; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 종료 - - -

		int step_cnt = 0;
		int broken_belt = 0;
		// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 종료한다
		while (broken_belt < K) {
			// 1. 벨트와 로봇이 함께 회전 > O(N)
			// 벨트회전
			int temp_belt = belt[size - 1];
			for (int i = size - 1; i > 0; i--) {
				belt[i] = belt[i - 1];
			}
			belt[0] = temp_belt;

			// 로봇회전
			for (int i = N - 1; i > 0; i--) {
				robot[i] = robot[i - 1];
				robot[i - 1] = false;
			}
			// 로봇이 내리는 위치에 갔다면 내려줌
			robot[N - 1] = false;

			// 2. 로봇을 벨트가 회전하는 방향으로 이동
			// 로봇회전
			for (int i = N - 1; i > 0; i--) {

				if (!robot[i] && robot[i - 1] && belt[i] > 0) {
					robot[i] = robot[i - 1];
					robot[i - 1] = false;
					belt[i]--;
					if (belt[i] <= 0) broken_belt++;
				}

			}
			// 로봇이 내리는 위치에 갔다면 내려줌
			robot[N - 1] = false;

			// 3. 올리는 위치에 로봇을 올려줌
			if (belt[0] > 0) {
				robot[0] = true;
				belt[0]--;
				if (belt[0] <= 0) broken_belt++;
			}

			step_cnt++;
		}

		System.out.print(step_cnt);

	}

}
