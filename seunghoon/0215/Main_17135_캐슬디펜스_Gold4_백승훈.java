package BOJ0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17135_캐슬디펜스_Gold4_백승훈 {

	
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	/** 
	 * MEMO : 
	 * 격자판의 크기 N*M
	 * 턴제 게임
	 * 각 칸에있는 적의 수 : 최대 1명
	 * N+1칸에는 성이 있다
	 * 궁수가 공격하는 적은 거리가 D이하인 적 중 가까운 적 -> 여러명이면 가장 왼쪽에 있는 적을 공격
	 * 같은 적이 여러 궁수에게 공격당할 수 있다, 공격받은 적은 게임에서 제외된다.
	 * 적은 아래로 한칸 이동하며, 성이 있는 칸으로 이동하면 게임에서 제외
	 * 
	 * 
	 * 아이디어 	
	 * 3명의 궁수 배치 -> 조합을 통해 위치 선택
	 * N+1칸에 궁수 위치를 배치시키는데 궁수는 2로 표시
	 * BFS를 통해 적의 위치를 파악하기 위해 왼쪽, 오른쪽, 위쪽에는 벽(-1)을 표시 
	 * 공격당한 적의 인덱스를 저장(궁수 1명은 1명만 공격가능하고, 총 3번의 공격이 가능하기 때문)
	 * 궁수위치를 파악해 놓고, 해당 인덱스 부터 BFS방식을 통해 거리 D안에 있는 적 파악		
	 * 남은 적의 수 파악 -> 적이 남아있으면 아래로 한칸 이동
	 * 
	 */
	
	static int N, M, D; // 3 ≤ N, M ≤ 15, 1 ≤ D ≤ 10
	
	//bfs에 활용하기 위해 4방탐색 좌표
	// 왼쪽을 우선적으로 탐색하기 위해서 좌 상 우 순서로 배치
	static int dr[] = {0, -1, 0}; // 좌 상 우 
	static int dc[] = {-1, 0, 1}; // 좌 상 우
	static int[][] board; // 격자판
	static int[][] originalBoard;
	static int[] archor;
	static List<int[]> archorPosition;
	private static int archorKill;
	private static int moveCount;
	private static int enemyDead;
	private static int enemy;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		board = new int[N+2][M+2];			// N+1행에는 궁수배치, 벽테두리 설정
		originalBoard = new int[N+2][M+2];			// N+1행에는 궁수배치, 벽테두리 설정
		archor = new int[M+2];
		archorPosition = new LinkedList<>();
		
		
		for(int i=1; i<=N; i++) { 			// 보드판 입력
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) enemy++;
			}
		}
		
		// 벽 설치
		for(int i=0; i<=N+1; i++) board[i][0] = board[i][M+1] = -1;
		for(int j=0; j<=M+1; j++) board[0][j] = -1;
		
		
		
		// 궁수 배치
		combination(0, 1, new int[3]);
		for (int[] position : archorPosition) {
			archor = position; 					// 궁수 위치 배치		
			archorKill = 0;						// 궁수가 죽인 적의 수 초기화
			moveCount = 0;
			enemyDead = 0;
			
			for(int block=0; block<=M+1; block++) // 성벽도 -1로 초기화
				board[N+1][block] = -1;
			board[N+1][position[0]] = board[N+1][position[1]] = board[N+1][position[2]] = 2;
			// 궁수가 있는 위치 이외의 장소는 -1처리 -> bfs효율 증가
			
			// bfs를 한명씩 차례대로 도는데 거리안에 가장 가까운 적을 찾으면 바로 큐에있는 좌표를 반환
			System.out.println("Enemy : "+enemy);
			int turn = 1;
			while(archorKill + enemyDead < enemy) {
				System.out.println("turn "+turn++);
				BFS(archor[0]);
				BFS(archor[1]);
				BFS(archor[2]);
				moveEnemy();
				
				System.out.println("PRINT");
				for(int i=0;i<=N+1; i++)	System.out.println(Arrays.toString(board[i]));
				System.out.println("archorKill : " + archorKill);
				System.out.println("enemyDead : " + enemyDead);
			}
			
			
			// 적 아래로 1칸 이동
			
			
			break;
		
		}
		
		
		
		
		
	} // end of main

	
	private static void moveEnemy() {
		// TODO 적 아래로 한칸 이동
		// 성벽 바로 위에서부터 탐색
		System.out.println("move enemy");
		for(int i=N; i>=moveCount; i--) { 		// 체크하지 않아도 되는 부분 제외
			for(int j=1; j<=M; j++) {
				if(board[i][j] == 1 && i==N) {
					board[i][j] = 0;
					enemyDead++;
				} else if(board[i][j] == 1) {
					board[i+1][j] = board[i][j];
					board[i][j] = 0;
				}
			}
		}	
	}



	private static void BFS(int ac) {
		// TODO 궁수의 위치 입력, 거리안에 있는 적을 죽이는 함수
		// 큐안에서 적을 찾을때 까지
		if(archorKill + enemyDead >= enemy) return;
		
		System.out.println("BFS start : "+ac);
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N+2][M+2]; 
		queue.offer(new int[] {N+1, ac});
		visited[N+1][ac] = true;
q:		while(!queue.isEmpty()) {
			int r = queue.peek()[0];
			int c = queue.peek()[1];
			queue.poll();
			
			for(int i=0; i<3; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(getDistance(r,c,nr,nc) >D) continue;	// 거리가 D를 넘어가면 통과
				if(!visited[nr][nc] && board[nr][nc] != -1) {
					queue.offer(new int[] {nr,nc});
					if(board[nr][nc] == 1) { 			// 적을 찾은 경우
						board[nr][nc] = 0;				// 적을 죽이고
						archorKill++;					// 죽인 적 증가
						return;
					}
				}
			}
			
		}
		
	}

	private static int getDistance(int r, int c, int nr, int nc) {
		// TODO Auto-generated method stub
		return Math.abs(r-nr)+Math.abs(c-nc);
	}



	// 궁수의 위치를 반환할 
	public static void combination(int cnt, int start, int[] result) {
		if(cnt == 3) {
			int[] position = new int[] {result[0], result[1], result[2] };
			archorPosition.add(position);
			return;
		}
		
		for(int i=start; i<=M; i++) {
			result[cnt] = i;
			combination(cnt+1, i+1, result);
		}
	} // end of combination
	
} // end of class















