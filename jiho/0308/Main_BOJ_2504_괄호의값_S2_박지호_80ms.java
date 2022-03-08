import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BOJ_2504_괄호의값_S2_박지호_80ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();

		Stack<Character> st = new Stack<>();
		boolean flag = true; 
		int answer = 0;
		int cnt =1;
		for(int i=0; i<line.length(); i++) {
			char cur = line.charAt(i);
			if(cur == '(') {
				st.push(cur);
				cnt *= 2;
			}
			else if(cur == '[') {
				st.push(cur);
				cnt *= 3;
			}
			else {
				if(cur == ')') {
					if(st.isEmpty() || st.peek() != '(') {
						flag=false;
						break;
					}
					if(line.charAt(i-1) =='(') {
						answer += cnt;
					}
					st.pop();
					cnt /= 2;
				}else if(cur==']') {
					if(st.isEmpty() || st.peek() != '[') {
						flag=false;
						break;
					}
					if(line.charAt(i-1)=='[') {
						answer += cnt;
					}
					st.pop();
					cnt /= 3;
				}
				else {
					flag = false;
					break;
				}
			}
		}
		if(!flag || !st.isEmpty()) {
			System.out.println(0);
		}else {
			System.out.println(answer);
		}
	}
}
// 1. 스택에 넣을때마다 값을 변환해서 넣어준다 => result에 값을 더해주는 부분
// 2. 괄호 닫는 부분과 맞닿을때마다 pop하면서 나눠줌

// (()[[]])([]) => (2[3])(3) => 