import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 최대한 적은 종류의 비행기를 타고 국가들을 이동
 * 처음 접근 : 당연히 노드 하나에서 bfs돌려 간선의 개수 체크하면 된다고 생각했음
 * -> 문제를 조금만 더 분석해보자!!
 * 한 국가를 방문해야하고, 거쳐갔던 간선을 또 지나갈 수 있으면 결국 하나의 국가를 방문할 떄마다 1개의 간선만 추가
 * 그말은 국가의 개수만큼 비행기를 타야되는데 첫번째 국가를 갈때는 비행기를 타지 않아도 된다
 * 따라서 (전체 국가의 수 )-1 만큼 비행기를 타면 끝!!
 * 생 각 좀 하 자!!
*/
public class Main_BOJ_9372_상근이의여행_Silver3_340ms {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static int N; // N(2 ≤ N ≤ 1 000)
	private static int M; // M(1 ≤ M ≤ 10 000) 
	private static boolean[][] map;
	private static boolean[] visited;
	private static ArrayList<Integer>[] list;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int test=0; test<tc; test++) {
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			list = new ArrayList[N+1];
			for(int i=0; i<=N; i++) list[i] = new ArrayList<>();
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
//				list[a].add(b);
//				list[b].add(a);
				
			}
			
			sb.append(N-1).append("\n");
//			sb.append(bfs(1)).append("\n");
			
		}
		System.out.println(sb);
	}



	private static int bfs(int start) {
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		visited = new boolean[N+1];
		visited[start] = true;
		
		int len = 0;
		while(!q.isEmpty()) {
			int front = q.poll();
			for (Integer node : list[front]) {
				if(!visited[node]) {
					visited[node] = true;
					len++;
					q.offer(node);
				}
			}
		}
		return len;
	}
}
