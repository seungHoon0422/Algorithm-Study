import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14719_빗물_G5_박지호_80ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int answer = 0;

		int[] block = new int[W];
		int max = 0;
		int max_idx = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			int temp = Integer.parseInt(st.nextToken());
			block[i] = temp;

			if (temp > max) {
				max = temp;
				max_idx = i;
			}
		}

		int left = block[0];
		for (int i = 1; i <= max_idx; i++) { // 왼쪽부터 최대값까지

			if (left >= block[i]) {
				answer += left - block[i];
			} else {
				left = block[i];
			}

		}

		int right = block[W-1];
		for (int i = W-2; i >= max_idx; i--) { // 최대값부터 오른쪽까지

			if (right >= block[i]) {
				answer += right - block[i];
			} else {
				right = block[i];
			}

		}
		System.out.println(answer);
	} // end of main
}

// 가장 높은 블록을 기준으로 좌 우 로 분할정복 하면 어떨지
