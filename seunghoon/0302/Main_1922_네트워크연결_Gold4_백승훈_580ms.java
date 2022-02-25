package BOJ0302;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
 * 기본적인 MST를 구하는 문제
 * MST를 구하는 방법에는 Kruskal, Prim 2가지 방식이 존재
 * Kruskal의 경우 간선 중심, Prim의 경우에는 정점 중심으로 풀이
 * 이문제를 풀 시점에는 Union에 대한 학습이 부족해서 Prim으로 풀이
 * 
 * 간선 정보를 arraylist형태의 map에 저장
 * 먼저 임의의 정점 0을 priority queue에 weight 0인 상태로 넣고 시작
 * 현재 속해있는 MST 정점에서 방문하지 않은 노드들 중에서 가장 가중치가 적은 간선을 선택
 * pqueue에는 가중치가 가장 작은 루트에 있으므로 방문하지 않은 노드면 poll
 * 
 * 
 */
public class Main_1922_네트워크연결_Gold4_백승훈_580ms {

	private static StringBuilder sb;
	private static StringTokenizer st;
	private static int N, M;
	private static ArrayList<ArrayList<Edge>> map;
	private static PriorityQueue<Edge> pqueue;
	private static boolean[] visited;
	
	static class Edge{
		int v, weight;
		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new ArrayList<>();
		for(int i=0; i<N; i++) map.add(new ArrayList<>());
		pqueue = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.weight-o2.weight;
			}
		});
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			map.get(from).add(new Edge(to,weight));
			map.get(to).add(new Edge(from,weight));
		}
		
		int result = 0;
		int count = 0;
		visited = new boolean[N];

		pqueue.offer(new Edge(0,0));
		while(!pqueue.isEmpty()) {
			Edge edge = pqueue.poll();
			if(visited[edge.v]) continue;
			result += edge.weight;
			visited[edge.v] = true;
			for (Edge e : map.get(edge.v)) {
				if(!visited[e.v]) {
					pqueue.offer(e);
				}
			}
		}
		
		System.out.println(result);
	} // end of main
	
} // end of class
