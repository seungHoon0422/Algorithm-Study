import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * category : graph traverse, tree, postorder
 * 
 * <IDEA 1>
 * 
 * 1. 입력 후 graph 리스트 사이즈를 확인해서 leafnode 들만 탐색
 * 2. 각 leaf노드에서 부터 1번까지의 경로를 쭉 탐색한다.
 * 2-1. 노드를 탐색하면서 양을 만나면 갖고다니는 양의 크기를 크게만들고, 그자리에 양 수를 0으로 만든다.
 * 2-2. 늑대를 만나는 경우 
 * 		양의수 > 늑대수 => 양을 늑대수만큼 죽이고 해당 노드 늑대수 0
		늑대수 < 양의수 => 늑대수를 야의 수 만큼 죽인다음 return
	3. 1번 노드에 도달하면 양의수를 합치고 종료
 * 
 * 왜안되는걸까...??
 * 갈라졌다가 합쳐지는 노드에 도달했을때 양의수를 바로 바꿔버리면
 * 오류발생???
 * 
 * 방문했던 노드를 다시방문할 때 업데이트는 잘 되는거같은데 로직에 문제가 있나??
 * ==>> **** leaf노드만 탐색해서 에러 *****
 * ==>> leaf에 모두 늑대가 있는 경우에는 탐색을 아예 하지않는다.
 * 
 * <IDEA2>
 * 
 * 트리후위순회 하면서 진행
 * 
 * 
 * @author SeongHoon
 *
 */
public class Main_BOJ_16437_양구출작전_Gold2_616ms {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static int N;
	private static ArrayList[] graph;
	private static Node[] nodes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine()); // N (2 ≤ N ≤ 123,456) 
		graph = new ArrayList[N+1];
		nodes = new Node[N+1];
		
		
		
		for(int i=0; i<=N; i++) graph[i] = new ArrayList<>();
		
		
		
		nodes[1] = new Node(1,0,0);
		for(int i=2; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			String type = st.nextToken();
			int count = Integer.parseInt(st.nextToken());
			int parent = Integer.parseInt(st.nextToken());
			
			
			// 자식노드의 개수 파악을 하기위해 부모의 node에 자기자신 추가
			graph[parent].add(i);
			// type 1 : 양, type 2 : 늑대
			if(type.equals("S")) nodes[i] = new Node(1,count,parent); // 양은 type 1
			else nodes[i] = new Node(2,count,parent);					// 늑대는 type 2
		}
		

		System.out.println(post(1));
		
		
//		long totalSheep = 0;
//		
//		// leaf node 파악
//		for(int i=2; i<=N; i++) {
//			
//			// 자식이 1명이라도 있는 경우, leaf노드가 아니거나 wolf노드이면 패스
//			if(graph[i].size()>0 || nodes[i].type==2) continue;
//			
//			// leaf노드인 경우
//			// root(1)번 노드에 도달할 떄 까지 탐색
//			
//			
//			long sheep = nodes[i].count;
//			int curr = i;
//			int next = 0;
//			// 루트노드에 도착할 떄 까지
//			while(curr != 1) {
//				next = nodes[curr].parent;
//				
//				// 부모가 양노드일 경우
//				if(nodes[next].type==1) {
//					
//					sheep += nodes[next].count;
//					nodes[curr].count = 0;
//					curr = next;
//					continue;
//				} else {
//					// 부모노드가 늑대인 경우
//					
//					// 양의 수 < 늑대의 수
//					if(sheep <= nodes[next].count) {
//						nodes[next].count -= sheep;
//						sheep = 0;
//						break;
//					} 
//					// 양의 수 >= 늑대의 수
//					else {
//						sheep -= nodes[next].count;
//						nodes[next].count = 0;
//						curr = next;
//					}
//				}
//			} // end of while
//			
//			// while문을 탈출했다는 의미는 양이 1번노드까지 도달했다는 의미로 해석
//			totalSheep += sheep;
//			
//		}
//	
//		System.out.println(totalSheep);
		
		
	} // end of main
	
	private static long post(int node) {
		
		long sheep = 0;
		

		for (int next : (ArrayList<Integer>) graph[node]) {
			sheep += post(next);
		}
		if(nodes[node].type == 1) return sheep += nodes[node].count;
		else if(sheep > nodes[node].count) return sheep - nodes[node].count;
		else return 0;
		
		
	}

	static class Node{
		int type, count, parent;

		public Node(int type, int count, int parent) {
			this.type = type;
			this.count = count;
			this.parent = parent;
		}
		
	}
	
	
} // end of class








