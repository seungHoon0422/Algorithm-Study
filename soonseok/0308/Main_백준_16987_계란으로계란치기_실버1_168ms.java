import java.io.*;
import java.util.*;

public class Main_16987 {

	static int N;
	static int[] power;
	static int[] hp;

	static int maxcrack;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		power = new int[N];
		hp = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			hp[i] = Integer.parseInt(st.nextToken());
			power[i] = Integer.parseInt(st.nextToken());
		}

		maxcrack = 0;
		backtrack(0, 0);

		System.out.print(maxcrack);
	}

	/**
	 * cnt : 지금까지 들고 때린 계란의 개수
	 * crack : 깬 계란의 개수
	 */
	public static void backtrack(int cnt, int crack) {
		// 기저 조건1 : 모든 계란에서의 선택이 끝났다.
		// 기저 조건2 : 더 이상 깰수가 없는 경우
		if (cnt == N || N - crack <= 1) {
			maxcrack = Math.max(maxcrack, crack);
			return;
		}

		// 현재 손에 들고있는 계란이 깨진 경우
		if (hp[cnt] <= 0) {
			backtrack(cnt + 1, crack);
			return;
		}

		// 맞는 계란에 대한 조건은 이 안에서
		for (int i = 0; i < N; i++) {
			if (cnt == i) continue; // 나 자신을 칠 수는 없다

			if (hp[i] <= 0) continue; // 내려치려는 계란이 깨진 경우

			int cracked = 0;

			// 두 계란을 부딪힌다
			hp[i] -= power[cnt];
			hp[cnt] -= power[i];

			if (hp[i] <= 0) cracked++;
			if (hp[cnt] <= 0) cracked++;

			backtrack(cnt + 1, crack + cracked);

			// 다음 계란치기 선택에는 해당 결과 원상복구
			hp[i] += power[cnt];
			hp[cnt] += power[i];

		}

	}

}
