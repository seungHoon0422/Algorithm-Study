import java.io.*;
import java.util.*;

public class Main_16401 {

	static int N;
	static int M;
	static int[] arr;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 조카의 수
		N = Integer.parseInt(st.nextToken()); // 과자 개수

		arr = new int[N];

		int max = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

			if (max < arr[i]) max = arr[i]; // 최대 막대 길이 저장
		}

		// 이분탐색 부분
		Arrays.sort(arr);

		int snack = 0; // 분배가 가능한 경우의 길이를 저장

		int lo = 1; // 최소 1의 길이는 나눠야 한다.
		int hi = max + 1; // 최대 길이는 막대 최대 길이 + 1

		while (lo < hi) {

			int mid = lo + (hi - lo) / 2;
			int cnt = 0;
			
			for (int i = 0; i < N; i++) {
				cnt += arr[i] / mid; // 각 막대를 나눌 수 있는 수
			}

			if (cnt >= M) { // 조카 만족이 가능한 경우
				if (snack < mid) snack = mid; // 더 긴 과자를 줄 수 있는 경우

				lo = mid + 1; // 다음 탐색 범위

			}
			else { // 분배가 불가능한 경우, 과자 길이를 줄인다

				hi = mid;
			}
		}

		System.out.print(snack);

	} // main()
}
