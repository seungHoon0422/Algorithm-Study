package ForIM;

import java.util.Scanner;

public class Main_BOJ_2292_벌집_B2_박지호_204ms {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int cnt = 1;
		int temp = N - 1;
		while (true) {
			if ((temp - 6 * cnt) > 0) {
				temp -= 6 * cnt++;
			} else {
				break;
			}
		}
		
		if(N == 1) {
			System.out.println(1);
		} else {
			System.out.println(cnt + 1);
		}

	}
}
