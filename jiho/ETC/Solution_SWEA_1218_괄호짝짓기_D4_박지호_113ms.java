package apple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_SWEA_1218_괄호짝짓기_D4_박지호_113ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			String line = br.readLine();

			Stack<Character> stack = new Stack<Character>();

			for (int i = 0; i < N; i++) {
				char temp = line.charAt(i);
				if (stack.isEmpty() || temp == '[' || temp == '{' || temp == '(' || temp == '<') {
					stack.push(temp);
				} else {
					switch (temp) {
					case ']':
						if (stack.peek() == '[') {
							stack.pop();
//							System.out.println("pop1");
						} else {
							stack.push(temp);
						}
						break;
					case '}':
						if (stack.peek() == '{') {
							stack.pop();
//							System.out.println("pop2");
						} else {
							stack.push(temp);
						}
						break;
					case ')':
						if (stack.peek() == '(') {
							stack.pop();
//							System.out.println("pop3");
						} else {
							stack.push(temp);
						}
						break;
					case '>':
						if (stack.peek() == '<') {
							stack.pop();
//							System.out.println("pop4");
						} else {
							stack.push(temp);
						}
						break;
					}

				}

			} // end of for
			if (!stack.isEmpty()) {
				System.out.printf("#%d 0", tc);
//				System.out.println(stack);
			} else {
				System.out.printf("#%d 1", tc);
			}
			System.out.println();
		} // end of tc

	}
}
