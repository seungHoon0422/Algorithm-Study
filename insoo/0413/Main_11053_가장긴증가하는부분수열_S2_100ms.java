package Algorithm.BOJ._0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11053_가장긴증가하는부분수열_S2_100ms {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 수열의 크기
        int[] arr = new int[N];  // 수열의 원소들 저장
        int[] LIS = new int[N]; // 자신을 끝으로 하는 LIS 길이
//        int[] path = new int[N]; // 나의 앞에 위치한  수열의 index

//        Arrays.fill(path, -1); // 사용하지 않는 index로 초기화

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int max = 0; // 해당 수열의 LIS 최장 길이
//        int maxIndex = -1;
        for (int i = 0; i < N; i++) { // 모든 원소에 대해 자신을 끝으로 하는 LIS 길이 계산
            LIS[i] = 1; // 자신 혼자 LIS 구성할 때의 길이 1로 초기화
            for (int j = 0; j < i; j++) { // 현재 원소부터 i원소 직전까지 비교
                if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
//                    path[i] = j; // 나의 앞에 위치한 수열의 index저장
                }
            }
            if (max < LIS[i]) {
                max = LIS[i];
//                maxIndex = i;
            }
        }


        System.out.println(max);
    }
}
