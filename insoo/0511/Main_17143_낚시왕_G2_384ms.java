package Algorithm.BOJ._0511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17143_낚시왕_G2_384ms {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};
    private static int R;
    private static int C;

    static class Shark {
        int x;
        int y;
        int speed;
        int dir;
        int size;

        public Shark(int x, int y, int speed, int dir, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        void updatePosition() {
            int move = this.speed;
            // 위 아래
            if (dir < 2) {
                move %= ((R - 1) * 2);
                while (move > 0) {
                    if (y == 0) {
                        dir = 1;
                    }
                    if (y == R - 1) {
                        dir = 0;
                    }
                    y += dy[dir];
                    move--;
                }
            }
            // 좌우
            else {
                move %= ((C - 1) * 2);
                while (move > 0) {
                    if (x == 0) {
                        dir = 2;
                    }
                    if (x == C - 1) {
                        dir = 3;
                    }
                    x += dx[dir];
                    move--;
                }
            }
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");

        // 세로
        R = Integer.parseInt(st.nextToken());
        // 가로  (2 ≤ R, C ≤ 100)
        C = Integer.parseInt(st.nextToken());
        // 상어의 수(0 ≤ M ≤ R×C)
        int M = Integer.parseInt(st.nextToken());

        Shark[][] mat = new Shark[R][C];
        List<Shark> sharks = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            // 상어 r
            int r = Integer.parseInt(st.nextToken()) - 1;
            // 상어 c
            int c = Integer.parseInt(st.nextToken()) - 1;
            // 상어 속력
            int s = Integer.parseInt(st.nextToken());
            // 상어 이동 방향 (0~3)
            int d = Integer.parseInt(st.nextToken()) - 1;
            // 크기
            int z = Integer.parseInt(st.nextToken());

            Shark temp = new Shark(c, r, s, d, z);
            sharks.add(temp);
            mat[r][c] = temp;
        }

        // 처음에 1번 열의 한칸 왼쪽에 위치 -> 0번 | 가장 오른쪽 열 이동시 멈춤(이동을)
        int res = 0;
        // 낚시꾼 이동
        for (int i = 0; i < C; i++) {
            // 현재 낚시꾼 위치에서 가장 가까운 물고기 잡음
            for (int j = 0; j < R; j++) {
                if (mat[j][i] != null) {
                    res += mat[j][i].size;
                    mat[j][i].speed = -1;
                    mat[j][i] = null;
                    break;
                }
            }

            // 중복된 위치 상어 없애기 위해 초기화
            for (int j = 0; j < R; j++) {
                Arrays.fill(mat[j], null);
            }

            // 상어위치 + 상어 이동은 이동할 횟수 / (움직이는 방향의 R or C) -> 방향전환환

            for (Shark shark :
                    sharks) {

                if (shark.speed == -1) continue;

                if (shark.speed != 0) {
                    // 상어 이동
                    shark.updatePosition();
                }
                // 이동 후 상어 넣기
                // 현재 위치에 이미 있으면 중복된 상어
                if (mat[shark.y][shark.x] != null) {
                    // 현재 상어가 더 크면 위치한 상어 없애기
                    if (mat[shark.y][shark.x].size < shark.size) {
                        mat[shark.y][shark.x].speed = -1;
                        mat[shark.y][shark.x] = shark;
                    } else { // 아니면 현재 상어 없애기
                        shark.speed = -1;
                    }
                } else {    // 해당 위치에 없으면 들어가기
                    mat[shark.y][shark.x] = shark;
                }

            }

        }

        System.out.println(res);

    }
}


