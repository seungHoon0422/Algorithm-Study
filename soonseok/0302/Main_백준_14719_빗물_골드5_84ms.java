import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_14719_빗물_골드5_84ms {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int H = Integer.parseInt(st.nextToken()); // 높이 H (1 ≤ H, W ≤ 500)
		int W = Integer.parseInt(st.nextToken()); // 가로 길이 W

		int[] height = new int[W];
		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		} // 입력 종료 - - -

		// 1. 좌 우 끝을 가리키는 left, right 변수를 둔다
		// 2. 좌 우에서 각자 가운데로 진행하는데, 이 때 자기 바깥쪽 벽이 더 작은쪽부터 진행한다.(같으면 왼쪽)
		// 3. 바깥쪽 벽을 최대로 갱신하면서 이동한다. i번째 칸의 빗물은 최소 바깥쪽 칸 - h[i]
		// 4. 우<좌가 되는 순간 종료

		int left = 0;
		int right = W - 1;

		int maxLeft = 0;
		int maxRight = 0;

		int sumRain = 0; // 쌓인 빗물을 더하면서 진행

		while (left <= right) {

			// 왼쪽 벽이 더 낮거나 같은 경우
			if (maxLeft <= maxRight) {
				// 만약 현재 칸보다 양쪽 벽이 더 높다면 빗물이 갇힐 수 있는 칸이다.
				// 두 최대 벽 높이중 최소값 안에 들어오는 경우에만 더해주면 된다.
				int capturedRain = Math.min(maxLeft, maxRight) - height[left];
				sumRain = (capturedRain > 0 ? sumRain + capturedRain : sumRain);

				// 현재 칸이 더 크다면 최대 벽 높이를 갱신한다.
				maxLeft = Math.max(maxLeft, height[left]);
				left++;
			}
			// 오른쪽 벽이 더 낮은 경우
			else {
				int capturedRain = Math.min(maxLeft, maxRight) - height[right];
				sumRain = (capturedRain > 0 ? sumRain + capturedRain : sumRain);

				maxRight = Math.max(maxRight, height[right]);
				right--;
			}

		} // end of while

		System.out.println(sumRain);

	} // end of main

} // end of class
