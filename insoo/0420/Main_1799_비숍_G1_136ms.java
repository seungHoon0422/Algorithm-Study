package Algorithm.BOJ._0420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1799_비숍_G1_136ms {
    private static int N;
    static int max = Integer.MIN_VALUE;
    static int bMax = Integer.MIN_VALUE;
    static int wMax = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 체스판의 크기 (10 이하)
        N = Integer.parseInt(br.readLine());

        boolean[][] mat = new boolean[N][N];

//        boolean[] isUsedLDiagonal = new boolean[2 * N - 1];
//        boolean[] isUsedRDiagonal = new boolean[2 * N - 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

//        arrangeBlack(0, 0, mat, 0, 0, 0);
//        arrange(0, 0, mat, 0, 0, 0);
        arrangeBlack(0, 0, mat, 0, 0, 0);
        arrangeWhite(1, 0, mat, 0, 0, 0);

        System.out.println(wMax + bMax);
    }

    private static void arrangeBlack(int x, int y, boolean[][] mat, int flagLDiagonal, int flagRDiagonal, int cnt) {
        if (x >= N) {
            y += 1;
            x = (y % 2 == 0) ? 0 : 1;
        }
        if (y == N) {
            bMax = Math.max(bMax, cnt);
            return;
        }

        if (isPutable(mat, y, x, flagLDiagonal, flagRDiagonal)) {
            arrangeBlack(x + 2, y, mat, flagLDiagonal, flagRDiagonal, cnt);
        } else {
            arrangeBlack(x + 2, y, mat, flagLDiagonal | 1 << (x + y), flagRDiagonal | 1 << (N - 1 + y - x), cnt + 1);
            arrangeBlack(x + 2, y, mat, flagLDiagonal, flagRDiagonal, cnt);
        }

    }

    private static void arrangeWhite(int x, int y, boolean[][] mat, int flagLDiagonal, int flagRDiagonal, int cnt) {
        if (x >= N) {
            y += 1;
            x = y % 2 == 0 ? 1 : 0;
        }

        if (y == N) {
            wMax = Math.max(wMax, cnt);
            return;
        }


        if (isPutable(mat, y, x, flagLDiagonal, flagRDiagonal)) {
            arrangeWhite(x + 2, y, mat, flagLDiagonal, flagRDiagonal, cnt);
        } else {
            arrangeWhite(x + 2, y, mat, flagLDiagonal | 1 << (x + y), flagRDiagonal | 1 << (N - 1 + y - x), cnt + 1);
            arrangeWhite(x + 2, y, mat, flagLDiagonal, flagRDiagonal, cnt);
        }

    }

    private static boolean isPutable(boolean[][] mat, int y, int x, int flagLDiagonal, int flagRDiagonal) {
        return !mat[y][x] || (flagLDiagonal & 1 << (x + y)) != 0 || (flagRDiagonal & 1 << (N - 1 + y - x)) != 0;
    }

//    시간초과 코드
//    private static void arrange(int x, int y, boolean[][] mat, int flagLDiagonal, int flagRDiagonal, int cnt) {
//        if (y == N) {
////            System.out.println(cnt);
//            max = Math.max(max, cnt);
//            return;
//        }
//
//
//
//        // 대각선에 걸리면 넘어감
//        if (isPutable(mat, y, x, flagLDiagonal, flagRDiagonal)) {
//            // x가 마지막까지 가면 다음 줄(y+1) 아니면 다음 칸
//            unselected(x, y, mat, flagLDiagonal, flagRDiagonal, cnt);
//        } else {
//            selected(x, y, mat, flagLDiagonal, flagRDiagonal, cnt);
//            unselected(x, y, mat, flagLDiagonal, flagRDiagonal, cnt);
//        }
//    }
//
//    private static void unselected(int x, int y, boolean[][] mat, int flagLDiagonal, int flagRDiagonal, int cnt) {
//        if (x == N - 1) arrange(0, y + 1, mat, flagLDiagonal, flagRDiagonal, cnt);
//        else arrange(x + 1, y, mat, flagLDiagonal, flagRDiagonal, cnt);
//    }
//
//    private static void selected(int x, int y, boolean[][] mat, int flagLDiagonal, int flagRDiagonal, int cnt) {
//        unselected(x, y, mat, flagLDiagonal | 1 << (x + y), flagRDiagonal | 1 << (N - 1 + y - x), cnt + 1);
//    }
}
