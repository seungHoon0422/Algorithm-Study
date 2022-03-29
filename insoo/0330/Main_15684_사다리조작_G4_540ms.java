import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_15684_사다리조작_G4_540ms {

    static int[][] mat;
    private static int M;
    private static int N;
    static final int GORIGHT = 1;
    static final int GODOWN = 0;
    static final int GOLEFT = -1;
    static int[] result = new int[3];
    private static int H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 세로선 개수
        N = Integer.parseInt(st.nextToken());
        // 가로선 개수
        M = Integer.parseInt(st.nextToken());

        H = Integer.parseInt(st.nextToken());

        // 패딩
        mat = new int[H][N];


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            // a번 가로선에 위치
            int a = Integer.parseInt(st.nextToken()) - 1;
            // b번의 세로선과 b+1세로선이
            int b = Integer.parseInt(st.nextToken()) - 1;
            mat[a][b] = GORIGHT;
            mat[a][b + 1] = GOLEFT;
        }


//        for (int i = 0; i < H; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(mat[i][j] + " ");
//            }
//            System.out.println();
//        }


        // 조합 돌리기 (1개일때 2개일때 3개일떄 -> 계단 3개 초과해서 놓을때 -1)
        // 작은것 부터해서 되면 바로 멈춰도 정답임
        // 0,0 ~ N , M
        if (exploreBride(0)) {
            System.out.println(0);
            return;
        }
        for (int i = 1; i <= 3; i++) {

            combination(0, 0, i, H * N);
        }

        System.out.println(-1);
    }

    private static void combination(int pos, int start, int end, int range) {
        if (pos == end) {
//            System.out.println(Arrays.toString(result));
            // 탐색
            if (exploreBride(end)) {
                System.out.println(end);
                System.exit(0);
            }
            return;
        }


        for (int i = start; i < range; i++) {
            // 현재 위치가 놓을 수 없거나 끝자락이거나 다음 위치에 놓을 수 없으면 넘김
            if (mat[i / N][i % N] != 0 || i % N == N - 1 || mat[i / N][(i + 1) % N] != 0) continue;
//            System.out.println(mat[i / M][i % N]);
            result[pos] = i;
            combination(pos + 1, i + 2, end, range);    // i+2 -> 뽑히고나서 다음 위치에 다리 이어서 그 다음칸 부터 확인
        }
    }

    private static boolean exploreBride(int end) {
        // 조합으로 뽑은 다리 만들기
        makeBridge(end);
        boolean isRight = true;
        // 탐색
        for (int i = 0; i < N; i++) {
            int startX = i;
            int startY = 0;
            int pre = GODOWN;    // 이전에 다리건너면 안건너려고 만든 변수
            while (true) {
                // 현재 다리에 도착하고 이전에 내려왔으면 다리 건넘
                if (mat[startY][startX] != 0 && pre == GODOWN) {
                    startX += mat[startY][startX];
                    pre = GORIGHT; // GORIGHT이던 GOLEFT던 상관없음 어짜피 내려가지만 않으면
                } else {    // 그게 아니면 아래로 내려감
                    startY += 1;
                    pre = GODOWN;
                }
                if (startY == H) break;
            }
            if (i != startX) {
                isRight = false;
                break;
            }
        }

        // 조합으로 만들었던 다리 부수기
        breakBridge(end);
        return isRight;
    }

    private static void makeBridge(int end) {
        for (int i = 0; i < end; i++) {
            mat[result[i] / N][result[i] % N] = GORIGHT;
            mat[result[i] / N][(result[i] + 1) % N] = GOLEFT;
        }
    }

    private static void breakBridge(int end) {
        for (int i = 0; i < end; i++) {
            mat[result[i] / N][result[i] % N] = 0;
            mat[result[i] / N][(result[i] + 1) % N] = 0;
        }
    }

}
