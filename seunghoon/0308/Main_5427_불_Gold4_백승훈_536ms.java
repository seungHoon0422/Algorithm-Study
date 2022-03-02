package BOJ0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5427_불_Gold4_백승훈_536ms {

	
	private static int N, M;
	private static char[][] map;
	private static Queue<Pos> fires;
	private static Queue<Pos> q;	
	private static int[] dr = {1,-1,0,0};
	private static int[] dc = {0,0,1,-1};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		for(int tc=0; tc<TC; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			map = new char[N][];
			fires=new LinkedList<Pos>();
			q = new LinkedList<Pos>();
			
			for(int i=0; i<N; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j=0; j<M; j++) {
					if(map[i][j] == '@') {
						q.offer(new Pos(i,j,1));
						map[i][j] = '.';
					} else if(map[i][j] == '*') {
						fires.offer(new Pos(i,j,0));
					}
				}
			} // end of input
			
			bfs();
		} // end of tc
		
	} // end of main

	private static void bfs() {

		boolean[][] visited = new boolean[N][M];	// 이미 방문했는지 체크하기위한 배열
		visited[q.peek().r][q.peek().c] = true;		// 시작지점 체크
		
		while(!q.isEmpty()) {
			fire();		// 불을 한칸 번지게 한 후 진행 => 불이 번질 칸에 가는 경우 제외시키기 위해서
			int qsize = q.size();	
			for(int s=0; s<qsize; s++) {	// 한칸 진행할 때 마다 확인
				Pos front = q.poll();		

				for(int i=0; i<4; i++) {
					int nr = front.r+dr[i];
					int nc = front.c+dc[i];
					if(nr < 0 || nr>N-1 || nc < 0 || nc > M-1) {	// 탈출 성공할 경우
						System.out.println(front.time);
						return;
					}
					if(map[nr][nc] == '.' && !visited[nr][nc]) {	// 방문하지 않고, 이동 가능한 좌표는 이동
						visited[nr][nc] = true;
						q.offer(new Pos(nr,nc,front.time+1));
					}
				}
				
			}
		}
		System.out.println("IMPOSSIBLE");
		
		
		
	}

	private static void fire() {
		
		int size = fires.size();
		for(int s=0; s<size; s++) {
			Pos pos = fires.poll();

			for(int d=0; d<4; d++) {
				int nr = pos.r+dr[d];
				int nc = pos.c+dc[d];
				
				if(0<=nr && nr<N && 0<=nc && nc<M && map[nr][nc] == '.') {
						fires.offer(new Pos(nr,nc,0));
						map[nr][nc] = '*';
				}
			}
		}
	}
	
} // end of class

class Pos{
	int r, c, time;

	public Pos(int r, int c, int time) {
		super();
		this.r = r;
		this.c = c;
		this.time = time;
	}
}




