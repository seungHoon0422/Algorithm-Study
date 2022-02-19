import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
  앞의 수들을 더한 상태로 계속 더해야하므로 오름차순이어야 그리디?하게 풀 수 있다.
*/
public class Main_11399_ATM_S3_배인수_132ms {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int sum = arr[0];
        for (int i = 1; i < N; i++) {
            arr[i] += arr[i-1];
            sum+=arr[i];
        }

        System.out.println(sum);

    } // end of main



} // end of class
