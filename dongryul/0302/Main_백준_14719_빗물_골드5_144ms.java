import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

/** Main_백준_14719_빗물_골드5_144ms*/
public class Main_백준_14719_빗물_골드5_144ms {
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		/*
		 * stack을 이용하여 높이를 저장하고, 계산이 요할 때 빼자
		 * 입력 받으면서 오른쪽으로 한번 탐색하고
		 * 배열에 다 추가한 후 max값 까지만 좌측에서 한번 탐색한다.
		 */
		
		int[] arr = new int[W];
		Stack<Integer> stack = new Stack<Integer>();
		int max = 0;
		int maxIdx = 0;
		int total = 0;
		
		// 입력 받으면서 오른쪽에서부터 탐색
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) {
			int wall = Integer.parseInt(st.nextToken());
			arr[i] = wall;
			// 기존 max보다 더 작은 값이 들어온다면
			if(wall<max) {
				stack.push(wall);
			}
			// 기존 max보다 더 큰 값이 들어온다면 
			else {
				// total 증가
				while(!stack.isEmpty()) total += max - stack.pop();
				// max값 갱신
				max = wall;
				maxIdx = i;
			}
		}
		
		// 처리하지 못한 좌표들이 있을 때 왼쪽부터 maxIndex까지 다시 한번 탐색
		if(!stack.isEmpty()) {
			// 기존 stack에 있는 놈들 비워주고
			stack.clear();
			// max값 초기화
			max = 0; 
			// 좌측에서부터 탐색
			for(int i=W-1; i>=maxIdx; i--) {
				int wall = arr[i];
				// 좌측에서 가장 큰 수보다 작은 값이 들어오면 stack에 추가
				if(wall<max) {
					stack.push(wall);
				}
				// 좌측에 있는 벽보다 크거나 같은 값이 온다면 ( 결국 maxIdx에서 만나긴한다 ) 
				else {
					while(!stack.isEmpty()) total += max-stack.pop();
					max = wall;
				}
			}
		}
		// 총합 출력
		System.out.println(total);
		
	} // end of main
} // end of class
