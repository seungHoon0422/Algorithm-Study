import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main_백준_1874_스택수열_실버3_340ms {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			queue.add(Integer.parseInt(br.readLine()));
		}

		int num = 1;

		while (!queue.isEmpty()) { // out when queue is over

			int target = queue.poll();

			if (target >= num) {
				for (int i = num; i <= target; i++) {
					stack.push(i);
					sb.append("+").append("\n");
				}
				num = target + 1; // 기존 target의 다음 값으로 간다.

				stack.pop();
				sb.append("-").append("\n");
			} else {
				if (stack.peek() == target) {
					stack.pop();
					sb.append("-").append("\n");
				} else {
					System.out.print("NO");
					return;
				}
			}
		} // end of while

		System.out.print(sb);

	} // end of main
} // end of class
