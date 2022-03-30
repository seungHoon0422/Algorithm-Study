import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_1520_내리막길_G4_372ms {

    static int[][] mat;
    static int[][] visited;
    static int cnt = 0;
    private static int M;
    private static int N;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        mat = new int[M][N];
        visited = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(visited[M - 1][N - 1]);
    }

//    private static void dfs(int x, int y) {
//        if (M - 1 == y && N - 1 == x) {
//            cnt++;
//            return;
//        }
//
//        visited[y][x] = true;
//        for (int i = 0; i < dx.length; i++) {
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//
//            if (ny < 0 || ny >= M || nx < 0 || nx >= N) continue;
//            if (mat[ny][nx] >= mat[y][x]) continue;
//            if (visited[ny][nx]) {
//                cnt++;
//                continue;
//            }
//            dfs(nx, ny);
//        }
//    }

    private static void bfs() {
        PriorityQueue<Triple> pq = new PriorityQueue<>();
        pq.offer(new Triple(0, 0, mat[0][0]));
        visited[0][0] = 1;
        while (!pq.isEmpty()) {
            Triple cur = pq.poll();

            for (int i = 0; i < dx.length; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];

                if (y < 0 || y >= M || x < 0 || x >= N) continue;
                if (mat[y][x] >= mat[cur.y][cur.x]) continue;
                if (visited[y][x] == 0) {
                    pq.offer(new Triple(x, y, mat[y][x]));
                }

                visited[y][x] += visited[cur.y][cur.x];
            }
        }
    }

    static class Triple implements Comparable<Triple> {
        int x;
        int y;
        int height;

        public Triple(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        @Override
        public int compareTo(Triple o) {
            return o.height - this.height;
        }
    }
}
