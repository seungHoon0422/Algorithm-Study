import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** Main_백준_17135_캐슬디펜스_골드4_김동률_236ms*/
public class Main_백준_17135_캐슬디펜스_골드4_김동률_236ms {
	
	/** 행의 크기*/
	private static int N;
	/** 열의 크기*/
	private static int M;
	/** 궁수의 사거리*/
	private static int D;
	/** 격자판*/
	private static int[][] board;
	/**처치한 적의 최대 수*/
	private static int max = 0;
	/** 방문 확인용 배열*/
	private static boolean[][] visited;
	/** BFS용 큐*/
	private static Queue<Node> q;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/* 
		 * 조건
		 * 입력
		 * 행의 수 N, 열의 수 M, 궁수의 공격 사거리 D 
		 * 3 <= N, M <= 15
		 * 1 <= D <= 10
		 * --> 완탐이라 데이터가 적은 것 같다.
		 * 
		 * IDEA
		 * 궁수의 배치는 완전 탐색으로 한다. 
		 * 궁수들이 동일한 적을 공격할 수 있으므로 
		 * 공격당한 적들의 위치를 따로 저장하고, 적군이 전진하기 이전에 게임에서 제외시킨다.
		 * 거리 계산은 가독성을 위해 메서드로 구현 + 좌표 이동도 + 제외도
		 * 적군 찾기는 궁수의 좌표 기준으로 BFS로(가까운 적 우선이니까) ( 좌측 우선이니까 좌 상 우 순으로 검색해라 )
		 * + 제외된 녀석들을 0으로 갱신 + 갱신된 좌표의 수 -> 처치한 적
		 * + 1턴 끝나면 적군 전진 -> 성벽에 제외
		 */
		
		// 초기 데이터 세팅
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		// 맨 아래 행에 궁수들을 배치하자 나머지는 벽으로 두자
		board = new int[N+2][M+2];
		// 맨위 맨 아래 벽으로 채우기
		Arrays.fill(board[0], -1);
		Arrays.fill(board[N+1], -1);
		// 게임 보드 초기화
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=M; c++) {
				int num = Integer.parseInt(st.nextToken());
				board[r][c] = num;
				// 좌우에 벽 세우기
				board[r][0] = -1;
				board[r][M+1] = -1;
			}
		}
		
		// 궁수의 좌표에 따른 완전 탐색 ( 배치를 1,2,3 -> 1,2,4 이런식으로 반복하여 처리한 적의 최대 수를 갱신한다. )
		for(int i=1; i<=M-2; i++) {
			for(int j=i+1; j<=M-1; j++) {
				for(int k=j+1; k<=M; k++) {
					int[] archer = {i, j, k};
					BFS(archer);
				}
			}
		}
		
		// 처치한 적의 최대 수를 출력한다.
		System.out.println(max);
	} // end of main

	/** BFS*/
	private static void BFS(int[] archers) {
		
		// 복제된 배열, 원 배열을 수정하면 안되니까 ( 2차원 배열 복사하는 법 ) 
		int [][]game = new int[N+2][M+2];
		for(int r=0; r<=N+1; r++) {
			game[r] = board[r].clone();
		}
		
		// 좌 상 우 탐색 ( 좌측 우선 탐색이니까 )
		int [] dr = {0, -1, 0};
		int [] dc = {-1, 0, 1};
		// 죽인 젹의 수 카운팅용 변수
		int killed = 0;
		
		// N번 만큼 반복
		int repeat=0;
		while(repeat<N) {
			// 죽은 적들의 좌표 저장
			Queue<Node> dead = new LinkedList<>();
			// 탐색 시작 ( 3명의 궁수 기준으로 탐색 ), ( 아래에서 초기화를 반복한다 )
			for(int i=0; i<3; i++) {
				// 큐 초기화
				q = new LinkedList<Node>();
				// 각 궁수 순서대로 탐색
				q.offer(new Node(N+1, archers[i]));
				// 방문 확인 배열 초기화
				visited = new boolean[N+2][M+2];
        // 사거리 안의 적을 발견하면 while 반복문에서 나가도록 label 설정
				W:while(!q.isEmpty()) {
					Node node = q.poll();
					int r = node.row;
					int c = node.col;
					// 3방 탐색
					for(int dir=0; dir<3; dir++) {
						// 탐색할 좌표
						int nr = r + dr[dir];
						int nc = c + dc[dir];
						// 다음 좌표가 성이거나 벽이거나, 사정거리를 벗어나거나, 방문했다면
						if(game[nr][nc]==-1 || distance(archers[i], nr, nc)>D || visited[nr][nc]) {
							continue;
						}
						// 방문 표시
						visited[nr][nc] = true;
						// 해당 좌표가 적이라면 제외할 좌표를 저장하고 반복문을 나간다.
						if(game[nr][nc]==1) {
							dead.offer(new Node(nr, nc));
							break W;
						}
						// 적이아니라면
						else {
							q.offer(new Node(nr, nc));
						}
					} // end of for 3방 탐색
					
				} // end of while isEmpty
				
			} // end of for archers
			// 죽은 적군 제외
			while(!dead.isEmpty()) {
				killed +=killEnemy(game, dead.poll());
			}
			// 적군 전진
			enemyMove(game);
			// 반복횟수 증가
			repeat++;
		} // end of while game 
		
		// 죽인 적군수 최대치로 갱신
		max = Math.max(max, killed);
		
	} // end of BFS 

	/** Move arrays game*/
	private static void enemyMove(int[][] game) {
		// 한 줄씩 땡긴다.
		for(int r=N; r>=1; r--) {
			game[r] = game[r-1];
		}
	}

	/** erase Enemy*/
	private static int killEnemy(int[][] game, Node enemy) {
		// 적이 있는 좌표라면 0으로 바꾸고 1를 반환
		if(game[enemy.row][enemy.col]==1) {
			game[enemy.row][enemy.col]=0;
			return 1;
		}
		// 적이 없는 좌표면 0 반환 ( 중복 된 경우 ) 
		return 0;
	}

	/** 거리계산 메서드*/
	private static int distance(int archer, int row, int col) {
		return Math.abs(archer-col) + Math.abs(N+1-row);
	}
	
} // end of Main

class Node{
	public int row;
	public int col;
	public Node(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
}
