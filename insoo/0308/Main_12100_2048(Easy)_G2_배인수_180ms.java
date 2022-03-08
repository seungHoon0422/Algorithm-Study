import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main_12100_2048(Easy)_G2_배인수_180ms {

    private static int N;
    static int max = Integer.MIN_VALUE;
    /*
            상하좌우 네 방향 중 하나로 이동시키는 것이다.
            이때, 같은 값을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐지게 된다.
            한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다.

            최대 5번 이동시켜서 얻을 수 있는 가장 큰 블록을 출력한다.
            -> 5번 이동하는데 조합을 돌려 가장 큰 블록 찾아보기

         */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 보드의 크기 N (1 ≤ N ≤ 20)
        N = Integer.parseInt(br.readLine());
        int[][] mat = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 조합 돌리기
        combination(0,mat);
        System.out.println(max);
    } // end of main

    static void combination(int pos, int[][] mat) {
        if (pos == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    max = Math.max(max, mat[i][j]);
                }
            }
            return;
        }

        // 상태 유지해야하기 위해 복사한 배열 이용
        int[][] cMat = copyMat(mat);

        for (int rot = 0; rot < 4; rot++) { // 4바퀴 돌리기
            // 한 방향으로 이동시키기
            move(cMat);
            combination(pos + 1, cMat);
            mat = rotate(mat);
            cMat = copyMat(mat);
        }

    }

    static void move(int[][] mat) {
        int[] temp = new int[N];    // 이동할 값 임시 저장
        boolean[] merge = new boolean[N];   // 이미 합쳐진 블록 다시 합칠 수 없음
        // 한 줄씩 다함
        for (int i = 0; i < N; i++) {
            int idx = 0;
            // 해당 줄의 왼쪽부터 탐색
            for (int j = 0; j < N; j++) {
                if (mat[i][j] == 0) continue;   // 0이면 할 거 없어서 스킵
                else if (temp[idx] == 0) {  // 해당 공간이 비어있으면 값 넣어줌
                    temp[idx] = mat[i][j];
                } else if (mat[i][j] == temp[idx] && !merge[idx]) {  // 같은 값이면 합치기
                    merge[idx] = true;
                    temp[idx++] *= 2;
                } else {    // 값이 채워져 있는데 같은 값이 아니거나 합쳐져 있는 상태면
                    // 다음 칸에 값 넣음
                    temp[++idx] = mat[i][j];
                }
            }
            System.arraycopy(temp, 0, mat[i], 0, N);
            Arrays.fill(temp, 0);
            Arrays.fill(merge, false);
        }
    }

    static int[][] rotate(int[][] mat) {
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = mat[N - 1 - j][i];
            }
        }

        return temp;
    }

    static int[][] copyMat(int[][] mat) {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(mat[i], 0, temp[i], 0, N);
        }
        return temp;
    }

} // end of class
