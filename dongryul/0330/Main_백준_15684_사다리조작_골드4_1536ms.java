import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** Main_백준_15684_사다리조작_골드4_1536ms*/
public class Main_백준_15684_사다리조작_골드4_1536ms {
	
	private static int N, M, H, row, col, ori, size;
	private static int[][] map;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/*
		 * 추가해야 하는 가로선 개수의 최솟값을 출력...
		 * 
		 * 이미 존재하는 가로선을 제외한 가능한 가로선중 0~3개를 선택하여 
		 * i -> i로 갈 수 있도록 만들기
		 * 
		 * 경우의 수가 몇개지? 
		 * 2700C3 -> 30억?
		 * 2700C2 -> 360만
		 * 2700C1 -> 2700
		 * 대략 30억개? 시간 내로 가능한가..
		 * 
		 * 먼저 0개 선택
		 * 다음 1개씩 선택
		 * 다음 2개씩 선택
		 * 마지막으로 3개씩 선택
		 * 
		 * 선택할 때 선택한 좌표의 값과, j+1의 값 모두 0이어야 가능하다
		 * 
		 * 1 -> 우측 이동
		 * 2 -> 좌측 이동
		 * 
		 * 1차원 배열로 바꿔보자
		 * 0번째, 첫줄, M번째 요소를 제외 나머지 정리
		 */
		
		N = Integer.parseInt(st.nextToken())+1; // 세로줄의 수 col 
		H = Integer.parseInt(st.nextToken()); // 가로선의 개수 
		M = Integer.parseInt(st.nextToken())+1; // 가로줄의 수 row
		ori = N-1;
		size = (M)*(N);
		
		map = new int[M][N];
		int n1, n2;
		// 가로선의 정보
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			map[n1][n2] = 1;
			map[n1][n2+1] = 2;
		}
		
		// 0개 설치했을 때 성공하면 0 출력 후 종료
		if(checkLadder()) {
			System.out.println(0);
			System.exit(0);;
		}
		
		// 1~3개 선택
		for(int i=1; i<=3; i++) {
			go(i, 0, N); // 선택할 개수, 선택된 개수
		}
		
		// 불가
		System.out.println(-1);
		
	} // end of main 
	
	private static void go(int cnt, int picked, int start) {
		if(cnt==picked) {
			// 탐색
			if(checkLadder()) {
				// 성공했다면, 추가한 사다리의 개수를 출력하고 강제 종료
				System.out.println(cnt);
				System.exit(0);
			}
			// 실패하면 그냥 return 
			return;
		} // 기저조건
		for(int i=start; i<size; i++) {
			if(i%N==0 || i%N==ori) continue;
			row = i/M; col = i%N;
			if(map[row][col]==0 && map[row][col+1]==0) {
				map[row][col]=1;
				map[row][col+1]=2;
				go(cnt, picked+1, i+1);
				map[row][col] = map[row][col+1] = 0;
			}
		}
		
	} // end of go
	
	private static boolean checkLadder() {
		// i번째가 i번째에서 종료하는지 확인
		for(int i=1; i<N; i++) {
			row = 1; col = i;
			while(row<M) {
				if(map[row][col]==1) col+=1;
				else if(map[row][col]==2) col-=1;
				row++;
			}
			// i -> i가 아니라면
			if(col!=i) return false;
		}
		return true;
	} // end of checkLadder
	
} // end of class 
