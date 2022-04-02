import java.util.Scanner;

public class Main_백준_11726_2xn타일링_실버3_116ms {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		sc.close();
		// 1이 입력되면 2x1 타일 한개니까 출력 후 종료
		if(n==1) System.out.println(1);
		else {
			// 2 이상일 때 피보나치
			long[] D = new long[n+1];
			D[1] = 1;
			D[2] = 2;
			for(int i=3; i<=n; i++) {
				D[i] = (D[i-1] + D[i-2])%10007 ;
			}
			
			System.out.println(D[n]);
		}
	}
}
