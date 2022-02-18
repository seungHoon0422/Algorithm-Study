import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1052_물병_Silver1_백승훈_924ms {

	
	

	private static int N;
	private static int K;

	/**
	 * 
	 *  아이디어 : 
	 *  처음에는 queue를 사용해서 앞에 1을 N개만큼 집어넣고
	 *  앞에서부터 1개씩 꺼내보면서 앞에있는 2개 숫자를 합할 수 있으면 합하고,
	 *  안되면 1을 추가해서 집어넣는 식으로 진행 => 구현이 너무빡셈... 시간초과 발생 예상
	 *  
	 *  이진수 표현으로 접근
	 *  toBinaryString사용
	 *  N을 1씩 증가시키면서 해당 숫자를 이진수로 표현헀을 떄 1의 개수를 물병의 개수로 파악
	 *  
	 *  
	 */
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int count = 0;	// 추가한 물병의 개수
		
		while(true) {
			String str = Integer.toBinaryString(N);
			int one = 0; // 현재 숫자의 물병 개수
			//숫자를 각 비트별로 확인하면서 물병의 개수 파악
			for(int i=0; i<str.length(); i++) {
				// '1' 로 표현돈 부분이물병이 있는 곳
				if(str.charAt(i) == '1') one++;
			}
			// 물병의 개수가 K개 보다 작아지면 탈출
			if(one <= K) break;
			else {
				// 아닌경우 N과 추가물병 
				N++; count++;
			}
		}

		System.out.println(count);
		
		
		
		
		
		
		
	} // end of main
} // end of class
