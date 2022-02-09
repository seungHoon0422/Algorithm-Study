package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1406_에디터_S3_박지호_884ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Character> A = new Stack<Character>();
		Stack<Character> B = new Stack<Character>();
		String temp = br.readLine();

		for (int i = 0; i < temp.length(); i++) { // 초기값을 연결리스트로 만듬
			A.push(temp.charAt(i));
		}

		int orders = Integer.parseInt(br.readLine());

		for (int i = 0; i < orders; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			if (st.countTokens() == 1) { // L, D, B
				switch (st.nextToken()) {
				case "L":
					if(A.isEmpty()) { break; }
					B.push(A.pop());
					break;
				case "D":
					if(B.isEmpty()) { break; }
					A.push(B.pop());
					break;
				case "B":
					if(A.isEmpty()) { break; }
					A.pop();
					break;

				}
			} else { // P
				String gar = st.nextToken();
				String repl = st.nextToken(); // 대체되어 들어가는 문자
				A.push(repl.charAt(0));
			}
		}
		
		while(!A.isEmpty()) {
			B.push(A.pop());
		}
		
		while(!B.isEmpty()) {
			sb.append(B.pop());
		}
		

		System.out.println(sb);
	}
}

// A B C D  => 문자열
//0 0 0 0 0 => 커서
// L : 왼쪽옮김 / R : 오른쪽옮김 / B : 커서 왼쪽문자삭제 / P * : * 문자를 커서왼쪽에 추가
// 모두 양 끝이라면 무시
// 명령어 수 = M

// 스택을 이용한 구현
// 초기 스택을 담은 스택 A / 옮기는 연산을 도울 스택 B
// A의 top = cursor
// L : A에서 B로 하나 꺼내서 옮긴다 / R : B에서 A로 하나 옮김  / B : A에서 POP해서 버린다 / P * : A에 *를 PUSH 
