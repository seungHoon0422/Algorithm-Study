import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_14719_빗물_G5_배인수_84ms {
    /*
        높이가 최대치를 잡고 갱신이 될때마다 넓이 계산
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 세로 길이 H 1 ≤ H, W ≤ 500
        int H = Integer.parseInt(st.nextToken());
        // 가로 길이 1 ≤ H, W ≤ 500
        int W = Integer.parseInt(st.nextToken());

        int max = 0;
        int temp = 0;
        int sum = 0;
        st = new StringTokenizer(br.readLine(), " ");
        int[] height = new int[W];
        for (int i = 0; i < W; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            if (max <= height[i]) { // 크거나 같은거 보면
                sum += temp;
                temp = 0;
                max = height[i];
            } else {    // 작은거 보면 임시로 누적합함
                temp += max - height[i];
            }
        }   // 해당 반복문이 자기보다 큰 값 또는 같은 값을 만났을 때만 가능
        
        max = 0;
        temp = 0;
        for (int i = W-1; i >=0 ; i--) {
            if (max < height[i]) { // 큰거 보면
                sum += temp;
                temp = 0;
                max = height[i];
            } else {    // 작은거 보면 임시로 누적합함
                temp += max - height[i];
            }
        }   // 작은 값 처리를 반대로 처리
        System.out.println(sum);
    } // end of main

} // end of class

/*
6 6
1 0 3 1 2 4 3

4 8
3 1 2 3 4 1 1 2
 */

