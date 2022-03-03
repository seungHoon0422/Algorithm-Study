import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** Main 불 5427*/
public class Main_백준_5427_불_골드4_676ms {
	private static class Node{
		int row, col;
		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	private static int sr, sc, W, H;
	private static Queue<Node> q, fire;
	private static char[][] map;
	private static int[][] vis;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	
	/** 불이 번지는 메서드*/
	private static void spreadFire() {
		/** 큐에 저장된 불에 개수만큼 반복하면서 번짐*/
		for(int repeat=0, len=fire.size(); repeat<len; repeat++) {
			Node curr = fire.poll();
			for(int i=0; i<4; i++) {
				int nr = curr.row + dr[i];
				int nc = curr.col + dc[i];
				// 범위 체크
				if(nr<0 || nr>=H || nc<0 || nc>=W) continue;
				// 빈칸이면 불이 번진다.
				if(map[nr][nc]=='.') {
					map[nr][nc]='*';
					// 번진 위치 기억
					fire.offer(new Node(nr, nc));
				}
			} // end of for 4 dir 
		} // end of for 
	} // end of spreadfire
	
	/** BFS 알고리즘*/
	private static String go() {
		// 시작 좌표 빈칸으로 표시
		map[sr][sc] = '.';
		vis = new int[H][W];
		vis[sr][sc] = 1;
		q = new LinkedList<Node>();
		q.offer(new Node(sr, sc));
		
		while(!q.isEmpty()) {
			// 한턴 돌리기
			for(int repeat=0, len=q.size(); repeat<len; repeat++) {
				Node curr = q.poll();
				int row = curr.row;
				int col = curr.col;
				// 해당 좌표가 불에 탄 지점이라면
				if(map[row][col]=='*') continue;
				for(int i=0; i<4; i++) {
					int nr = row + dr[i];
					int nc = col + dc[i];
					// 배열 밖으로 나갔다면
					if(nr<0 || nr>=H || nc<0 || nc>=W) {
						return String.valueOf(vis[row][col]);
					}
					// 갈 수 있는 좌표라면
					else if(map[nr][nc]=='.' && vis[nr][nc]==0) {
						vis[nr][nc] = vis[row][col]+1;
						q.add(new Node(nr, nc));
					}
				} // end of 4방 탐색
			} // end of for
			spreadFire();
		} // end of while
		// 탈출하지 못한 경우 
		return "IMPOSSIBLE";
	} // end of go
	
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		
		/*
		 * 상근이 이동 -> 불 이동 
		 * 상근이 좌표가 불에 탄 좌표인지 확인 후 위 과정 반복
		 * 배열을 벗어나면 탈출 성공, 벗어나지 못한디면 탈출 실패
		 */
		
		for(int tc = 1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			fire = new LinkedList<>();
			map = new char[H][W];
			for(int i=0; i<H; i++) {
				String temp = br.readLine();
				for(int j=0; j<temp.length(); j++) {
					map[i][j] = temp.charAt(j);
					// 상근이의 위치 저장
					if(temp.charAt(j)=='@') {
						sr=i; sc=j;
					}
					// 불의 위치 저장
					else if(temp.charAt(j)=='*') {
						fire.offer(new Node(i, j));
					}
				}
			}
			// BFS돌면서 정답 저장
			sb.append(go()).append("\n");
		} // end of for tc 
		System.out.println(sb.toString());
	} // end of main
} // end of class
