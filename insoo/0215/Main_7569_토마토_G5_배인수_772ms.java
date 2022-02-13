import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_7569_토마토_G5_배인수_772ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Queue<Triple> queue = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][][] tomatos = new int[h][n][m];
        int[][][] dis = new int[h][n][m];

        int cnt = 0;
        int notTomato = 0;
        int min = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    tomatos[i][j][k] = Integer.parseInt(st.nextToken());
                    dis[i][j][k] = -1;
                    if (tomatos[i][j][k] == 1) {
                        cnt++;
                        queue.offer(new Triple(k, j, i));
                        dis[i][j][k] = 0;
                    } else if (tomatos[i][j][k] == -1) {
                        notTomato++;
                    } // end of if-else
                } // end of for
            } // end of for
        } // end of for

        if (m * n * h - notTomato == cnt) {
            System.out.println(min);
            System.exit(0);
        }

        // 사방 + z 위 아래
        int[] dx = {1, 0, -1, 0, 0, 0};
        int[] dy = {0, 1, 0, -1, 0, 0};
        int[] dz = {0, 0, 0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Triple cur = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];
                int z = cur.z + dz[i];

                if (x < 0 || x >= m || y < 0 || y >= n || z < 0 || z >= h) continue;
                if (tomatos[z][y][x] != 0 || dis[z][y][x] >= 0) continue;

                queue.offer(new Triple(x, y, z));
                dis[z][y][x] = dis[cur.z][cur.y][cur.x] + 1;
                cnt++;
                if (min < dis[z][y][x]) min = dis[z][y][x];
            } // end of for
        } // end of while

        if (m * n * h - notTomato == cnt) {
            System.out.println(min);
        } else {
            System.out.println(-1);
        }

    } // end of main
}

class Triple {
    int x;
    int y;
    int z;

    public Triple(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
