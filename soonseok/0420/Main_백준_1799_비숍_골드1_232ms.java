import java.io.*;
import java.util.*;

/*

복잡도 계산 : 원래는 N*N 개의 칸에서 각 칸마다 0 or 1 의 경우의 수이다 -> O(2^(N*N))

하지만 다른 비숍을 탐지해서 놓지 않고 지나가는 경우가 있다.

 이 경우는 최소 N*N/2 이상이므로, 즉 절반의 칸에 대해서는 항상 경우의 수 계산을 하지 않는다 -> O(2^(N*N/2))
 (참고 : https://www.acmicpc.net/board/view/68546)
 
 거기서 흑, 백을 따로 나눠서 계산하면 N*N/2 -> N*N/4 + N*N/4 이므로 -> O(2^(N*N/4))

 */

public class Main_1799 {

	static int[] dr = { -1, -1 }; // 좌상 우상
	static int[] dc = { -1, 1 };

	static int N;
	static int[][] map;

	static List<int[]> list_even;
	static List<int[]> list_odd;

	static int total_even;
	static int total_odd;

	static int max_even;
	static int max_odd;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		list_even = new ArrayList<int[]>();
		list_odd = new ArrayList<int[]>();

		total_even = 0;
		total_odd = 0;

		for (int row = 0; row < N; row++) {
			String line = br.readLine();
			for (int col = 0, idx = 0; col < N; col++, idx += 2) {
				map[row][col] = line.charAt(idx) - '0';

				if (map[row][col] == 1) {
					if ((row % 2 == 0 && col % 2 == 0) || (row % 2 == 1 && col % 2 == 1)) {
						list_even.add(new int[] { row, col });
						total_even++;
					}
					else {
						list_odd.add(new int[] { row, col });
						total_odd++;
					}
				}
			}
		} // 입력

		//				System.out.println(total_even + "," + total_odd);
		//				System.out.println("even");
		//				for (int i = 0; i < list_even.size(); i++) {
		//					System.out.println(list_even.get(i)[0] + "," + list_even.get(i)[1]);
		//				}
		//				System.out.println();
		//				System.out.println("odd");
		//				for (int i = 0; i < list_odd.size(); i++) {
		//					System.out.println(list_odd.get(i)[0] + "," + list_odd.get(i)[1]);
		//				}
		//				System.out.println();

		max_even = 0;
		max_odd = 0;
		bishop_even(0, 0);
		bishop_odd(0, 0);

		//		for (int row = 0; row < N; row++) {
		//			System.out.println(Arrays.toString(map[row]));
		//		}

		System.out.print(max_even + max_odd);

	} // main()

	public static void bishop_even(int cnt, int start) {
		// 이전에 체크한 자리 이후로만 체크 (조합)
		for (int i = start; i < total_even; i++) {

			int sr = list_even.get(i)[0];
			int sc = list_even.get(i)[1];

			// isAvailable (다른 비숍에 걸린다면 둘 수 없다)
			if (isAvailable(sr, sc)) {

				map[sr][sc] = 2; // 비숍 체크

				bishop_even(cnt + 1, i + 1); // 비숍 하나 놨고, 다음 비숍부터 체크한다

				map[sr][sc] = 1; // 비숍 체크 빼준다
			}
		}

		max_even = Math.max(max_even, cnt);

	} // bishop_even()

	public static void bishop_odd(int cnt, int start) {
		// 이전에 체크한 자리 이후로만 체크 (조합)
		for (int i = start; i < total_odd; i++) {

			int sr = list_odd.get(i)[0];
			int sc = list_odd.get(i)[1];

			// isAvailable (다른 비숍에 걸린다면 둘 수 없다)
			if (isAvailable(sr, sc)) {

				map[sr][sc] = 2; // 비숍 체크

				bishop_odd(cnt + 1, i + 1); // 비숍 하나 놨고, 다음 비숍부터 체크한다

				map[sr][sc] = 1; // 비숍 체크 빼준다
			}
		}

		max_odd = Math.max(max_odd, cnt); // 더 이상 체스말을 둘 수 없는경우 여기로 내려온다

	} // bishop_odd()

	public static boolean isAvailable(int sr, int sc) {
		// 상단부터 비숍 두면서 내려오기 때문에, 위쪽방향에 이미 놓여져 있는지만 체크
		for (int i = 0; i < 2; i++) {

			int newR = sr + dr[i];
			int newC = sc + dc[i];

			// 범위 벗어날 때 까지 진행
			while (true) {
				if (newR < 0 || newC < 0 || newR >= N || newC >= N) break;

				if (map[newR][newC] == 2) { // 다른 비숍을 만난다면, 둘 수 없는 자리 판정
					return false;
				}

				newR += dr[i];
				newC += dc[i];
			}
		}

		return true; // 모두 통과했다면 true

	} // isAvailable()

}

/*

흑과 백의 칸이 서로 영향을 미치지 않기 때문에,

반으로 나눠서 진행한다.

*/
