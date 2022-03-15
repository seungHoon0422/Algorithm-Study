import java.io.*;
import java.util.*;

/** Main_백준_9466_텀프로젝트_골드3_1492ms*/
public class Main_백준_9466_텀프로젝트_골드3_1492ms {
	
	private static class cycle{
		int start, len;
		public cycle(int start, int len) {
			super();
			this.start = start;
			this.len = len;
		}
	}

	private static cycle[] teams;
	private static int[] pair;
	private static boolean[] vis;
	private static int left;
	
	public static void main(String[] args) throws Exception{
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for (int tc = 0; tc < TC; tc++) {
			int n = Integer.parseInt(br.readLine());
			pair = new int[n+1]; // 1~n
			teams = new cycle[n+1]; // 1~n
			vis = new boolean[n+1];
			left = 0; // 버려진 친구들 카운트
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) {
				pair[i] = Integer.parseInt(st.nextToken());
			}
			
			// 꼬리에 꼬리를 물어 cycle을 형성하게 된다면 한팀이 형성됨
			// cycle이 형성되긴 했는데, 짤리는 놈들을 어떻게 계산할까
			// 중간에 잘리는 경우 ex 1-2-3-4-5-2 --> 2-3-4-5 한팀, 이고 1이 버려짐 --> 친구 수를 저장하는 배열을 구현하자 ( dis[2]-1 만큼의 친구가 버려짐 )
			// 필요한거 연결 정보 pair, 친구 수 카운팅 배열 teams, 방문 체크 vis
			// + 어떤 친구가 이미 연결된 cycle에 닿게 되면 그 친구의 집합도 버려짐
			
			for(int i=1; i<=n; i++) {
				// 방문된적 없는 좌표라면
				if(!vis[i]) {
					go(i, i, 1);
				}
			}
			sb.append(left).append("\n");
		}
		
		System.out.println(sb.toString());
		
	} // end of main

	private static void go(int start, int num, int cnt) {
		// 방문 처리
		vis[num] = true;
		teams[num] = new cycle(start, cnt);
		// 다음 좌표
		int next = pair[num];
		// 방문된적 없다면
		if(!vis[next]) {
			go(start, next, cnt+1);
		} else { // 방문된적 있다면
			// 같은 사이클에 속한 지점에 도달했다면
			if(teams[next].start == start) {
				left += teams[next].len-1;
			}
			// 다른 사이클에 속한 지점에 닿았다면
			else {
				left += teams[num].len;
			}
		} // end of else
	} // end of go
	
} // end of class 
