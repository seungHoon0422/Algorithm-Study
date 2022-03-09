import java.io.*;
import java.util.*;

/** Main_백준_15683_감시_골드5_492ms*/
class Main_백준_15683_감시_골드5_492ms{
	
	private static class CCTV{
		int row, col;
		public CCTV(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	private static int safe, min, N, M;
	private static CCTV[] cctv = new CCTV[8];
	// 시계방향
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/*
		 * 벽을 만나면 해당 방향 탐방을 종료
		 * 사각지대의 최소 수를 찾아라
		 * 테두리를 설치하여, 무조건 벽을 만나면 종료하게 만들고
		 * 이전 2048에서 인수님이 구현하신 것처럼
		 * 카피배열을 저장 후 들고가며 일종의 메모이제이션을 사용해보자
		 * + 배열의 크기 <= 8 , CCTV <= 8 완탐
		 */
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		safe = 0;
		// 테두리에 벽 설치
		int[][] map = new int[N+2][M+2];
		Arrays.fill(map[0], 6);
		Arrays.fill(map[N+1], 6);
		
		int idx = 0;
		for(int i=1; i<=N; i++) {
			map[i][0] = map[i][M+1] = 6;
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				// 뚫린 구역이라면
				if(n==0) safe++;
				// 감시 카메라라면
				else if(n!=6) {
					cctv[idx++] = new CCTV(i, j);
				}
			}
		}
		min = 100;
 		go(0, 0, idx, map);
		System.out.println(min);
		
	} // end of main

	private static void go(int picked, int changed, int idx, int[][] cmap) {
		// TODO Auto-generated method stub
		if(picked==idx) {
			min = Math.min(min, safe - changed);
			return;
		} // 기저 조건
		// 모든 cctv에 대해 가능한 방향으로 탐색
		CCTV curr = cctv[picked];
		int row = curr.row;
		int col = curr.col;
		int change = 0;
		int[][] copymap = new int[N+2][M+2];
		switch(cmap[row][col]) {
		case 1:
			// 4방향 모두 트라이
			for(int i=0; i<4; i++) {
				change = 0;
				copymap = copy(cmap);
				change=dfs(i, copymap, row, col);
				go(picked+1, changed+change, idx, copymap);
			}
			break;
			// 상하 or 좌우
		case 2:
			for(int i=0; i<2; i++) {
				change = 0;
				copymap = copy(cmap);
				change+=dfs(i, copymap, row, col);
				change+=dfs(i+2, copymap, row, col);
				go(picked+1, changed+change, idx, copymap);
			}
			break;
		case 3:
			// 인접한 두 방향씩
			for(int i=0; i<4; i++) {
				change = 0;
				copymap = copy(cmap);
				change+=dfs(i, copymap, row, col);
				change+=dfs((i+1)%4, copymap, row, col);
				go(picked+1, changed+change, idx, copymap);
			}
			break;
		case 4:
			// 3개씩 4번
			for(int i=0; i<4; i++) {
				change = 0;
				copymap = copy(cmap);
				change+=dfs(i, copymap, row, col);
				change+=dfs((i+1)%4, copymap, row, col);
				change+=dfs((i+2)%4, copymap, row, col);
				go(picked+1, changed+change, idx, copymap);
			}
			break;
		case 5:
			// 4방향 모두 확인 후 다음
			copymap = copy(cmap);
			for(int i=0; i<4; i++) {
				change+=dfs(i, copymap, row, col);
			}
			go(picked+1, changed+change, idx, copymap);
			break;
		default:
			break;
		}
	}
	
	private static int[][] copy(int[][] mat){
		int[][] temp = new int[N+2][M+2];
        for (int i=0; i<=N+1; i++) {
            System.arraycopy(mat[i], 0, temp[i], 0, M+2);
        }
        return temp;
	} // end of copy
	
	private static int dfs(int i, int[][] map, int sr, int sc) {
		// TODO Auto-generated method stub
		Queue<CCTV> q = new LinkedList<CCTV>();
		q.offer(new CCTV(sr, sc));
		int cnt = 0;
		while(!q.isEmpty()) {
			CCTV curr = q.poll();
			int nr = curr.row + dr[i];
			int nc = curr.col + dc[i];
			if(map[nr][nc]==6) break;
			// 아직 잡히지 않은 사각지대라면 변경 후 카운트 증가
			if(map[nr][nc]==0) {
				map[nr][nc]=-1;
				cnt++;
			}
			q.offer(new CCTV(nr, nc));
		}
		return cnt;
	} // end of dfs
} // end of class
