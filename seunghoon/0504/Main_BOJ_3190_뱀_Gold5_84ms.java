import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * 
 * 뱀의 머리와 꼬리의 위치, 몸통 위치를 저장하고,
 * 머리와 꼬리를 자유롭게 수정하기 위해서는 Deque를 사용해야 한다.
 * 뱀 머리의 이동 방향은 queue를 
 * 
 * 게임 시작부터 x초가 끝난 뒤에 방향 회전
 * @author SeongHoon
 *
 */
public class Main_BOJ_3190_뱀_Gold5_84ms {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static Deque<Node> snake = new ArrayDeque<>();
	static Queue<Dir> queue = new LinkedList<>();
	private static int N;
	private static int K;
	private static int[][] board;
	private static int D;
	
	
	static int[] dr = {0,1,0,-1}; // 시계방향 : 동, 남, 서, 북
	static int[] dc = {1,0,-1,0};
	
	
	public static void main(String[] args) throws Exception {
		
		
		N = Integer.parseInt(br.readLine())+2; // 보드의 크기
		K = Integer.parseInt(br.readLine()); // 사과의 개수
		
		board = new int[N][N];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			// 사과가 있는 위치는 1로 표시
			board[row][col] = 1;
		}
		
		// 보드의 가장자리 벽설치
		Arrays.fill(board[0], -1);
		Arrays.fill(board[N-1], -1);
		for(int i=0; i<N; i++) {
			board[i][0] = board[i][N-1] = -1;
		}
		
//		for(int i=1; i<N-1; i++) System.out.println(Arrays.toString(board[i]));
		
		
		D = Integer.parseInt(br.readLine());
		for(int i=0; i<D; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			queue.offer(new Dir(time,d));
		}
		
		int time = 0;
		int head = 0;
		snake.offer(new Node(1,2));
		snake.offer(new Node(1,1));
		board[1][1] = 2;
		while(true) {
			
			// 앞으로 전진
			Node front = snake.peek();
			
			// 벽인 경우
			if(board[front.r][front.c] == -1) break;
			// 자기 몸인 경우
			if(board[front.r][front.c] == 2) break;
			
			// 사과가 없는 칸인 경우 꼬리를 자른다.
			if(board[front.r][front.c] == 0) {
				board[snake.peekLast().r][snake.peekLast().c] = 0;
				snake.pollLast();
			}
			
			board[front.r][front.c] = 2; 
//			System.out.println("time : "+time);
//			for(int i=1; i<N-1; i++) System.out.println(Arrays.toString(board[i]));
			
			
			
			time++;
			// 큐의 peek를 확인해서 방향회전 여부 체크
			if(!queue.isEmpty() && queue.peek().time == time) {
				
				// 왼쪽으로 90도 회전
				if(queue.peek().dir == 'L') {
					head = (head+3)%4;
				// 오른쪽으로 90도 회전
				} else {
					head = (head+1)%4;
				}
				queue.poll();
			}
			int nr = snake.peek().r+dr[head];
			int nc = snake.peek().c+dc[head];
			snake.addFirst(new Node(nr,nc));
		} // end of while
		
		System.out.println(time+1);
		
	} // end of main
	
	
	
	static class Node{
		int r,c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}
		
	}
	static class Dir{
		int time;
		char dir;
		
		
		public Dir(int time, char dir) {
			super();
			this.time = time;
			this.dir = dir;
		}


		@Override
		public String toString() {
			return "Dir [time=" + time + ", dir=" + dir + "]";
		}
		
		
	}
	
} // end of class








