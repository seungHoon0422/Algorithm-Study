package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1629_곱셈_S1_박지호_128ms {
	public static long C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());

		System.out.println(divide(A, B));

	}

	public static long divide(long dA, long dB) {

		if (dB == 1) {
			return dA % C;
		}

		long temp = divide(dA, dB / 2);

		return (dB % 2 == 0) ? temp * temp % C : (temp * temp % C) * dA % C;
//		if (dB % 2 == 1) {
//			return (temp * temp % C) * dA % C;
//		}
//		return temp * temp % C;

	}

//	public static long exp(long x, long n) {
//		System.out.println("test1 : " + n);
//		callCnt++;
//		if (n == 1)
//			return x;
//
//		long y = exp(x, n / 2);
//
//		System.out.println("test2 : " + y + n);
//		return (n % 2 == 0) ? y * y : y * y * x; // ex) 2^5 = 2^2 * 2^2 *2
//
//	}

}

// 나머지 법칙 => (a*b)%c = (a%c * b%c)%c
