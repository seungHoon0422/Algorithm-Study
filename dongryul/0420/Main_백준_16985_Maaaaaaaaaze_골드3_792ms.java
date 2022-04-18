import java.io.*;
import java.util.*;

public class Main_백준_16985_Maaaaaaaaaze_골드3_792ms {
	
	private static int nh, nr, nc;	
	private static int min = 999_999_999;
	private static boolean[][][] vis = new boolean[5][5][5];
	private static ArrayList<int[][]> maps = new ArrayList<>();
	private static Queue<int[]> q = new LinkedList<>();
	// 시계방향으로.. 시작지점 지정
	private static int[] sr = {0, 0, 4, 4};
	private static int[] sc = {0, 4, 4, 0};
	// 3차원 이동 지정 
	private static int[] dh = {-1, 1, 0, 0, 0, 0};
	private static int[] dr = {0, 0, -1, 1, 0, 0};
	private static int[] dc = {0, 0, 0, 0, -1, 1};
	private static int time;
	
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		/*
		 * 1. 1~5번 판 순서 결정
		 * 2. 판 회전 
		 * 3. 바닥 찍어보기
		 * 4. 성공했다면 몇 턴 걸렸는지 저장
		 * 5. 최저경로 출력
		 * 
		 * 아이디어는 떠오르는데 진헹이 더디고.. 디버깅하는데 시간이 좀 걸린 문제였습니다.
		 */
		
		for(int i=0; i<5; i++) {
			int[][] map = new int[5][5];
			for(int r=0; r<5; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<5; c++) map[r][c] = Integer.parseInt(st.nextToken());
			}
			maps.add(map);
		} // end of for 
		
		int[] order = new int[5];
		Arrays.fill(order, -1);
		makePerm(0, order, new boolean[5]);
		
    // 경로가 없다면 -1 
		System.out.println(min==999_999_999?-1:min);
		
	} // end of main 

	private static void makePerm(int cnt, int[] order, boolean[] using) {
		if(cnt==5) {
			chooseRotation(0, order, new int[5][5][5]);
			return;
		} // end of if 
		for(int i=0; i<5; i++) {
			if(using[i]) continue;
			using[i] = true;
			order[cnt] = i;
			makePerm(cnt+1, order, using);
			using[i] = false;
		}
	} // end of makePerm

	private static void chooseRotation(int cnt, int[] order, int[][][] map) {
		if(cnt==5) {
			// BFS 
			for(int i=0; i<4; i++) {
				// 참자가자 갈 수 없는 곳이라면
				if(map[0][sr[i]][sc[i]] == 0) continue;
				min = Math.min(min, BFS(map, sr[i], sc[i], sr[(i+2)%4], sc[(i+2)%4]));
				if(min==12) {
					System.out.println(min);
					System.exit(0);
				} // 12번이 최소 이동이니, 출력 후 강제 종료
			}
			return;
		}
		// 층별 배열 돌리면서 완성시키자
		for(int i=0; i<4; i++) {
			rotateArr(i, maps.get(order[cnt]), map[cnt]);
			chooseRotation(cnt+1, order, map);
		}
	} // end of choose Rotation 
	
	private static int BFS(int[][][] map, int row, int col, int destRow, int destCol) {
		// 1. 이전에 사용한 방문 배열 초기화
		for(int h=0; h<5; h++) {
			for(int r=0; r<5; r++) {
				for(int c=0; c<5; c++) vis[h][r][c] = false;
			}
		}
		
		// 2. 시작 지점 방문 처리 및 큐에 추가
		vis[0][row][col] = true;
		q.offer(new int[] {0, row, col});
		
		time = 0;
		while(!q.isEmpty()) {
			for(int rep=0, size=q.size(); rep<size; rep++) {
				int[] curr = q.poll();
				// 목적지라면
				if(curr[0] == 4 && curr[1] == destRow && curr[2] == destCol) {
					q.clear();
					return time;
				}
				for(int i=0; i<6; i++) {
					nh = curr[0] + dh[i];
					nr = curr[1] + dr[i];
					nc = curr[2] + dc[i];
					// 배열의 범위를 벗어나거나 이미 방문했거나 갈 수 없는 좌표라면
					if(nh<0 || nh>=5 || nr<0 || nr>=5 || nc<0 || nc>=5 || vis[nh][nr][nc] || map[nh][nr][nc]==0) continue;
					vis[nh][nr][nc] = true;
					q.offer(new int[] {nh, nr, nc});
				}
			} // end of for q size 
			time++;
		} // end of while 
		
		// 경로를 못찾은 경우
		return 999_999_999;
		
	} // end of BFS

	private static void rotateArr(int degree, int[][] ori, int[][] dest){
		switch(degree) {
		case 0:
			for(int i=0; i<5; i++) System.arraycopy(ori[i], 0, dest[i], 0, 5);
			break;
		case 1:
			for (int i=0; i<5; i++) {
				for (int j=0; j<5; j++) dest[j][4-i] = ori[i][j];
			}
			break;
		case 2:
			for (int i=0; i<5; i++) {
				for (int j=0; j<5; j++) dest[4-i][4-j] = ori[i][j];
			}
			break;
		case 3:
			for (int i=0; i<5; i++) {
				for (int j=0; j<5; j++) dest[4-j][i] = ori[i][j];
			}
			break;
		} // end of switch
	} // end of rotate Array
	
	
} // end of class 
