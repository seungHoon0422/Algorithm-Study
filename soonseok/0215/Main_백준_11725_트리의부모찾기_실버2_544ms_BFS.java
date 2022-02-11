package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 양방향 그래프를 이용한 BFS O(V+E)
 * 각 정점에 방문 V + 백트래킹 E
 * 한번의 if문 검사
 */

public class Main_백준_11725_트리의부모찾기_실버2_544ms_BFS {
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
		boolean[] visited = new boolean[N + 1];

		Queue<Node> queue = new LinkedList<Node>();
		nodes[1].parent = -1; // parent의 root는 -1(없음)
		visited[1] = true; // root부터 시작
		queue.offer(nodes[1]); // root를 추가해준다

		// queue가 빌 때 까지
		while (!queue.isEmpty()) {
			// 큐 맨 앞에서 하나 뽑는다. 이번에 검사할 차례
			Node current = queue.poll();
			// 현재 뽑은 정점과 인접해 있는 정점에 대하여
			for (Node node : current.adj) {
				if (!visited[node.idx]) { // 인접한 정점이 방문된적이 없다면,
					visited[node.idx] = true; // 방문하고 체크 해준다.
					node.parent = current.idx; // 지금 뽑은 정점이 부모가 된다.
					queue.offer(node); // 인접한 녀석들을 큐에 넣어 다음번 순회에 사용한다.
				}
			}
		} // 전형적인 queue를 사용하는 BFS

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

		@Override
		public String toString() {
			return this.idx + "";
		}

	}

} // end of class
