package BOJ0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_토마토_Gold5_백승훈_520ms {

	
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	/** 
	 * MEMO : N, M숫자가 헷갈리게 input이 들어와 순서바꿔서 체크
	 * 	
	 * 아이디어 :	익은 토마토의 좌표를 미리 저장해놓는다.
	 * 			BFS방식을 사용해 큐에 익은토마토의 좌표를 모두 넣고 4방에 있는 익지 않은 토마토자리에 
	 * 			1씩 증가시키며 값을 저장시킨다.
	 * 			BFS가 끝나고 상자를 확인하여 결과 출력
	 * 			
	 * 			처음에는 visit check를 사용했는데 
	 * 			box에 직접 익은 날짜를 입력하면서 Memoization 기법 활용 -> visitied 배열 사용 x
	 * 			크게차이나진 않지만 50ms 빨라진다.
	 */
	
	
	static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dc = {0, 0, -1, 1}; // 상 하 좌 우	
	static int N,M;
	static int[][] box;
	static Queue<int[]> queue = new LinkedList<int[]>();
//	private static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // 2 ≤ M,N ≤ 1,000
		N = Integer.parseInt(st.nextToken()); // 2 ≤ M,N ≤ 1,000
		box = new int[N+2][M+2]; 				// 박스 주변에 벽을 설치해서 인덱스 범위체크 x
//		visited = new boolean[N+2][M+2]; // 방문 확인 배열		
		
		// box input
		for(int i=1; i<=N; i++){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 1) {						// 익은 토마토 위치 저장
					queue.offer(new int[]{i, j});
//					visited[i][j] = true;
				}
			}
		}
	
		// 박스 테두리에 벽설치
		for(int i=0; i<N+2; i++)  // 왼쪽, 오른쪽 벽설치
			box[i][0] = box[i][M+1] = -1;
		for(int i=0; i<M+2; i++)  // 위 ,아래 벽 설치
			box[0][i] = box[N+1][i] = -1;
		
		BFS(); // bfs 시작
		
		int result = -1; 				// 최종 결과를 반활할 변수
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(box[i][j] == 0) { 	// 익지않은 토마토가 있으면 -1 출력
					System.out.println(-1);
					System.exit(0);
				} else {
					result = Math.max(result, box[i][j]); // max값 저장
				}
			}
		}
		
		System.out.println(result-1); // 처음에는 0일차		
	} // end of main

	private static void BFS() {
		// TODO 익은 토마토를 기준으로 BFS탐색
		
		while(!queue.isEmpty()) { // 큐에 남아있는 토마토가 없을떄까지 반복
			int row = queue.peek()[0]; // front 토마토의 행
			int col = queue.peek()[1]; // front 토마토의 열
			queue.poll(); // 바로 삭제
			
			for(int i=0; i<4; i++) { // 4방탐색을 위한 반복문
				int nr = row + dr[i]; // 인접 토마토의 행 좌표 
				int nc = col + dc[i]; // 인접 토마토의 열 좌표
				
				// visit체크 굳이 해야하나???
				if( box[nr][nc] == 0) { // 익지않은 토마토인데 방문한적이 없는 경우
					box[nr][nc] = box[row][col]+1;

					queue.offer(new int[] {nr,nc});
				}	
			}
		} // end of while
	
		
	} // end of bfs	
} // end of class















