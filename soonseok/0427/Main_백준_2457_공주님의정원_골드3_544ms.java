import java.io.*;
import java.util.*;

public class Main_2457 {

	static int N;

	static Node[] list;

	// 그리디하게 뽑기 위해 정렬 기준을 정해준다
	static class Node implements Comparable<Node> {

		int s;
		int e;

		public Node(int s, int e) {
			super();
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Node o) {
			// 더 빨리 피는 꽃, 동일하다면 늦게 지는 꽃
			return this.s != o.s ? this.s - o.s : o.e - this.e;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		list = new Node[N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int start_month = Integer.parseInt(st.nextToken());
			int start_day = Integer.parseInt(st.nextToken());
			int end_month = Integer.parseInt(st.nextToken());
			int end_day = Integer.parseInt(st.nextToken());

			int start = 100 * start_month + start_day;
			int end = 100 * end_month + end_day;

			list[i] = new Node(start, end);
		}

		// 그리디하게 뽑기 위해 정렬
		Arrays.sort(list);

		int start = 301; // 시작
		int end = 1201; // 공주가 원하는 끝

		int idx = 0; // 꽃 탐색 위치
		int f_end = 0; // 선택한 꽃중 가장 마지막으로 시든 날짜

		int picked = 0; // 선택한 꽃의 개수

		// 꽃의 날짜를 다 채우지 못한 경우 반복
		while (start < end) {

			boolean flag = false; // 변화 체크

			// 모든 꽃을 검사
			for (int i = idx; i < N; i++) {

				if (list[i].s > start) break; // 비는 날짜가 있다면 뒤쪽 애들도 다 비니까, break

				if (f_end < list[i].e) { // 새로 채울 날짜가 있는 경우에만 고른다
					f_end = list[i].e; // 현재 꽃 선택( 끝 날짜 갱신 )
					idx++; // 다음 꽃 부터 탐색
					flag = true; // 변화 체크
				}
			}

			if (flag) {
				start = f_end;
				picked++; // 이 꽃을 고른다
			}
			else break; // 변화가 없다면, 비는 날짜가 있는 것이다

		}

		// 조건 만족시 선택한 꽃의 숫자
		if (f_end < end) System.out.print(0);
		else System.out.print(picked);

	} // main()
}

/*

처음에는 날짜를 숫자 ( 1 ~ 365 ) 로 변환하려 했는데 (switch, case 써서) ,

이거보다 더 좋은 방법은 그냥 1월1일 -> 101, 11월11일 -> 1111 이렇게 변환하는것


*/
