package Algorithm.BOJ._0504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1744_수묶기_G4_80ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 얜 0이랑 곱해야 이득
        List<Integer> minus = new ArrayList<>();
        // 얜 1 더해야 이득
        List<Integer> plus = new ArrayList<>();
        int oneCnt = 0;
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());
            if (a > 1) {
                plus.add(a);
            } else if (a == 1) {
                oneCnt++;
            } else {
                minus.add(a);
            }
        }

        plus.sort(Collections.reverseOrder());
        Collections.sort(minus);


        int sum = oneCnt;   // 1은 따로 더하는게 이득

        int plusSize = plus.size();
        int minusSize = minus.size();

        boolean isEven = plusSize % 2 == 0;
        if (!isEven) {
            plus.add(1);    // 1이랑 곱하면 의미 X
        }

        isEven = minusSize % 2 == 0;
        if (!isEven) {
            minus.add(1);
        }

        for (int i = 0; i < plusSize; i += 2) {
            sum += plus.get(i) * plus.get(i + 1);
        }

        for (int i = 0; i < minusSize; i += 2) {
            sum += minus.get(i) * minus.get(i + 1);
        }

        System.out.println(sum);
    }


}
