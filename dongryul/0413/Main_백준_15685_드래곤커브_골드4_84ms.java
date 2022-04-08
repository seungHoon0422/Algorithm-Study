import java.io.*;
import java.util.*;

public class Main_백준_15685_드래곤커브_골드4_84ms {
	
	private static int C, R, D, T;
	private static int[][] path;
	private static boolean[][] map = new boolean[101][101];
	private static int[] dr = {0, -1, 0, 1};
	private static int[] dc = {1, 0, -1, 0};
	private static int[] power = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
	
	public static void main(String[] args) throws Exception{
		
		/*
		 * 드래곤 커브,,,,
		 * 이전에 왔던 경로를 90도로 꺾어서 이동하면 된다...
		 * 뭔가 말은 쉬운데 어떻게 구현할지,,,,
		 * 
		 * 패턴을 찾는다.. 진행하는 순서가 있다면 BFS로 ? 
		 * 
		 * 모르겠음 그냥 경로 저장하는게 좋을 듯 
		 * 진행했던 경로들 ( 방향 ) 저장하고 각 방향을 90도로 회전해서 진행
		 * 가장 최근에 추가한 순서대로 반대로 진행
		 * 0 - 0 1 -  0 1 2 1 - 0 1 2 1 2 3 2 1 - .....  
		 * 
		 * path 배열을 사용해보자
		 * 미리 경로를 저장해두자
		 * 4방향으로 각 1024개 이동방향을 저장하자
		 * 그리고 2^n(세대) 만큼만 이동
		 * 
		 * 한줄만 확정하면 
		 * 나머지는 +1씩 하면 된다 ( 그냥 for문 돋릴 때 다 연산해두자 ) 
		 * 
		 * + 입력으로 주어지는 값은 격자를 벗어나지 않는다
		 */
		
		// 10세대까지 있으니까 
		path = new int[4][1024];
		for(int i=0; i<4; i++) path[i][0] = i;
		
		int start = 0;
		int end = 0;
		for(int i=1; i<=10; i++) {
			start = power[i]-1;
			end = power[i-1];
			for(int j=start; j>=end; j--) {
				for(int dir=0; dir<4; dir++) {
					path[dir][j] = (path[dir][start-j]+1)%4;
				}
			}
		} // 경로 저장
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			go(C, R, D, T);
		}
		
		System.out.println(countSquare());
		
	} // end of main 

	private static int countSquare() {
		int count = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] && map[i][j+1] && map[i+1][j+1] && map[i+1][j]) count++;
			}
		}
		return count;
	} // end of countSquare

	private static void go(int sc, int sr, int dir, int type) {
		int end = power[type];
		map[sr][sc] = true;
		// 2^type 만큼 이동하면서 map 갱신
		for(int i=0; i<end; i++) {
			sr += dr[path[dir][i]];
			sc += dc[path[dir][i]];
			map[sr][sc] = true;
		} // end of for 
	} // end of go 
	
	
} // end of class 
