package BOJ0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/*
	처음 아이디어 : 
	dfs를 진행하여 최종 목적지에 도착하는 경우를 count, 완전탐색 진행
	시간초과가 발생해서 탐색 범위를 좁혀보고자 오른쪽, 아래방향 => 목적지 방향으로 진행하는
	방향만 탐색했는데 여전히 시간초과 발생
	
	정답 아이디어 : 
	똑같은 지점에 도착을 해도 말의 움직임을 사용한 횟수가 다른 경우에는 다른 상황으로 이해
	따라서 모든 좌표에 K가 줄어드는 모든 경우에 대해 visit check를 진행한다.
	그전의 아이디어로는 똑같은 지점을 방문 했을 때 서로 독립적인 경우로 파악해야 한다고 생각해서
	dfs로 코드를 짯는데 visit을 3차원 배열로 구성하면서 bfs탐색으로 진행 가능



*/
public class Main_1600_말이되고픈원숭이_Gold4_백승훈_520ms {

	
	private static int K;
	private static int M;
	private static int N;
	private static int[][] board;
	private static boolean[][][] visited;
	private static int result;
	private static int[] dr= {1,-1,0,0};
	private static int[] dc= {0,0,1,-1};
	private static int[] hr= {-2,-1,1,2,2,1,-1,-2};
	private static int[] hc= {1,2,2,1,-1,-2,-2,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		K =Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M][K+1]; // 말의 움직임으로 가능한 경우느 총 K번
		result = -1;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end of input
		
		bfs();
		System.out.println(result);
		
		
		
	} // end of main

	private static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0,0,0,K));
		visited[0][0][K] = true;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.r==N-1 && node.c==M-1) {
				result = node.count;
				break;
			}
			
			for(int i=0; i<4; i++) {	// 일반적인 4방탐색
				int nr = node.r+dr[i];
				int nc = node.c+dc[i];
				if(0<=nr && nr<N && 0<=nc && nc<M && !visited[nr][nc][node.k] && board[nr][nc]!=1) {
					visited[nr][nc][node.k] = true;
					q.offer(new Node(nr,nc,node.count+1, node.k));
				}
			}
			
			if(node.k>0) {	// 대각방향으로 이동이 가능한 경우
				for(int i=0; i<8; i++) {	// 모든 대각방향에 대해서 탐색
					int nr = node.r+hr[i];
					int nc = node.c+hc[i];
					if(0<=nr && nr<N && 0<=nc && nc<M && !visited[nr][nc][node.k-1] && board[nr][nc]!=1) {
						visited[nr][nc][node.k-1] = true;
						q.offer(new Node(nr,nc,node.count+1, node.k-1));
					}
					
				}
			}
			
			
		}
		
	}

	

} // end of class


class Node implements Comparable<Node>{
	int r,c,count,k;

	public Node(int r, int c, int count, int k) {
		super();
		this.r = r;
		this.c = c;
		this.count = count;
		this.k = k;
	}

	@Override
	public int compareTo(Node o) {
		return this.count-o.count;
	}
	
	
	
}




