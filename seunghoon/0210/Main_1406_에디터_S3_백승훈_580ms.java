package BOJ0210;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;

public class Main_1406_에디터_S3_백승훈_580ms {
	
	
	static StringBuilder sb = new StringBuilder();
	static String str, command;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		N = Integer.parseInt(br.readLine());
		Stack<String> before = new Stack<>();
		Stack<String> after = new Stack<>();
		
		String[] split = str.split("");
		for(int i=0; i<str.length(); i++) {
			before.push(split[i]);
		}
		
		for(int i=0; i<N; i++) {
			command = br.readLine();
			char Navigate = command.charAt(0);
			
			
			switch (Navigate) {
			case 'L' :
				if(!before.empty()) {
					after.push(before.pop());
				}
				break;
						
			case 'D' :
				if(!after.empty()) {
					before.push(after.pop());
				} 				
				break;
			case 'B' :
				if(!before.empty()) {
					before.pop();
				} 
				break;
			case 'P' :
				before.push(String.valueOf(command.charAt(2)));				
				break;
		
			}	

//			// 커서 왼쪽 이동
//			if(Navigate == 'L' && !before.empty()) {
//				after.push(before.pop());
//			} 
//			// 커서 오른쪽 한칸 이동
//			else if(Navigate == 'D' && !after.empty()) {
//				before.push(after.pop());
//			} 
//			// 커서 왼쪽 문자 삭제			
//			else if(Navigate == 'B' && !before.empty()) {
//				before.pop();
//			} 
//			// 커서 왼쪽에 문자 추가
//			else if(Navigate == 'P') {
//				before.push(String.valueOf(command.charAt(2)));
//			}
		} // end of for
		
		String result ="";
		while(!before.empty()) {
			after.push(before.pop());
		}
		while(!after.empty()) {
			sb.append(after.pop());
		}
		System.out.println(sb);
		
	} // end of main
}// end of class

/*
abcd
3
P x
L
P y
*/
