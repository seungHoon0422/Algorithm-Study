import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

		//수열의 각 원소 Ai에 대하여 오큰수 NGE(i)를 구하려고 한다
		// NGE(i) : Ai의 오른쪽에 있으면서, Ai보다 큰 수 중 가장 왼쪽에 있는 수
		// 존재하지 않을 경우 -1
		// 인덱스가 1부터 시작함
		// 배열 순회로 푼다면.. O(N^2) worst case
		// 스택을 이용하는 탑과 비슷한 문제
		// 스택에 index를 넣어야 한다는 점이 차이

public class Main_백준_17298_오큰수_골드4_984ms {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Stack<Integer> stack = new Stack<Integer>();
		
		int N = Integer.parseInt(br.readLine()); // N {A1,A2,...,An}
		
		int[] result = new int[N+1]; // not using 0
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");	// A1, A2, ..., An
		
		// 해당 값들을 가진 배열을 만들어준다
		for (int i = 1; i <= N; i++) {
			result[i] = Integer.parseInt( st.nextToken() );
		}
		
		// 문자열 길이만큼 반복
		for (int i = 1; i <= N; i++) {
			
			// 스택이 비어있지 않고, 이번에 보는 배열 값이 스택 맨 위의 값보다 크다면
			while( !stack.isEmpty() && (result[stack.peek()] < result[i]) ) {
				// 스택에서 꺼낸 값의 idx에 있는 배열 값을 이번에 보는 값으로 바꾼다
				result[stack.pop()] = result[i];
			}
			// 이번 값은 다 썼으니 인덱스로 스택에 넣는다
			stack.push(i);
		} // end of for
		// 스택에 남아있는 값들은 오큰수가 없는 애들. pop하면서 해당 idx를 -1로 만든다
		while(!stack.empty()) {
			result[stack.pop()] = -1;
		}
		
		for (int i = 1; i <= N; i++) {
			sb.append(result[i]).append(" ");
		}
		
		if(sb.length() > 0)
			sb.setLength(sb.length() - 1);
		
		
		System.out.print(sb.toString());
		
	} // end of main
} // end of class
