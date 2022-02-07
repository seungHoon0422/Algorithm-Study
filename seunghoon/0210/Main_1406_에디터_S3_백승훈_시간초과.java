import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1406_에디터_S3_백승훈_시간초과 {
	
	
	static StringBuilder sb = new StringBuilder();
	static String str, command;
	static int N, cursor;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		N = Integer.parseInt(br.readLine());
		cursor = str.length();
		
		for(int i=0; i<N; i++) {
			command = br.readLine();
			char Navigate = command.charAt(0);
			
			// 커서 왼쪽 이동
			if(Navigate == 'L') {
				if(cursor > 0) {
					cursor--;
				}	
			} 
			// 커서 오른쪽 한칸 이동
			else if(Navigate == 'D') {
				if(cursor <str.length()) {
					cursor++;
				}	
			} 
			// 커서 왼쪽 문자 삭제			
			else if(Navigate == 'B') {
				if(cursor==0) continue;
				if(cursor == str.length()) { // 커서가 맨 뒤일 경우 인덱스 감소
					str = str.substring(0,cursor-1);
					
				} else {
					str = str.substring(0,cursor-1)+str.substring(cursor);
					
				}
				cursor--;
			} 
			// 커서 왼쪽에 문자 추가
			else if(Navigate == 'P') {
				char c = command.charAt(2);
				if(cursor == str.length()) {
					str = str+c;
				} else {
					str = str.substring(0,cursor)+c+str.substring(cursor);						
				}
				cursor++;
			}
//			System.out.println(cursor+" "+str);
		} // end of for
		sb.append(str);
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
