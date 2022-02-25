package BOJ0302;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
	.....    .....
	SYSYS    SYSYS
	....Y    .Y...
	....S    .S...
	.....    .....
	
	아이디어 1:
	모든 지점에서 DFS탐색을 진행하는데 Y의 개수가 3을 넘어가면 프루닝
	하지만 이아이디어로는 두번째 케이스를 잡기가 힘듬...
	
	아이디어 2 : 
	모든 지점에서 BFS시작
	queue안에 현재 속해있는 
	
	
 */
public class Main_1941_소문난칠공주_Gold3_백승훈_712ms {

	private static StringBuilder sb;
	private static char[][] board;
	private static boolean[][] visited;
	private static int result;
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();
		board = new char[5][];
		
		for(int i=0; i<5; i++) board[i] = br.readLine().toCharArray();
		result = 0;
		combination(0,0,new int[7]);
		
		System.out.println(result);
		
		 
	} // end of main

	private static void combination(int cnt, int start, int[] select) {
		
		if(cnt == 7) {
			int scount=0,ycount=0;
			int[][] map = new int[5][5];
			for (int index : select) {
				int r = index/5;
				int c = index%5;
				map[r][c] = 1;
				if(board[r][c] == 'S') scount++;
				else ycount++;
			}
			if(scount >=4) bfs(select);
			return;
		}
		
		for(int i=start; i<25; i++) {
			select[cnt] = i;
			combination(cnt+1, i+1, select);
		}
	}

	private static void bfs(int[] select) {
		
		int adjcount = 0;
		visited = new boolean[5][5];
		Queue<Integer> q = new LinkedList<>();
		q.offer(select[0]);
		boolean[]  visit = new boolean[7];
		visit[0] = true;
		visited[select[0]/5][select[0]%5] = true;

		while(!q.isEmpty()) {
			int index = q.poll();
			int r = index/5;
			int c = index%5;
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(0<=nr && nr<5 && 0<=nc && nc<5 && !visited[nr][nc]) {
					for(int a=1; a<7; a++) {
						if(nr==select[a]/5 && nc==select[a]%5) {
							visit[a] = true;
							visited[nr][nc] = true;
							q.offer(nr*5+nc);
						}
					}
					
				}
			}
		}
		for(int i=0; i<7; i++) if(!visit[i]) return;
//		System.out.println(Arrays.toString(select));
		result++;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // end of class
