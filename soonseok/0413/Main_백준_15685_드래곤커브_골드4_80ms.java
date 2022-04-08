import java.io.*;
import java.util.*;

public class Main_15685 {

	static int N = 100;

	static int[] dr = { 0, -1, 0, 1 }; // 동북서남
	static int[] dc = { 1, 0, -1, 0 };

	static int[] drcv = new int[1050]; // 2^10 개 필요

	static int[][] map = new int[N + 1][N + 1]; // 0 ≤ x,y ≤ 100 

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int M = Integer.parseInt(br.readLine()); // 드래곤 커브 입력 개수

		// 드래곤커브 0 ~ 10 세대
		// 동쪽 시작 기준으로 드래곤커브를 미리 기록
		drcv[0] = 0; // 처음은 동쪽으로 시작
		for (int i = 1; i <= 10; i++) {

			int idx = 1 << (i - 1); // 해당 드래곤커브의 진행 단위

			for (int j = idx, k = idx - 1; j < 2 * idx; j++, k--) {
				// idx 기준으로 왼쪽으로 진행하며 값을 채운다
				drcv[j] = (drcv[k] + 1) % 4; // 한칸 다음칸으로, 4 이상이 되면 0으로 

			}
		}

//		System.out.println("\n< dragon curve >");
//		for (int i = 0; i < 16; i++) {
//			System.out.print(drcv[i] + " ");
//		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int c = Integer.parseInt(st.nextToken()); // col
			int r = Integer.parseInt(st.nextToken()); // row
			int d = Integer.parseInt(st.nextToken()); // direction
			int g = Integer.parseInt(st.nextToken()); // generation

			dragon_curve(r, c, d, g);

		}

//		for (int i = 0; i < 10; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		// 정사각형 개수를 출력
		System.out.print(square());

	} // main()

	/** 드래곤 커브를 수행하며 맵을 수정함 */
	public static void dragon_curve(int sr, int sc, int d, int g) {

		// 기본적으로 동쪽 진행으로 되어있으므로, 시작 방향만큼 더해주며 진행하도록 한다

		map[sr][sc] = 1; // 시작위치 찍어주고

		// 진행 방향으로 한칸 보내준다
		sr += dr[d];
		sc += dc[d];

		if (g == 0) { // 0 세대라면 끝점 찍고 끝낸다
			map[sr][sc] = 1;
			return;
		}

		for (int i = 1; i <= g; i++) { // 세대만큼 반복

			int idx = 1 << (i - 1); // 해당 드래곤커브의 진행 단위

			// 드래곤 커브 시작 위치 찍고
			map[sr][sc] = 1;

			for (int j = idx; j < 2 * idx; j++) {
				// 드래곤 커브만큼 진행하며 값을 채워나간다

				sr += dr[(drcv[j] + d) % 4]; // 다음 칸으로 이동해서 끝나는 지점 찍기
				sc += dc[(drcv[j] + d) % 4];

				map[sr][sc] = 1;
			}
		}

	} // drg_crv()

	/** 정사각형 개수를 센다 */
	public static int square() {

		int cnt = 0;

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {

				if (map[row][col] == 0 || map[row][col + 1] == 0 || map[row + 1][col] == 0
						|| map[row + 1][col + 1] == 0)
					continue;

				cnt++;
			}
		}

		return cnt;

	} // square()

}
