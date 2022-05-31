import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_BOJ_1744_수묶기_G4_박지호_116ms {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] arr = new int[N];
		int zero = 0;
		int plus = 0;
		int minus = 0;
		int result = 0;
		Queue<Integer> total = new LinkedList<Integer>();

		for (int i = 0; i < N; i++) {
			int temp = sc.nextInt();

			if (temp == 0) { // 0 일 경우
				zero++;
			} else if (temp > 0) { // 양수일 경우
				plus++;
			} else { // 음수일 경우
				minus++;
			}
			arr[i] = temp;
		}

		Arrays.sort(arr);
		if (N == 1) {
			System.out.println(arr[0]);
			System.exit(0);
		}

		for (int i = N - 1; i >= minus + zero + 1; i -= 2) { // 뒤에서부터 양수만 계산
			if (i - 1 < 0 || arr[i - 1] < 0) {
				break;
			}
			if (arr[i] == 1 || arr[i - 1] == 1) {
				total.offer(arr[i] + arr[i - 1]); // 1은 그냥 더해주는게 이득 => 이부분이 문제!!
			} else {
				total.offer(arr[i] * arr[i - 1]);
			}
		}
		for (int i = 0; i < minus + zero + 1; i += 2) { // 앞에서부터 음수만 계산
			if (i + 1 >= N || arr[i + 1] > 0) {
				break;
			}
			total.offer(arr[i] * arr[i + 1]);
		}

		if (plus % 2 == 0 && minus % 2 == 0) { // 양짝 음짝

		} else if (plus % 2 == 1 && minus % 2 == 0) { // 양홀 음짝
			total.offer(arr[minus + zero]); // 가장 작은 양수를 더한다

		} else if (plus % 2 == 0 && minus % 2 == 1) { // 양짝 음홀
			if (zero > 0) {

			} else {
				total.offer(arr[minus - 1]); // 가장 작은 음수를 더한다
			}

		} else { // 양홀 음홀
			total.offer(arr[minus + zero]); // 가장 작은 양수를 더한다
			if (zero > 0) {

			} else {
				total.offer(arr[minus - 1]); // 가장 작은 음수를 더한다
			}
		}

		while (total.size() != 0) {
			result += total.poll();
		}
		System.out.println(result);
	}
}

// 입력받을 때 양수 음수 0 개수를 카운트
// 오름차순 정렬
// 양홀 음홀 => 0이 있다면 가장 작은 음수와 0을 곱해서 더한다 + 가장 작은 홀수를 그냥 더한다
// 양홀 음짝 => 가장 작은 홀수를 그냥 더한다
// 양짝 음홀 => 0이 있다면 가장 작은 음수와 0을 곱해서 더한다 / 없다면 그냥 제일 작은 음수 더한다
// 양짝 음짝 => 각자 계산해서 더하면됨