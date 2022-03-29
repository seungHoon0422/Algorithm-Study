import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_13913_숨바꼭질4_G4_1064ms {
    static int res = Integer.MAX_VALUE;
    private static int K;

    /*
     목적값보다 우선 덜가서 확인하고 그다음 목접값보다 큰 값으로 확인예정
     */

    static class Pair {
        int x;
        StringBuilder sb;

        public Pair(int x, StringBuilder sb) {
            this.x = x;
            this.sb = sb;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            System.out.println(N - K);
            for (int i = N; i >= K ; i--) {
                sb.append(i).append(' ');
            }
            System.out.println(sb);
            return;
        } else {
            bfs(N);
        }


    } // end of main

    private static void bfs(int n) {
        Queue<Pair> queue = new LinkedList<>();

        int[] vis = new int[100_001];
        queue.offer(new Pair(n, new StringBuilder().append(n)));
        vis[n] = 1;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            int[] dir = new int[]{cur.x - 1, cur.x + 1, cur.x * 2};

            for (int i = 0; i < 3; i++) {

                if (dir[i] > 100_000 || dir[i] < 0 || vis[dir[i]] > 0) continue;
                if (dir[i] == K) {
                    System.out.println(vis[cur.x]);
                    System.out.println(cur.sb.append(' ').append(dir[i]));
                    return;
                }
                queue.offer(new Pair(dir[i], new StringBuilder().append(cur.sb).append(' ').append(dir[i])));
                vis[dir[i]] = vis[cur.x] + 1;
            }
        }
    }

} // end of class
