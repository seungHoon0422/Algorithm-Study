import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_1600_말이되고픈원숭이_G4_배인수_600ms {

    private static int K;
    private static int W;
    private static int H;
    /*
            격자판의 맨 왼쪽 위에서 시작해서 맨 오른쪽 아래까지 가야한다.
            최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 방법

            원숭이의 동작수의 최솟값을 출력
            시작점에서 도착점까지 갈 수 없는 경우엔 -1
         */
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int[] hDx = {2, 1, 2, 1, -2, -1, -2, -1};
    static int[] hDy = {1, 2, -1, -2, 1, 2, -1, -2};

    static class Triple {
        int x;
        int y;
        int step;

        public Triple(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // K번 말 움직임으로 가능  (0이상 30이하)
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 가로길이 (W와 H는 1이상 200이하)
        W = Integer.parseInt(st.nextToken());
        // 세로길이
        H = Integer.parseInt(st.nextToken());

        int[][] mat = new int[H][W];

        // 2차원배열에 값 입력
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        } // end of for 2차원배열에 값 입력

        // BFS 탐색
        System.out.println(bfs(mat));
    } // end of main

    static int bfs(int[][] mat) {
        Queue<Triple> queue = new LinkedList<>();
//        boolean[][] vis = new boolean[H][W];
        // 내가보기엔 지나간곳 다시 못가면 문제 생길 확률 크게 존재
        int[][][] vis = new int[K + 1][H][W];
        // 시작점
        queue.offer(new Triple(0, 0, 0));
        vis[0][0][0] = 1;

        while (!queue.isEmpty()) {
            Triple cur = queue.poll();
            if (cur.x == W - 1 && cur.y == H - 1) return vis[cur.step][cur.y][cur.x] - 1;
            // 말 움직임
            if (cur.step < K) {
                for (int i = 0; i < hDx.length; i++) {
                    int x = cur.x + hDx[i];
                    int y = cur.y + hDy[i];

                    if (x < 0 || x >= W || y < 0 || y >= H || mat[y][x] == 1 || vis[cur.step + 1][y][x] > 0) continue;
//                    if (x == W - 1 && y == H - 1) return vis[cur.step][cur.y][cur.x];
                    queue.offer(new Triple(x, y, cur.step + 1));
                    vis[cur.step + 1][y][x] = vis[cur.step][cur.y][cur.x] + 1;
                }
            }
            // 원숭이 움직임
            for (int i = 0; i < dx.length; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];

                if (x < 0 || x >= W || y < 0 || y >= H || mat[y][x] == 1 || vis[cur.step][y][x] > 0) continue;

//                if (x == W - 1 && y == H - 1) return vis[cur.step][cur.y][cur.x];
                queue.offer(new Triple(x, y, cur.step));
                vis[cur.step][y][x] = vis[cur.step][cur.y][cur.x] + 1;
            }

        }
        return -1;
    }

} // end of class
