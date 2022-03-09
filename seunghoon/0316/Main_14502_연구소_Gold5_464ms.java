package BOJ0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 초기 아이디어 :
 * 완전 탐색이 필요해 보인다.
 * 벽을 3개를 세우는데 그리디한 방법이 떠오르지 않아 2차원 배열을 1차원으로 생각하여
 * 벽을 세울 위치를 조합으로 고른다.
 * 3 ≤ N, M ≤ 8 이므로 최대 64C3 의 경우의수가 나오는데 벽이있는 곳과, 바이러스가 존재하는 곳은 벽으세우지 못하므로
 * 가지치기를 진행하며 탐색한다.
 * 벽을 세울 위치가 정해진 경우 안전 영역의 크기 탐색 후 값을 갱신해준다.
 *
 */
public class Main_14502_연구소_Gold5_464ms {

	private static int N;
	private static int M;
	private static int[][] map;
	private static LinkedList<int[]> virus;
	private static int[] dr= {1,-1,0,0};
	private static int[] dc= {0,0,1,-1};
	private static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		virus = new LinkedList<int[]>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virus.offer(new int[] {i,j});
			}
		} // end of input map
		result = 0;
		combination(0, 0, new int[3]);
		System.out.println(result);
		
		
		

	} // end of main

	private static void combination(int cnt, int start, int[] selected) {
		if(cnt == 3) {
			bfs(selected);
			return;
		}
		for(int i=start; i<N*M; i++) {
			if(map[i/M][i%M] == 0) {
				selected[cnt] = i;
				combination(cnt+1, i+1, selected);
			}
		}
	}

	
	
	
	private static void bfs(int[] walls) {
		boolean[][] visited = new boolean[N][M];
		int[][] cmap = new int[N][M];
		
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++) cmap[i][j] = map[i][j];
		
		for (int pos : walls) {
			cmap[pos/M][pos%M] = 1;
		}
		
		Queue<int[]> q = new LinkedList<>();
		for (int[] v : virus) {
			q.offer(v);
			visited[v[0]][v[1]] = true;
		}
		
		while(!q.isEmpty()) {
			int[] front = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = front[0]+dr[i];
				int nc = front[1]+dc[i];
				if(0<=nr && nr<N && 0<=nc && nc<M && !visited[nr][nc] && cmap[nr][nc] == 0) {
					visited[nr][nc] = true;
					cmap[nr][nc] = 2;
					q.offer(new int[] {nr,nc});
				}
			}
		} // end of while
		
		// 안전구역 찾기
		int count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(cmap[i][j] == 0) count++;
			}
		}
		result = Math.max(result,  count);
		
	} // end of bfs
	
	
} // end of class







