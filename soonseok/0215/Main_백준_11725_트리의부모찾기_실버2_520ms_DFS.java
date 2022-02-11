package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 양방향 그래프를 이용한 DFS O(V+E)
 * 각 정점에 방문 V + 백트래킹 E
 * if문 1번 검사
 */

public class Main_백준_11725_트리의부모찾기_실버2_520ms_DFS {

	public static boolean[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine()); // 노드의 개수 N (2 ≤ N ≤ 100,000)

		// 노드를 담고있는 graph 생성
		Node[] nodes = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node(i); // N개의 노드 생성
		}

		// 2번 정점부터 N번 정점까지의 정보
		for (int i = 2; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// a 정점의 이웃에 정점b 추가
			nodes[a].adj.add(nodes[b]);
			nodes[b].adj.add(nodes[a]);
		}

		// 양방향 그래프이기 때문에 visited 필요
		// 각 정점 idx 별로 체크하도록 만듬
		visited = new boolean[N + 1];

		// root node에서 dfs 호출
		dfs(nodes[1], -1);

		for (int i = 2; i <= N; i++) {
			sb.append(nodes[i].parent).append("\n");
		}
		System.out.print(sb.toString());

	} // end of main

	public static class Node {
		int idx;
		int parent;
		ArrayList<Node> adj;

		public Node(int idx) {
			super();
			this.idx = idx;
			this.parent = 0;
			this.adj = new ArrayList<>();
		}

	}

	public static void dfs(Node node, int parent) {
		// 기본 조건
		if (visited[node.idx])
			return;

		// 방문 체크 및 부모 갱신
		visited[node.idx] = true;
		node.parent = parent;
		// 인접 리스트에 있는 노드들을 순차적으로 방문
		for (Node n : node.adj) {
			dfs(n, node.idx);
		}

	}

} // end of class
