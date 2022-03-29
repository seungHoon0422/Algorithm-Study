import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_11559_PuyoPuyo_G4_76ms {

    /*
        뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
        뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다.
        이때 1연쇄가 시작된다.

        "아래로 떨어지고 나서" 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데,
        터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.
        (터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.)
        -> 아래로 떨어지고 나서 다시 터져야 연쇄 늘어남

        전체 탐색하며 뿌요뿌요 색깔을 BFS로 개수 세며 4개이상 시 제거 (1번 이상 제거시 연쇄  +1)
        (12*6*11*6 + 12*6)x ... 시간 초과 안될 듯

        총 12개의 줄에 필드의 정보가 주어지며, 각 줄에는 6개의 문자가 있다.

        이때 .은 빈공간이고 .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.

        R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.
     */

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static char[][] mat;
    static boolean[][] vis = new boolean[12][6];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        mat = new char[12][];
        for (int i = 0; i < 12; i++) {
            mat[i] = br.readLine().toCharArray();
        }

        int chainCnt = 0;
        while (bfs()) {
            // 중력으로 아래로 떨어뜨리기
            gravity();

            for (int i = 0; i < 12; i++) {
                Arrays.fill(vis[i], false);
            }
            chainCnt++;
        }

        System.out.println(chainCnt);
    } // end of main

    private static void gravity() {
        char[] temp = new char[12];
        for (int i = 0; i < 6; i++) {
            int idx = 11;
            Arrays.fill(temp, '.');
            for (int j = 11; j >= 0; j--) {
                if (mat[j][i] != '.') {
                    temp[idx--] = mat[j][i];
                }
            }
            for (int j = 0; j < 12; j++) {
                mat[j][i] = temp[j];
            }
        }
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static boolean bfs() {
        Queue<Pair> queue = new LinkedList<>();
        Queue<Pair> willDelete = new LinkedList<>();
        boolean isPuyo = false; // 하나라도 터지면 체이닝 + 1 아니면 끝

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (mat[i][j] != '.' && !vis[i][j]) {   // 뿌요가 있거나 방문 안했으면
                    queue.offer(new Pair(j, i));
                    willDelete.offer(new Pair(j, i));
                    vis[i][j] = true;
                    int cnt = 1;
                    while (!queue.isEmpty()) {
                        Pair cur = queue.poll();

                        for (int k = 0; k < dx.length; k++) {
                            int x = cur.x + dx[k];
                            int y = cur.y + dy[k];

                            if (x < 0 || x >= 6 || y < 0 || y >= 12) continue;
                            // 빈공간이거나 방문했거나 원래 색이랑 다르면 스킵
                            if (mat[y][x] == '.' || vis[y][x] || mat[i][j] != mat[y][x]) continue;

                            queue.offer(new Pair(x, y));
                            willDelete.offer(new Pair(x, y));
                            vis[y][x] = true;
                            cnt++;  // 뿌요 개수 세기
                        }
                    }
                    if (cnt >= 4) {
                        while (!willDelete.isEmpty()) {
                            Pair del = willDelete.poll();
                            mat[del.y][del.x] = '.';
                        }
                        isPuyo = true;
                    } else {
                        willDelete.clear();
                    }
                }
            }
        }
        return isPuyo;
    }

} // end of class
