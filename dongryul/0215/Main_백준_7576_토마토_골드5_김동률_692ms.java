import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/** Main_백준_7576_토마토_골드5_김동률_692ms*/
public class Main_백준_7576_토마토_골드5_김동률_692ms {
	
	private static int[][] tomato;
	private static int colsize;
	private static int rowsize;
	// 객체인 Node를 만드는 것보다 효율적인 배열을 사용했다.
	private static ArrayDeque<int[]> dq;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		colsize = Integer.parseInt(st.nextToken());
		rowsize = Integer.parseInt(st.nextToken());
		
		// 2개씩 크게 만들어서 범위 체크 안하기
		tomato = new int[rowsize+2][colsize+2];
		
		// -1로 초기화(바깥 태두리 벽으로 설정)
		Arrays.fill(tomato[0], -1);
		Arrays.fill(tomato[rowsize+1], -1);
		for(int r=1; r<=rowsize; r++) {
			tomato[r][0] = -1;
			tomato[r][colsize+1] = -1;
		}
		
		// 토마토 위치 저장
		dq = new ArrayDeque<int[]>();
		for(int r=1; r<=rowsize; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=colsize; c++) {
				int num = Integer.parseInt(st.nextToken());
				tomato[r][c] = num;
				if(num==1) {
					// 토마토가 있는 위치의 좌표를 저장
					int[] temp = {r, c};
					dq.offer(temp);
				}
			}
		}
		
		// BFS를 통해 일수 계산
		int result = BFS();
		
		// 토마토 상자를 순회하며 정상 토마토가 발견되면 -1 출력
		for(int i=1; i<=rowsize; i++) {
			for(int j=1; j<=colsize; j++) {
				if(tomato[i][j]==0) {
					System.out.println(-1);
					System.exit(0);
				}
			}
		}
		
		// 정상적인 토마토가 없다면 정답 출력
		System.out.println(result);
		
	} // end of main

	
	// 너비 우선 탐색 메서드
	private static int BFS() {
		// 방문 여부도 1대1 매칭
		boolean[][] v = new boolean[rowsize+2][colsize+2];
		// 상하좌우
		int [] dr = {-1, 1, 0, 0};
		int [] dc = {0, 0, -1, 1};
		int days = 0;
		while(!dq.isEmpty()) {
			// 나중에 모두 1로 변경되었는데 큐를 빼면서 일수가 증가할 수 있으니 변경 확인용 변수
			boolean changed = false;
			// 데크에 들어가 있는 토마토수 만큼씩 전염(한번 돌면 하루 증가
			for(int range=dq.size(), i=0; i<range; i++) {
				int[] rc = dq.poll();
				// 4방 탐색
				for(int dir=0; dir<4; dir++) {
					// 탐색할 좌표
					int nr = rc[0] + dr[dir];
					int nc = rc[1] + dc[dir];
					// 벽이면 가지마라
					if(tomato[nr][nc]==-1) {
						continue;
					}
					// 정상 토마토이며 방문하지 않았을 때
					if(tomato[nr][nc]==0 && v[nr][nc]==false) {
						int[] temp = {nr, nc};
						dq.offer(temp);
						tomato[nr][nc]=1;
						v[nr][nc]=true;
						// 해당 일에 토마토가 바뀌었다 명시
						changed = true;
					}
				}
			}
			// 토마토가 변경되었다면 일수 증가
			if(changed) days++;
		} // end of while isEmpty
		return days;
	} // end of BFS
	
} // end of class
