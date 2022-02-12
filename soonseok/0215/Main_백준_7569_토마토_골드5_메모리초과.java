package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1차원 배열로 인덱싱해서 해보자
 * bfs로 짜야 깊이(일 수)를 제대로 알 수 있다.
 */

public class Main_백준_7569_토마토_골드5_메모리초과 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 토마토 상자의 정보 입력, M N H 순서
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[] box = new int[(N + 2) * (M + 2) * (H + 2)]; // 바깥에 padding
		Arrays.fill(box, -1); // 상자요소에 한번 싹 접근, O(N*M*H)

		// bfs용 queue 생성
		Queue<int[]> queue = new LinkedList<int[]>();

		// 상자에 입력, O(N*M*H)
		// 토마토 상자의 맨 아래에서 한층 위 가장 왼쪽위에서 한칸 오른쪽 아래가 1,1,1 자리
		for (int h = 1; h <= H; h++) { // 토마토 층 만큼
			for (int row = 1; row <= N; row++) { // row
				st = new StringTokenizer(br.readLine(), " ");
				for (int col = 1; col <= M; col++) { // col
					// 높이(한 층에 N*M 만큼) row(한 줄에 col만큼)
					box[(N * M) * h + (M * row) + col] = Integer.parseInt(st.nextToken());
					// 익은 토마토의 idx를 전부 queue에 넣어준다
					if (box[(N * M) * h + (M * row) + col] == 1) {
						queue.offer(new int[] { h, row, col });
					}
				}
			}
		} // end of input box

		int result = tomatoBFS(H, N, M, queue, box); // BFS 실행

		for (int h = 1; h <= H; h++) { // 토마토 층 만큼
			for (int row = 1; row <= N; row++) { // row
				for (int col = 1; col <= M; col++) { // col
					// 토마토가 다 익을수 없다면
					if (box[(N * M) * h + (M * row) + col] == 0)
						result = -1;
				}
			}
		}

		System.out.println(result);

	} // end of main

	public static int tomatoBFS(int H, int N, int M, Queue<int[]> queue, int[] box) {
		// 배열 범위 벗어나면 안됨. 패딩으로 처리했음
		// 패딩으로 처리하면 메모리를 (면 6개 - 겹치는 변 4개) 만큼 더 씀
		// 하지만 매번 6방향의 조건을 체크하는것보다 나을거라고 생각함
		int[] dr = { 0, 0, -1, 1, 0, 0 };
		int[] dc = { 0, 0, 0, 0, -1, 1 };
		int[] dh = { -1, 1, 0, 0, 0, 0 };

		// 맨 처음상태는 날짜가 지난것이 아닌데도 큐가 차있기만 하면 +1 해주니까 -1부터 시작해야한다.
		int day = -1;

		// tomato 검사 값이 -1, 1 인 경우 진행이 불가
		// 현재 큐의 사이즈는 하루에 6방향 검사 회수
		while (!queue.isEmpty()) {
			// depth를 위해서 size를 저장
			int size = queue.size();
			// 하나 빼면서 시작하기때문에 --size
			while (--size >= 0) {
				
				int[] current = queue.poll();
				
				for (int i = 0; i < 6; i++) { // 6방향 인접 친구들 체크
					// 주변 애들 값이 0일때는 그친구를 queue에 넣어주고 익게해준다
					if (box[(N * M) * (current[0] + dh[i]) + (M * (current[1] + dr[i])) + current[2] + dc[i]] == 0) {
						box[(N * M) * (current[0] + dh[i]) + (M * (current[1] + dr[i])) + current[2] + dc[i]] = 1;
						queue.offer(new int[] { current[0] + dh[i], current[1] + dr[i], current[2] + dc[i] });
					}
						
				}
			}

			day++; // 하루가 지났다
		} // end of bfs queue

		return day;
	} // end of tomato()

} // end of class
