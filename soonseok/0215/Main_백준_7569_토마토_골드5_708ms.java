package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 개선할점 : 익은 토마토 안익은 토마토 수를 카운트해서 응용할 것
 * 	-> 처음에 배열 -1로 초기화 x, 배열 전체에 0이 남아있는지 검사 x
 * 
 * 1차원 배열의 경우엔 인덱싱을 해줘도 어차피 음수로 index가 갈 수 있어서 패딩 별로
 */

public class Main_백준_7569_토마토_골드5_708ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int[] dr = { 0, 0, -1, 1, 0, 0 }; // 6방향
		int[] dc = { 0, 0, 0, 0, -1, 1 };
		int[] dh = { -1, 1, 0, 0, 0, 0 };

		int M = Integer.parseInt(st.nextToken()); // 토마토 상자의 정보 입력, M N H 순서
		int N = Integer.parseInt(st.nextToken()); // N=row, M=col, H=height
		int H = Integer.parseInt(st.nextToken());

		int[][][] box = new int[N + 2][M + 2][H + 2]; // 바깥에 padding
		// -1로 초기화 시켜놔야한다 안그러면 테두리 기본값 0을 사용해버린다
		for (int h = 0; h <= H + 1; h++) {
			for (int row = 0; row <= N + 1; row++) {
				for (int col = 0; col <= M + 1; col++) {
					box[row][col][h] = -1;
				}
			}
		}

		int noTomato = 0; // 안익은 토마토의 개수
		int yesTomato = 0; // 익히게 만들어줄 토마토의 개수

		Queue<int[]> queue = new LinkedList<int[]>(); // bfs용 queue 생성

		// 상자에 입력, O(N*M*H)
		for (int h = 1; h <= H; h++) {
			for (int row = 1; row <= N; row++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int col = 1; col <= M; col++) {
					box[row][col][h] = Integer.parseInt(st.nextToken());
					if (box[row][col][h] == 1) {
						queue.offer(new int[] { h, row, col });
					} else if (box[row][col][h] == 0)
						noTomato++;
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

					// 조건문 검사가 확 줄어든다
					if (box[newR][newC][newH] == 0) {

						yesTomato++; // 익은 토마토수 증가
						box[newR][newC][newH] = 1;
						queue.offer(new int[] { newH, newR, newC });
					}
				}
			}

			day++;
		} // end of while queue

		day = (noTomato == yesTomato) ? day : -1;

		System.out.println(day);

	} // end of main
} // end of class
