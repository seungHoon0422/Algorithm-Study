import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2343_기타레슨_Silver1_백승훈 {

	
	

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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		lectures = new int[N];
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		for(int i=N-1; i>=0; i--) {
			lectures[i] = Integer.parseInt(st.nextToken());
			sum += lectures[i];
		}
		
		int avg = sum/M;
		for(int i=0; i<N; ) {
			int temp = 0;
			while(i<N && temp < avg) {
				temp += lectures[i++];
			}
			
			System.out.println("i"+i);
			System.out.println(temp);
			if(temp > avg) avg = temp;
		}
		System.out.println(avg);
		
		
		
		
		
		
		
		
		
		
	} // end of main
} // end of class
