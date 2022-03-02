import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/*

 	아이디어 :
  	DFS방식을 사용, 목표가 벽을 최대한 부시지 않고 마지막 방에 도착하는 것이므로
  	많이 돌아가도 벽을 최대한 부수지 않느 방법을 선택한는게 정답 => 4방 탐색을 진행하는데 pruning하기가 힘들다. => 완전탐색으로 풀이
  	DFS를 진행하면서 현재 좌표와, 길을 오면서 부순 벽의 개수를 카운팅 한 후 최종 목표에 도달했을 때 값을 갱신해준다.

  
 
*/
public class Main_2665_미로만들기_Gold4_백승훈 {

	private static StringBuilder sb;
	private static int N;
	private static char[][] board;
	private static int result = Integer.MAX_VALUE;
	private static int[] dr= {0, 1, 0, -1};
	private static int[] dc= {1, 0, -1, 0};
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		board = new char[N][];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		visited[0][0] = true;
		dfs(0,0,0);
		System.out.println(result);
		
		 
	} // end of main

	private static void dfs(int r, int c, int block) {
		System.out.println(r+" "+c+" "+block);
		if(r==N-1 && c==N-1) {
			result  = Math.min(result, block);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc]) {
				visited[nr][nc] = true;
				if(board[nr][nc] == '0') dfs(nr,nc,block+1);
				else dfs(nr,nc,block);
				visited[nr][nc] = false;
			}
		}
		
	}
	
	
	
	
	
	
	
	
} // end of class



