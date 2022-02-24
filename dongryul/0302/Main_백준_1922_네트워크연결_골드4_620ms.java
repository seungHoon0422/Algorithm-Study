import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** Main_백준_1922_네트워크연결_골드4_620ms*/
public class Main_백준_1922_네트워크연결_골드4_620ms {
	
	private static class Node implements Comparable<Node>{
		int from, to, E;
		public Node(int from, int to, int e) {
			super();
			this.from = from;
			this.to = to;
			E = e;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.E - o.E;
		}
	}
	
	private static Node[] lines;
	private static int N, total;
	private static int[] p;
	
	// 루트 노드를 갱신하며 찾기
	private static int findSet(int i) {
		if(p[i] == i) return i;
		else return p[i] = findSet(p[i]);
	}
	
	// 유니온 
	private static boolean union(Node node) {
		// TODO Auto-generated method stub
		int root1 = findSet(node.from);
		int root2 = findSet(node.to);
		// 이미 같은 네트워크에 속했더라면
		if(root1==root2) return false;
		// 합치기
		total += node.E;
		p[root2] = root1;
		return true;
	}

	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		/*
		 * 컴퓨터와 컴퓨터를 연결하는 네트워크를 구축하라.
		 * 모든 컴퓨터가 연결되야 하고, 최소 비용으로 컴퓨터를 연결하라.
		 * 
		 * --> MST를 구축하라는 문제
		 * 1 <= N <= 1000     	컴퓨터의 수
		 * 1 <= M <= 100000		간선의 수
		 * 1 <= C <= 10000		비용 
		 * 
		 * --> 가중치의 합의 최대값은 1000000 Int타입 
		 * 
		 * 먼저 모든 가중치를 저장하는 데이터타입을 정의하고, 나중에 이를 오름차순으로 정렬 -> MST 구성
		 */
		
		total = 0;
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		// makeSet
		p = new int[N+1];
		for(int i=1; i<=N; i++) {
			p[i] = i;
		}
		
		lines = new Node[M];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			lines[i] = new Node(from, to, e);
		}
		// 오름차순 정렬
		Arrays.sort(lines);
		
		// 본인도 포함하여 1부터 시작
		int cnt = 1;
		for(Node node:lines) {
			if(union(node)) cnt++;
			// N개 짜리 네트워크를 구성했다면
			if(cnt==N) break;
		}
		
		System.out.println(total);
		
	} // end of main
} // end of class
