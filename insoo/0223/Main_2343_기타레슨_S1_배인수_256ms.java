import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_2343_기타레슨_S1_배인수_256ms {
    /*
        이전에 이분탐색 응용하는 것을 보고 완탐을 더 빠르게 하는 것을 봤는데 이 문제가 그 문제 같다.
        정답의 범위가 정해져 있는 문제는 완탐도 가능하고 그것을 더 빠르게 이분 탐색으로도 가능하다.
        처음의 정답의 범위를 잘못 설정한 것이 많이 틀리게 된 이유같다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        // 정답이 될 수 있는 최댓 값
        int endValue = 0;
        int max = 0;
        int min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            endValue += arr[i];
            if (arr[i] > max) max = arr[i];
        } // end of for

        int startValue = 1;
        while (startValue <= endValue) {

            int mid = (startValue + endValue) / 2;
//            System.out.println("mid = " + mid);


            /*
                max의 존재 이유가
                9 9
                1 2 3 4 5 6 7 8 9 일때
                시작 범위를 1로 설정해 1이 나오는 결과가 있어서 리스트의 최댓값인 9 이하로 답을 구하러 가면 안됨
                (동률님, 순석님 코드 보니 시작 값의 범위를 확실히 정해줘서 처리한 방식이 더 효율적인 것 같다.)
             */
            if (mid >= max && makeBluRay(mid, N, arr) <= M) { // 해당 값 보다 정답이 작거나 같으면
                // 답 후보중 하나
                if (min > mid) min = mid;
                endValue = mid - 1;

            } else { // 해당 값보다 정답이 크면
                startValue = mid + 1;
            }
        }

        System.out.println(min);
    } // end of main

    private static int makeBluRay(int target, int N, int[] arr) {
        int cnt = 1;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (sum + arr[i] > target) {
                cnt++;
                sum = arr[i];
            } else {
                sum += arr[i];
            }
        }
        return cnt;
    }


} // end of class
