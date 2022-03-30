import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_13335_트럭_S1_96ms {

    /*
        트럭의 순서는 바꿀 수 없으며, 트럭의 무게는 같을 수 있는 트럭이 존재.

        다리에 실제처럼 한칸씩 움직이며 다리 시간을 잴거냐
        아님 각 시간을 주어주고 뺄거냐

        그것이 문제로다.
     */

    static class Pair {
        int weight;
        int passTime;

        public Pair(int weight, int passTime) {
            this.weight = weight;
            this.passTime = passTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ArrayList<Pair> bridge = new ArrayList<>();
        // 세 개의 정수 n (1 ≤ n ≤ 1,000) , w (1 ≤ w ≤ 100) and L (10 ≤ L ≤ 1,000)

        // 다리를 건너는 트럭의 수
        int n = Integer.parseInt(st.nextToken());
        // 다리의 길이
        int w = Integer.parseInt(st.nextToken());
        // 다리의 최대하중
        int L = Integer.parseInt(st.nextToken());

        int[] trucks = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        int truckCnt = 0;   // 다리 위 트럭의 개수
        int trucksWeight = 0;   // 다리 위 트럭의 총 무게
        int tIdx = 0;   // 트럭 인덱스
        int bIdx = 0;   // 다리 인덱스
        int time = 1;
        while (tIdx < n || !bridge.isEmpty()) {
            if (bIdx < w && tIdx < n && trucksWeight + trucks[tIdx] <= L) {
                trucksWeight += trucks[tIdx];
                bridge.add(new Pair(trucks[tIdx++], w));
                bIdx++;
            }
            for (int i = bIdx - 1; i >= 0; i--) {
                if (--bridge.get(i).passTime == 0) {
                    trucksWeight -= bridge.get(i).weight;
                    bridge.remove(i);
                    bIdx--;
                }
            }
            time++;
        }

        System.out.println(time);
    } // end of main
} // end of class
