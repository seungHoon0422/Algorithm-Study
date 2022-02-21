import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2343_기타레슨_Silver1_백승훈_264ms {

	
	

	private static int N;
	private static int M;
	private static int[] lectures;

	/**
	 * 
	 *  아이디어 : 
	 *  	그리디 적으로 접근
	 *  	i부터  j까지의 강의는 모두 들어가야 하므로 강의의 순서가 중요
	 *  	따라서 정렬하면 문제 발생 가능!!
	 *  	블루레이의 개수가 주어졌을 때  블루레이의 길이가 최소
	 *  	블루레이는 모두 같은 개수
	 */
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 1 ≤ N ≤ 100,000
		M = Integer.parseInt(st.nextToken());	// 1 ≤ M ≤ N
	
		lectures = new int[N];
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		int max = 0;
		for(int i=0; i<N; i++) {
			lectures[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, lectures[i]);
			sum += lectures[i];
		}
		
		
		int left = max;
		int right = sum;
		int result = (left+right)/2;
		while(left<=right) {
			int mid = (left+right)/2;
			if(check(mid)) {
				result = mid;
				right = mid-1;
			} else {
				left = mid+1;
			}
			
		}
		
		System.out.println(result);
		
		
		
		
		
	} // end of main

	public static boolean check(int avg) {
		int count = 0;
		int sum = 0;
		for(int i=0; i<N; i++) {
			if(sum +lectures[i] <= avg) sum += lectures[i];
			else {
				count++;
				sum = lectures[i];
			}
		}
		if(count>=M) return false;
		else return true;
	}
} // end of class
