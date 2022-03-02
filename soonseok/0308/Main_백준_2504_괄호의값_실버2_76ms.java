import java.io.*;
import java.util.*;

public class Main_2504 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Stack<String> stack = new Stack<String>();

		String line = br.readLine();

		for (int i = 0, size = line.length(); i < size; i++) {
			char input = line.charAt(i);

			switch (input) {
			case '(':
				stack.push(input + "");
				break;
			case '[':
				stack.push(input + "");
				break;
			case ')':
				if (stack.isEmpty()) {
					System.out.print(0);
					return;
				}

				if (stack.peek().equals("(")) {
					stack.pop();
					stack.push(2 + "");
					break;
				}

				int temp = 0;
				while (!stack.isEmpty() && !stack.peek().equals("(")) {
					if (stack.peek().equals("[")) {
						System.out.print(0);
						return;
					}

					temp += Integer.parseInt(stack.pop());
				}

				if (stack.isEmpty()) {
					System.out.print(0);
					return;
				}

				stack.pop();
				stack.push(temp * 2 + "");

				break;
			case ']':
				if (stack.isEmpty()) {
					System.out.print(0);
					return;
				}

				if (stack.peek().equals("[")) {
					stack.pop();
					stack.push(3 + "");
					break;
				}

				temp = 0;
				while (!stack.isEmpty() && !stack.peek().equals("[")) {
					if (stack.peek().equals("(")) {
						System.out.print(0);
						return;
					}

					temp += Integer.parseInt(stack.pop());
				}

				if (stack.isEmpty()) {
					System.out.print(0);
					return;
				}

				stack.pop();
				stack.push(temp * 3 + "");

				break;
			}

		}

		int res = 0;
		while (!stack.isEmpty()) {
			if (stack.peek().equals("[") || stack.peek().equals("(")) {
				System.out.print(0);
				return;
			}

			res += Integer.parseInt(stack.pop());
		}

		System.out.print(res);

	}
}

/*
 * 
 * 하나의 스택에 숫자와 문자를 모두 담으려 하니, 값 비교 시에 문제가 생김
 * 
 */
