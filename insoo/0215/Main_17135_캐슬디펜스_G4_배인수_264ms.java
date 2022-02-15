import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_17135_캐슬디펜스_G4_배인수_264ms {

    static int N, M, D, max;
    static int[][] mat;
    // 3방 탐색 왼쪽부터
    static int[] dx = {-1, 0, 1};
    static int[] dy = {0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        mat = new int[N][M];

        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
            } // end of for
        } // end of for
        max = 0;
        // 궁수 조합
        archerCombination(0, 0, 0);
        System.out.println(max);
    } // end of main

    static void archerCombination(int start, int archerCnt, int flag) { // i = 인덱스, archerCnt = 궁수 인원, castle = 성
        // 기저 조건
        if (archerCnt == 3) { // 궁수 최대 3마리 5C3 = 10개

            // 원본배열에 하면 다음 테스트 못하니 배열 복사
            int[][] mat1 = copyMat();

//            System.out.println(Arrays.toString(castle));
            // 궁수 배치 끝
            // 적 제거하는 코드 유도 (동시 제거)
            Queue<Pair> kill = new LinkedList<>();
            int cnt = 0;
            int y = N;
            while (y-- > 0) {
                for (int j = 0; j < M; j++) {
                    // 궁수가 있으면
                    if ((flag & 1 << j) != 0) {
                        // 바로 앞에 적이 있으면 처리
                        if (mat1[N - 1][j] == 1) {
                            kill.offer(new Pair(j, N - 1));
                        } else {
                            // 아니면 탐색
                            findEnemy(j, kill, mat1);
                        } // end of if-else 바로 앞에 적이 있으면 처리 - 아니면 탐색
                    } // end of if 궁수가 있으면
                } // end of for 적 제거하는 코드 만들면 될 듯? (동시 제거)

                // 적 죽임(동시에)
                cnt = killEnemy(kill, cnt, mat1);
                // 그다음 적 아래로 1칸
                move(mat1);
            }

            if (cnt > max) max = cnt;
            return;
        }

        // 유도 조건
        for (int i = start; i < M; i++) {
            archerCombination(i + 1, archerCnt + 1, flag | 1 << i);
        }

    } // end of method archerCombination

    static void findEnemy(int j, Queue<Pair> kill, int[][] mat) {
        // 아니면 탐색
        boolean[][] vis = new boolean[N][M];
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(j, N - 1));
        vis[N - 1][j] = true;
        Pair archorPos = new Pair(j, N - 1);
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            for (int k = 0; k < dx.length; k++) {
                int x = cur.x + dx[k];
                int y = cur.y + dy[k];

                // 범위 나갔거나 방문한 적 없으면
                if (x < 0 || x >= M || y < 0 || y >= N) continue;
                if (Math.abs(archorPos.x - x) + Math.abs(archorPos.y - y) >= D || vis[y][x]) continue;

                // 적이 없으면 찾으러 감
                if (mat[y][x] != 1) {
                    queue.offer(new Pair(x, y));
                    vis[y][x] = true;
                } else { // 적을 발견하면 죽일 예정하고 멈춤
                    kill.offer(new Pair(x, y));
                    return;
                }
            } // end of for
        }
    } // end of method findEnemy

    static int killEnemy(Queue<Pair> kill, int cnt, int[][] mat) {
        while (!kill.isEmpty()) {
            Pair killIt = kill.poll();
            if (mat[killIt.y][killIt.x] != 0) {
                mat[killIt.y][killIt.x] = 0;
                cnt++;
            }
        } // end of while
        return cnt;
    } // end of method killEnemy

    static int[][] copyMat() {
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            System.arraycopy(mat[i], 0, map[i], 0, M);
        }

        return map;
    }

    static void move(int[][] mat) {
        //  1이면 0만들고 다음꺼 1넘김
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] = 0;

                    if (i + 1 < N)
                        mat[i + 1][j] = 1;
                }
            }
        }
    }

} // end of class

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
