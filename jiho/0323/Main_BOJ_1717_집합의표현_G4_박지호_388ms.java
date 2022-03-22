import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1717_집합의표현_G4_박지호_388ms {

	private static int[] arr;
	private static int N, M;

	// 단위집합 생성
	public static void makeSet() {
		arr = new int[N + 1];
		// 자신의 부모 노드를 자신의 값으로 세팅
		for (int i = 1; i <= N; i++) {
			arr[i] = i;
		}

	}

	// a의 집합 찾기 : a의 대표자 찾기
	public static int findSet(int a) {
		if (a == arr[a]) {
			return a;
		}

		return arr[a] = findSet(arr[a]); // path compression

	}

	// a, b 두 집합 합치기
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) { // 루트가 같으면 같은 집합 => union 불가
			return false;
		}

		arr[bRoot] = aRoot; // b집합의 대표자의 부모를 a집합의 대표자로
		return true;

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

//		arr = new int[N+1];
		makeSet();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int num_1 = Integer.parseInt(st.nextToken());
			int num_2 = Integer.parseInt(st.nextToken());

			if (type == 1) { // 같은 집합인지 확인
				if (findSet(num_1) == findSet(num_2)) {
					sb.append("YES").append("\n");
				} else {
					sb.append("NO").append("\n");
				}
			} else { // 0일때 = 합집합연산
				union(num_1, num_2);
			}

		}

		System.out.println(sb);

	}

}
