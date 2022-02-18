import java.util.Scanner;

public class Main_백준_1052_물병_실버1_732ms {
	
	private static int K;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 1 <= N <= 10_000_000
		K = sc.nextInt(); // 1 <= K <= 1_000
		
		/*
		 * 완전탐색으로,
		 * N을 이진법으로 표현했을 때 1의 개수가 K개 이하면 정답
		 * 1의 개수가 K개 이하가 될때까지 1을 더하자
		 * 
		 * 비트마스킹을 쓰고싶은데, 못쓰겠어요,,, 
     * N>>1 하면서 1 카운팅하고,,,,, 부터 생각이 안나네요
		 */
		
		int cnt=0;
		while(!CountOnes(N)) {
			// 이진수로 만들기
			N++;
			cnt++;
		}
		System.out.println(cnt);
		
	} // end of main

	private static boolean CountOnes(int n) {
		// TODO Auto-generated method stub
		String binary = Integer.toBinaryString(n);
		int cnt = 0;
		for(int i=0, range=binary.length(); i<range; i++) {
			if(binary.charAt(i)=='1') {
				cnt++;
			}
		}
		if(cnt<=K) return true;
		return false;
	}
} // end of Main
