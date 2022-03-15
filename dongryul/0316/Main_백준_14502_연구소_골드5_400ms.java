package src.boj.bfs_dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** Main_백준_14502_연구소_골드5_400ms*/
public class Main_백준_14502_연구소_골드5_400ms {
	
	private static int N;
	private static int M;
	private static int[][] lab;
	private static ArrayList<int[]> virus = new ArrayList<>();
	private static ArrayList<int[]> zeros = new ArrayList<>();
	private static int max;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/*
		 * IDEA
		 * 벽을 3개 세울 수 있다 
		 * --> 완전 탐색
		 * 
		 * + 메서드를 돌리는 곳 안에서
		 * 배열의 복사와 방문 작업을 초기화한다.
		 * + 안전 영역의 크기는 다시 배열을 돌면서 카운팅 한다.
		 * + 어차피 0인 좌표만 탐색하며 전염하기에 visited가 필요 없다
		 */
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		max = 0;
		
		// 테두리 설정 및 연구소 초기화
		lab = new int[N+2][M+2];
		Arrays.fill(lab[0], 1);
		Arrays.fill(lab[N+1], 1);
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				int num = Integer.parseInt(st.nextToken());
				lab[i][j] = num;
				// 바이러스의 위치 저장
				if(num==2) {
					int[] temp = {i, j};
					virus.add(temp);
				}
				else if(num==0) {
					int[] temp = {i, j};
					zeros.add(temp);
				}
			}
			lab[i][0] = lab[i][M+1] = 1;
		}
		
		// 완전 탐색 ( 모든 0의 좌표에 벽을 세워본다 ) // 이거 사용 안하려면 순열을 사용하면 된다.
		int size = zeros.size();
		for(int ilen=size-2, i=0; i<ilen; i++) {
			for(int jlen=size-1, j=i+1; j<jlen; j++) {
				for(int k=j+1; k<size; k++) {
					BFS(i,j,k);
				}
			}
		}
		// 정답 출력
		System.out.println(max);
	}
	
//	/* 재귀적으로, 순열을 사용하여 벽을 배치하는 방법*/
//	private static void permWalls(int cnt) {
//		if(cnt==3) {
//			// 실행 BFS()
//		}
//		
//		for(int i=1; i<=N+1; i++) {
//			for(int j=1; j<=M+1; j++) {
//				if(lab[i][j]==0) {
//					// 벽 세우고
//					lab[i][j] = 1;
//					permWalls(cnt+1);
//					// 벽 없애고 반복
//					lab[i][j] = 0;
//				}
//			}
//		}
//	} // end of permWalls 

	private static void BFS(int wall1, int wall2, int wall3) {
		
		// 복사
		int[][] BFSLab = new int[N+2][M+2];
		for(int i=0; i<=N+1; i++) {
			BFSLab[i] = lab[i].clone();
		}
		// 전달받은 3개의 좌표를 1로 초기화한다.
		BFSLab[zeros.get(wall1)[0]][zeros.get(wall1)[1]] = 1;
		BFSLab[zeros.get(wall2)[0]][zeros.get(wall2)[1]] = 1;
		BFSLab[zeros.get(wall3)[0]][zeros.get(wall3)[1]] = 1;
		
		// 상하좌우 탐방
		int [] dr = { -1, 1, 0, 0};
		int [] dc = {  0, 0, -1,1};
		
		int save = 0;
		
		Queue<int[]> q = new LinkedList<int[]>();
		
		// 모든 바이러스들에 대해 탐색
		for(int i=0; i<virus.size(); i++) {
			// 바이러스 좌표 하나를 준다
			q.offer(virus.get(i));
			while(!q.isEmpty()) {
				int[] vir = q.poll();
				int r = vir[0];
				int c = vir[1];
				for(int dir=0; dir<4; dir++) {
					int nr = r + dr[dir];
					int nc = c + dc[dir];
					// 이거도 필요 없는 검증 
					if(BFSLab[nr][nc]==1) {
						continue;
					}
					if(BFSLab[nr][nc]==0) {
						BFSLab[nr][nc] = 2;
						q.offer(new int[]{nr, nc});
					}
				}
			} // end of while 
			
		} // end of for virus
		
		for(int r=1; r<=N+1; r++) {
			for (int c=1; c<=M+1; c++) {
				if(BFSLab[r][c]==0) {
					save++;
				}
			}
		}
		
		// 안전 구역 최대 갱신
		max = Math.max(max, save);
		
	} // end of BFS
	
} // end of class
