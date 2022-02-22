package practice;

import java.util.Scanner;

public class Main_BOJ_1052_물병_S1_박지호_976ms {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int answer = 0;

		while (true) {
			char[] binary = Integer.toBinaryString(N).toCharArray(); // N을 이진수로 만듬
			int cnt = 0;

			for (int i = 0; i < binary.length; i++)
				if (binary[i] == '1')
					cnt++;

			if (cnt <= K) {
				System.out.println(answer);
				break;
			}

			N++;
			answer++;
		}

	}

}
// 물의 재분배 과정 = 10진수를 2진수로 변환하는 작업
// K개의 물병을 만든다 => 물병의 총 개수를 이진수변환했을때 1의 개수
