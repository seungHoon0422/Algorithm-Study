import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_14499_주사위굴리기_G4_92ms {

    /*
        가장 처음에 주사위에는 모든 면에 0이 적혀져 있다.

        지도의 각 칸에는 정수가 하나씩 쓰여져 있다. 주사위를 굴렸을 때,
        이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
        0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며,
        칸에 쓰여 있는 수는 0이 된다.

        동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
     */

    static int[] dice = new int[6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 지도의 세로 크기 N, 가로 크기 M (1 ≤ N, M ≤ 20)
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // 주사위를 놓은 곳의 좌표 x, y(0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1)
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        // 명령의 개수 K (1 ≤ K ≤ 1,000)
        int k = Integer.parseInt(st.nextToken());

        int[][] mat = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0 바닥, 2 위
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            int temp = dice[0];
            switch (Integer.parseInt(st.nextToken())) {
                case 1: // 동쪽
                    if (x + 1 >= M) continue;
                    x += 1;
                    dice[0] = dice[3];
                    dice[3] = dice[2];
                    dice[2] = dice[1];
                    dice[1] = temp;
                    break;

                case 2: // 서쪽
                    if (x - 1 < 0) continue;
                    x -= 1;
                    dice[0] = dice[1];
                    dice[1] = dice[2];
                    dice[2] = dice[3];
                    dice[3] = temp;
                    break;

                case 3: // 북쪽
                    if (y - 1 < 0) continue;
                    y -= 1;
                    dice[0] = dice[5];
                    dice[5] = dice[2];
                    dice[2] = dice[4];
                    dice[4] = temp;
                    break;

                case 4: // 남쪽
                    if (y + 1 >= N) continue;
                    y += 1;
                    dice[0] = dice[4];
                    dice[4] = dice[2];
                    dice[2] = dice[5];
                    dice[5] = temp;
                    break;
            }

            if (mat[y][x] == 0) {
                mat[y][x] = dice[0];
            } else {
                dice[0] = mat[y][x];
                mat[y][x] = 0;
            }
            sb.append(dice[2]).append('\n');
        }
        System.out.println(sb);
    } // end of main
} // end of class
