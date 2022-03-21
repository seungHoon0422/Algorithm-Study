import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.StringTokenizer;


public class Main_20055_컨베이어벨트위의로봇_G5_208ms {
    private static int N;
    private static int K;
    static int zeroCnt = 0;
    static Pair[] temp;
    /*
        1번 칸이 있는 위치를 "올리는 위치",
        N번 칸이 있는 위치를 "내리는 위치"

        로봇은 "올리는 위치"에만 올릴 수 있다. 언제든지 로봇이 "내리는 위치"에 도달하면 그 즉시 내린다.
        로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면 그 칸의 내구도는 즉시 1만큼 감소
     */

    static class Pair {
        int durability;
        boolean isRobot;

        public Pair(int durability) {
            this.durability = durability;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Deque<Pair> deque = new ArrayDeque<>();
        // 2 ≤ N ≤ 100
        N = Integer.parseInt(st.nextToken());
        // 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료 (1 ≤ K ≤ 2N)
        K = Integer.parseInt(st.nextToken());
        temp = new Pair[2 * N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 2 * N; i++) {
            deque.add(new Pair(Integer.parseInt(st.nextToken())));
        }

        int level = 1;
        while (true) {
            // 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            deque.offerFirst(deque.pollLast());

            // 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다
            moveRobot(deque);

            if (!deque.peekFirst().isRobot && deque.peekFirst().durability > 0) {
                deque.peekFirst().durability -= 1;
                if (deque.peekFirst().durability < 1) zeroCnt++;
                deque.peekFirst().isRobot = true;
            }


            if (zeroCnt >= K) {
                break;
            }
            level++;
        }
        System.out.println(level);
    } // end of main

    static void moveRobot(Deque<Pair> deque) {


        temp = deque.toArray(temp);
        temp[N - 1].isRobot = false;

        // 먼저 올라온 로봇부터 이동
        for (int i = N - 2; i >= 0; i--) {
            Pair cur = temp[i];
            if (cur.isRobot) {  // 현재 로봇이면
                Pair nxt = temp[i + 1]; // 다음 칸 확인
                if (!nxt.isRobot && nxt.durability > 0) {
                    cur.isRobot = false;
                    nxt.isRobot = true;
                    nxt.durability -= 1;
                    if (nxt.durability < 1) zeroCnt++;
                    if (i + 1 == N - 1) nxt.isRobot = false;    // N번째 칸이면 로봇 내려야함
                }
            }
        }
    }

} // end of class
