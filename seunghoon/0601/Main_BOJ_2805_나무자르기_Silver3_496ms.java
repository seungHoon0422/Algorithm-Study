import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2805_나무자르기_Silver3_496ms {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static int N;
	private static int M;
	private static int[] tree;

	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new int[N];
		st = new StringTokenizer(br.readLine());
		int left = 0;
		int right = 0;
		for(int i=0; i<N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			if(tree[i] > right) right = tree[i];
		}
		
		int answer = 0;
		while(left <= right) {
			int mid = (left + right) >> 1;
			boolean possible = isPossible(mid);
			if(possible) {
				if(mid > answer) answer = mid;
				left = mid+1;
			} else right = mid-1;
		}
	
		
		System.out.println(right);
		
	} // end of main

	private static boolean isPossible(int mid) {
		
		long sum = 0;
		for(int i=0; i<N; i++) {
			if(tree[i]-mid > 0) sum += tree[i]-mid;
		}
		if(sum >= M) return true;
		else return false;
	}

	
	
	
	
	
} // end of class








