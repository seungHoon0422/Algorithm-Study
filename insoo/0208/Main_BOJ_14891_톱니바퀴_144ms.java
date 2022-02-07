import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_BOJ_14891_톱니바퀴_144ms {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Queue<Pair> queue = new LinkedList<>();
        // 톱니바퀴 회전시 맞닿는 톱니의 극이 다르면 반대방향으로 회전
        // 극이 같으면 회전하지 않음
        // N극이 0, S극이 1
        int[][] gears = new int[5][8];

        // 입력
        for (int i = 1; i <= 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = str.charAt(j) - '0';
            } // end of for
        } // end of for 입력

        int k = Integer.parseInt(br.readLine());

        // right, left 방향
        int[] dy = {1, -1};

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            boolean[] vis = new boolean[5];
            int no = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            queue.offer(new Pair(no, dir));
            vis[no] = true;
            while (!queue.isEmpty()) {
                Pair cur = queue.poll();
                for (int j = 0; j < dy.length; j++) {
                    int nxt = cur.no + dy[j];

                    // 범위 나가면 넘김
                    if (nxt < 1 || nxt >= 5) continue;
                    // 이미 바퀴 돌았으면 넘김
                    if (vis[nxt]) continue;
                    // 오른쪽 방향이면
                    if (dy[j] == 1) {
                        // 우측 끝 톱니바퀴가 아니면
                        if (gears[cur.no][2] == gears[nxt][6]) continue;
                    } else { // 왼쪽 방향이면
                        if (gears[cur.no][6] == gears[nxt][2]) continue;
                    } // end of if-else

                    queue.offer(new Pair(nxt, ~cur.dir + 1));
                    vis[nxt] = true;
                } // end of for

                // 현재 기어 회전
                // 반시계 방향
                int temp;
                if (cur.dir == -1) {
                    temp = gears[cur.no][0];
                    for (int j = 0; j < 7; j++) {
                        gears[cur.no][j]=gears[cur.no][j+1];
                    }
                    gears[cur.no][7] = temp;
                } else { // 시계 방향
                    temp = gears[cur.no][7];
                    for (int j = 7; j >= 1; j--) {
                        gears[cur.no][j] = gears[cur.no][j - 1];
                    }
                    gears[cur.no][0] = temp;
                }

            } // end of while

        } // end of for

        int score = 1;
        int sum = 0;
        // 결과 계산
        for (int i = 1; i <= 4; i++) {

            if (gears[i][0] == 1) {
                sum += score;
            }
            score *= 2;
        }
        System.out.println(sum);
    } // end of main
} // end of class

class Pair {
    int no;
    int dir;

    public Pair(int no, int dir) {
        this.no = no;
        this.dir = dir;
    }
}
