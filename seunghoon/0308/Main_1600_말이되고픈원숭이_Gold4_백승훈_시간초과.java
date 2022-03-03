package BOJ0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1600_말이되고픈원숭이_Gold4_백승훈_시간초과 {

	
	private static int K;
	private static int M;
	private static int N;
	private static int[][] board;
	private static boolean[][] visited;
	private static int result;
	private static int[] dr= {1,2,1,0};
	private static int[] dc= {2,1,0,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		K =Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M];
		result = -1;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end of input
		
		visited[0][0] = true;
		dfs(0,0,K,0);
		
		System.out.println(result);
		
		
		
	} // end of main

	private static void dfs(int sr, int sc, int k, int cnt) {
		if(k<0) return;
//		System.out.println(cnt+" : "+sr+" "+sc+" "+k+" ");
		if(sr==N-1 && sc==M-1) { //기저조건
			System.out.println(cnt);
			System.exit(0);
			if(result > 0) result = Math.min(result, cnt);
			else result = cnt;
			return;
		}
		
		for(int i=0; i<4 ;i++) {
			int nr = sr+dr[i];
			int nc = sc+dc[i];
			if(nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc] && board[nr][nc]!=1) {
				visited[nr][nc] = true;
				if(i<2) dfs(nr,nc,k-1,cnt+1);
				else dfs(nr,nc,k,cnt+1);
				visited[nr][nc] = false;
			}
		}
	}

	


} // end of class





