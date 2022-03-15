import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_2146_다리만들기_G3_180ms {


    static int N, res = Integer.MAX_VALUE;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Queue<Pair> island = new LinkedList<>();
        N = Integer.parseInt(br.readLine());
        int[][] mat = new int[N][N];
        int[][] dis = new int[N][N];
        // 섬 정보 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
                // 섬의 위치 다 넣어 둠 -> 바다에 인접한 후보들 다 넣기
                if (mat[i][j] == 1) island.offer(new Pair(j, i));
            } // end of for
        } // end of for 섬 정보 입력

        identifyIsland(mat);

        calcShortestBridgeLength(island,mat,dis);

        System.out.println(res);
    } // end of main

    static void calcShortestBridgeLength(Queue<Pair> island, int[][] mat, int[][] dis) {

        /*
            섬의 인접한 바다부터 시작함(큐의 특성을 이용)
         */
        Queue<Pair> sea = new LinkedList<>();
        while (!island.isEmpty()) {
            Pair isCur = island.poll();
            sea.offer(isCur);

            while (!sea.isEmpty()) {
                Pair cur = sea.poll();

                // 사방탐색
                for (int i = 0; i < dx.length; i++) {
                    int x = cur.x + dx[i];
                    int y = cur.y + dy[i];

                    // 범위 나갔거나 현재 섬이랑 같으면 스킵 -> 인접한 섬의 바다부터 시작
                    if (x < 0 || x >= N || y < 0 || y >= N || mat[y][x] == mat[isCur.y][isCur.x]) continue;
                    if (dis[y][x] >= 1) continue;
                    // 다른 섬 만나면
                    if (mat[y][x] != 0) {   // bfs 특성상 거리적으로 탐색하기 때문에 먼저 찾은 섬이 가장 작은 섬이다.
                        res = Math.min(res, dis[cur.y][cur.x]);
                        sea.clear();
                        break;
                    } else {    // 바다면
                        dis[y][x] += dis[cur.y][cur.x] + 1;
                        sea.offer(new Pair(x, y));
                    }
                } // end of for 사방탐색
            } // end of while 바다 탐색

            for (int i = 0; i < N; i++) {
                Arrays.fill(dis[i], 0);
            }
        } // end of while bfs
    }

    static void identifyIsland(int[][] mat) {

        Queue<Pair> queue = new LinkedList<>();
        int cnt = 200;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (mat[i][j] == 1) { // 섬 찾으면
                    // bfs 돌릴 섬 좌표 저장
                    queue.offer(new Pair(j, i));

                    // 방문 확인 및 섬 식별값 부여 (N이 최대 100이라 200이상 값 부여)
                    mat[i][j] = ++cnt;
                    while (!queue.isEmpty()) {
                        Pair cur = queue.poll();

                        // 사방탐색
                        for (int k = 0; k < dx.length; k++) {
                            int x = cur.x + dx[k];
                            int y = cur.y + dy[k];

                            if (x < 0 || x >= N || y < 0 || y >= N) continue;
                            if (mat[y][x] == 0 || mat[y][x] == cnt) continue;

                            queue.offer(new Pair(x, y));
                            mat[y][x] = cnt;
                        } // end of for 사방탐색

                    } // end of while bfs
                } // 섬 찾으면
            } // end of for
        } // end of for
    } // end of identifyIsland

}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
