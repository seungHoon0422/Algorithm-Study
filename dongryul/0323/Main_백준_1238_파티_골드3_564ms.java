import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/** Main_백준_1238_파티_골드3_564ms*/
public class Main_백준_1238_파티_골드3_564ms {
	
	private static class Node implements Comparable<Node>{
		int Ver;
		int len;
		public Node(int Ver, int len) {
			super();
			this.Ver = Ver;
			this.len = len;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.len - o.len;
		}
	}
	
	private static final int inf = 999_999_999;
	private static int N;
	private static int M;
	private static ArrayList<Node>[] list;
	private static int[] distance;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		// 초기화
		list = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Node>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int curr = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			list[curr].add(new Node(next, len));
		}
		
		// 모든 지점에서 X로 가는게 걸리는 거리
		int[] lengo = new int[N+1];
		// 모든 점에서 X로 가는데 걸리는 거리 갱신
		for(int i=1; i<=N; i++) {
			go(i);
			lengo[i] = distance[X];
		}
		// X에서 모든 좌표로 가기
		go(X);
		// 최대 거리 측정, 마지막 go(X)에서 distance가 X에서 출발하는 모든 좌표 거리로 바뀌기 때문에 
		int max = 0;
		for(int i=1; i<=N; i++) {
			int total = lengo[i]+distance[i];
			if(max<total) {
				max = total;
			}
		}
		
		System.out.println(max);
		
	} // end of main

	private static void go(int start) {
		
		distance = new int[N+1];
		boolean[] visited = new boolean[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		// 임의의 노드를 만들고, 다음 노드가 Start이고, weight=0;
		pq.offer(new Node(start, 0));
		// INF 임의로 부여
		Arrays.fill(distance, inf);
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int currVer = curr.Ver;
			// 방문 처리. 양방향으로 왔다갔다 할 수 있기 때문에
			if(!visited[currVer]) {
				visited[currVer] = true;
			}
			for(int len=list[currVer].size(), i=0; i<len; i++) {
				Node next = list[currVer].get(i);
				int nextVer = next.Ver;
				int nextE = next.len;
				// 초기화되지 않았거나 더 작은 값이 들어온다면
				if(distance[nextVer]==inf || distance[nextVer] > distance[currVer] + nextE) {
					distance[nextVer] = distance[currVer] + nextE;
					pq.offer(new Node(nextVer, distance[nextVer]));
				}
			}
		}
	} // end of go dijkstra

} // end of class 
