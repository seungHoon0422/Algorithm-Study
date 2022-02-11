package BOJ0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11725_트리의부모찾기_Silver2_백승훈_메모리초과 {

	static int N;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static boolean[][] tree;
	static boolean[] visited;
	static int[] parent;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		/*
		 * 
		 * 
		 * 메모리초과 발생!!!
		 * N 최대 100,000
		 * N * N 배열 생성시 10^10 * 1byte
		 * 이차원 배열 생성하는 과정에서 터지나???
		 * 
		 * 
		 */
		
		
		
		
		N = Integer.parseInt(br.readLine());
		tree = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			visited[i] = false;
			for(int j=1; j<=N; j++) {
				tree[i][j] = false;
			}
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			tree[node1][node2] = tree[node2][node1] = true;	
		}
		
		visited[1] = true;
		search(1);
		
		for(int i=2; i<=N; i++) {
			sb.append(parent[i]).append("\n");
		}
		
		
		System.out.println(sb);
		
	} // end of main
	private static void search(int node) {
		// TODO 트리 순회하며 자식노드를 찾는다.
		
		for(int i=1; i<=N; i++) {
			if(tree[node][i] && !visited[i]) {		// 현재 노드의 자식을 봤는데 부모가 없는 경우
				parent[i] = node;
				visited[i] = true;
				search(i);
			}
		}
		
		
		
		
		
	}
} // end of class















