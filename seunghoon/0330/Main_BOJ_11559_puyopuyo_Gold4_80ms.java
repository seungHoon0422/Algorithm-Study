import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_11559_puyopuyo_Gold4_80ms {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static boolean[][] visited;
	static char[][] map;
	private static int[] dr= {1,-1,0,0};
	private static int[] dc= {0,0,1,-1}; 
	public static void main(String[] args) throws IOException {
		
		String[] board = new String[12];
		map = new char[12][];
		for(int i=0; i<12; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int turn = 0;
		while(true) {
			boolean go = false;
			visited = new boolean[12][6];
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(!visited[i][j] && map[i][j] !='.') {
						if(bfs(i,j)) go = true;
					}
				}
			}
			
			if(go) {
				// 빈칸없이 움직이기
				turn++;
				moveMap();
			} else {
				break;
			}
		}
		System.out.println(turn);
		
		
		
	}

	private static void moveMap() {
		for(int i=0; i<6; i++) {
			ArrayList<Character> list = new ArrayList<>();
			for(int j=11; j>=0; j--) {
				if(map[j][i] != '.') list.add(map[j][i]);
			}
			int index = 11;
			for (Character character : list) {
				map[index--][i] = character;
			}
			for(int idx=index; idx>=0; idx--) map[idx][i] = '.';
		}
	}

	private static boolean bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		ArrayList<int[]> list = new ArrayList<>();
		char color = map[r][c];

		list.add(new int[] {r,c});
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		
		
		while(!q.isEmpty()) {
			int[] front = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = front[0] + dr[i];
				int nc = front[1] + dc[i];
				
				if(0<=nr && nr<12 && 0<=nc && nc<6 && !visited[nr][nc]) {
					if(map[nr][nc] == color) {
						visited[nr][nc] = true;
						list.add(new int[] {nr,nc});
						q.offer(new int[] {nr,nc});
					}
				}
			}
		}
		if(list.size()<=3) return false;
		for (int[] pos : list) {
			map[pos[0]][pos[1]] = '.';
		}
		return true;
	}
}
