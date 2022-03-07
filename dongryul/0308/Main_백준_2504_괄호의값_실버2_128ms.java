import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/** Main_백준_2504_괄호의값_실버2_128ms*/
public class Main_백준_2504_괄호의값_실버2_128ms {
	public static void main(String[] args) throws Exception{
		
		/*
		 * 괄호들을 스택에 쌓고,
		 * 닫는 괄호가 나왔을 때 
		 * 1. stack이 비었는지
		 * 2. 짝이 맞는지 비교
		 * 3. 합산이 필요한지를 확인
		 * 
		 * 반복문 종료 후 스택이 비어있지 않다면 어디선가 짝이 맞지 않는 것이기 때문에 0을 출력
		 * 
		 * 생각 
		 * 처음부터 temp.length를 계산하여, 홀수면 종료하는 방식도 좋을 것 같다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total = 0;
		int mult = 1;
		// flag 변수 사용 닫는 괄호가 연속해서 나올 경우, 해당 변수를 통해 값을 더할지 말지 판단
		// ex ( ( ( ) ) ) ( 
	   	//          T     F 첫번째 닫는 괄호에서 값을 더하고, flag를 T로 둔다. 다시 여는 괄호가 나올 때 F로 변환한다.
		boolean added = false;
		Stack<Character> stack = new Stack<>();
		String temp = br.readLine();
		for(int i=0, len=temp.length(); i<len; i++) {
			if(temp.charAt(i)=='(') {
				added = false;
				mult*=2;
				stack.push('(');
			}
			else if(temp.charAt(i)=='[') {
				added = false;
				mult*=3;
				stack.push('[');
			}
			else if(temp.charAt(i)==')') {
				// 스택이 비었거나 짝이 맞지 않는 경우
				if(stack.isEmpty() || stack.peek()!='(') {
					System.out.println(0);
					return;
				}
				// 스택이 비지 않았고, 짝이 맞는 경우
				else if(!stack.isEmpty() && stack.pop()=='('){
					// 값을 합산할지 판단
					if(!added) {
						total+=mult;
						added = true;
					}
					mult/=2;
				}
			}
			else {
				// 스택이 비었거나 짝이 맞지 않는 경우
				if(stack.isEmpty() || stack.peek()!='[') {
					System.out.println(0);
					return;
				}
				// 스택이 비지 않았고, 짝이 맞는 경우
				else if(!stack.isEmpty() && stack.pop()=='['){
					if(!added) {
						total+=mult;
						added = true;
					}
					mult/=3;
				}
			}
		} // end of for
		if(stack.isEmpty()) System.out.println(total);
		else System.out.println(0);
	}
}
