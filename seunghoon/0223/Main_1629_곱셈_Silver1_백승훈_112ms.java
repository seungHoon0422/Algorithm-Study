import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_1629_곱셈_Silver1_백승훈_112ms {

	
	
	private static long A;
	private static long B;
	private static long C;

	/**
	 * 
	 *  아이디어 : A^B % C 연산
	 *  C로 나눈 나머지 연산을 하기 전에 A를 B번 곱해가는 과정에서도 C나머지 연산을 계속 실행
	 *  
	 */
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		A = sc.nextLong();
		B = sc.nextLong();
		C = sc.nextLong();
		A = A%C;
		System.out.println(pow(A, B));
	}

	 static long pow(long value, long exp) {

		if(exp == 1) {
			return A;
		}
		
		long val = pow(value, exp/2);

		if(exp%2 == 0) return val * val % C;
		else return ((val * val)%C) *A % C;
	}
	

}
