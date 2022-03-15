package src.boj.bfs_dfs;

import java.util.*;
import java.io.*;

// Main_백준_17472_다리만들기2_골드2_128ms
public class Main_백준_17472_다리만들기2_골드2_128ms {
	
	// 좌표를 저장할 클래스
	private static class land implements Comparable<land>{
		int row, col, island;
		public land(int row, int col, int island) {
			super();
			this.row = row;
			this.col = col;
			this.island = island;
		}
		@Override
		public int compareTo(land o) {
			// TODO Auto-generated method stub
			return this.island - o.island;
		}
	}
	
	// MST를 위한 class 
	private static class Edge implements Comparable<Edge>{
		int from, dest, length;

		public Edge(int from, int dest, int length) {
			super();
			this.from = from;
			this.dest = dest;
			this.length = length;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.length-o.length;
		}
	}
	
	private static int N;
	private static int M;
	private static int island = 1;
	private static int[][] map;
	// 상하좌우
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static PriorityQueue<land> bridge;
	private static PriorityQueue<Edge> edge;

	public static void main(String[] args) throws Exception{
		
		/*
		 * 먼저 섬의 구역을 나눈다
		 * 각 섬에서부터 가로-세로 방향으로 다른 섬과 연결 시도
		 * 이때 다리는 가로 세로 일자로만 설치할 수 있고,
		 * 거리는 2 이상이어야 한다. 
		 * 
		 * 모든 섬과 연결되는 2 이상의 다리의 간선 정보를 저장하고,
		 * 이를 통해 MST를 구현한다.
		 * ( 뭔가 잘 짠듯? )
		 * 
		 * 예제에서 궁금한 점
		 * 예시 중 총 길이가 12인 부부네서, 3-5는 길이가 2로 2-5보다 짧은데 왜 채택이 안된건가
		 * 자세히 예제를 보니, 길이가 12라고 예시로 나온거지, 정답이 12는 아니었다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 간선의 정보를 저장할 우선순위 큐
		edge = new PriorityQueue<Edge>();
		// 모든 섬을 연결 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1) {
					go(i, j);
				}
			}
		}
		
		// MST 구현 
		// 섬의 개수만큼 생성
		int[] p = new int[island+1];
		for(int i=0; i<=island; i++) {
			p[i] = i;
		}
		
		int distance = 0;
		int cnt = 0;
		int limit = island-2; // 2부터 시작해서
		// 간선 우선순위큐가 빌 때까지 반복
		while(!edge.isEmpty()) {
			Edge curr = edge.poll();
			if(union(curr.from, curr.dest, p)) {
				distance += curr.length;
				// 모든 섬을 연결했다면 거리 출력 후 종료
				if(++cnt==limit) {
					System.out.println(distance);
					return;
				}
			}
		}
		// 모든 섬 연결 실패
		System.out.println(-1);
		
	} // end of main
	
	private static int findSet(int a, int[] p) {
		if(a==p[a]) return a;
		return p[a] = findSet(p[a], p);
	}
	
	private static boolean union(int a, int b, int[] p) {
		int ar = findSet(a, p);
		int br = findSet(b, p);
		if(ar==br) return false;
		p[br] = ar;
		return true;
	}

	// BFS인데, 다리처럼 쭉 가버리는?
	private static void go(int sr, int sc) {
		
		bridge = new PriorityQueue<land>();
		// 1 구역 나누기
		sameLand(sr, sc, ++island);
		
		while(!bridge.isEmpty()) {
			land curr = bridge.poll();
			int currIsland = curr.island;
			int row = curr.row;
			int col = curr.col;
			for(int i=0; i<4; i++) {
				int cnt = 1;
				while(true) {
					int nr = row+dr[i]*cnt;
					int nc = col+dc[i]*cnt;
					// 범위를 벗어났거나, 같은 섬이라면
					if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]==currIsland) break;
					// 미발견된 다른 섬이 도달했다면? or 이미 발견된적 있는 섬이라면
					if(map[nr][nc]==1 || map[nr][nc]!=0) {
						// 다리의 길이가 2 이상이라면
						if(cnt>2) {
							// 미발견된 섬이라면
							if(map[nr][nc]==1) {
								// 구역 표시
								sameLand(nr, nc, ++island);
							}
							// 시작섬 -> 도착섬, 거리
							edge.offer(new Edge(currIsland, map[nr][nc], cnt-1));
						}
						// 아무튼 다른 섬을 만나면 종료
						break;
					}
					cnt++;
				} // end of while
			} // end of for 4 dir
		} // end of while
	} // end of go
	
	// 같은 구역인지 확인하면서 BFS를 할 좌표를 추가한다.
	private static void sameLand(int sr, int sc, int island) {
		// 1 구역 나누기
		Queue<land> q = new LinkedList<land>();
		// 시작 섬 영역 표시
		map[sr][sc] = island;
		bridge.offer(new land(sr, sc, island));
		q.offer(new land(sr, sc, island));
		while(!q.isEmpty()) {
			land curr = q.poll();
			int row = curr.row;
			int col = curr.col;
			for(int i=0; i<4; i++) {
				int cnt = 1;
				while(true) {
					int nr = row+dr[i]*cnt;
					int nc = col+dc[i]*cnt;
					// 범위를 벗어나거나 같은 구역의 섬이 아닌 경우
					if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]==0 || map[nr][nc]==island) break;
					map[nr][nc]=island;
					q.offer(new land(nr, nc, island));
					// 탐색할 좌표 추가
					bridge.offer(new land(nr, nc, island));
					cnt++;
				}
			} // end of for
		} // end of while same land
	}
	
	
} // end of class



