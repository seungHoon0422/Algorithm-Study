import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main_BOJ_2660_회장뽑기_Gold5_80ms {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static int N;
	private static ArrayList[] graph;

	public static void main(String[] args) throws Exception {
		
		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1];
		for(int i=0; i<=N; i++) graph[i] = new ArrayList<Integer>();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			if(node1 == -1) break;
			graph[node1].add(node2);
			graph[node2].add(node1);
		}

		int[] results = new int[N+1];
		int min = Integer.MAX_VALUE;
		// 회원마다 bfs
		for(int i=1; i<=N; i++) {
			boolean[] visited = new boolean[N+1];
			
			Queue<Node> q = new LinkedList<Node>();
			
			int result = 0;
			visited[i] = true;
			q.offer(new Node(i,0));
			
			
			while(!q.isEmpty()) {
				Node poll = q.poll();
				result = poll.distance;
				
				for (Integer people : (ArrayList<Integer>) graph[poll.number]) {
					if(visited[people]) continue;
					visited[people] = true;
					q.offer(new Node(people, poll.distance+1));
				}
			}
			min = Math.min(min, result);
			results[i] = result;
		}
		
		
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=1; i<=N; i++) 
			if(results[i]==min) list.add(i);
		
		System.out.println(min+" "+list.size());
		for (int i : list) {
			System.out.print(i+" ");
		}
		
		
	} // end of main
	
	static class Node{
		int number;
		int distance;
		public Node(int number, int distance) {
			this.number = number;
			this.distance = distance;
		}

	}
	
	
} // end of class








