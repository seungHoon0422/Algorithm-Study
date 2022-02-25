import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_백준_1941_소문난칠공주_골드3_300ms{
	
	private static class Node{
		int row, col;
		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	private static boolean[][] visited;
	private static char[][] map;
	private static int total;
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception{
		
		/*
		 * BFS로 접근해야 함 
		 * 우선 25C7 ( S >= 4 )를 뽑고 --> 이 방법이 아니면 경로 체크를 해야함
		 * 선택한 좌표들이 연속적인지 체크 -> BFS로
		 * 
		 * + DFS로 접근해봤는데, 역시 안됩니다. 
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[5][5];
		for(int i=0; i<5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		total = 0;
		go(0, 0, 0, new boolean[25]); // 시작점, 이다솜파, 고른개수, 방문배열
		
		System.out.println(total);
	} // end of main 

	private static void go(int start, int S, int picked, boolean[] vis) {
		// 임도연파가 4명 이상이면 return
		if(picked-S>=4) return;
		// S가 4이상이고, 7명을 뽑았을 때
		if(picked==7) {
			// 이전에 한칸 증가했으므로 뒤로 한칸
			BFS((start-1)/5, (start-1)%5, vis);
			return;
		}
		// 조합 알고리즘
		for(int i=start; i<25; i++) {
			vis[i] = true;
			if(map[i/5][i%5]=='S') go(i+1, S+1, picked+1, vis);
			else go(i+1, S, picked+1, vis);		
			vis[i] = false;
		}
		
	} // end of go 

	// row col에서 시작하여 인접한 좌표들 중 vis에 속한 좌표가 있는지 확인
	private static void BFS(int sr, int sc, boolean[] vis) {
		visited = new boolean[5][5];
		visited[sr][sc] = true;
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(sr, sc));
		int cnt=1;
		while(!q.isEmpty()) {
			Node curr = q.poll();
			int row = curr.row;
			int col = curr.col;
			for(int i=0; i<4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];
				// 범위를 벗어나거나, 이미 방문한 지점이라면
				if(nr<0 || nr>=5 || nc<0 || nc>=5 || visited[nr][nc]) continue;
				// 가능한 지점이 아니라면
				if(!vis[nr*5+nc]) continue;
				// 가능한 지점이고, 방문한적 없는 좌표라면
				q.offer(new Node(nr, nc));
				visited[nr][nc] = true;
				cnt++;
			} // end of for dir 
		} // end of while 
		// 모든 경로가 연결되었을 때 
		if(cnt==7) total++;
	} // end of BFS 
} // end of class 

