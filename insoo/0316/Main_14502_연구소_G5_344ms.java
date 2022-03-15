import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_14502_연구소_G5_344ms {

    /*
        바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다.

        새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.
        -> 빈공간 개수 세고 조합 돌려야 할듯?

        바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다.
        안전 영역 크기의 최댓값을 구하는 프로그램
    */

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    private static int[][] mat;
    private static int N;
    private static int M;
    private static int blankCnt;
    private static int virusCnt;

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Pair[] blanks = new Pair[61];  // 빈칸 최소 3개 이상 -> 최대 8X8 - 3
    static Pair[] viruses = new Pair[10]; // 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수
    static Pair[] walls = new Pair[3];

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mat = new int[N][M];

        blankCnt = 0;
        virusCnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
                if (mat[i][j] == 0) {   // 빈공간에 벽 세울거라 위치 저장
                    blanks[blankCnt++] = new Pair(j, i);
                } else if (mat[i][j] == 2) {
                    viruses[virusCnt++] = new Pair(j, i);
                }
            }
        }

        combination(0, 0);

        System.out.println(max);
    } // end of main

    static void combination(int pos, int start) {
        if (pos == 3) {
            // bfs 돌면서 빈칸 최대 영역 개수 비교
            max = Math.max(max, bfs());
            return;
        }

        for (int i = start; i < blankCnt; i++) {
            walls[pos] = blanks[i];
            combination(pos + 1, i + 1);
        }
    }

    static int bfs() {
        Queue<Pair> queue = new LinkedList<>();
        int[][] cMat = copyMat();   // 원본 상태 유지위해 얇은 복사
        for (int i = 0; i < 3; i++) {   // 벽 세우기
            cMat[walls[i].y][walls[i].x] = 1;
        }
        for (int i = 0; i < virusCnt; i++) {    // 바이러스 시작위치 다 넣어줌
            queue.offer(viruses[i]);
        }

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];

                if (x < 0 || x >= M || y < 0 || y >= N || cMat[y][x] > 0) continue;

                queue.offer(new Pair(x, y));
                cMat[y][x] = 1;
            }
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cMat[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    static int[][] copyMat() {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            System.arraycopy(mat[i], 0, temp[i], 0, M);
        }

        return temp;
    }
} // end of class
