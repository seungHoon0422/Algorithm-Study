package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1874_스택수열_S3_박지호_368ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> su = new Stack<>();
		int maxT = 0;

		for (int i = 1; i <= N; i++) {
			int T1 = Integer.parseInt(br.readLine());
			
			if(T1 > maxT) {
				for (int j = maxT + 1; j <= T1; j++) { // maxT+1 => 4까지 push했다면 다음번엔 5부터 push해야 하므로
					su.push(j);
					sb.append("+").append("\n");
				}
				maxT = T1; // 이걸 해줘야 다음 push에서 오름차순을 유지한다
			} else if(su.peek() != T1) {
				System.out.println("NO");
				return;
			}
			su.pop();
			sb.append("-").append("\n");
		}
		
		System.out.println(sb.toString());

	}
}

// 최대값이 갱신될 때마다 이전최대값+1 부터 현최대값까지 모두 push
// push하자마자 하나를 뺌 => 다음 라인으로 넘어간다
// 입력값을 최대값과 비교해서  스택의 top이 최대값보다 작으면서 top이 아니다 => 오름차순 불가능
// 최대값 갱신 시 맨처음 push상태 반복
// 아니면 pop해준다

