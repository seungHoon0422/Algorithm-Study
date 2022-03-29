import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
1. 메모이제이션과 구분하기 위해 DP 배열은 -1으로 초기화한다.
2. 처음 방문할 경우 -1 -> 0으로 변경시키고 배열 범위를 넘지 않고, 자신의 값이 더 큰 경우에 재귀를 실행하도록 한다.
3. 방문한 경우 0으로 값을 변경해주었기 때문에 재귀 시에 -1이 아닌 경우 메서드를 종료시켜 시간을 단축시킨다.
4. 목적지에 도달했을 때 경우의 수를 1씩 계속 누적하여 처음 시작한 좌표의 경우의 수를 구한다.
 */
public class Main_BOJ_1520_내리막길_Gold4_328ms {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static int N;
	private static int M;
	private static int[][] board;
	private static int[] dr= {1,-1,0,0};
	private static int[] dc= {0,0,1,-1};
	private static int[][] memory;

	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		memory = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				memory[i][j] = -1;
			}
		}

		System.out.println(go(0,0));
	}

	private static int go(int r, int c) {
		
		if(r==N-1 && c == M-1) {
			return 1;
			
		}
		if(memory[r][c] != -1) {
			return memory[r][c];
		}
		
		memory[r][c] = 0;
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(0<=nr && nr<N && 0<=nc && nc<M) {
				if(board[nr][nc] < board[r][c]) {
						memory[r][c] += go(nr,nc);
				}
			}
		}
		return memory[r][c];
		
		
		
	}
}
