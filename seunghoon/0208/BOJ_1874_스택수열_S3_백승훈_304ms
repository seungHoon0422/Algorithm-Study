import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BOJ_1874_스택수열_S3_백승훈_304ms{
	
	
	static int[] numList;
	static Stack<Integer> st = new Stack<>();
	static StringBuilder sb = new StringBuilder();
	
	 public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		numList = new int[n];
		for(int i=0; i<n; i++) {
			numList[i] = Integer.parseInt(br.readLine());
		}
		
		int beforeTop = 1;
		for(int i=0; i<n; i++) {
			int next = numList[i];
			if(beforeTop <= next) { // 등호 안붙인 오류 찾느라 30분동안 찾음!!!!!!!!!!!!!!
				while(beforeTop <=next) {
					st.add(beforeTop++);
					sb.append("+\n");
				}
			}
			if(!st.isEmpty() && st.lastElement() == next) {
				sb.append("-\n");
				st.pop();
				continue;
			}
			System.out.println("NO");
			return;
		}



		System.out.println(sb);

		
	} // end of main
} // end of class



//
//if(!beforeSt.isEmpty() && beforeSt.lastElement() < next) {
//	while(!beforeSt.isEmpty() && beforeSt.lastElement() <=next) {
//		st.add(beforeSt.lastElement());
//		sb.append("+\n");
//		beforeSt.pop();
//	}
//}
//if(!st.isEmpty() && st.lastElement() == next) {
//	sb.append("- pop").append(next).append("\n");
//	st.pop();
//} else {
//	System.out.println("NO");
//	return;
//}
//
