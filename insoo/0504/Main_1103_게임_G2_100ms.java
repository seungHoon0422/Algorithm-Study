package Algorithm.BOJ._0504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1103_게임_G2_100ms {
    private static char[][] mat;
    private static int[][] DP;
    private static int N;
    private static int M;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static final int CYCLE = Integer.MAX_VALUE;
    private static boolean[][] visited;
    private static int max = Integer.MIN_VALUE;
    /*
        왔던 곳 다시 가면 무한
        4^(N*M)만큼 갈 수 있을 확률 있어 시간 초과나서 DP를 써야할 것으로 보이는데...
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        // 세로
        N = Integer.parseInt(st.nextToken());
        // 가로
        M = Integer.parseInt(st.nextToken());

        mat = new char[N][];
        DP = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            mat[i] = br.readLine().toCharArray();
        }

        visited[0][0] = true;
        // cnt 1인 이유는 어짜피 한번은 무조건 가니까
        boolean isCycle = dfs(0, 0, 1);
        if (isCycle) {
            System.out.println(-1);
        } else {
            System.out.println(max);
        }
    }

    private static boolean dfs(int x, int y, int cnt) {
        if (max < cnt) max = cnt;   // 들어올때마다 최댓값일 수 있으니 갱신
        DP[y][x] = cnt;

        int move = (mat[y][x] - '0');

        for (int i = 0; i < 4; i++) {
            int nx = x + move * dx[i];
            int ny = y + move * dy[i];

            // 범위 나가면 끝
            if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
            // 구멍빠져도 끝
            if (mat[ny][nx] == 'H') continue;
            // 다음 갈 곳의 상태가 같거나 적으면 할 필요 없음
            if (cnt + 1 <= DP[ny][nx]) continue;
            // 싸이클 -> -1
            if (visited[ny][nx]) {
                return true;
            }

            visited[ny][nx] = true;
            if (dfs(nx, ny, cnt + 1)) return true;  // 싸이클시
            visited[ny][nx] = false;
        }

        return false;
    }
}
