import java.io.*;
import java.util.*;

public class Main_백준_16637_괄호추가하기_골드3_88ms {
	
	private static int max = Integer.MIN_VALUE;
	private static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/*
		 * 주어진 수식에
		 * 모든 괄호의 가지수를 다 시도해보자
		 * 
		 * 어떻게 할까? 
		 * 닫고 안닫고 --> 
		 * 이후 괄호가 포함된 수식을 만들어서 중위표기법 연산? 하면 될 듯
		 * 중위식을 후위식으로 바꿔서 연산 
		 * 
     * 아래 두 조건이 핵심.....
		 * + 괄호 안에 연산자가 하나만 들어가야 한다.
		 * + 연산자 우선순위가 없음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 */
		
		int N = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		
		go(0, N, arr, "", false);
		
		System.out.println(max);
		
	} // end of main 
	
	private static void go(int cnt, int N, char[] arr, String calcStr, boolean open) {
		if(cnt==N) {
			// 여는 괄호가 있다면
			if(open) calcStr+=")";
			// 연산식
			calcInfix(calcStr);
			return;
		} // end of 기저조건
		if(cnt%2==0) {
			// 이전에 여는 괄호가 없었다면
			if(!open) {
				// 열고 다음
				go(cnt+1, N, arr, calcStr+"("+arr[cnt], true);
				// 안열고 다음
				go(cnt+1, N, arr, calcStr+arr[cnt], false);
			}
			// 이전에 여는 괄호가 있었다면
			else {
				// 닫고 다음 
				go(cnt+1, N, arr, calcStr+arr[cnt]+")", false);
			}
		}
		// 연산자라면 
		else {
			go(cnt+1, N, arr, calcStr+arr[cnt], open);
		}
	} // end of go 

	
//	(	)	+	-	*
//	40	41	43	45	42	
	
	private static char[] oper 	= 	{'(', ')', '*', '+', 0, '-', 0};
	private static int[] power 	= 	{ 0,   0,   1,   1,  0,  1,  0}; // 우선순위 사라져서... 사실 필요 없는 배열일 듯 합니다
	
	private static void calcInfix(String calcStr) {
		StringBuilder post = new StringBuilder();
		// 1. 후위표기법으로 변경
		char curr = 0;
		for(int i=0, size=calcStr.length(); i<size; i++) {
			curr = calcStr.charAt(i);
			switch (curr) {
			case '+':
			case '-':
			case '*':
				while(!stack.isEmpty() && power[stack.peek()] >= power[curr-'(']) {
					post.append(oper[stack.pop()]);
				}
				stack.push(curr-'(');
				break;
			case '(':
				stack.push(0);
				break;
			case ')':
				while(!stack.isEmpty() && stack.peek()!=0) {
					post.append(oper[stack.pop()]);
				}
				// ( 비워주기
				stack.pop();
				break;
			// 알파벳일 때
			default:
				post.append(curr);
				break;
			}
		}
		
		while(!stack.isEmpty()) post.append(oper[stack.pop()]);
		
		int a, b;
		a = b = 0;
		char temp = 0;
		String postExpression = post.toString();
		// 2. 후위표기법 연산
		for(int i=0, size=postExpression.length(); i<size; i++) {
			temp = postExpression.charAt(i);
			if(temp>='0' && temp<='9') stack.push(temp-'0');
			else {
				a = stack.pop();
				b = stack.pop();
				// 요구하는 연산 수행
				switch(temp) {
				case '+':
					stack.push(b+a);
					break;
				case '-':
					stack.push(b-a);
					break;
				case '*':
					stack.push(b*a);
					break;
				}
			}
		}
		
		max = Math.max(max, stack.pop());
	} // end of calc
} // end of class 
