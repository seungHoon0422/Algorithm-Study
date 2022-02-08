import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_1966_프린터큐_실버3_136ms {

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(bf.readLine()); // input TC

		for (int tc = 0; tc < TC; tc++) {

			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

			Queue<int[]> queue = new LinkedList<>();

			int N = Integer.parseInt(st.nextToken()); // 1 <= N <= 100, no of docs
			int M = Integer.parseInt(st.nextToken()); // 0 <= M < N, idx
			int idx = 0;

			st = new StringTokenizer(bf.readLine(), " ");

			while (st.hasMoreElements()) { // init queue
				queue.offer(new int[] { idx, Integer.parseInt(st.nextToken()) });
				idx++; // queue [ idx , priority ]
			}

			int cnt = 1; // 출력 순서

			while (!queue.isEmpty()) {

				Queue<int[]> tempQueue = new LinkedList<>(); // 임시 배열

				int[] front = queue.poll(); // 맨 앞을 빼서 들고 있는다

				// 하나 뽑은 front에 대하여 뒤의 queue 검사
				while (!queue.isEmpty()) { // 비어있지 않은 경우 계속 반복해서 queue를 비울 것
					if (queue.peek()[1] > front[1]) { // 맨 위만 구경해봤는데 더 큰놈이 있다.
						queue.offer(front);
						front = null;
						break;
					} else { // 맨 위만 구경해봤는데 작거나 같다. 다음거로 가야함.
						tempQueue.offer(queue.poll());
					}
				}
				// 출력할 차례다
				if (queue.isEmpty() && front != null) {

					if (M == front[0])
						sb.append(cnt).append("\n");

					cnt++;
				}

				while (!tempQueue.isEmpty()) {
					queue.offer(tempQueue.poll());
				}

			} // queue while

		} // end of testcase for
		System.out.print(sb.toString());

	} // end of main
} // end of class
