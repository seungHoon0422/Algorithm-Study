import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_17298_오큰수_G4_백승훈_1160ms {


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
			
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> answer = new Stack<>();
		
		for(int i=N-1; i>=0; i--) {
			 // 오른쪽 끝에 수부터 시작
			while(!stack.empty()) {
				// 자기보다 큰수를 찾으면 break
				if(stack.peek()>arr[i]) { 
					break;
				}
				// 아닐경우 pop
				stack.pop();
			}
			if(stack.empty()) { // 자기보다 큰수가 없는 경우
				answer.push(-1);
			} else { // 오큰수륾 만났을때 push
				answer.push(stack.peek());
			}
			stack.push(arr[i]);
		}
		// answer 스택 역순으로 출력
		while(!answer.empty()) {		
			sb.append(answer.peek()).append(" ");
			answer.pop();
		}
		System.out.println(sb);
	} // end of main
} // end of class
