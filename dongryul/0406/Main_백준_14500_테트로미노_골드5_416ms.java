import java.io.*;
import java.util.*;
/** Main_백준_14500_테트로미노_골드5_416ms*/
public class Main_백준_14500_테트로미노_골드5_416ms {
	
	private static int N, M;
	
	// 3개씩 짝 
	private static int[][][] dir = {
			{// 일자 
				{0, 1}, {0, 2}, {0, 3}, {1, 0}, {2, 0}, {3, 0}
			},
			{// 네모 시계방향
				{0, 1}, {1, 1}, {1, 0}
			},
			{// ㄱ모양, 긴줄 짧은 줄 순으로 씀 --. 
				{-1, 0}, {-2, 0}, {0, 1}, {0, 1}, {0, 2}, {1, 0}, {1, 0}, {2, 0}, {0, -1}, {0, -1}, {0, -2}, {-1, 0}
			},
			{// ㄱ모양 반전 
				{-1, 0}, {-2, 0}, {0, -1}, {0, 1}, {0, 2}, {-1, 0}, {1, 0}, {2, 0}, {0, 1}, {0, -1}, {0, -2}, {1, 0}
			},
			{// ㅗ 모양 ㅗ ㅏ ㅜ ㅓ
				{0, -1}, {0, 1}, {-1, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}, {0, 1}, {1, 0}, {-1, 0}, {1, 0}, {0, -1}
			},
			{// ㄱㄴ 모양
				{-1, 0}, {-1, -1}, {-2, -1}, {0, 1}, {-1, 1}, {-1, 2}, {1, 0}, {1, 1}, {2, 1}, {0, -1}, {1, -1}, {1, -2}
			},
			{// ㄱㄴ 모양 반전
				{-1, 0}, {-1, 1}, {-2, 1}, {0, 1}, {1, 1}, {1, 2}, {1, 0}, {1, -1}, {2, -1}, {0, -1}, {-1, -1}, {-1, -2}
			}
	};

	private static int[][] map;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/*
		 * 완전 탐색 문제? 라고 하기엔 가로 세로의 사이즈가 좀 크긴 하다.
		 * 그럼에도 불구하고 완전 탐색을 해야할 거 같은데, 
		 * 
		 * 어떠한 좌표를 선택하고, 
		 * 거기서 파생되는 모든 가지수에 대해 계산 최대값 체크
		 * 
		 * 한점에서 BFS를 돌리더라도 중복을 제거하긴 힘들듯
		 * DFS는 가능할거 같은데
		 * 이는 ㅓㅏㅗㅜ를 못간다.
		 * 
		 * 모든 점에 대해
		 * 1. DFS
		 * 2. ㅗ 모양 검사?
		 * 시간이.. 될까
		 * 
		 * 배열을 벗어나는 것을 방지하기 위해 N+6 M+6으로 만들어서 해보자
		 * 각각의 케이스를 모두 파악하면서 최대값을 갱신하자  ( 23개 케이스? )
		 */
		
		map = new int[N+6][M+6];
		for(int i=3; i<N+3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=3; j<M+3; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		for(int r=3; r<N+3; r++) {
			for(int c=3; c<M+3; c++) {
				max = Math.max(max, TetrisCount(r, c));
			}
		}
		System.out.println(max);
		
	} // end of main 

	
	// 한 점에 대한 모든 가지수 체크
	private static int TetrisCount(int r, int c) {
		// TODO Auto-generated method stub
		int max = 0;
		int start = map[r][c];
		for(int i=0; i<7; i++) {
			int temp = 0;
			for(int j=0, rowsize=dir[i].length; j<rowsize; ) {
				temp = start + map[r+dir[i][j][0]][c+dir[i][j++][1]] + map[r+dir[i][j][0]][c+dir[i][j++][1]] 
						+ map[r+dir[i][j][0]][c+dir[i][j++][1]];
				if(max<temp) max = temp;
			}
		}
		return max;
	} // end of TetrisCount
	
} // end of class 
