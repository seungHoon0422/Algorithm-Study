package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1543_문서검색_S4_박지호_140ms {
	public static void main(String[] args) throws Exception {
		// 우선은 1번으로
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		StringBuilder sb = new StringBuilder();
		int cnt = 0;

		for (int i = 0; i < A.length() - (B.length() - 1); i++) {
			for (int j = i; j < B.length() + i; j++) {
				sb.append(A.charAt(j));
			}
			
			if (B.equals(sb.toString())) {
				cnt++;
				i += B.length() - 1;
				sb.setLength(0);
			} else {
				sb.setLength(0);
			}

		}

		System.out.println(cnt);

	}  
}

//전체입력을 A에 저장
//비교입력을 B에 저장

//A를 String에 저장해서 charAt 으로 0번지부터 B와 비교
//for(int i = 0; i < A.length();i++) { 비교구문 }
//1번 : B를 통째로 비교
//2번 : B의 구성요소 하나씩 비교
