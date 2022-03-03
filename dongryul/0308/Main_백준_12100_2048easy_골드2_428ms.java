import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/** 데크로 구현*/
public class Main_백준_12100_2048easy_골드2_428ms {
	
	private static class easy{
		int V;
		boolean changed;
		public easy(int v, boolean changed) {
			super();
			V = v;
			this.changed = changed;
		}
	}
	
	private static int N;
	private static int[][] map;
	private static int total;
	private static Deque<easy> dq;
	
	private static void makeOper(int cnt, int[] oper) {
		if(cnt==5) {
			// 배열 복사
			int[][] cmap = new int[N][N];
			for(int i=0; i<N; i++) cmap[i] = map[i].clone();
			// 5번의 오버레이션 
			for(int i=0; i<5; i++) {
				moveBlock(oper[i], cmap);
			}
			// 최대값 찾기
			findmax(cmap);
			return;
		}
		for(int i=0; i<4; i++) {
			oper[cnt] = i;
			makeOper(cnt+1, oper);
		}
	}
	
	private static void findmax(int[][] cmap) {
		int max = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				max = Math.max(max, cmap[i][j]);
			}
		}
		total = Math.max(total, max);
	}

	// 배열 이동
	private static void realMove(int row, int col, int[][] cmap) {
		if(cmap[row][col]==0) {
			return;
		}
		// 0이 아니라면
		else {
			// 데크가 비어있다면
			if(dq.isEmpty()) dq.offer(new easy(cmap[row][col], false));
			else {
				// 데크 맨 끝에 있는 값과 동일하며, 그 값이 변경된 값이 아니라면
				if(cmap[row][col]==dq.peekLast().V && !dq.peekLast().changed) {
					// 그 값의 2배짜리를 변경된 상태로 넣는다.
					int temp = dq.pollLast().V*2;
					dq.offer(new easy(temp, true));
				}
				// 데크에 있는 값과 다르거나, 동일한 값인데 이미 변경된적 있는 값이라면
				else {
					dq.offer(new easy(cmap[row][col], false));
				} // end of else cmap == stack.peek
			} // end of else !stack.isEmpty
			// 해당 자리 비워주기
			cmap[row][col] = 0;
		} // end of else not zero
	}

	// 이동 지시
	private static void moveBlock(int oper, int[][] cmap) {
		// 데이터를 넣을 데크
		dq = new ArrayDeque<easy>();
		switch(oper) {
		case 0: // 전부 올리기
			// 맨 아래 열의 시작부터 
			for(int col=0; col<N; col++) {
				for(int row=0; row<N; row++) {
					realMove(row, col, cmap);
				} // end of for rows 
				// 맨 위까지 올라왔을 때
				int r = 0;
				while(!dq.isEmpty()) {
					cmap[r++][col] = dq.poll().V;
				}
			} // end of for cols
			break;
			
		case 1: // 전부 내리기
			for(int col=0; col<N; col++) {
				for(int row=N-1; row>=0; row--) {
					realMove(row, col, cmap);
				} // end of for rows 
				int r = N-1;
				while(!dq.isEmpty()) {
					cmap[r--][col] = dq.poll().V;
				}
			}
			break;
			
		case 2: // 전부 왼쪽으로
			for(int row=0; row<N; row++) {
				for(int col=0; col<N; col++) {
					realMove(row, col, cmap);
				} // end of for rows 
				int c = 0;
				while(!dq.isEmpty()) {
					cmap[row][c++] = dq.poll().V;
				}
			} // end of for cols
			break;
			
		case 3: // 전부 오른쪽으로
			for(int row=0; row<N; row++) {
				for(int col=N-1; col>=0; col--) {
					realMove(row, col, cmap);
				} // end of for rows 
				int c = N-1;
				while(!dq.isEmpty()) {
					cmap[row][c--] = dq.poll().V;
				}
			} // end of for cols
			break;
		default: break;
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		/*
		 * 아이디어
		 * 이동 방향을 1, 2, 3, 4로 두고
		 * 1, 2, 3, 4를 5개로 만들 수 있는 모든 가지수에 대해 가장 큰 값을 기억한다. -> 4^5 --> 1024개 가지수
		 * 
		 * 1 <= N <= 20   ==> 최대 400개의 칸에 대해 연산 5 번을 총 1024번 --> 400*5*1024 = 2048000번의 연산? 
		 * 주어지는 방향키에 따라 값을 합산하자
		 * 
		 * 0	1	2	3
		 * 상	하	좌	우
		 */
		
		// 보드의 크기
		N = Integer.parseInt(br.readLine());
		// 게임판 초기화
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		total = 0;
		
		makeOper(0, new int[5]);
		
		System.out.println(total);
		
	} // end of main 
}
