import java.io.*;
import java.util.*;

public class Main_13335 {

	static int N;
	static int W;
	static int L;

	static int[] trucks;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 트럭 수
		W = Integer.parseInt(st.nextToken()); // 다리 길이
		L = Integer.parseInt(st.nextToken()); // 다리 하중 제한

		trucks = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			trucks[i] = Integer.parseInt(st.nextToken());
		}

		System.out.print(cross());

	}

	public static int cross() {
		// 배열의 값을 한칸씩 미는것은 O(N) 이 걸린다.
		// Queue로 밀면, O(1) 로 가능하다
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < W; i++) {
			queue.offer(0); // 다리 길이만큼 0을 채워준다
		}

		int ti = 0; // 트럭은 0부터 N-1 idx 까지 있다
		int time = 0; // 경과 시간
		int total_weight = 0; // 현재 다리 위의 하중

		// 트럭이 모두 다리위로 올라갈 때 까지 반복
		while (ti != N) {
			// 1. 맨 앞의 차가 무조건 빠져나간다(다리의 하중이 줄어든다)
			total_weight -= queue.poll();

			// 2. 새로운 트럭이 도로 위로 올라올 수 있는지 체크한다
			if (total_weight + trucks[ti] <= L) {
				// 올라올 수 있다면
				total_weight += trucks[ti]; // 다리에 가해지는 하중이 증가하고
				queue.offer(trucks[ti]); // 트럭이 다리 큐에 들어간다
				ti++; // 다음 트럭이 대기한다
			}
			else {
				// 올라올 수 없다면
				queue.offer(0); // 단위 시간 하나를 잡아먹는다
			}

			time++; // 시간은 트럭이 들어가지 않아도 항상 증가
		}

		// 마지막으로 올라간 트럭이 빠져나갈 때 까지 반복
		while (total_weight != 0) {
			total_weight -= queue.poll();
			time++;
		}

		return time;
	}

}
