import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 현재 위치 청소 
 * 왼쪽에 빈공간 존재 => 회전 하고 전진
 * 빈공간잉 없으면 왼쪽 회전만
 * 
 * 왼쪽 회전만 4번 실행되면 한칸 후진
 * 뒤쪽이 벽이라면 작동을 멈춘다.
 * 
 * 1 로 패딩을 시켜놓고 1 을 만나면 정지 => 하려고했는데 이미 패딩이 되어있네?
 * 
 * 바라보는 방향
 * 0 : 북쪽
 * 1 : 동쪽
 * 2 : 남쪽
 * 3 : 서쪽
 * 
 * 왼쪽으로 회전을 시키려먼 direction 배열의 인덱스를 활용해서 방향 체크해야 한다.
 * 후진 방향 => index+2 % 4
 * 바라보는 방향의 왼쪽 => index+3 % 4
 * 
 * 재귀함수 형식으로 짜긴 했는데 굳이 반복문으로도 처리할 수 있을 것 같다.
 * 
 */
public class Main_BOJ_14503_로봇청소기_Gold5_76ms {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int M;
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	private static char[][] board;
	private static boolean[][] visited;

	
	
	
	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //  (3 ≤ N, M ≤ 50)
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		int sd = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0, index=0; j<M; j++, index+=2) {
				board[i][j] = str.charAt(index);
			}
		}
		
		visited = new boolean[N][M];
		System.out.println(go(sr,sc,sd, 0));
		
		
		
		
	} // end of main


	

	private static int go(int r, int c, int d, int count) {
		
		
		
		// 현재 위치가 청소하지 않은 방이면 2로 방문체크 후에 count 증가
		if(board[r][c] == '0') {
			count++;
			board[r][c] = '2';
		}

		int nr = r;
		int nc = c;
		int nd = d;
		int rotateCount = 0;
		
		
		for(; rotateCount<4; rotateCount++) {
			
			// 왼쪽 방이 방문한적이 없고, 청소가 가능한 방인 경우
			if(checkLeft(nr,nc,nd)) {
				// 왼쪽 방향으로 회전
				nd = (nd+3)%4;
				nr = r+dr[nd];
				nc = c+dc[nd];
//				board[nr][nc] = '2';
				
				return go(nr,nc,nd, count);
			}
			// 왼쪽 방향으로 회전
			nd = (nd+3)%4;
		}

		// 후진이 가능한 경우
		if(moveBackPossible(r,c,nd)) {
			return go(r+dr[(nd+2)%4],c+dc[(nd+2)%4],nd, count);
		}
		// 후진이 불가능한 경우에느 count를 바로 return
		return count;
	}




	private static boolean moveBackPossible(int r, int c, int nd) {
		
		nd = (nd+2)%4;
		int nr = r+dr[nd];
		int nc = c+dc[nd];
		if(board[nr][nc] == '1') return false;
		return true;
	}




	private static boolean checkLeft(int r, int c, int d) {
		
		d = (d+3)%4;
		int nr = r+dr[d];
		int nc = c+dc[d];
		if(board[nr][nc] == '0') return true;
		else return false;
	}
	
	

} // end of class








