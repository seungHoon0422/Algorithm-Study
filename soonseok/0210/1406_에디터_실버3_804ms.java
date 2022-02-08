import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 처음에 Array 로 접근했다가 시간초과뜸 문자열(Array) 의 특정 위치에 삽입 및 삭제는 O(N) ops 의 개수 M번만큼 수행하므로
 * O(N*M) = O(N^2) 이므로 시간 초과됨
 * 
 * stack의 push pop은 O(1) 이므로 O(M) 으로 수행 가능
 */
public class Main_백준_1406_에디터_실버3_804ms {
	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();

		String str = bf.readLine(); // lowercase, N < 100,000

		for (int i = 0; i < str.length(); i++) {
			left.push(str.charAt(i));
		}

		int M = Integer.parseInt(bf.readLine()); // ops, 1<=M<=500,000

		/*
		 * <left> <right> abcd cursor - L abc cursor d
		 */

		for (int i = 0; i < M; i++) {

			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

			switch (st.nextToken().charAt(0)) {
			case 'L': // <-
				if (!left.isEmpty())
					right.push(left.pop());
				break;
			case 'D': // ->
				if (!right.isEmpty())
					left.push(right.pop());
				break;
			case 'B': // <-delete
				if (!left.isEmpty())
					left.pop();
				break;
			case 'P':
				left.push(st.nextToken().charAt(0));
				break;
			default:
				break;
			}

		} // end of ops for
			// all left stack pop & push to right stack
			// then pop right stack will make result
		while (!left.isEmpty()) {
			right.push(left.pop());
		}

		while (!right.isEmpty()) {
			sb.append(right.pop());
		}

		System.out.print(sb.toString());

	} // end of main
} // end of class
