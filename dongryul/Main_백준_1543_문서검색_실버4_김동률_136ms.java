import java.io.BufferedReader;
import java.io.InputStreamReader;

/** Main_백준_1543_문서검색_실버4_김동률_136ms*/
public class Main_백준_1543_문서검색_실버4_김동률_136ms {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		int row = str2.length;
		int col = str1.length;
		// 최장 공통 부분문자열 문제로 해결해보자
		int[][] LCS = new int [row+1][col+1];
		int count = 0;
		for(int r=1; r<=row; r++) {
			for(int c=1; c<=col; c++) {
				// str1의 좌표와 str2의 좌표가 일치한다면
				if(str2[r-1]==str1[c-1]) {
					LCS[r][c] = LCS[r-1][c-1] + 1;
					// str2 문자열의 길이와 LCS값이 일치한다면 중복 확인
					if(LCS[r][c]==row) {
						// 해당 좌표 좌측 row의 크기만큼 탐색하며, 같은 row값이 발견되면 증가 안함
						// 발견되면 0으로 초기화
						boolean flag = false;
						for(int i=1; i<row; i++) {
							if(LCS[r][c-i]==row) {
								flag = true;
								LCS[r][c] = 0;
							}
						}
						// 중복되지 않는다면 증가
						if(flag==false) {
							count++;
						}
					}
				}
				// 일치하는 값이 없으면 0 
				else {
					LCS[r][c] = 0;
				}
			}
		}
		System.out.println(count);
	}
}
