import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_1629_곱셈_S1_배인수_120ms {
    /*
        A^n * A^n = A^2(n) -> 분할 정복
        해당문제에서 본 모듈러의 관점이 당연히 오버플로우를 방지하는 것이지만
        또 해당 자리수만큼만 보면 된다는 것이다. 우리가 초등학생때 곱셈을 각 자리수끼리 곱을 한다는 것을 생각하면
        mod가 분배법칙이 되는 것을 알 수 있다.
        예를 들어 12^58 = 4 (mod 67 한 결과)
        (12^58 mod 67) x (12^58 mod 67) = 12^2(58) mod 67 = 4x4 = 16 (mod 67결과)
     */
    static long C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long A = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(recursive(A, B));
    } // end of main

    static long recursive(long x, long n) {
        // base condition
        if (n == 1) return x % C;

        long val = recursive(x, n / 2);
        val = val * val % C;
        // 2n : 2n + 1 지수 승 구함
        return n % 2 == 0 ? val : val * x % C;
    }

} // end of class
