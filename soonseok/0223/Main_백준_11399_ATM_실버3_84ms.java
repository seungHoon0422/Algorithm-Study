import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 오름차순 정렬 후 누적합을 구하는 문제.
 * 정렬에는 클래스의 Comparable 인터페이스를 이용한다.
 */
public class Main_백준_11399_ATM_실버3_84ms {

	static class Person implements Comparable<Person> {
		int idx;
		int delay;

		public Person(int idx, int delay) {
			super();
			this.idx = idx;
			this.delay = delay;
		}

		@Override
		public int compareTo(Person o) {
			// 기다리는 시간이 적은 순서대로 비교
			return this.delay - o.delay;
		}
	} // end of class Person

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 사람의 수 N(1 ≤ N ≤ 1,000)
		int[] P = new int[N + 1]; // i번째 사람이 인출하는데 걸리는 시간 Pi가 주어진다. (1 ≤ Pi ≤ 1,000)
		// 가장 덜 기다리는 순서대로 사람을 보내자.

		Person[] line = new Person[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			line[i] = new Person(i + 1, Integer.parseInt(st.nextToken()));
		} // 입력 종료 - - -

		// 오름차순 정렬이 최선
		Arrays.sort(line);

		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += line[i].delay * (N - i);
		}

		System.out.print(sum);

	} // end of main
} // end of class
