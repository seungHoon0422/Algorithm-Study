package ForIM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1244_스위치켜고끄기_S3_박지호_128ms {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine()); // 스위치 수
		int[] light = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			light[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine()); // 학생 수

		// 남자 1 여자 0
		for (int i = 0; i < M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st2.nextToken());
			int num = Integer.parseInt(st2.nextToken());

			if (sex == 1) { // 남학생일 경우
				int temp = num;
				while (temp <= N) {
					if (light[temp] == 1) {
						light[temp] = 0;
					} else {
						light[temp] = 1;
					}

					temp += num;
				}

			} else { // 여학생일 경우

				int cnt = 0;

				int min = Integer.min(num - 1, N - num);

				for (int j = 1; j <= min; j++) {

					int front = num - j; // 0번지에 가까운쪽
					int back = num + j; // N번지에 가까운쪽

					if (light[front] == light[back]) {
						cnt++;
					} else {
						break;
					}
				}

				for (int j = num - cnt; j <= num + cnt; j++) {
					if (light[j] == 1) {
						light[j] = 0;
					} else {
						light[j] = 1;
					}

				}

			}

		} // end of big for

		if (N <= 20) {
			for (int i = 1; i <= N; i++) {
				sb.append(light[i]).append(" ");
			}

		} else {

			int loop = N / 20;
			if (N > loop * 20) {
				loop++;
			}

			for (int i = 0; i <= loop; i++) {
				for (int j = 1; j <= 20; j++) {
					if (20 * i + j > N) {
						break;
					}
					sb.append(light[20 * i + j]).append(" ");
					if (j == 20) {
						sb.setLength(sb.length() - 1);

					}
				}
				sb.append("\n");
			}

		}

		System.out.println(sb);

	} // end of main
}

// 스위치수 N 학생수 M  

// 스위치 켜짐 1 스위치 꺼짐 0
// 1 이상 스위치수 이하 자연수 배분
// 남자 : 숫자 받으면 해당 숫자의 배수에 해당되는 스위치 모두 반전
// 여자 : 해당 숫자 기준으로 양쪽을 비교 좌우대칭이면서 가장 많은 스위치를 포함하는 구간까지 모두 반전
// ex) 받은 숫자 기준 양옆이 다르다 => 받은 숫자만 바꿈 / 양옆 한칸이 같다 => 해당칸까지 반전