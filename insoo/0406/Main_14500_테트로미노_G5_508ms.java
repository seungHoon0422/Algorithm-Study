package Algorithm.BOJ._0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500_테트로미노_G5_508ms {

    private static int N;
    private static int M;

    /*
            500 X 500 = 250_000
            대충 N^2나와도 큰 문제 없을듯
            각 테트로미노 모형으로 탐색하고 진행 (정사각형은 한번만) 그리고 배열 돌리기
         */

    // {x, y} 형태
    static int[][][] tetromino = {
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
            {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
            {{0, 0}, {1, 0}, {1, 1}, {2, 0}},
            {{0, 0}, {1, 0}, {0, 1}, {1, 1}}
    };
    static int[][] tetrominoRange = {
            {3, 0},
            {1, 2},
            {1, 2},
            {2, 1},
            {1, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] mat = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = findSum(mat, tetromino.length - 1);

        for (int rot = 0; rot < 8; rot++) {
            // 테트로미노 크기만큼 탐색 (마지막꺼는 한번만 돌아도 되어서 빼고함)
            for (int tetrominoNum = 0; tetrominoNum < tetromino.length - 1; tetrominoNum++) {
                max = Math.max(max, findSum(mat, tetrominoNum));
            }
            mat = rotateMat(mat.length, mat[0].length, mat);
            // y축 반전
            if (rot == 3) mat = reverseMat(mat.length, mat[0].length, mat);
        }
//        for (int i = 0; i < mat.length; i++) {
//            for (int j = 0; j < mat[0].length; j++) {
//                System.out.print(mat[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(max);
    }

    private static int findSum(int[][] mat, int tetrominoNum) {
        int maxSum = 0;

        for (int i = 0; i < mat.length - tetrominoRange[tetrominoNum][1]; i++) {
            for (int j = 0; j < mat[0].length - tetrominoRange[tetrominoNum][0]; j++) {
                int sum = 0;
                for (int[] dir : tetromino[tetrominoNum]) {
                    sum += mat[i + dir[1]][j + dir[0]];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }


    static int[][] rotateMat(int N, int M, int[][] mat) {
        int[][] temp = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = mat[N - j - 1][i];
            }

        }

        return temp;
    }

    static int[][] reverseMat(int N, int M, int[][] mat) {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = mat[i][M - j - 1];
            }
        }

        return temp;
    }
}
