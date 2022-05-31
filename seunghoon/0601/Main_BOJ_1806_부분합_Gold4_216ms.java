import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1806_부분합_Gold4_216ms {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static int N;
	private static int S;
	private static int[] arr;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N+1];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = 0;
		// 처음시작을왜 0으로 잡아야되지...? arr[0]으로 잡았다가 시간 증발
		// sum의 범위를 for(i=left; i<right; i++) 로 구해 방식이구나...
		int sum = 0;
		int answer = Integer.MAX_VALUE;
		
		while(true) {
			
			if(sum >=S) {
				sum -= arr[left++];
				answer = Math.min(answer, right-left+1);
				
			}
			else if(right == N) break;
			else sum += arr[right++];
		}
		if(answer == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(answer);
		
		
	} // end of main
	
	
	
	
	
} // end of class









