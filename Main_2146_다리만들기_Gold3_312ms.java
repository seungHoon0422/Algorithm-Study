package BOJ0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2146_다리만들기_Gold3_312ms {
/*
 * 기본적인 아이디어 : 
 * 각 대륙에서 길이가 1찍 증가한 대륙을 표시하고 맞닿은 부분이 있는지 체크한다.
 * 가장 처음 맞닿은 부분이 짧은 다리가 되므로 그때를 캐치!!
 * 각 대륙별로 큐를 만들어서 테두리에 해당하는 좌표값들을 저장한다.
 * 큐의 크기만큼 bfs를 돌리면서 길이를 1씩 증가시킨다.
 * 
 */
	
	
	private static int N;
	private static int[][] board;
	private static int mapNumber;
	private static boolean[][] visited;
	private static int[] dr = {1,-1,0,0};
	private static int[] dc = {0,0,1,-1};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		board = new int[N+2][N+2];
		Arrays.fill(board[0], -1);
		Arrays.fill(board[N+1], -1);
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			board[i][0] = board[i][N+1] = -1;
			for(int j=1; j<=N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		
		// 대륙 번호 나누기
		mapNumber = 1;
		visited = new boolean[N+2][N+2];
		for(int i=1; i<=N; i++) 
			for(int j=1; j<=N; j++) {
				if(!visited[i][j] && board[i][j] != 0) {
					visited[i][j] = true;
					countingMap(i, j, mapNumber++);
				}
			}
		
		// 대륙을 순회하면서 가장 짧은 거리를 반환
		int answer = Integer.MAX_VALUE;
		for(int i=1; i<mapNumber; i++) {
			visited = new boolean[N+2][N+2];
			answer = Math.min(answer, BFS(i));
		}
		System.out.println(answer);
		
	}

	private static int BFS(int val) {
		
		Queue<int[]> q = new LinkedList<int[]>();
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(board[i][j] == val && !visited[i][j]) {
					q.offer(new int[] {i,j});
					visited[i][j] = true;
				}
			}
		}
		
		int distance = 0;
		while(!q.isEmpty()) {
			
			int size = q.size();
			for(int d=0; d<size; d++)
			{
				int[] front = q.poll();
				int r = front[0];
				int c = front[1];
				for(int i=0; i<4; i++) {
					int nr = r+dr[i];
					int nc = c+dc[i];
				
					if(!visited[nr][nc] && board[nr][nc] > 0) {
						// 다른 대륙을 처음 만났을 때
						return distance;
					} else if(!visited[nr][nc] && board[nr][nc] == 0) {
						visited[nr][nc] = true;
						q.offer(new int[]{nr,nc});
					}
				}
			}
			distance++;
		}
		return distance;
	}

	private static void countingMap(int r, int c, int number) {
		
		board[r][c] = number;
		visited[r][c] = true;
		
		for(int i=0; i<4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(!visited[nr][nc] && board[nr][nc] == 1){
				countingMap(nr, nc, number);
			}
		}
		
		
	}
	
	
}

