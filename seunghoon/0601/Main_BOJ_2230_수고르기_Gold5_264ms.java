import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2230_수고르기_Gold5_264ms {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static int N;
	private static int M;
	private static int[] arr;
	private static int answer;

	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		answer = Integer.MAX_VALUE;
		
		
		int left = 0;
		int right = 0;
		while(right < N) {
			if(left == right) {
				right++;
				continue;
			}
			int diff = arr[right] - arr[left];
			if(diff < M) right++;
			else {
				answer = Math.min(answer, diff);
				left++;
			}
		}
//		search(0,N-1);
		System.out.println(answer);
		
		
	} // end of main

//	private static void search(int left, int right) {
//
//		answer = Math.min(answer, arr[right]-arr[left]);
//		if(left+1 < right && arr[right] - arr[left+1] >= M) search(left+1, right);
//		if(left < right-1 && arr[right-1] - arr[left] >= M) search(left, right-1);
//	}
	
	
	
	
	
} // end of class








