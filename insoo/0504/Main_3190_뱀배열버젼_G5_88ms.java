package Algorithm.BOJ._0504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_3190_뱀배열버젼_G5_88ms {

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Move {
        int time;
        char dir;

        public Move(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] mat = new int[N][N];
        Queue<Move> moves = new LinkedList<>();
        Deque<Pair> snake = new ArrayDeque<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            mat[r - 1][c - 1] = 1;
        }

        int L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);

            moves.add(new Move(X, C));
        }

        mat[0][0] = -1; // 뱀
        int time = 0;
        int dir = 0;

        snake.add(new Pair(0, 0));

        while (true) {
            time++;

            int x = snake.peekFirst().x + dx[dir];
            int y = snake.peekFirst().y + dy[dir];
//            System.out.println(y + " : " + x);
//            System.out.println("time = " + time);
            if (y < 0 || N <= y || x < 0 || N <= x || mat[y][x] == -1) break;

            if (mat[y][x] != 1) {
                Pair tail = snake.pollLast();
                mat[tail.y][tail.x] = 0;
            }

            snake.offerFirst(new Pair(x, y));
            mat[y][x] = -1;

            // 게임 시작 시간으로부터 X초가 끝난 뒤
            if (moves.size() > 0 && time == moves.peek().time) {
                if (moves.poll().dir == 'L') {
                    dir--;
                    if (dir < 0) dir = 3;
                } else {
                    dir++;
                    if (dir > 3) dir = 0;
                }
            }
        }

        System.out.println(time);
    }
}
