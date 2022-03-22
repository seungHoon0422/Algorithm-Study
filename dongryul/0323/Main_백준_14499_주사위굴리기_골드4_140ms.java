import java.io.*;
import java.util.*;

/** Main_백준_14499_주사위굴리기_골드4_140ms*/
public class Main_백준_14499_주사위굴리기_골드4_140ms {
	
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] dice;
	private static int[][] movedice = {
			{0},
			{2, 1, 5, 0, 4, 3},
			{3, 1, 0, 5, 4, 2},
			{1, 5, 2, 3, 0, 4},
			{4, 0, 2, 3, 5, 1}
	};
	// 0 동 서 북 남  oper 1~4에 매칭
	private static int[] dr = {0, 0, 0, -1, 1};
	private static int[] dc = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		// 지도 초기화
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처음엔 0으로 초기화
		dice = new int[6];
		int[] copydice = new int[6];
		
		// 이동 명령
		int oper = 0;
		int nr, nc;
		nr = nc = 0;
		dice[0] = map[row][col]; // 첫 시작점 바닥 복사
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			oper = Integer.parseInt(st.nextToken());
			// 1. 주사위 이동
			nr = row + dr[oper];
			nc = col + dc[oper];
			// 범위 체크
			if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
			// 주사위 복사
			for(int rep=0; rep<6; rep++) copydice[rep] = dice[rep];
			// 주사위 값 변경
			for(int d=0; d<6; d++) {
				dice[d] = copydice[movedice[oper][d]];
			}
			// 2. 바닥면이 0이 아니면 복사
			if(map[nr][nc]!=0) {
				dice[0] = map[nr][nc];
				map[nr][nc] = 0;
			}
			// 이 조건을 빼서 진짜 시간 버렸네요...
			else map[nr][nc] = dice[0];
			// 3. 주사위 윗면 출력
			sb.append(dice[5]).append("\n");
			// 4. 주사위 좌표 이동
			row = nr;
			col = nc;
		}
		// 정답 출력
		System.out.println(sb.toString());
	} // end of main
	
} // end of class 
