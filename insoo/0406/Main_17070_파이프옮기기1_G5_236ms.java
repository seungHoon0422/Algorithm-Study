package Algorithm.BOJ._0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1_G5_236ms {

    /*
        파이프는 밀면서 회전시킬 수 있다
        그냥 다 해보면 대충 16,777,216((16*16)^방법의 수)번 시간초과 안날듯?
        내리막길 형식으로 DP 해보려다 실패...
     */

    static final int HORIZONTAL = 0;
    static final int VERTICAL = 1;
    static final int DIAGONAL = 2;

    // {x, y, dir}
    static int[][][] mv = {
            {{1, 0, HORIZONTAL}, {1, 1, DIAGONAL}}, // 가로
            {{0, 1, VERTICAL}, {1, 1, DIAGONAL}}, // 세로
            {{1, 0, HORIZONTAL}, {0, 1, VERTICAL}, {1, 1, DIAGONAL}} // 대각
    };
    private static int[][] mat;
    private static int[][] DP;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        mat = new int[N][N];
//        DP = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        for (int i = 0; i < N; i++) {
//            Arrays.fill(DP[i], -1);
//        }
        dfs(1, 0, HORIZONTAL);

        System.out.println(cnt);

    } // end of main

    static int cnt = 0;
    private static void dfs(int x, int y, int dir) {
        if (x == N - 1 && y == N - 1) {

            cnt++;
            return;
        }



        for (int i = 0; i < mv[dir].length; i++) {
            int nx = mv[dir][i][0] + x;
            int ny = mv[dir][i][1] + y;
            int nDIr = mv[dir][i][2];
            if (nx >= N || ny >= N) continue;   // 범위 나가면 안감

            // 각 방향일때 주벼에 벽 있는지 확인
            if (nDIr == HORIZONTAL) {
                if (mat[ny][nx] == 1) continue;
                if (nx == N - 1 && ny != N - 1) continue;
            } else if (nDIr == VERTICAL) {
                if (mat[ny][nx] == 1) continue;
                if (ny == N - 1 && nx != N - 1) continue;
            } else {
                if (mat[ny][nx] == 1 || mat[ny][nx - 1] == 1 || mat[ny - 1][nx] == 1) continue;
            }

            dfs(nx, ny, nDIr);


        }


    }
}
