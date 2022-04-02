/** Main_백준_9251_LCS_골드5_156ms*/
public class Main_백준_9251_LCS_골드5_156ms {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 두개의 문자열 비교, 문자열은 최대 1000자 
		String arr1 = br.readLine();
		String arr2 = br.readLine();
		
		int n = arr1.length();
		int m = arr2.length();
		
		int [][] LCS = new int[m+1][n+1];
		// 반복문을 돌면서 앞에서부터 일치하는 좌표를 발견한다면 해당 좌표부터 그 이후 값들을 해당 값의 대각선 뒤의 값 + 1로 초기화한다
		for(int r=1; r<=m; r++) {
			for(int c=1; c<=n; c++) {
				// arr2의 해당 좌표와 arr1의 좌표를 앞에서부터 탐색하는데, 일치하는 값이 발견되면
				if(arr2.charAt(r-1)==arr1.charAt(c-1)) {
					LCS[r][c] = LCS[r-1][c-1]+1;
				}
				// 일치하지 않는다면 위, 왼쪽 좌표 비교하여 더 큰 값으로 초기화
				else {
					LCS[r][c] = Math.max(LCS[r-1][c], LCS[r][c-1]);
				}
			}
		}
		// 맨 끝값이 무조건 최대니까, 출력
		System.out.println(LCS[m][n]);
	} // end of main
} // end of class
