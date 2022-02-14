package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_9229_한빈이와SpotMart_D3_195ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int testCase = 1; testCase <= TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 과자 수
			int M = Integer.parseInt(st.nextToken()); // 무게 제한
			int[] snack = new int[N];

			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < snack.length; i++) {
				snack[i] = Integer.parseInt(st2.nextToken());
			}

			int maxM = -1;

			for (int i = 0; i < snack.length - 1; i++) {
				for (int j = i + 1; j < snack.length; j++) {
					int temp = snack[i] + snack[j];
					if (temp <= M && temp >= maxM) {
						maxM = temp;
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(maxM).append("\n");

		} // testCase 끝
		System.out.println(sb.toString());

	} // main 끝
}

// 무조건 과자는 2개를 들고가야 한다 => 이중포문을 고정으로 사용하며 배열을 활용함