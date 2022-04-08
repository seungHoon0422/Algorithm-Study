import java.io.*;
import java.util.*;

/*
 * 수식의 길이가 9일때 가능한 괄호 경우는
 * 
 * 4가지 경우가 있고, 최대 2개까지 동시에 가능
 * 
 * 즉 N/2개의 괄호 경우의 수가 생긴다
 * 
 * 그리고 괄호를 선택하면, 좌우로 인접한 애들은 선택할 수 없다.
 * 
 * 
 * -> 최대값 초기화를 min integer로 해야함
 * 
 */

public class Main_16637 {

	static int N; // 수식의 길이

	static List<String> formula;

	static Deque<String> queue;

	static int M; // 괄호 선택 가능 자리 수

	static int max_formula;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = N / 2;

		formula = new LinkedList<String>();
		queue = new LinkedList<String>();

		String temp = br.readLine();
		for (int i = 0; i < N; i++) { // formula를 LL로 만든다
			formula.add(temp.charAt(i) + "");
		}

		max_formula = Integer.MIN_VALUE;

		combination(0, 0, 0);

		System.out.print(max_formula);

	} // main()

	public static void combination(int cnt, int start, int flag) {

		for (int i = start; i < M; i++) {

			combination(cnt + 1, i + 2, flag | 1 << i);

		}

		// 가능한 조합을 모두 골랐다면, 밑으로 빠지게 된다
		// 여기서 flag를 이용해 괄호식을 새로 만들어주고 계산하여 max값 계산

		// 선택한 괄호에 맞게 수식을 개조
		for (int idx = 0; idx < N; idx += 2) {

			// 해당 수식 자리의 괄호가 선택됐다면
			if ((flag & 1 << idx / 2) != 0) {

				int a = Integer.parseInt(formula.get(idx));
				int b = Integer.parseInt(formula.get(idx + 2));

				int result = 0;

				switch (formula.get(idx + 1)) {
				case "+":
					result = a + b;
					break;
				case "-":
					result = a - b;
					break;
				case "*":
					result = a * b;
					break;
				}
				// 기존 식을 새로운 정수로 대체함
				queue.offer(result + "");
				// 마지막 칸이 아니면, 연산자도 같이 넣는다
				if (idx + 3 < N) queue.offer(formula.get(idx + 3));
				idx += 2;
			}
			else {
				// 식에 넣어준다, 마지막이 아니면 연산자도 같이 넣는다
				queue.offer(formula.get(idx));
				if (idx + 1 < N) queue.offer(formula.get(idx + 1));
			}
		}

		// 수식의 길이만큼 진행하면서 계산한다
		// 식에 숫자가 하나 남을때까지 반복
		while (queue.size() != 1) {

			int a = Integer.parseInt(queue.poll());

			String op = queue.poll(); // 연산자

			int b = Integer.parseInt(queue.poll());

			int result = 0;

			switch (op) {
			case "+":
				result = a + b;
				break;
			case "-":
				result = a - b;
				break;
			case "*":
				result = a * b;
				break;
			}

			queue.offerFirst(result + "");

		}

		// 식 결과 반영
		int local_formula = Integer.parseInt(queue.poll());
		max_formula = Math.max(max_formula, local_formula);

	} // combination()

}
