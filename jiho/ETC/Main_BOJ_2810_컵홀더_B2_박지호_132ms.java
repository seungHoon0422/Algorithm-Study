package ForIM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BOJ_2810_컵홀더_B2_박지호_132ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String line = br.readLine();
		Queue<Character> queue = new LinkedList<Character>();

		for (int i = 0; i < N; i++) {
			queue.offer(line.charAt(i));
		}

		boolean isCouple = false;
		while (!queue.isEmpty()) {
			char temp = queue.poll();

			if (temp == 'L' && queue.peek() == 'L') {
				isCouple = true;
				queue.poll();
				N--;
			}

		}

		if (isCouple) {
			System.out.println(N + 1);
		} else {
			System.out.println(N); // 전부다 S라면
		}

	}

}

// 자리수 N 커플석사이에는 컵홀더가없다
// L의 수를 고려해야한다
// 전부다 S라는 가정 하에 컵홀더 수 = N+1
// LL이 들어오는 수 만큼 컵홀더수에서 1개씩뺀다
// 인원수보다 컵홀더수가 많다 => 인원수 출력
// 인원수보다 컵홀더수가 적다 => 컵홀더수 출력
