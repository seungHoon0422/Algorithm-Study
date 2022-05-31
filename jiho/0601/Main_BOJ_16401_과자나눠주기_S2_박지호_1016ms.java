import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_16401_과자나눠주기_S2_박지호_1016ms {

	private static int N, M, result;
	private static int[] snack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		result = 0;
		st = new StringTokenizer(br.readLine());
		snack = new int[N];
		for (int i = 0; i < N; i++) {
			snack[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(snack);

		calc(1, snack[N - 1]); // 1부터 가장 큰 과자의 길이를 이용한 이분탐색

		System.out.println(result);

	}

	public static void calc(int s, int e) {
		if (s > e) {
			return;
		}
		int mid = (s + e) / 2;
		int cnt = 0;

		for (int i = 0; i < snack.length; i++) {
			cnt += snack[i] / mid;
		}

		if (cnt >= M) { //
			if (result < mid) {
				result = mid;
				calc(mid + 1, e);
			}
		} else {
			calc(s, mid - 1);
		}

	}
}

// 조카 수 : M
// 과자 수 : N
// 조카 1명에게 줄 수 있는 과자의 최대길이
// 부서진거 모아서 길이로 만드는게 아니라
// 하나로 된 과자의 최대 길이를 구한다

// 일단 내림차순 정렬
