package BOJ0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_11725_트리의부모찾기_Silver2_백승훈_660ms {

	static int N;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static boolean[] visited;
	static Map<Integer, List<Integer>> map = new HashMap<>();
	static int[] parent;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		/*
		 * hashMap활용해서 각 노드별 연결되어있는 노드 리스트 생성
		 * DFS 방식으로 트리 탐색
		 * 
		 * 
		 */
		

		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			map.put(i, new ArrayList<>());
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			map.get(node1).add(node2);
			map.get(node2).add(node1);
		}
		
		visited[1] = true; // 루트노드 방문처리
		search(1); // 루트노드부터 탐색 시작
		
		for(int i=2; i<=N; i++) {
			sb.append(parent[i]).append("\n");
		}	
		System.out.println(sb);
		
	} // end of main
	
	private static void search(int node) {
		// TODO 트리 순회하며 자식노드를 찾는다.
		List<Integer> nodes = map.get(node);
		for(int i=0; i<nodes.size(); i++) {
			int next = nodes.get(i);
			if(!visited[next]) {
				visited[next] = true;
				parent[next] = node;
				search(next);				
			}
		}
		
		
		
		
	}
} // end of class















