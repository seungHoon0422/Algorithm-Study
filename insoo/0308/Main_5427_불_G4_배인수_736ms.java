import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_5427_불_G4_배인수_736ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Queue<Pair> queue1 = new LinkedList<>();
        Queue<Pair> queue2 = new LinkedList<>();

        int t = Integer.parseInt(br.readLine());

        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 너비
            int w = Integer.parseInt(st.nextToken());
            // 높이
            int h = Integer.parseInt(st.nextToken());

            char[][] mat = new char[h][w];
            int[][] fireDis = new int[h][w];
            int[][] sangDis = new int[h][w];

            // 거리 -1 초기화
            for (int j = 0; j < h; j++) {
                Arrays.fill(fireDis[j], -1);
                Arrays.fill(sangDis[j], -1);
            } // end of for

            // 입력
            for (int j = 0; j < h; j++) {
                String line = br.readLine();
                for (int k = 0; k < w; k++) {
                    mat[j][k] = line.charAt(k);
                    // 불 | 상근이면 시작 장소로 정해줌
                    switch (mat[j][k]) {
                        case '*':
                            queue1.offer(new Pair(k, j));
                            fireDis[j][k] = 0;
                            break;
                        case '@':
                            queue2.offer(new Pair(k, j));
                            sangDis[j][k] = 0;
                            break;
                    }
                } // end of for
            } // end of for 입력


            // 불 bfs
            while(!queue1.isEmpty()) {
                Pair cur = queue1.poll();

                for (int j = 0; j < dx.length; j++) {
                    int x = cur.x + dx[j];
                    int y = cur.y + dy[j];

                    if (x < 0 || x >= w || y < 0 || y >= h) continue;
                    if (mat[y][x] == '#' || fireDis[y][x] >= 0) continue;

                    queue1.offer(new Pair(x, y));
                    fireDis[y][x] = fireDis[cur.y][cur.x] + 1;
                }
            }

            boolean isEscape = false;

            while (!queue2.isEmpty()) {
                Pair cur = queue2.poll();

                for (int j = 0; j < dx.length; j++) {
                    int x = cur.x + dx[j];
                    int y = cur.y + dy[j];

                    // 탈출 성공
                    if (x < 0 || x >= w || y < 0 || y >= h) {
                        sb.append(sangDis[cur.y][cur.x] + 1).append('\n');
                        isEscape = true;
                        break;
                    }
                    if (mat[y][x] == '#' || sangDis[y][x] >= 0) continue;
                    if (fireDis[y][x] != -1 && sangDis[cur.y][cur.x] + 1 >= fireDis[y][x]) continue;

                    queue2.offer(new Pair(x, y));
                    sangDis[y][x] = sangDis[cur.y][cur.x] + 1;
                }
                if (isEscape) break;
            }

            if (!isEscape) sb.append("IMPOSSIBLE\n");
            queue1.clear();
            queue2.clear();
        } // end of for
        System.out.println(sb);
    } // end of main
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
