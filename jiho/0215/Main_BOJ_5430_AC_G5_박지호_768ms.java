package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_BOJ_5430_AC_G5_박지호_768ms {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		int TC = Integer.parseInt(br.readLine()); 
		ArrayDeque<Integer> deque;

		for (int testCase = 1; testCase <= TC; testCase++) {

			String command = br.readLine();
			int N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");

			deque = new ArrayDeque<Integer>();

			for (int i = 0; i < N; i++) { // 입력배열을 큐에 저장

				deque.add(Integer.parseInt(st.nextToken()));

			}

			AC(command, deque);

		}

		System.out.println(sb);

	}

	public static void AC(String command, ArrayDeque<Integer> deque) {
		boolean isRight = true;

		for (char cmd : command.toCharArray()) { // 스트링을 캐릭터배열로 변환
			if (cmd == 'R') { // deque의 front의 위치만 맨앞에서 맨뒤로 변경한다고 생각
				isRight = !isRight;
				continue;
			}

			// 아래는 D경우
			if (isRight) {
				if (deque.pollFirst() == null) { // 반환된 원소가 없을 경우
					sb.append("error\n"); // error 출력
					return;
				}

			} else {
				if (deque.pollLast() == null) { // front가 맨뒤로 변경되었으므로 pollLast
					sb.append("error\n");
					return;
				}
			}
		}
		makePrintString(deque, isRight); // 덱을 출력하는 메서드

	}

	public static void makePrintString(ArrayDeque<Integer> deque, boolean isRight) {
		sb.append('[');

		if (deque.size() > 0) {
			if (isRight) { // 정방향
				sb.append(deque.pollFirst());

				while (!deque.isEmpty()) {
					sb.append(',').append(deque.pollFirst());
				}

			} else { // 역방향
				sb.append(deque.pollLast()); // pollFirst => pollLast 로 변경만 해주면 끝

				while (!deque.isEmpty()) {
					sb.append(',').append(deque.pollLast());
				}

			}

		}

		sb.append(']').append('\n');

	}

}
