import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이분탐색 문제에 익숙하지 않아서 어려웠던 문제.
 * 
 * 무엇을 탐색할 것인지 정하는 것이 중요함.
 * 
 * 이 문제의 경우 가능한 블루레이의 크기를 가능한지 체크하면서 탐색한다.
 * 
 * left (최대인 원소) -> mid(중간 크기) <- (모든 원소의 합) right
 * 
 * 이분탐색하면서 1개 원소를 가리킬때까지 줄어든다.
 * 
 * 앞에서부터 선택한 블루레이 크기대로 담았을때
 * 블루레이 개수가 모자란경우에는, 블루레이 크기를 더 크게
 * 블루레이 개수가 모자라지 않았다면, 블루레이 크기를 더 작게
 * 
 * left, right, mid에서 idx 계산을 확실하게
 * 
 */
public class Main_백준_2343_기타레슨_실버1_252ms {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 강의의 수 (1 ≤ N ≤ 100,000)
		int M = Integer.parseInt(st.nextToken()); // M개의 블루레이에 녹화 (1 ≤ M ≤ N)

		int[] bluray = new int[N];

		st = new StringTokenizer(br.readLine(), " ");

		int max = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			bluray[i] = Integer.parseInt(st.nextToken());

			sum += bluray[i]; // 모든 블루레이의 녹화시간 합
			max = Math.max(max, bluray[i]); // 블루레이 중 최댓값

		} // 입력 종료 - - -

		int left = max;
		int right = sum;
		int mid = (left + right) >> 1;

		while (left <= right) {

			mid = (left + right) >> 1;
		
			// 나눠본 결과가 블루레이 개수에 못미치거나 같은 경우(더 세밀하게 나눈다)
			if (blurayDivide(mid, bluray) <= M) {
				right = mid - 1;
			}
			// 나눴는데 허용 블루레이 개수보다 더 많은 경우(더 큰 덩어리로 나눈다)
			// 허용 개수보다 많다면 확실히 답이 아니기때문에 +1로 옮겨도 된다
			else {
				left = mid + 1;
			}
		}

		System.out.print(left);

	} // end of main

	public static int blurayDivide(int size, int[] bluray) {

		int N = bluray.length;

		int sum = 0; // 블루레이 부분합을 저장하기 위한 변수
		int cnt = 0; // 블루레이가 총 몇개로 나뉘었는지 저장
		for (int i = 0; i < N; i++) {
			// 블루레이 한장의 영상 총합이 가용 크기를 넘어섰다면
			if (sum + bluray[i] > size) {
				sum = 0; // 다른 블루레이를 가져온다
				cnt++; // 블루레이 개수를 늘린다
			}
			// 블루레이를 계속 넣어준다
			sum += bluray[i];

		}
		// 다 하고나서 마지막 sum에 영상이 있다면 한장 더
		if (sum > 0)
			cnt++;

		return cnt;

	} // end of blurayDivide

} // end of class
