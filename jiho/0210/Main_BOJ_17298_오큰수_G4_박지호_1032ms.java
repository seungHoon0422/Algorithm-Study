package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_17298_오큰수_G4_박지호_1032ms { 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] A = new int[N];
		int[] B = new int[N];
		Stack<Integer> C = new Stack<Integer>();
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			B[i] = -1;
		}
		
		C.push(0);
		for (int i = 0; i < A.length-1; i++) {
			while(!C.isEmpty() && A[C.peek()] < A[i+1]) {
				B[C.pop()] = A[i+1];
				
			}
			
			C.push(i+1);
		}
		
		for (int i = 0; i < B.length; i++) {
			sb.append(B[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}

// 스택으로 풀어보자
// A배열에 입력값을 스택에는 인덱스값을 넣는다
// B배열에 정답을 넣는다
// A배열 첫번째 값의 인덱스를(=0) 스택에 넣고 다음 값과 비교
// 다음 값이 크다면 스택에서 POP하고 다음값의 인덱스를 PUSH
// 다음 값이 작다면 다음값의 인덱스를 PUSH





