import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2805_나무자르기_S3_박지호_792ms {

	private static int N, M, result;
	private static int[] trees;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 나무 수
		M = Integer.parseInt(st.nextToken()); // 집으로 가저가려 하는 나무 길이
		result = 0;
		st = new StringTokenizer(br.readLine());
		trees = new int[N];
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(trees);

		cut(1, trees[N - 1]);
		
		System.out.println(result);
	}

	public static void cut(int s, int e) {
		if (s > e) {
			return;
		}

		long sum = 0;
		int mid = (s + e) / 2;

		for (int i = 0; i < trees.length; i++) {
			if (trees[i] > mid) {
				sum += trees[i] - mid;
			}
		}

		if (sum == M) {
			result = mid;
			return;
		} else if (sum > M) { // 앞부분 이분탐색
			if(result < mid) {
				result = mid;
			}
			cut(mid + 1, e);
		} else { // 뒷부분 이분탐색
			cut(s, mid-1);
			
		}

	}
}

// 적어도 M미터를 가져간다
// 절단기 설정의 최대값 H 구하기