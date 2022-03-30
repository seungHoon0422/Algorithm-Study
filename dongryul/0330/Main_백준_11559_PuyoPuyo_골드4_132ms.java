import java.util.*;
import java.io.*;

/** Main_백준_11559_PuyoPuyo_골드4_132ms*/
public class Main_백준_11559_PuyoPuyo_골드4_132ms {
	
	private static class Node{
		int row, col;
		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	private static int row, col, nr, nc;
	private static char[][] map;
	private static boolean[][] vis;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static Queue<Node> q = new LinkedList<>();
	private static Queue<Node> team = new LinkedList<>();
	private static Queue<Node> delete = new LinkedList<>();
	private static Deque<Character> chain = new ArrayDeque<>();

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[12][];
		vis = new boolean[12][6];
		for(int i=0; i<12; i++) map[i] = br.readLine().toCharArray();
		// end of initialize
		
		/*
		 * 뿌요뿌요 문제
		 * 좌표의 탐색은 위에서부터 진행
		 * 방문되지 않은 좌표 중 뿌요를 발견하면, 같은 색의 뿌요들을 BFS를 통해 찾고
		 * 4개 이상 발견되면 해당 delete 큐에 존재하는 모든 진짜 처리 큐에 넣기 ( 동시에 N개 뿌요가 연쇄되면 1연쇄로 인정 )
		 * 3개 이하면 delete 큐 날려버리기
		 * 
		 * 한 턴이 종료될 때 진짜 처리될 뿌요 큐에 저장된 좌표들을 연쇄 처리 후 
		 * 좌표 갱신 ( 2048 처럼 )
		 */
		
		// 연쇄의 수 카운팅
		int cnt = 0;
		while(true) {
			// 1. 위에서부터 탐색
			for(int r=0; r<12; r++) {
				for(int c=0; c<6; c++) {
					if(map[r][c]!='.' && !vis[r][c]) {
						// 2. 방문되지 않은 좌표 중 뿌요 발견 같은 색 뿌요 BFS 
						go(r, c, map[r][c]);
					}
				}
			}
			// 처리될 뿌요가 없을 때 종료
			if(delete.isEmpty()) break;
			// 처리될 뿌요가 있다면 처리 후 배열 당기기, 방문 배열 초기화 등 작업 수행
			InitGame();
			cnt++;
		}
		
		System.out.println(cnt);
		
	} // end of main

	private static void InitGame() {
		// 좌표 . 으로 갱신
		while(!delete.isEmpty()) {
			Node curr = delete.poll();
			map[curr.row][curr.col] = '.';
		}
		
		// 바닥으로 당기기
		for(int c=0; c<6; c++) {
			for(int r=0; r<12; r++) {
				if(map[r][c]!='.') chain.offer(map[r][c]);
				map[r][c] = '.';
			}
			// 바닥에 추가
			row = 11;
			while(!chain.isEmpty()) {
				map[row--][c] = chain.pollLast();
			}
		}
		// 방문 배열 초기화
		for(int i=0; i<12; i++) Arrays.fill(vis[i], false);
	}

	private static void go(int sr, int sc, char puyo) {
		// 방문 처리
		vis[sr][sc] = true;
		q.offer(new Node(sr, sc));
		team.offer(new Node(sr, sc));
		// 몇 개짜리 쌍인지 확인
		while(!q.isEmpty()) {
			Node curr = q.poll();
			row = curr.row;
			col = curr.col;
			for(int i=0; i<4; i++) {
				nr = row + dr[i];
				nc = col + dc[i];
				// 범위를 벗어나거나 / 이미 방문했거나 / 같은 색이 아니라면
				if(nr<0 || nr>=12 || nc<0 || nc>=6 || vis[nr][nc] || map[nr][nc]!=puyo) continue;
				vis[nr][nc] = true;
				Node next = new Node(nr, nc);
				team.offer(next);
				q.offer(next);
			}
		}
		
		// 4개 이상인 쌍이라면 연쇄 대상에 추가
		if(team.size()>=4) {
			while(!team.isEmpty()) delete.offer(team.poll());
		} 
		// 3개 이하면 그냥 날리기
		team.clear();
	}
	
} // end of class 
