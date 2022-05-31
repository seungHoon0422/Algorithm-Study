import java.io.*;
import java.util.*;

public class Main_백준_13460_구슬탈출2_골드1_556ms {
	
	private static class Node{
		int row, col;
		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		public boolean compare(Node o) {
			if(this.row==o.row && this.col==o.col) return true;
			return false;
		}
	}
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static char[][] map;
	private static boolean Rflag, Bflag;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Queue<Node> Red = new LinkedList<>();
		Queue<Node> Blue = new LinkedList<>();
		
		map = new char[N][];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'R') {
					Red.offer(new Node(i, j));
					map[i][j] = '.';
				} else if(map[i][j] == 'B') {
					Blue.offer(new Node(i, j));
					map[i][j] = '.';
				} 
			} // end of for column
		} // end of initialize 
		
		int ans = -1;
		int row, col;
		// 10턴 동안 반복
		for(int rep=1; rep<=10; rep++) {
			for(int s=0, size=Red.size(); s<size; s++) {
				Node red = Red.poll();
				Node blue = Blue.poll();
				// 4방향으로 진행
				for(int i=0; i<4; i++) {
					Rflag = Bflag = false;
					// 해당 방향 기준, 배열의 끝과 더 인접한 애 먼저 움직임, 다른 구슬이 벽에 닿기 전에 닿지 않게 하기 위해
					// 빨간 구슬 먼저
					row = red.row;
					col = red.col;
					// 다음칸이 벽이 아니라면 움직여라.
					while(map[row+dr[i]][col+dc[i]]!='#') {
						row += dr[i];
						col += dc[i];
						// 구멍에 들어갔다면
						if(map[row][col]=='O') {
							Rflag = true;
							break;
						}
					}
					Node nextR = new Node(row, col);
					
					row = blue.row;
					col = blue.col;
					// 다음칸이 벽이 아니라면 움직여라.
					while(map[row+dr[i]][col+dc[i]]!='#') {
						row += dr[i];
						col += dc[i];
						// 구멍에 들어갔다면
						if(map[row][col]=='O') {
							Bflag = true;
							break;
						}
					}
					Node nextB = new Node(row, col);
					// 파란 구슬이 들어가지 않은 경우 자리 갱신
					if(!Bflag) {
						// 파란 구슬은 안들어갔고 빨간 구슬만 들어간 경우
						if(Rflag) break;
						// 같은 칸으로 이동하게된 경우
						if(nextR.compare(nextB)) {
							// 빨간 구슬 우선
							if((i==0 && red.row<blue.row) || (i==1 && red.row>blue.row)
									|| (i==2 && red.col<blue.col) || (i==3 && red.col>blue.col)) {
								nextB = new Node(nextB.row-dr[i], nextB.col-dc[i]);
							}
							// 파란 구슬 우선
							else {
								nextR = new Node(nextR.row-dr[i], nextR.col-dc[i]);
							}
						}
						// 움직임 갱신
						Red.offer(nextR);
						Blue.offer(nextB);
					}
				} // end of 4 direction 
				// 빨간 구슬만 들어간 경우 반복 탈출
				if(Rflag && !Bflag) break;
			} // end of while Empty
			// 빨간 구슬이 구멍에 빠졌을 경우 탈출
			if(Rflag && !Bflag) {
				ans = rep;
				break;
			}
		} // end of for repeat 10 times
		
		System.out.println(ans);
		
	} // end of main
	
} // end of class
