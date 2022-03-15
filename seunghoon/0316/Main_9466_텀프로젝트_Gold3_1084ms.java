package BOJ0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_9466_텀프로젝트_Gold3_1084ms {

	private static int N;
	private static int[] pick;
	private static boolean[] selected;
	private static int teams;
	private static boolean[] visited;
	private static boolean[] done;
	private static int answer;

	/*
	 * 초기 아이디어 : 
	 * 내가 같은 팀을하고 싶은 학생이 이미 선택되었거나, 자기자신을 선택한 경우 => 지목을 한 친구는 절대 짝을 만날 수 없다.
	 * 자기자신을 선택한 친구는 바로 탈출
	 * 선택한 친구가 현재 팀을 구성하고자 하는 목록에 들어가 있는 경우에는 팀을 즉시 생성한다.
	 * 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int TC = Integer.parseInt(st.nextToken());
		
		
		for(int test=0; test<TC; test++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			pick = new int[N+1];
			for(int i=0; i<N; i++) {
				pick[i+1] = Integer.parseInt(st.nextToken());
			}
			visited = new boolean[N+1];
			done = new boolean[N+1];
			answer = 0;
			for(int i=1; i<=N; i++) {
				if(!visited[i]) DFS(i);
			}
			System.out.println(N-answer);
//			selected = new boolean[N+1];
//			teams = 0;
//			for(int i=1; i<=N; i++) 
//				if(!selected[i]) go(i);
//		
//			System.out.println(N-teams);
			
		}
		
		
	}
	private static void DFS(int n)
	{
	    visited[n] = true;
	    int node = pick[n];
	    if (!visited[node])
	    {
	        DFS(node);
	    }
	    else if (!done[node])
	    {
	        for (int i = node; i != n; i = pick[i])
	        {
	            answer++;
	        }
	        answer++;
	    }
	    done[n] = true;
	}
//	private static void go(int index) {
//		boolean[] picked = new boolean[N+1];
//		ArrayList<Integer> list = new ArrayList<>();
//		list.add(index);
//		picked[index] = true;
//		int next = 0;
//		while(true) {
//			next = pick[index];
//			if(selected[next]) {
//				// 이미 선택된 놈인 경우
//				for (Integer i : list) {
//					selected[i] = true;
//				}
//				return;
//			} else if(pick[next] == next) {
//				// 자기 자신을 선택한 경우
//				selected[next] = true;
//				for (Integer i : list) {
//					selected[i] = true;
//				}
//				teams++;
//				return;
//			} else if(picked[next]){
//				for (Integer i : list) {
//					selected[i] = true;
//				}
//				teams++;
//				return;
//			}
//			else {
//				list.add(next);
//				index = pick[next];
//				
//				picked[next] = true;
//			}
//		}
//
//	}
}
