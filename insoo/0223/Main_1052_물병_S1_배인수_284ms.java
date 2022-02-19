import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_1052_물병_S1_배인수_284ms {

    /*
        1L 당 1병 8L -> 8병
        처음엔 홀수 일때 각 2^m 에서 m 값을 스택에 저장해 계산했는데 반례를 찾음
        11 3
        -> 8 2 1 로 답이 0이 나와야함
        -> N/2 를 반복해 N < K 가 되면  N++ == K를 하는데 11 3같은 홀수이면서 K개 안에서 처리 가능한 것을 보고 수정
     
        1개로 줄일 수 있는 병(N을 무조건 최소의 1개 병으로 만듦)과 
        홀수여서 당장 처리가 힘든 잉여 병(N을 나누면서 나오는 홀수라서 합칠 수 없어 남는 잉여병)의 개수의 합이 K개 이하 되면 멈추고 안되면 1개를 추가해 다시 해본다.  
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int buy = 0;
        while (true) {

            int curN = N + buy;
            // 물 담을 최소 병 1개
            int needBot = 1;

            // 현재 N에서 컵 합치면서 부족한 병 개수 셈
            while (curN != 1) {

                if (curN % 2 != 0) needBot++;
                curN = curN >> 1;

            }
            // 현재 1개로 줄일 수 있는 병과 홀수여서 당장 처리가 힘든 잉여 병의 개수의 합이 K개 이하면 더 구매할 필요 없음
            if (needBot <= K) break;
            // 한 병 더 구매해서 시도해봄
            buy++;
        }


        System.out.println(buy);
    } // end of main


} // end of class

/*
    11 3
    8 2 1

    64  7-1
    128 8-1
    256 9-1
    1024 11-1

 */
