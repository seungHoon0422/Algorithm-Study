import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main_백준_1629_곱셈_실버1_124ms {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/*
		 * 자연수 A를 B번 곱한 수를 C로 나눈 나머지를 출력해라
		 * 
		 * 방정식을 사용해보자
		 * A^B = CQ + R
		 * 10^11 = (10+2)Q + R
		 * 
		 * 나머지 정리란
		 * 어떤 정수에서 A와 양의 정수 B가 주어질 때 다음을 만족하는 정수Q와 R이 존재한다
		 * A = B*Q + R
		 * 이러한 수를 표현할 때 A mod B = R이라고 쓴다
		 * (A*B) mod C = ( A mod C * B mod C) mod C와 같다
		 * 
		 * A^B mod C = ((A mod C)^B) mod C 와 같다.
		 * 위 성질을 이용하여 분할 정복을 통해 2^90 mod 13과 같은 연산을 할 수 있다.
		 * 
		 * 
		 * 10^3%12 = (10mod12 * 10^2mod12)*mod12
		 * 		   = (10mod12)*((10mod12*10mod12)*mod12)*mod12
		 *       = ..... 
		 * 
		 * 위 방식을 쓰면 극한의 값이 들어왔을 때 스택 오버플러우가 발생한다 ( 2147483646 2147483646 2147483647 )
		 * 
		 * (A+B)modC = (AmodC + BmodC)modC 
		 * (A-B)modC = (AmodC - BmodC)modC
		 * + 
		 * 
		 */
		
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		
		System.out.println(mod(A, B, C));
		
	}
	
//	// 실패
//	private static long mod(long A, long B, long C) {
//		if(B==1) {
//			return A%C;
//		}
//		
//		return (A%C)*mod(A, B-1, C)%C;
//	}
	
	// 순석님꺼 보고 했읍니다,, 
	private static long mod(long A, long B, long C) {
		if(B==0) {
			return 1;
		}
		if(B==1) {
			return A%C;
		}
		
		// 이렇게 2배씩 줄이면서 연산해야 연산 횟수가 O(n)에서 O(logN)까지 줄어든다..
		long remainder = mod(A,B/2,C);
		
		return (B%2==0)?(remainder*remainder)%C:((remainder*remainder)%C*A)%C;
	}
}
