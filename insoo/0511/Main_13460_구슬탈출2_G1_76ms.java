package Algorithm.BOJ._0511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_13460_구슬탈출2_G1_76ms {

    /*
    이 문자열은 '.', '#', 'O', 'R', 'B' 로 이루어져 있다. '.'은 빈 칸을 의미하고,
    '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, 'O'는 구멍의 위치를 의미한다.
    'R'은 빨간 구슬의 위치, 'B'는 파란 구슬의 위치이다.

    입력되는 모든 보드의 가장자리에는 모두 '#'이 있다.
    구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.

    빨간 구슬과 파란 구슬을 같은 방향으로 움직이는데 빨간 구슬 기준으로 움직이며 파란 구슬은 빨간 구슬의 움직임에 맞게 움직임
    (빨간 구슬 좌표랑 파란 구슬 좌표를 둘 다 저장해야할듯?)
    지나갔던 방향 혹은 직전에 움직인 방향과 그 반대편 방향은 안봐도 됨
    (이미 왔던 길이니 visited 처리? 아니면 해당 방향 저장하고 수직인 방향만 가게끔?)
    파란색 공과 같은 공간에 존재 X
    빨간공 들어가더라도 파란공 들어가는지 확인하고 끝내야 할 듯?
     */

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    private static int N;
    private static int M;

    static class RBAxis {
        int rX;
        int rY;
        int bX;
        int bY;

        public RBAxis(int rX, int rY, int bX, int bY) {
            this.rX = rX;
            this.rY = rY;
            this.bX = bX;
            this.bY = bY;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 보드의 세로 N, M (3 ≤ N, M ≤ 10)
        N = Integer.parseInt(st.nextToken());
        // 가로 크기
        M = Integer.parseInt(st.nextToken());

        char[][] mat = new char[N][M];

        RBAxis fPos = new RBAxis(0, 0, 0, 0);
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                mat[i][j] = temp.charAt(j);
                if (mat[i][j] == 'R') {
                    fPos.rX = j;
                    fPos.rY = i;
                } else if (mat[i][j] == 'B') {
                    fPos.bX = j;
                    fPos.bY = i;
                }
            }
        }

        System.out.println(bfs(mat, fPos));
    } // end of main

    static int bfs(char[][] mat, RBAxis fPos) {
        Queue<RBAxis> queue = new LinkedList<>();
        boolean[][][][] RBVisited = new boolean[N][M][N][M];
        queue.offer(fPos);
        // 빨간 공 파란 공 지나간 곳 저장하려했는데 그때의 빨간 공과 파란 공의 상태를 저장해야해서 4차원으로 해야할듯
        RBVisited[fPos.rY][fPos.rX][fPos.bY][fPos.bX] = true;
        int moveCnt = 1;
        while (!queue.isEmpty()) {
            // 같은 계층의 움직임 개수 확인 위해
            int size = queue.size();

            while (size-- > 0) {
                RBAxis cur = queue.poll();
                for (int i = 0; i < dx.length; i++) {
                    int nxtRX = cur.rX;
                    int nxtRY = cur.rY;
                    int nxtBX = cur.bX;
                    int nxtBY = cur.bY;
                    int rStep = 0;
                    int bStep = 0;
                    // 바로 옆에서 같이 가면 누가 더 많이 갔는지에 따라 뒤에 있는지 확인 가능
                    // 빨간 공 움직이기
                    while (mat[nxtRY + dy[i]][nxtRX + dx[i]] != '#' && mat[nxtRY][nxtRX] != 'O') {
                        nxtRX += dx[i];
                        nxtRY += dy[i];
                        rStep++;
                    }


                    // 파란 공 같은 방향으로 가보기
                    while (mat[nxtBY+ dy[i]][nxtBX+ dx[i]] != '#' && mat[nxtBY][nxtBX] != 'O') {
                        nxtBX += dx[i];
                        nxtBY += dy[i];
                        bStep++;
                    }


                    // 파란공 들어가면 안됨
                    if (mat[nxtBY][nxtBX] == 'O') continue;
                    // 빨간공 들가면 정답
                    if (mat[nxtRY][nxtRX] == 'O') return moveCnt;

                    // 두 개의 공이 같이 있을 수 없음
                    if (nxtRX == nxtBX && nxtRY == nxtBY) {
                        if (rStep > bStep) {    // 빨간색 공이 더 많이 갔으면 더 뒤에 있다는 뜻
                            nxtRX -= dx[i];
                            nxtRY -= dy[i];
                        } else {    // 같이 있을 수 없음 그래서 파란색 공이 더 뒤에 있다는 뜻
                            nxtBX -= dx[i];
                            nxtBY -= dy[i];
                        }
                    }

                    // 이미 이런 상태를 한번 겪었으면 다음패턴은 뻔해서 안함
                    if (RBVisited[nxtRY][nxtRX][nxtBY][nxtBX]) continue;

                    RBVisited[nxtRY][nxtRX][nxtBY][nxtBX] = true;
                    queue.offer(new RBAxis(nxtRX, nxtRY, nxtBX, nxtBY));
                }
            }
            if (++moveCnt > 10) return -1;
        }
        // 둘다 벽에 갖히거나 그런 상태이면 걍 못나감
        return -1;
    }

} // end of class
