import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * disjoint set을 이용하여 집합 형태로 관리
 * 
 * 핵심 idea) 노드 번호 형태로 각 String name을 mapping 함
 * 
 */
public class Main_백준_4195_친구네트워크_골드2_524ms {

	static Map<String, Integer> frd; // 어떤 인원에 대하여 정수인 노드 변호로 변환함
	static int[] networkSize;
	static int[] parents;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine()); // 테스트 케이스 수

		for (int tc = 1; tc <= TC; tc++) {

			int N = Integer.parseInt(br.readLine()); // 친구 관계의 수 (N ≤ 100,000)

			// 1. 배열 초기화(node 준비시켜준다)
			makeSet(N);

			int nodeNumber = 0; // map에 인원별로 할당할 노드 번호
			for (int i = 0; i < N; i++) {
				// N개의 친구 관계 (edge) 입력
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				String A = st.nextToken(); // A - B 는 친구다
				String B = st.nextToken();

				// 처음 입력받은 인원인 경우 : map에 추가시켜준다
				if (!frd.containsKey(A)) {
					frd.put(A, nodeNumber++);
				}
				if (!frd.containsKey(B)) {
					frd.put(B, nodeNumber++);
				}

				int nodeA = frd.get(A);
				int nodeB = frd.get(B);

				// 2. 합집합 => 합집합 이후 반드시 A쪽으로 합쳐진다
				union(nodeA, nodeB);

				// 3. 문자열 붙이기 (nodeA의 root의 size가 해당 사이즈)
				sb.append(networkSize[findSet(nodeA)]).append('\n');

			}

		} // end of TC for

		if (sb.length() > 0)
			sb.setLength(sb.length() - 1);
		System.out.print(sb.toString());

	} // end of main

	// 1. 단위집합 생성
	public static void makeSet(int N) {
		// 매 테스트케이스마다 map을 초기화
		frd = new HashMap<String, Integer>();

		int size = N * 2;

		networkSize = new int[size]; // 입력 값마다 노드가 2개씩 들어온다
		parents = new int[size]; // 최대 필요한 배열은 edge * 2

		for (int i = 0; i < size; i++) {
			parents[i] = i; // 자기 자신이 자신의 초기 부모값
			networkSize[i] = 1; // 네트워크 크기의 초기 값은 모두 1
		}
	}

	// 2. 집합의 부모 찾기
	public static int findSet(int a) {
		// 기저조건 : 자기 자신을 가리키는 root
		if (a == parents[a])
			return a;

		// path compression을 하면서, 부모의 idx를 재귀적으로 return 시킨다
		return parents[a] = findSet(parents[a]);
	}

	// 3. 합집합
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		// 이미 같은 집합에 속한 경우
		if (aRoot == bRoot)
			return false;

		// 둘이 다른 집합에 속해있다면,
		parents[bRoot] = aRoot; // B를 A 밑으로 같은 네트워크에 넣어주고
		networkSize[aRoot] += networkSize[bRoot]; // B의 사이즈만큼 A의 사이즈를 더해준다

		return true;
	}

} // end of class
