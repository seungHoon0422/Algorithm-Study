import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_20304_비밀번호제작_플래티넘5_428ms {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 관리자 계정 비밀번호의 최댓값 N ( 0 ≤ N ≤ 1,000,000 )
		int M = Integer.parseInt(br.readLine()); // 해킹한 시도 M ( 1 ≤ M ≤ 100,000 )

		Queue<Integer> queue = new LinkedList<Integer>();
		int[] pwdist = new int[N + 1];
		Arrays.fill(pwdist, -1);

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int temp = Integer.parseInt(st.nextToken());
			// 존재하는 비밀번호는 안전거리가 0인 시작 정점이다.
			// 해당 비밀번호와 동일하다면 안전거리가 0이기 때문.
			pwdist[temp] = 0;
			queue.add(temp);
		} // 입력 종료 - - -

		int bitsize = Integer.toBinaryString(N).length(); // 가능한 최대 비트 자리 수
		int maxSafety = 0;
		while (!queue.isEmpty()) {

			int cur = queue.poll(); // 현재 체크할 정점(비밀번호)

			// 가능한 최대 비트 수만큼 밀어주면서 체크
			for (int i = 0; i < bitsize; i++) {

				int val = cur ^ (1 << i); // i번째 bit를 타고 1칸 이동한 정점 val.

				// 이동한 곳이 패스워드 범위 이내이고, 해당 배열의 자리에 값이 없다면
				if (val <= N && pwdist[val] == -1) {
					// 해당 값의 비트 거리 갱신
					pwdist[val] = pwdist[cur] + 1;
					queue.add(val);
					// 최대값 갱신
					maxSafety = Math.max(maxSafety, pwdist[val]);
				}

			}

		} // end of while

		System.out.print(maxSafety);

	} // end of main

} // end of class
