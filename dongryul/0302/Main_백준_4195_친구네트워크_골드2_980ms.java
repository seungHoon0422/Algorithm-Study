import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 그냥 Set을 사용하면 시간 초과가 난다. -> HashMap을 써보자
/** Main_백준_4195_친구네트워크_골드2_980ms*/
public class Main_백준_4195_친구네트워크_골드2_980ms {
	
	private static int index;
	private static int F;
	private static int[] p;
	private static int[] depths;
	private static HashMap<String, Integer> map;
	
	private static void union(String friend1, String friend2) {
		// map의 index-1번 자리에 String 이름을 넣는다 --> p[i] = i 유지
		if(!map.containsKey(friend1)) {
			map.put(friend1, index++);
		}
		if(!map.containsKey(friend2)) {
			map.put(friend2, index++);
		}
		int root1 = findSet(map.get(friend1));
		int root2 = findSet(map.get(friend2));
		if(root1==root2) return;
		// head 바꿔주기
		p[root2] = root1;
//		map.put(friend2, root1); 필요한 과정인가
		// 깊이 갱신
		depths[root1] = depths[root1] + depths[root2];
		// depths 초기화, 필요한가? 현재 root2는 다시는 쓰이지 않을 자리
//		depths[root2] = 1;
	}

	// 부모 찾으면서 갱신
	private static int findSet(Integer i) {
		if(p[i] == i) return p[i];
		return p[i] = findSet(p[i]);
	}

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<TC; tc++) {
			// makeSet F가 최대 100000이니 친구의 수가 최대 200000이 올 수 있음 1~200000사용
			p = new int[200001];
			depths = new int[200001]; // 각 자리의 깊이를 저장 ( 사실상 rank를 사용하는 UnionFind ) ( Set.size하니까 시간 초과 )
			for(int i=1; i<200001; i++) {
				p[i] = i;
				depths[i] = 1;
			}
			// 여기에 String 있는지 확인
			map = new HashMap<String, Integer>();
			index = 1;
			StringTokenizer st;
			F = Integer.parseInt(br.readLine()); // 친구의 수
			for(int i=0; i<F; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String friend1 = st.nextToken();
				String friend2 = st.nextToken();
				union(friend1, friend2);
				// 무조건 friend1의 root에 붙일 예정이니, 해당 깊이를 출력
				sb.append(depths[findSet(map.get(friend1))]).append("\n");
			}
		}
		
		System.out.println(sb.toString());
		
	} // end of main
	
} // end of class 
