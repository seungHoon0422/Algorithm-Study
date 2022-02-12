package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 개선할점 : 익은 토마토 안익은 토마토 수를 카운트해서 응용할 것
 * 	-> 처음에 배열 -1로 초기화 x, 배열 전체에 0이 남아있는지 검사 x
 */

public class Main_백준_7569_토마토_골드5_600ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int[] dr = { 0, 0, -1, 1, 0, 0 }; // 6방향
		int[] dc = { 0, 0, 0, 0, -1, 1 };
		int[] dh = { -1, 1, 0, 0, 0, 0 };

		int M = Integer.parseInt(st.nextToken()); // 토마토 상자의 정보 입력, M N H 순서
		int N = Integer.parseInt(st.nextToken()); // N=row, M=col, H=height
		int H = Integer.parseInt(st.nextToken());

		int[] box = new int[N * M * H]; // 바깥에 padding

		int noTomato = 0; // 안익은 토마토의 개수
		int yesTomato = 0; // 익히게 만들어줄 토마토의 개수

		Queue<int[]> queue = new LinkedList<int[]>(); // bfs용 queue 생성

		// 상자에 입력, O(N*M*H)
		for (int h = 0; h < H; h++) {
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int col = 0; col < M; col++) {
					int newH = (N * M) * h;
					int newR = M * row;
					int newC = col;
					// 높이(한 층에 N*M 만큼) row(한 줄에 col만큼)
					box[newH + newR + newC] = Integer.parseInt(st.nextToken());
					if (box[newH + newR + newC] == 1) {
						queue.offer(new int[] { h, row, col }); // 익은 토마토의 idx를 전부 queue에 넣어준다
					} else if (box[newH + newR + newC] == 0)
						noTomato++; // 안익은 토마토 개수 동시에 센다
				}
			}
		} // 입력 종료

		int day = 0;
		// 방금 입력에서 큐에 몇개 넣으면서 동시에 다 익었다면 틀리게 된다
		// 토마토 개수 비교조건을 하나 더 넣는다
		while (!queue.isEmpty() && (yesTomato < noTomato)) {
			for (int q = queue.size(); q > 0; q--) {

				int[] current = queue.poll();

				for (int i = 0; i < 6; i++) { // 6방향 인접 토마토 체크
					int newH = current[0] + dh[i];
					int newR = current[1] + dr[i];
					int newC = current[2] + dc[i];
					// 조건문이 까다롭다.
					if (newH < 0 || newR < 0 || newC < 0 || newH >= H || newR >= N || newC >= M
							|| box[(N * M) * newH + (M * newR) + newC] != 0)
						continue;

					yesTomato++; // 익은 토마토수 증가
					box[(N * M) * newH + (M * newR) + newC] = 1;
					queue.offer(new int[] { newH, newR, newC });
				}
			}

			day++;
		} // end of while queue

		day = (noTomato == yesTomato) ? day : -1;

		System.out.println(day);

	} // end of main
} // end of class
