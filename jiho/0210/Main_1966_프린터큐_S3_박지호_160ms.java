package apple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1966_프린터큐_S3_박지호_160ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		for (int testCase = 0; testCase < TC; testCase++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

			int cnt = 0;
			Queue<int[]> docs = new LinkedList<int[]>();
			// 0번지 = 중요도 1번지 = 인덱스

			for (int i = 0; i < N; i++) {
				docs.offer(new int[] { Integer.parseInt(st2.nextToken()), i });
			}

			while (!docs.isEmpty()) {
				int[] temp = docs.poll();
				boolean check = true; // 최고값인지 확인

//				for (int i = 0; i < docs.size(); i++) {
//					int[] temp2 = docs.poll();
//					if (temp[0] > temp2[0]) {
//						check = false;
//						break;
//					} else {
//						docs.offer(temp2);
//					}
//				}
				for (int q[] : docs) {
					if (q[0] > temp[0]) {
						check = false;
						break;
					}
				}

				if (check) {
					cnt++;
					if (temp[1] == M) {
						break;
					}
				} else {
					docs.offer(temp);
				}

			}
			System.out.println(cnt);

		}

	}

}

// Queue에 int배열을 참조객체로 선언
// 0번지에는 중요도, 1번지에는 인덱스 넣어놓기
// 문서개수 N / 현재 큐에서 몇번째 놓여있는지(=인덱스) M
