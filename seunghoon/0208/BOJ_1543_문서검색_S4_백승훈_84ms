import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1543_문서검색_S4_백승훈_84ms {

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String dup = br.readLine();
		int result = 0;
		
		// 비교구문에서 등호 주의
		// 증감식 위치를 따로 분리
		for(int i=0; i<=str.length()-dup.length();) {
			if(str.charAt(i) == dup.charAt(0)) {
				if(str.substring(i, i+dup.length()).equals(dup)) {
					result++;
					i += dup.length();
					continue;
				}
			}
			i++;
		}
		sb.append(result);
		System.out.println(sb);
		
		
	} // end of main
} // end of class
