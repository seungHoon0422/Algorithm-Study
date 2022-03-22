import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;




/*

	간단하게 풀린문제
	친구의 친구까지 모두 초대하는데 초대한 친구는 visit체크 후 count
	첫번째 친구 목록을 모두 순회하면서 똑같은 행위 반복
	
	친구 리스트 관계는 arrayList 배열을 사용해서 저장

*/



public class Main_BOJ_5567_결혼식_Silver2_144ms {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;
	private static int N;
	private static int M;
	private static ArrayList<Integer>[] list;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		for(int i=0; i<=N; i++) list[i] = new ArrayList<Integer>();
		
			
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			list[node1].add(node2);
			list[node2].add(node1);
		}

		
		int count = 0;
		visited = new boolean[N+1];
		visited[1] = true;
		ArrayList<Integer> friends = new ArrayList<>();
		for (Integer friend : list[1]) {
			if(!visited[friend]) {
				visited[friend] = true;
				friends.add(friend);
				count++;
			}
		}
		for (Integer friend : friends) {
			for (Integer last : list[friend]) {
				if(!visited[last]) {
					visited[last] = true;
					count++;
				}
			}
		}
		System.out.println(count);
		
	} // end of main
	
	
	
} // end of class
