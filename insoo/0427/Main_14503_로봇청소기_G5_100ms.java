package Algorithm.BOJ._0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503_로봇청소기_G5_100ms {
    /**
     *  하라는대로 하면 풀릴듯함 N,M도 최대 크기가 50이라
     */


    // d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    private static int N;
    private static int M;

    static class Robot {
        int r;
        int c;
        int d;

        public Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public void process(boolean[][] mat) {
            boolean[][] cleared = new boolean[N][M];
            int cnt = 0;
            while (true) {
                if (!cleared[r][c]) cnt++;  // 처음 청소하면
                cleared[r][c] = true;   // 청소 완료
                
                // 4번회전 다했을때
                if (rotate(mat, cleared) == dx.length) {
                    checkRear(mat, cnt);
                }
            }
        }

        private void checkRear(boolean[][] mat, int cnt) {
            int dir = (d + 2) % 4;  // 뒷 방향
            // 뒷방향이 벽이면 멈춤
            if (!mat[r + dy[dir]][c + dx[dir]]) {
                System.out.println(cnt);
                System.exit(0);
            } else {    // 아니면 후진
                c = c + dx[dir];
                r = r + dy[dir];
            }
        }

        private int rotate(boolean[][] mat, boolean[][] cleared) {
            int i;  // 4번 회전시 확인하려고 for 스코프에서 빼냄
            for (i = 0; i < dx.length; i++) {
                if (--d < 0) d = 3; // 왼쪽으로 방향 꺽음
                int x = c + dx[d];
                int y = r + dy[d];

                // 벽이 아니고 청소를 안했으면 그방향으로 감
                if (mat[y][x] && !cleared[y][x]) {
                    c = x;
                    r = y;
                    break;
                }
            }
            return i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 세로 크기
        N = Integer.parseInt(st.nextToken());
        // 가로 크기
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        Robot robot = new Robot(r, c, d);

        boolean[][] mat = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken()) != 1;
            }
        }

        robot.process(mat);
    }
}
