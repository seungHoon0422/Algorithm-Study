import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_15683_감시_G4_220ms {


    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static cctvPos[] cctv = new cctvPos[8];
    private static int N;
    private static int M;
    static int[][][] dir = {
            {{0}},
            {{0}, {1}, {2}, {3}},
            {{0, 2}, {1, 3}},
            {{3, 0}, {0, 1}, {1, 2}, {2, 3}},
            {{3, 0, 1}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}},
            {{0, 1, 2, 3}},
    };
    private static int wallCnt;

    static class cctvPos {
        int x;
        int y;
        int type;

        public cctvPos(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 8)
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] mat = new int[N][M];
        int cctvCnt = 0;
        wallCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
                if (mat[i][j] > 0 && mat[i][j] < 6) {
                    cctv[cctvCnt++] = new cctvPos(j, i, mat[i][j]);
                } else if (mat[i][j] == 6) {
                    wallCnt++;
                }
            }
        }

        dfs(cctvCnt, 0, 0, mat);
        System.out.println(min);
    } // end of main

    static void dfs(int cctvCnt, int pos, int sum, int[][] mat) {
        if (cctvCnt == pos) {
            min = Math.min(min, N * M - sum - wallCnt);
            return;
        }
        int[][] cMat = copyMat(mat);

        // 각 CCTV 볼 수 있는 경우의 수 유도
        for (int i = 0; i < dir[cctv[pos].type].length; i++) {
            int sightCnt = 0;   // 해당 방향으로 볼 수 있는 개수

            // CCTV가 볼 수 있는 경로 확인
            for (int j = 0; j < dir[cctv[pos].type][i].length; j++) {
                sightCnt += cctvSight(cMat, dir[cctv[pos].type][i][j], cctv[pos].x, cctv[pos].y);
            }
            dfs(cctvCnt, pos + 1, sum + sightCnt, cMat);
            // 다음꺼 볼 때는 이전 상태로 봐야함
            cMat = copyMat(mat);
        }
    }

    // 해당 방향으로 계속 확인
    static int cctvSight(int[][] mat, int dir, int x, int y) {
        int sightCnt = 0;
        while (true) {
            if (indexOutOfBounds(x, y) || mat[y][x] == 6) return sightCnt; // 벽이거나 끝이면 끝
            if (mat[y][x] == 7) {
                x += dx[dir];
                y += dy[dir];
                continue; // 이미 방문했으면}
            }
            sightCnt++;
            mat[y][x] = 7;
            x += dx[dir];
            y += dy[dir];
        }
    }

    static int[][] copyMat(int[][] mat) {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(mat[i], 0, temp[i], 0, M);
        }
        return temp;
    }

    static boolean indexOutOfBounds(int x, int y) {
        if (x < 0 || x >= M || y < 0 || y >= N) return true;
        return false;
    }
} // end of class
