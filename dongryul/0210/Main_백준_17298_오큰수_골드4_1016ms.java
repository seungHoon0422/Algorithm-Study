import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 0207 과제 타워랑 정말 유사하네요!
public class Main_백준_17298_오큰수_골드4_1016ms {
	
	// 오큰수와 숫자를 저장할 배열
	static int[] RBN, NUMS;
	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		// RBN에는 배열 값을 저장
		RBN = new int[N+1];
		// NUMS 배열은 입력된 숫자를 저장
		NUMS = new int[N+1];
		// int형의 최대값을 넣어 벽(?) 만들기
		NUMS[0] = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		// 숫자 배열 초기화
		for(int i=1; i<=N; i++) {
			NUMS[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		stack.push(1);
		
		int idx = 2;
		while(true) {
			// 스텍 비우면서 -1 넣기
			if(idx > N) {
				while(!stack.isEmpty()) {
					RBN[stack.pop()] = -1;
				}
				break;
			}
			// NUMS[top] 보다 더 큰 값이 온다면(결국 NUMS[0]에서 걸림) --> EmptyStackException 방지
			if(NUMS[stack.peek()] < NUMS[idx]) {
				while(NUMS[stack.peek()] < NUMS[idx]) {
					RBN[stack.pop()] = NUMS[idx];
				}
				stack.push(idx);
			}
			// NUMS[top] 보다 더 작은 값이라면 스텍에 추가
			else {
				stack.push(idx);
			}
      // 다음 숫자 탐색
			idx++;
		}
		// RBN 배열 값 문자열로 바꾸고 출력
		for(int i=1; i<=N; i++) {
			sb.append(RBN[i]).append(" ");
		}
		
		System.out.println(sb.toString());
		
	} // end of main
} // end of class
