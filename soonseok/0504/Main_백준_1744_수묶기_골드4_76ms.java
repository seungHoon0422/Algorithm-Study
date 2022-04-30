import java.io.*;
import java.util.*;

/*
 * 
 * 양수 따로 세기
 * 1 따로 체크 ( 곱하면 안됨 )
 * 
 * 0 따로 가지고 있기
 * 
 * 음수 따로 세기
 * 
 */

public class Main_1744 {

	static int N;

	static boolean zero; // 0이 있는지만 체크
	static int one; // +1 의 개수를 체크

	static PriorityQueue<Integer> plus; // 양수 큐
	static PriorityQueue<Integer> minus; // 음수 큐

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		zero = false; // 일단 0이 없다고 생각한다.
		one = 0; // 1의 개수는 현재 0이다.

		plus = new PriorityQueue<Integer>(Collections.reverseOrder()); // 양수는 내림차순
		minus = new PriorityQueue<Integer>(); // 음수는 오름차순 (절댓값이 더 큰 수)

		for (int i = 0; i < N; i++) {

			int k = Integer.parseInt(br.readLine());

			if (k > 1)
				plus.offer(k); // 1 보다 큰 수

			else if (k == 1)
				one++; // 1

			else if (k == 0)
				zero = true; // 0 존재 확인

			else minus.offer(k); // -1 이하

		}

		System.out.print(calc()); // 함수 실행 및 결과 출력

	} // main()

	/** 최댓값 계산 */
	static int calc() {

		int result = 0;

		// 양수 부분 계산
		while (plus.size() > 1) {

			int a = plus.poll(); // 가장 큰 두개의 숫자를 꺼내서
			int b = plus.poll();

			result += a * b; // 묶은 수로 만들고 결과에 더해준다

		}

		// 음수 부분 계산
		while (minus.size() > 1) {

			int a = minus.poll();
			int b = minus.poll();

			result += a * b;

		}

		// 나머지 부분 처리

		if (!plus.isEmpty()) result += plus.poll(); // 곱하지 못한 양수가 있다면 더해준다

		result += one; // 1은 그냥 더해준다

		if (!minus.isEmpty() && !zero) result += minus.poll(); // 곱하지 못한 음수가 있고, 0이 존재하지 않는다면 빼주는 수밖에 없다

		return result;

	} // calc()

}
