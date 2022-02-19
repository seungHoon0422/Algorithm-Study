package ForIM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2798_블랙잭_B2_박지호_132ms {
	private static int N, M, max;
	private static int[] deck;
	private static int[] pick;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		max = Integer.MIN_VALUE;
		deck = new int[N];
		pick = new int[3];

		StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			deck[i] = Integer.parseInt(st2.nextToken());
		}

		combi(0, 0);
		System.out.println(max);

	}

	public static void combi(int cnt, int start) {
		if (cnt == 3) { // 기저조건
			int temp = 0;
			for (int i = 0; i < pick.length; i++) {
				temp += pick[i];
			}

			if (temp <= M && temp >= max) {
				max = temp;
			}
			return;
		}

		for (int i = start; i < N; i++) {

			pick[cnt] = deck[i];
			combi(cnt + 1, i + 1);

		}

	}

}

// N 장의 카드 중 3장을 고른다 => 세장의 합이 M을 넘지 않으면서 최대한 가깝게
// 조합문제