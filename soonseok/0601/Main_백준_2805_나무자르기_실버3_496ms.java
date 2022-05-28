import java.io.*;
import java.util.*;

public class Main_2805 {

	static int N;
	static int M;
	static int[] arr;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 나무 수 N
		M = Integer.parseInt(st.nextToken()); // 필요 나무 길이 M
		
		arr = new int[N];
		int max = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if (max < arr[i]) max = arr[i]; // 최대 값 갱신
		}
		
		// 딱히 정렬은 필요없다. 최소 1 ~ 최대 나무 값 까지 범위에서 이분탐색
		
		int lo = 1;
		int hi = max;
		
		while(lo < hi) {
			
			int mid = (lo + hi) / 2;
			
			long sum = 0;
			for (int i = 0; i < N; i++) {
				if(arr[i] - mid > 0) sum += arr[i] - mid;
			}
			
			if(sum < M) { // 필요 나무 길이가 모자라면, 더 낮춰서 베어야 한다
				hi = mid;
			}
			else { // 필요 나무 길이가 넘치면, 더 높게 베어야 하고, 같은 경우에도 최대한 적게 베어야 하므로.
				lo = mid + 1;
			}
		}
		
		System.out.print(lo - 1);
		
	} // main()
}
