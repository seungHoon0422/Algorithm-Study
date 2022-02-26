import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_2665_미로만들기_G4_배인수_84ms {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    private static int n;

    static class Axis implements Comparable<Axis> {
        int x;
        int y;
        int broken;

        public Axis(int x, int y, int broken) {
            this.x = x;
            this.y = y;
            this.broken = broken;
        }

        @Override
        public int compareTo(Axis o) {
            return this.broken - o.broken;
        }
    }
    
    /*
        가중치 없고 최소로 부시면서 가기 -> BFS
        BFS돌면서 벽 있으면 부시면서 부신개수 늘리고 도착하면 최솟값 출력
        -> 도착할때 최솟값 하려면 Priority Queue에서 부신 개수 적은 기준으로 먼저 탐색을 진행하다 보면 최솟값이 자연스레 나올 것으로 봄  
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 방의 수 n(1 ≤ n ≤ 50)
        n = Integer.parseInt(br.readLine());
        //  0은 검은 방, 1은 흰 방

        // 패딩 처리
        char[][] mat = new char[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            // 원본 문자열의 시작위치, 원본 문자열 복사할 길이, 복사할 배열, 복사할 배열 시작 위치
            br.readLine().getChars(0, n, mat[i], 1);   
        }

        System.out.println(bfs(mat));
    } // end of main

    static int bfs(char[][] mat) {
        PriorityQueue<Axis> pQ = new PriorityQueue<>();
        boolean[][] vis = new boolean[n + 2][n + 2];
        // 첫번째 구역 집어넣음 (무조건 흰 방)
        pQ.offer(new Axis(1, 1, 0));

        while (!pQ.isEmpty()) {
            Axis cur = pQ.poll();

            for (int i = 0; i < dx.length; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];

                // 방문했거나 널 문자열이면
                if (vis[y][x] || mat[y][x] == 0) continue;
                // 다음 위치가 도착지면 현재 부신 개수 리턴(마지막 위치는 검은 색 아니라서 +1 안해줌)
                if (x == n && y == n) {
                    return cur.broken;
                }
                if (mat[y][x] == '1') { // 그냥 흰벽
                    pQ.offer(new Axis(x, y, cur.broken));
                    vis[y][x] = true;
                } else {    // 검은 벽
                    pQ.offer(new Axis(x, y, cur.broken + 1));
                    vis[y][x] = true;
                }
            }
        }
        // 에러?
        return -1;
    }
} // end of class



