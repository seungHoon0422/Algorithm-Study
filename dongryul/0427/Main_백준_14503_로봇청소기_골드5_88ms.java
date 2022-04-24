import java.io.*;
import java.util.*;

public class Main_백준_14503_로봇청소기_골드5_88ms {
	
	// 북 동 남 서
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		/*
		 * 1. 현재 위치 청소
		 * 2.1 현재 위치 바로 왼쪽에 청소하지 않은 빈 공건이 있다면 왼쪽 회전 후 한칸 전진 후 1번, 빈공간이 없다면 있을 때까지 회전,,
		 * 2.2 연속 4번 회전했는데 청소할 구역이 없다면 후진 ( 만약 바로 뒤쪽이 벽이면 작동을 멈춤 )
		 * 
		 * BFS? 틀을 유지하면서 주어진 순서대로 구현하자.
		 */
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		
		int[] cleaner = new int[3];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<3; i++) cleaner[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		go(cleaner, map, N, M);
		
	} // end of main 

	private static void go(int[] cleaner, int[][] map, int N, int M) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(cleaner);
		
		int[] curr = new int[3];
		int row, col, dir, nr, nc;
		boolean found = false;
		int cnt = 0;
		while(!q.isEmpty()) {
			curr = q.poll();
			row = curr[0];
			col = curr[1];
			dir = curr[2];
			found = false;
			// 1. 현재 위치 청소
			if(map[row][col]==0) {
				map[row][col] = 2;
				cnt++;
			}
			// 2.1 인접한 칸 탐색
			for(int i=1; i<=4; i++) {
				dir = (dir+3)%4;
				nr = row + dr[dir];
				nc = col + dc[dir];
				// 범위를 벗어났거나 벽이라면 
				if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]!=0) continue;
				// 인접한 좌표중 청소할 좌표를 찾은 경우
				found = true;
				q.offer(new int[] {nr, nc, dir});
				break;
			} // end of for 
			// 2.2 청소할 칸을 찾지 못했고, 뒤칸이 벽이 아닌 경우 후진해라
			if(!found && map[row+dr[(dir+2)%4]][col+dc[(dir+2)%4]] != 1) {
				q.offer(new int[] {row+dr[(dir+2)%4], col+dc[(dir+2)%4], dir});
			}
		} // end of while 
		
		System.out.println(cnt);
		
	} // end of go
	
} // end of class 
