import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/** Main_백준_11725_트리의부모찾기_실버2_580ms*/
public class Main_백준_11725_트리의부모찾기_실버2_580ms {
	
	// 정답 배열
	private static int[] arr;
	// 배열의 개수
	private static int N;
	
	// 노드의 연결관계 (다익스트라에서 항상 쓰는 방식) 
	/* list[i]  ArrayList<Integer>
	 * Node1 -> 연결된 노드들
	 * Node2 -> 연결된 노드들
	 * ......
	 * 위 같은 구조를 이루게 된다.
	 * --> 각 배열 값에 ArrayList를 하나씩 갖는 구조
	 */
	private static ArrayList<Integer>[] list;

	// 노란줄 싫어서 
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{

		
		/* 
		 * 문제
		 * 루트가 없는 트리가 주어진다.
		 * 트리의 루트를 1이라고 가정했을 때 각 노드의 부모를 구하는 프로그램을 작성해라
		 * 
		 * IDEA
		 * 2번째 트라이에서는 문제의 접근을 달리 하기 위해 문제 분류를 봤는데 그래프 탐색이여서,, 그래프 탐색으로 
		 * DFS를 사용하는데, visited 여부를 정답 배열 갱신 여부로 하였다.
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 배열의 개수 초기화
		N = Integer.parseInt(br.readLine());
		// 노드의 관계 정보 초기화
		list = new ArrayList[N+1];
		// 정답 배열 초기화 
		arr = new int[N+1];
		// 각 배열에 존재하는 ArrayList들 초기화( 꼭 필요한 작업 )
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		// 누가 자식인지 부모인지 모르니 양방향 연결
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			list[node1].add(node2);
			list[node2].add(node1);
		}
		// 배열을 선언하면 모든 값이 0으로 초기화되니, 아래 조건에서 오류가 발생할 수 있다. if(arr[child]==0)
		arr[1] = -1;
		// 루트노드(1번)부터 깊이우선탐색을 한다.
		findParent(1);
		
		// 정답 출력
		for(int i=2; i<=N; i++) {
			sb.append(arr[i]).append("\n");
		}
		System.out.println(sb.toString());
		
	} // end of main

	// 부모를 찾는 메서드 -> 깊이우선으로 루트 노드인 1부터 탐색한다.
	private static void findParent(int start) {
		// ex) Node1에 연결된 노드들 모두 탐색
		for(int i=0; i<list[start].size(); i++) {
			// ex) Node1에 연결된 노드 자식 노드를 탐색
			int child = list[start].get(i);
			// 아직 초기화되지 않았다면 -> 방문하지 않았다 --> visited 여부 확인이 자동으로 된다.
			if(arr[child]==0) {
				// 자식의 부모는 start로 갱신
				arr[child] = start;
				// 자식 기준 탐색
				findParent(child);
			}
		}
	}
} // end of Main
