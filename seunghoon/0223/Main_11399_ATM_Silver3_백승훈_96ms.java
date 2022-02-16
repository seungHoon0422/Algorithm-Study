
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11399_ATM_Silver3_백승훈_96ms {

	
	
	private static int N;
	private static int[] input;
	private static int[] sum;

	/**
	 * 
	 *  아이디어 : 인출하는데 걸리는 시간이 짧은놈들부터 
	 *  먼저 뽑게 해서 긴놈들을 뒤로 보내 기다리는데 축적되는 시간이 최소가되게 함
	 *  
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		input = new int[N];
		sum = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
			
		for(int i=0; i<N; i++) {
			for(int j=0; j<=i; j++) {
				sum[i] += input[j];
			}
		}
		int result = 0;
		for(int i=0; i<N; i++) {
			result += sum[i];
		}
		System.out.println(result);
	}
}
