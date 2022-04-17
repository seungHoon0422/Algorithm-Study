package Algorithm.BOJ._0420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16985_Maaaaaaaaaze_G3_772ms {

    /**
     * 그냥 각 지점 순열돌려서 넣고 그상태에서 배열 돌려서 한번씩 bfs 돌릴듯
     * 120(pos | perm) * 5*5*5*5*5(rot | dupPerm) * 5*5*5(findWay | bfs) = 46,875,000? (배열 복사 제외)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][][] input3D = new int[5][5][5];

        permuRes = new int[5];
        rotateRes = new int[5];
        buildRes = new int[5][5][5];
        visited = new boolean[5][5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < 5; k++) {
                    input3D[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        min = Integer.MAX_VALUE;
        permutation(0, 0, input3D);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static int[] permuRes;
    static int[][][] buildRes;
    static int[] rotateRes;
    static int min;
    static boolean[][][] visited;

    static int[] dx = {1, 0, -1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    // {{Entrance} , {Exit}} | {x,y} 반대로도 쓸 예정
    static int[][][] entranceExit = {
            {{0, 0}, {4, 4}},
            {{4, 0}, {0, 4}},
            {{0, 4}, {4, 0}},
            {{4, 4}, {0, 0}}
    };

    static class Pos {
        int x;
        int y;
        int z;
        int step;

        public Pos(int x, int y, int z, int step) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.step = step;
        }
    }

    private static void permutation(int pos, int flag, int[][][] input3D) {
        if (pos == 5) {
//            System.out.println(Arrays.toString(permuRes));
            // 매트릭스 쌓기
            putMatrix(input3D);
            // 배열 돌리는 곳에 보냄
            makeRotateNum(0);
            return;
        }

        for (int i = 0; i < 5; i++) {
            if ((flag & 1 << i) != 0) continue;
            permuRes[pos] = i;
            permutation(pos + 1, flag | 1 << i, input3D);
        }
    }

    private static void makeRotateNum(int pos) {
        if (pos == 5) {
//            System.out.println(Arrays.toString(rotateRes));
            // 배열 돌리기
            rotateEach();
            // 4개의 꼭짓점에서 bfs타보기?
            bfsFourEntryVertex();
            return;
        }

        for (int i = 0; i < 4; i++) {
            rotateRes[pos] = i;
            makeRotateNum(pos + 1);
        }
    }

    private static void bfsFourEntryVertex() {
        for (int[][] enEx :
                entranceExit) {
            int[] entrance = enEx[0];
            int[] exit = enEx[1];
            // 애초에 막혀있으면 시도안함
            if (buildRes[0][entrance[1]][entrance[0]] == 0 || buildRes[4][exit[1]][exit[0]] == 0) continue;
            int mvCnt = bfs(entrance, exit);
            if (mvCnt != -1) {
                if (min > mvCnt) min = mvCnt;
                if (min == 12) {
                    // 갈 수 있는 최소값
                    System.out.println(12);
                    System.exit(0);
                }

            }
            initVisited();
        }
    }

    private static void initVisited() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(visited[i][j], false);
            }
        }
    }

    private static int bfs(int[] entrance, int[] exit) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(entrance[0], entrance[1], 0, 0));
        visited[0][entrance[1]][entrance[0]] = true;
        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];
                int z = cur.z + dz[i];

                if (x < 0 || x >= 5 || y < 0 || y >= 5 || z < 0 || z >= 5) continue;
                if (buildRes[z][y][x] == 0 || visited[z][y][x]) continue;

                if (z == 4 && y == exit[1] && x == exit[0]) return cur.step + 1;

                queue.offer(new Pos(x, y, z, cur.step + 1));
                visited[z][y][x] = true;
            }
        }
        // 못감
        return -1;
    }

    private static void rotateEach() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < rotateRes[i]; j++) {
                rotate(i);
            }
        }
    }

    private static void rotate(int i) {
        int[][] temp = new int[5][5];
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                temp[k][l] = buildRes[i][5 - 1 - l][k];
            }
        }
        buildRes[i] = temp;
    }

    private static void putMatrix(int[][][] input3D) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.arraycopy(input3D[permuRes[i]][j], 0, buildRes[i][j], 0, 5);
            }
        }
    }
}
