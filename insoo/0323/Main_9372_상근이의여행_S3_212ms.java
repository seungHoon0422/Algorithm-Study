import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_9372_상근이의여행_S3_212ms {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            // 국가의 수
            int N = Integer.parseInt(st.nextToken());
            // 비행기 종류
            int M = Integer.parseInt(st.nextToken());


            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                
            }
            // 항상 연결 리스트라 그냥 다 탐색 가능
            sb.append(N - 1).append('\n');
        }
        System.out.println(sb);
    } // end of main

}
