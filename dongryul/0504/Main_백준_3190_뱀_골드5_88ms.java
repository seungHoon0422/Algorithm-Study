import java.io.*;
import java.util.*;

public class Main_백준_3190_뱀_골드5_88ms {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		/*
		 * Head와 Tail을 사용해야 하니까 Dequeue를 사용하자 나머지는 구현하면 될거 같다. 방향 전환을 위한 수는 HashMap을 사용하자.
		 * 나머지는 순서대로 구현하자
		 */

		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N + 2][N + 2];
		// 벽을 세우는 과정, 벽도 몸통과 동일하게 =2로 처리. 
		Arrays.fill(map[0], 2);
		Arrays.fill(map[N + 1], 2);
		for (int i = 1; i <= N; i++)
			map[i][0] = map[i][N + 1] = 2;
		
		// 사과의 위치를 입력 받는다.
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}

		// 움직임 변화를 HashMap에 저장한다.
		int L = Integer.parseInt(br.readLine());
		HashMap<Integer, Character> changeDirection = new HashMap<Integer, Character>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			changeDirection.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}

		System.out.println(go(map, changeDirection));

	} // end of main

	private static int go(int[][] map, HashMap<Integer, Character> changeDirection) {
		int time = 0;
		// 우 하 좌 상
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };

		Deque<int[]> dq = new ArrayDeque<int[]>();
		dq.offer(new int[] { 1, 1 }); // row, col
		map[1][1] = 2;

		int[] curr = new int[2];
		int nr, nc, dir;
		dir = 0;
		while (true) {
			// 뱀의 머리를 확인
			curr = dq.peekLast();
			time++;
			// 1. 머리를 한칸 앞으로
			nr = curr[0] + dr[dir];
			nc = curr[1] + dc[dir];
			// 2. 벽이거나 뱀의 몸이 있으면 종료
			if (map[nr][nc] == 2) 
				break;
			// 3. 움직일 수 있다면 움직임 처리
			dq.offer(new int[] { nr, nc });
			// 4.1 사과가 있다면 먹고
			if (map[nr][nc] != 1) {
				// 뱀의 꼬리 선택.
				curr = dq.poll();
				map[curr[0]][curr[1]] = 0;
			}
			// 움직임 처리(위에서 map[nr][nc]=2로 바꾸고 검사하면 이상해짐
			map[nr][nc] = 2;
			// 5. 방향 변경이 요구되면 변경
			if (changeDirection.containsKey(time)) {
				// 우측으로 90도
				if (changeDirection.get(time).equals('D'))
					dir = (dir + 1) % 4;
				else // 좌측으로 90도
					dir = (dir + 3) % 4;
			}
		} // end of while
		return time;
	} // end of go

} // end of class
