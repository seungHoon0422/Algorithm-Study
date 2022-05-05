import java.io.*;
import java.util.*;

/*

물고기의 Size 가 고유하다, 이것을 key로 이용하여, 물고기 정보를 가지는 HashMap 을 구성한다.

해당 TreeMap을 이용하여, int[][]에 size를 기록한다.
(TreeMap 은 Comparable 정렬을 쓸 수 있다. 크기 내림차순으로 정렬하면, 나중에 순회시에 쉽게 겹치는 칸의 물고기를 삭제할 수 있다.)

낚시왕은 int[][]를 체크하여 물고기를 잡는다. (잡은 물고기는 TreeMap 에서 삭제해준다.)

TreeMap에 존재하는 물고기를 모두 이동시켜 새로운 int[][] 을 만든다.
(이 때 Map을 순회하기 위해 iterator 사용, 순회 도중에 배열에 이미 값이 있다면 순회 도중 삭제를 해야하기 때문에 쓴다.
iterator를 쓰지 않고 그냥 foreach를 쓰면, 에러가 난다.)

낚시왕이 끝까지 갈 때 까지 반복한다.

+ 물고기 방향, 물고기 speed를 입력단에서 1차 가공해둠

+ r, c 좌표를 1 빼서 0 indexing 사용

-> TreeMap은 get, next, contains 모두 O(log n) 의 복잡도를 갖기 때문에, 속도가 느림 (568ms)
	HashMap으로 변경하여 최초 한번만 정렬하는게 훨씬 효율적일줄 알았는데 아니었음 (572ms)
	Map의 순서를 보장하려면 애초에 LinkedHashMap이나 TreeMap을 써야하는데 이것 자체가 비효율적임.

 */

public class Main_17143 {

	static int[] dr = { -1, 0, 1, 0 }; // 북동남서(시계방향 회전)
	static int[] dc = { 0, 1, 0, -1 };

	static int N, M, K;

	static Map<Integer, Shark> info; // 상어 정보를 기록할 Map
	static Iterator<Map.Entry<Integer, Shark>> iterator; // 상어 정보 Map 순회용

	static int[][] map; // 크기는 중복이 없다, 이거로 체크

	static int total;

	static class Shark {

		int r, c, s, d;

		public Shark(int r, int c, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M]; // 상어의 현재 위치를 기록할 int[][]

		info = new HashMap<Integer, Shark>(); // 상어 정보를 기록할 Map

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			switch (d) { // 방향을 다루기 쉽게 전환 & 상어의 속도가 과도한 것을 방지
			case 1: // 북->0
				d--;
				s %= (N - 1) * 2;
				break;
			case 2: // 남->2
				s %= (N - 1) * 2;
				break;
			case 3: // 동->1
				d -= 2;
				s %= (M - 1) * 2;
				break;
			case 4: // 서->3
				d--;
				s %= (M - 1) * 2;
				break;
			}
			int z = Integer.parseInt(st.nextToken());

			map[r][c] = z; // int[][]의 초기 상태를 설정한다

			info.put(z, new Shark(r, c, s, d));
		}
		info = sortByKeys(info); // info를 상어 크기 내림차순으로 정렬

		total = 0; // 잡은 물고기 크기 0
		go(); // 물고기 잡으러 출발

		System.out.print(total);

	} // main()

	/** 물고기 잡기 시작 */
	static void go() {

		for (int col = 0; col < M; col++) { // 열을 이동하며 진행
			for (int row = 0; row < N; row++) { // 행을 이동하며 진행

				if (map[row][col] > 0) { // 물고기를 발견한 경우

					total += map[row][col]; // 잡은 물고기의 총 합을 더해준다

					info.remove(map[row][col]); // 잡은 물고기는 더 이상 int[][]에 존재하지 않는다

					break; // 한마리밖에 못잡으니, 잡았다면 다음 열로 진행
				}
			}

			if (col != M - 1) newMap(); // Map에서 상어를 이동시킨다. (마지막 col 제외)

		}

	} // go()

	/** 새로운 Map을 만든다 */
	static void newMap() {

		for (int row = 0; row < N; row++) { // 우선 기존 int[][]을 초기화한다
			Arrays.fill(map[row], 0);
		}

		iterator = info.entrySet().iterator(); // info Map을 순회할 iterator, 매번 선언해야 함

		while (iterator.hasNext()) { // Map을 순회한다.

			Map.Entry<Integer, Shark> entry = iterator.next(); // 이번에 체크할 entry(상어)를 받는다

			int size = entry.getKey(); // 이번 상어의 사이즈
			Shark cur = entry.getValue(); // 이번 상어의 정보

			sharkMove(cur); // 상어를 이동시키면서, entry에서의 r, c, d 값을 수정한다

			if (map[cur.r][cur.c] > 0)
				iterator.remove(); // 이미 int[][]을 더 큰 상어가 선점했으니, 잡아먹혔다. (내림차순이기 때문)
			else map[cur.r][cur.c] = size; // 이번 상어가 int[][]의 해당 위치를 선점한다
		}

	} // newMap()

	/** 상어 하나의 움직임을 담당한다 */
	static void sharkMove(Shark shark) {

		for (int i = 0; i < shark.s; i++) { // 상어의 움직임 횟수(Speed) 만큼 반복
			int newR = shark.r + dr[shark.d];
			int newC = shark.c + dc[shark.d];

			if (newR < 0 || newC < 0 || newR >= N || newC >= M) { // 벽에 부딪힌 경우
				shark.d = (shark.d + 2) % 4; // 방향 반대로 회전해주고

				newR = shark.r + dr[shark.d]; // 방향 바뀐 상태라면 이동할 원래 위치로 변환
				newC = shark.c + dc[shark.d];
			}

			shark.r = newR; // 다음 칸으로 이동
			shark.c = newC;
		}

	} // sharkMove()

	/** HashMap을 내림차순으로 정렬한다 */
	static <K extends Comparable, V> Map<K, V> sortByKeys(Map<K, V> map) {

		List<K> keys = new ArrayList<K>(map.keySet()); // keySet으로 리스트를 만든 후
		Collections.sort(keys, Collections.reverseOrder()); // 내림차순으로 정렬한다

		Map<K, V> linkedHashMap = new LinkedHashMap<K, V>(); // linkedHashMap을 만든다

		ListIterator<K> itr = keys.listIterator(); // map을 순회할 itr

		while (itr.hasNext()) { // 맵을 순회하며 새로운 HashMap을 만든다
			K key = itr.next();
			linkedHashMap.put(key, map.get(key));
		}

		return linkedHashMap; // 내림차순으로 정렬된 HashMap을 반환한다

	} // sortByKeys()

}
