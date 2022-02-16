import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 분할정복을 이용하여 제곱을 구현해보자
 * 큰 범위의 정수를 사용하기 때문에 long을 이용해야 한다.
 *  
 *  Aⁿ % C
 *  
 *  (a * b) % c = (a % c * b % c) % c
 */
public class Main_백준_1629_곱셈_실버1_76ms {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		long A = Integer.parseInt(st.nextToken()); // A를
		long B = Integer.parseInt(st.nextToken()); // B로 제곱하기
		long C = Integer.parseInt(st.nextToken()); // C로 나눈 나머지 출력

		System.out.print(expmod(A, B, C));

	} // end of main

	public static long expmod(long A, long n, long c) {
		if (n == 0)
			return 1;

		if (n == 1)
			return A % c; // A¹ = A

		long B = expmod(A, n / 2, c);
		// A⁴ = A² * A² // A⁵ = A² * A² * A
		// 홀수일때는 기존 수인 A를 한번 더 곱한 값이어야 한다
		return (n % 2 == 0) ? (B * B) % c : ((B * B) % c * A) % c;

	} // end of exp()

} // end of class
