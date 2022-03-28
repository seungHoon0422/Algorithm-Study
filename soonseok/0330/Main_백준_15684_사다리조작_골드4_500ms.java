import java.io.*;
import java.util.*;

public class Main_15684_500ms {

	static int N;
	static int M;
	static int H;

	static int[][] map;

	static int[] selected;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 세로선 1~N
		M = Integer.parseInt(st.nextToken()); // 초기에 존재하는 가로선의 개수
		H = Integer.parseInt(st.nextToken()); // 가로선의 가용 위치

		map = new int[H][N]; // 0~N-1, 0~H-1 사다리 맵(그림대로면 col이 N)
		for (int row = 0; row < H; row++) { // -1로 초기화
			Arrays.fill(map[row], -1);
		}
		
		selected = new int[3];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			// 이동해야하는 다리의 idx를 넣어준다
			map[a][b] = b + 1;
			map[a][b + 1] = b;
		}
		
		
		// 다리 개수 범위는 0~3 까지
		for (int tc = 0; tc <= 3; tc++) {
//			Arrays.fill(selected, -1);
			combination(0,0,tc);
		}

		System.out.print(-1);
	}

	/** 조합 뽑기 */
	public static void combination(int cnt, int start, int limit) {
		// 기저조건 : 모두 뽑은 경우
		if (cnt == limit) {
			// 조건을 만족했다면, 즉시 종료
			if (isBridgeDone(cnt)) {
				System.out.print(cnt);
				System.exit(0);
			}

			return;
		}

		// 세로선, 가로선을 따라서 순차적으로 고르기
		for (int i = start; i < N * H; i++) {

			// 해당 자리에 다리를 놓을 수 있는 경우에만 선택
			if (isBridgeOk(cnt, i)) {
				selected[cnt] = i;

				combination(cnt + 1, i + 1, limit);
			}

		}
	}

	/** 해당 자리에 다리를 놓을 수 있는지 체크 */
	public static boolean isBridgeOk(int cnt, int n) {
		int row = n / N; // 2d 좌표로 변환
		int col = n % N;

		// 예외 : 내 자리에 이미 선이 연결된 경우, 우측으로 선을 뻗을 수 없는 경우
		if (map[row][col] != -1 || col == N - 1) return false;
		// 예외 : 우측 자리에 다리가 이미 놓인 경우 false
		if (map[row][col + 1] != -1) return false;
		// 앞에서 뽑은 다리 위치때문에 걸리는 경우를 체크
		for (int i = 0; i < cnt; i++) {
			int sr = selected[i] / N;
			int sc = selected[i] % N;

			// 같은줄에 있고, col이 연속한다면, 가지치기
			if (sr == row && sc + 1 == col) return false;
		}

		return true;
	}

	/** 다리가 조건을 만족하는지 체크 */
	public static boolean isBridgeDone(int cnt) {
		// 1. 맵에 실제로 다리를 놓는다
		for (int i = 0; i < cnt; i++) {
			int sr = selected[i] / N;
			int sc = selected[i] % N;
			
			map[sr][sc] = sc + 1;
			map[sr][sc + 1] = sc;
		}
		

		// 2. 해당 맵을 타고 각 사다리를 진행하면 조건이 만족하는지 체크한다
		//	    만약 성공한다면, 즉시 종료해버려도 된다
		boolean flag = true;

		for (int n = 0; n < N; n++) {
			// 어떤 한 세로선에 대하여  바닥에 닿을때까지 진행
			int curN = n;
			int curH = 0;

			while (curH < H) {
				
				if(map[curH][curN] != -1) { // 다리가 연결된 경우
					
					// 다리가 왼쪽으로 연결
					if (curN - 1 >= 0 && map[curH][curN - 1] == curN) {
						// 왼쪽으로 이동해서 한칸 밑으로 내려간다
						curN--;
					}
					// 다리가 오른쪽으로 연결
					else if (curN + 1 < N && map[curH][curN + 1] == curN) {
						curN++;
					}
				}

				curH++; // 아래론 무조건 내려간다
			}

			// 바닥에 닿았을 때, 사다리가 다르다면 false
			if (n != curN) {
				flag = false;
			}

			// 하나라도 만족 못하면 즉시 종료
			if (!flag) break;
		}

		// 3. 다시 맵에서 다리를 제거한다
		for (int i = 0; i < cnt; i++) {
			int sr = selected[i] / N;
			int sc = selected[i] % N;

			map[sr][sc] = map[sr][sc + 1] = -1;
		}

		return flag;
	}

}

/*


해당 자리에 다리 놓을수있는지 boolean 함수
사다리를 골랐을때 어디에 도착하는지 체크하는 int 함수

다리 놓을 수 있는 곳을 고르는 조합 dfs 함수
-> 0개, 1개, 2개, 3개 뽑을 수 있는 각 경우의 수를 순차적으로 main에서 호출
-> 모두 뽑은 경우 사다리 내려가면서 하나씩 확인해본다
	-> 조건을 만족하는 경우 cnt 값을 출력하며 즉시 종료.

-> 0,1,2,3 모두 돌렸는데도 종료 못했으면 불가능한 상태로 판정


-> 사다리가 왼쪽으로 연결된지 오른쪽으로 연결된지 체크를 위해 index 값으로 변경

-> 500ms

*/
