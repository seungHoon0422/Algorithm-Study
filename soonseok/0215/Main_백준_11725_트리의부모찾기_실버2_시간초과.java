package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 배열 idx와 큐를 이용하여 구현
 * if문을 훨씬 많이 체크해야해서, 노드 체크숫자는 적더라도 비효율적
 * 
 */

public class Main_백준_11725_트리의부모찾기_실버2_시간초과 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Queue<int[]> queue = new LinkedList<int[]>();

		int N = Integer.parseInt(br.readLine()); // 노드의 개수 N (2 ≤ N ≤ 100,000)
		int[] tree = new int[N + 1]; // idx=정점 번호, tree[idx]=해당 정점의 부모 idx
		tree[1] = 100001; // root는 가능한 최댓값 넣어두고 시작.
		// N-1개의 트리 상에서 연결된 두 정점
		// 2번 정점부터 N번 정점까지의 정보
		for (int i = 2; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			// 노드a와 노드b는 서로 연결되어있다.
			int[] temp = new int[2];
			temp[0] = Integer.parseInt(st.nextToken());
			temp[1] = Integer.parseInt(st.nextToken());

			// 둘 중 root가 있다면 해당 정점의 부모는 root(1) 이다.
			if (temp[0] == 1)
				tree[temp[1]] = 1;
			else if (temp[1] == 1)
				tree[temp[0]] = 1;

			// 일단 모든 정보를 queue에 삽입
			queue.offer(temp);
		}

		// 큐가 비었다면 탈출.
		while (!queue.isEmpty()) {
			// 검사시 양쪽 모두 부모가 차버릴 가능성은 없음.(이 방식으로 순회시 tree구조에서 불가능)
			// 양쪽 다 부모가 없는 경우
			if (tree[queue.peek()[0]] == 0 && tree[queue.peek()[1]] == 0) {
				queue.offer(queue.poll()); // 검사 순번 다시 기다려라
			}
			// 한쪽에 부모가 있는 경우는 부모가 없는쪽이 무조건 자식이다
			// 두 노드의 연결 정보를 알고, 루트에서부터 연결하면서 내려오기 때문이다.
			else {
				// 왼쪽의 부모는 오른쪽의 정점이다
				if (tree[queue.peek()[0]] == 0) {
					// 오른쪽 정점값을 왼쪽 정점의 부모값으로 대입
					tree[queue.peek()[0]] = queue.peek()[1];
					queue.poll();
				}
				// 오른쪽의 부모는 왼쪽의 정점이다
				else {
					tree[queue.peek()[1]] = queue.peek()[0];
					queue.poll();
				}
			}
		}

		for (int i = 2; i <= N; i++) {
			sb.append(tree[i]).append("\n");
		}
		System.out.print(sb.toString());

	} // end of main
} // end of class
